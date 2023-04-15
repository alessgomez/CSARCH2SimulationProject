package src;

import java.util.*;

public class Simulator {
    private final int blockSize;
    private final int setSize;
    private final int mainMemorySize; //in words
    private final int cacheMemorySize; //in words
    private final int numOfCacheSets;
    private final ArrayList<String> programFlow;
    private int numOfCacheHits;
    private int numOfCacheMiss;
    private final String[][] cacheData; //TODO: edit
    private final int[][] cacheAge;
    private final String inputType;
    private int numOfTagBits;
    private int numOfSetBits;
    private int numOfWordBits;
    private int totalNumOfBits;

    public Simulator(int blockSize, int setSize, int mainMemorySize, int cacheMemorySize, ArrayList<String> programFlow, String inputType) {
        this.blockSize = blockSize;
        this.setSize = setSize;
        this.mainMemorySize = mainMemorySize;
        this.cacheMemorySize = cacheMemorySize;
        this.programFlow = programFlow;
        this.numOfCacheHits = 0;
        this.numOfCacheMiss = 0;
        numOfCacheSets = cacheMemorySize / blockSize / setSize; 
        this.cacheData = new String[numOfCacheSets][setSize]; //TODO: might change to arraylist of objects
        this.cacheAge = new int[numOfCacheSets][setSize];
        this.inputType = inputType;
        partitionMainMemoryAddress();
        initializeCacheAge();
        System.out.println("set: " + numOfSetBits);
        System.out.println("word: "+ numOfWordBits);
    }

    private void initializeCacheAge() {
        for (int i = 0; i < cacheAge.length; i++)
        {
            for (int j = 0; j < cacheAge[i].length; j++)
                cacheAge[i][j] = -1;
        }
    }

    private void partitionMainMemoryAddress() {
        this.totalNumOfBits = log2(mainMemorySize);

        numOfWordBits = log2(blockSize);
        numOfSetBits = log2(numOfCacheSets);
        numOfTagBits = totalNumOfBits - (numOfWordBits + numOfSetBits);
    }

    public int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    public void simulate() {
        int setNum;
        int dataInd;
        int LRUInd;
        for (int i = 0; i < programFlow.size(); i++)
        {
            // Step 1: Get mapping 
            // Step 2: Check if data is in cache already
                // Step 2.1: If yes, increase the age ONLY; Cache hit +=1
                // Step 2.2: Else, find the index of SMALLEST age, change the data in that index and increment the CURRENT AGE; Cache miss += 1 
            setNum = getBlockSetAssociativeMapping(programFlow.get(i));

            dataInd = findCacheData(programFlow.get(i), setNum); //change implementation for word

            if (dataInd != -1)
            {
                cacheAge[setNum][dataInd] = getLatestCacheAge(setNum);
                numOfCacheHits++;
            }

            else 
            {
                LRUInd = getLRUBlockIndex(setNum);
                if (inputType.equals("Blocks"))
                    cacheData[setNum][LRUInd] = programFlow.get(i);
                else 
                    cacheData[setNum][LRUInd] = getBlockBaseAddress(programFlow.get(i));
                cacheAge[setNum][LRUInd] = getLatestCacheAge(setNum);
                numOfCacheMiss++;
            }
        }
    }

    private String getBlockBaseAddress(String word) {
        
        int wordInt = Integer.parseInt(word, 16);
        while (wordInt % blockSize != 0)
        {
            wordInt--;
        }
        return Integer.toHexString(wordInt);
    }

    private int getLatestCacheAge(int setNum) {
        int maxAge = cacheAge[setNum][0];

        for (int i = 1; i < cacheAge[setNum].length; i++)
        {
            if (cacheAge[setNum][i] > maxAge)
                maxAge = cacheAge[setNum][i];
        }

        return maxAge + 1;
    }

    private int getLRUBlockIndex(int setNum) {
        int minIndex = 0;
        for (int i = 1; i < cacheAge[setNum].length; i++) {
            if (cacheAge[setNum][i] < cacheAge[setNum][minIndex]) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    private int findCacheData(String data, int setNum) {

        if (inputType.equals("Addresses"))
        {
            data = getBlockBaseAddress(data);
        }

        for (int i = 0; i < cacheData[setNum].length; i++)
        {
            if (cacheData[setNum][i] == null)
                return -1;
            else if (cacheData[setNum][i].equals(data)) {
                return i;
            }
        }

        return -1;
    }

    public int getBlockSetAssociativeMapping(String input) {
        int setNum = -1;

        if (inputType.equals("Blocks")) //TODO: change in frontend
            setNum = Integer.parseInt(input) % numOfCacheSets;
        else if (inputType.equals("Addresses")) // TODO: Should we accept decimal addresses? (reason: problem set)
        {
            int decAddress = Integer.parseInt(input, 16);
            String binAddress = Integer.toBinaryString(decAddress);
            String setNumBinStr = "";

            while (binAddress.length() < totalNumOfBits) {
                binAddress = "0" + binAddress;
            }
            
            for (int i = 0; i < numOfSetBits; i++)
            {
                setNumBinStr += binAddress.charAt(i + numOfTagBits);
            }

            setNum = Integer.parseInt(setNumBinStr, 2);
        }
        
        return setNum;
    }

    String hexToBinary(String hex) {
        int i = Integer.parseInt(hex, 16);
        String bin = Integer.toBinaryString(i);
        //retrieve only the set bits 
        int j = Integer.parseInt(bin, 2);
        return bin;
    }

    public int getNumOfCacheHit() {
        return numOfCacheHits;
    }

    public int getNumOfCacheMiss() {
        return numOfCacheMiss;
    }

    public float getMissPenalty(String readType, int cacheAccessTime, int memoryAccessTime) {
        if (readType.equals("Non Load-Through"))
        {
            return cacheAccessTime + memoryAccessTime * blockSize + cacheAccessTime;
        }
        else if (readType.equals("Load-Through"))
        {
            return cacheAccessTime + memoryAccessTime;
        }
        return 0;
    }

    public float getAverageMemoryAccessTime(String readType, int cacheAccessTime, int memoryAccessTime) {
        float hitrate = (float) numOfCacheHits / (numOfCacheHits + numOfCacheMiss);
        return Math.round((hitrate * cacheAccessTime) + ((1 - hitrate) * getMissPenalty(readType, cacheAccessTime, memoryAccessTime)));

    }

    public float totalMemoryAccessTime(String readType, int cacheAccessTime, int memoryAccessTime) {
        float hits = numOfCacheHits * blockSize * cacheAccessTime;
        float miss = 0;
        if (readType.equals("Non Load-Through"))
            miss = numOfCacheMiss * (cacheAccessTime + (memoryAccessTime * blockSize) + (cacheAccessTime * blockSize));
        else if (readType.equals("Load-Through"))
            miss = numOfCacheMiss * (cacheAccessTime + memoryAccessTime);
        return hits + miss;
    }

    public String[][] getSnapshotOfCacheMemory() {
        return cacheData;
    }
}
