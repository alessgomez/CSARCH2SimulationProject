package src;

import java.util.*;

public class Simulator {
    private final int blockSize;
    private final int setSize;
    private final int mainMemorySize; //in words
    private final int cacheMemorySize; //in words
    private final int numOfCacheSets;
    private final ArrayList<String> programFlow;
    private final int numOfCacheHits;
    private final int numOfCacheMiss;
    private final int[][] cacheData; //TODO: edit
    private final int[][] cacheAge;
    private final String inputType;
    private int numOfTagBits;
    private int numOfSetBits;
    private int numOfWordBits;

    public Simulator(int blockSize, int setSize, int mainMemorySize, int cacheMemorySize, ArrayList<String> programFlow, String inputType) {
        this.blockSize = blockSize;
        this.setSize = setSize;
        this.mainMemorySize = mainMemorySize;
        this.cacheMemorySize = cacheMemorySize;
        this.programFlow = programFlow;
        this.numOfCacheHits = 0;
        this.numOfCacheMiss = 0;
        numOfCacheSets = cacheMemorySize / blockSize / setSize; 
        this.cacheData = new int[numOfCacheSets][setSize]; //TODO: might change to arraylist of objects
        this.cacheAge = new int[numOfCacheSets][setSize]; 
        this.inputType = inputType;
        partitionMainMemoryAddress();
    }

    private void partitionMainMemoryAddress() {
        int totalNumOfBits = log2(mainMemorySize);

        numOfWordBits = log2(blockSize);
        numOfSetBits = log2(numOfCacheSets);
        numOfTagBits = totalNumOfBits - (numOfWordBits + numOfSetBits);
    }

    public int log2(int x) {
        return (int) (Math.log(x) / Math.log(2));
    }

    public void simulate() {
        int setNum;
        for (int i = 0; i < programFlow.size(); i++)
        {
            // Step 1: Get mapping 
            setNum = getBlockSetAssociativeMapping(programFlow.get(i));
        }
    }

    public int getBlockSetAssociativeMapping(String input) {
        int setNum;

        if (inputType.equals("block")) //TODO: change in frontend
        {
            setNum = Integer.parseInt(input) % numOfCacheSets;
        }

        else // if inputType.equals("word") --- address (hex) 
        {
            int decAddress = Integer.parseInt(input);
            String binAddress = Integer.toBinaryString(decAddress);
            String setNumBinStr = "";
            
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

    public int getMissPenalty() { //TODO: add formula 
        return 0;
    }

    public float getAverageMemoryAccessTime() { //TODO: add formula 
        return 0;
    }

    public int totalMemoryAccessTime() { //TODO: add formula 
        return 0;
    }

    public int[][] getSnapshotOfCacheMemory() {
        return cacheData;
    }

    //TODO: With option to output result in text file function
}
