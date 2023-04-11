package src;

import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Controller implements ActionListener {
    private final View view;
    private Simulator simulator;
    private boolean allOutputsIntegers;
    private boolean allOutputsPositive;
    private float missPenalty;
    private float aveMemoryAccessTime;
    private float totalMemoryAccessTime;
    private String[][] outputTable;
    private ArrayList<String> result = new ArrayList<>();
    private ArrayList<String[]> programFlowContig = new ArrayList<>();

    public Controller (View view) {
        this.view = view;
        this.view.addClearListener(this);
        this.view.addSimulateListener(this);
        this.view.addSaveListener(this);
    }

    public void actionPerformed (ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Clear" -> {view.clearInput(); view.hideOutput(); view.resetTable();}
            case "Simulate" -> {view.resetTable(); simulate();}
            case "Save to Text File" -> saveToTextFile();
        }
    }

    private int validateInput (String inputValue, String fieldName) {
        int integerValue = 0;

        try {
            integerValue = Integer.parseInt(inputValue);

            if (integerValue <= 0)
                allOutputsPositive = false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: Invalid input in " + fieldName + ".",
                    "BSA-LRU Cache Simulator", JOptionPane.ERROR_MESSAGE);
            allOutputsIntegers = false;
        }

        return integerValue;
    }

    private void simulate () {
        allOutputsIntegers = true;
        allOutputsPositive = true;

        int cacheMemorySize = validateInput(view.getCacheSize(), "Cache Size");
        int mainMemorySize = validateInput(view.getMMSize(), "Main Memory Size");
        int setSize = validateInput(view.getSetSize(), "Set Size");
        int blockSize = validateInput(view.getBlockSize(), "Block Size");
        int cacheAccessTime = validateInput(view.getCacheAccTime(), "Cache Access Time");
        int memoryAccessTime = validateInput(view.getMemoryAccTime(), "Memory Access Time");

        if (allOutputsIntegers && allOutputsPositive) {
            String cacheSizeType = view.getCacheSizeType();
            String mmSizeType = view.getMMSizeType();
            String readType = view.getReadType();
            String addressType = view.getAddressType();
            String inputType = view.getInputType();

            if (addressType.equals("Contiguous")) {
                programFlowContig = view.getProgramFlowContiguous();
                result.clear();
                recursion(0);
            }
            else
                this.result = view.getProgramFlowNonContiguous();

            if (result.isEmpty() || result.get(0).equals(""))
                JOptionPane.showMessageDialog(null, "ERROR: Please enter a valid program flow.",
                        "BSA-LRU Cache Simulator", JOptionPane.ERROR_MESSAGE);
            else {
                if (cacheSizeType.equals("Blocks"))
                    cacheMemorySize *= blockSize;

                if (mmSizeType.equals("Blocks"))
                    mainMemorySize *= blockSize;

                this.simulator = new Simulator(blockSize, setSize, mainMemorySize, cacheMemorySize, result, inputType);
                simulator.simulate();

                String[] colNames = new String[setSize];

                for (int i = 0; i < colNames.length; i++)
                    colNames[i] = "Block " + i;

                view.addColsToTable(colNames);

                String[][] snapshot = simulator.getSnapshotOfCacheMemory();
                this.outputTable = new String[snapshot.length][snapshot[0].length + 1];

                for (int i = 0; i < outputTable.length; i++) {
                    for (int j = 0; j < outputTable[i].length; j++)
                        if (j == 0)
                            outputTable[i][j] = String.valueOf(i);
                        else
                            outputTable[i][j] = snapshot[i][j - 1];
                }

                view.setTable(outputTable);
                view.setCacheHits(simulator.getNumOfCacheHit());
                view.setCacheMisses(simulator.getNumOfCacheMiss());

                this.missPenalty = simulator.getMissPenalty(readType, cacheAccessTime, memoryAccessTime);
                this.aveMemoryAccessTime = simulator.getAverageMemoryAccessTime(readType, cacheAccessTime, memoryAccessTime);
                this.totalMemoryAccessTime = simulator.totalMemoryAccessTime(readType, cacheAccessTime, memoryAccessTime);

                view.setMissPenalty(missPenalty);
                view.setAveMemAccTime(aveMemoryAccessTime);
                view.setTotalMemAccTime(totalMemoryAccessTime);
                view.showOutput();
            }
        }
        else if (allOutputsIntegers)
            JOptionPane.showMessageDialog(null, "ERROR: All inputs should be greater than 0.",
                    "BSA-LRU Cache Simulator", JOptionPane.ERROR_MESSAGE);
    }

    private void addContiguousDataToProgramFlow(int start, int end, ArrayList<String> result) {
        for (int i = start; i <= end; i++)
            result.add(Integer.toHexString(i));
    }

    private int recursion(int rowNum) {
        if (rowNum >= programFlowContig.size())
            return rowNum;
        else
        {
            String[] row = programFlowContig.get(rowNum);
            switch(row[0]) {
                case "RANGE":
                    addContiguousDataToProgramFlow(Integer.parseInt(row[1], 16), Integer.parseInt(row[2], 16), result);
                    return recursion(rowNum+1);

                case "LOOP":
                    int loopNum = Integer.parseInt(row[2]);
                    int currRow = 0;
                    for (int i = 0; i < loopNum; i++)
                    {
                        currRow = recursion(rowNum+1);
                    }
                    return recursion(currRow+1);
                case "J" : //assume nested loops (nearest J is end of the current loop)
                    return rowNum;
                default:
                    return -1;
            }
        }
    }

    private void saveToTextFile () {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showSaveDialog(null);

            String filename = fileChooser.getSelectedFile()+"\\" + "BSA_LRU_Cache_Simulator_Output.txt";
            FileWriter writer = new FileWriter(filename);
            String stringToWrite = "BSA-LRU CACHE SIMULATOR OUTPUT\n\n" +
                    "Cache Memory Snapshot:\n" + writeOutputTable() + "\n" +
                    "Cache Hits: " + simulator.getNumOfCacheHit() + "\n\n" +
                    "Cache Misses: " + simulator.getNumOfCacheMiss() + "\n\n" +
                    "Miss Penalty: " + missPenalty + "\n\n" +
                    "Average Memory Access Time: " + aveMemoryAccessTime + "\n\n" +
                    "Total Memory Access Time: " + totalMemoryAccessTime;

            writer.write(stringToWrite);
            writer.close();
            JOptionPane.showMessageDialog(null, "Successfully exported text file!",
                    "BSA-LRU Cache Memory Simulator", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ignored) {}
    }

    private String writeOutputTable () {
        StringBuilder outputString = new StringBuilder("* Each snapshot row is formatted as [SET #, BLOCK 0, BLOCK 1, ..., BLOCK N]\n\n");

        for (String[] row : outputTable)
            outputString.append(Arrays.toString(row)).append("\n");

        return outputString.toString();
    }
}
