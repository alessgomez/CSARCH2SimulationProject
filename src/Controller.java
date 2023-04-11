package src;

import java.awt.event.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Controller implements ActionListener {
    private final View view;
    private Simulator simulator;
    private int missPenalty;
    private float aveMemoryAccessTime;
    private int totalMemoryAccessTime;
    private String[][] outputTable;

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

    private void simulate () {
        int cacheMemorySize = Integer.parseInt(view.getCacheSize());
        int mainMemorySize = Integer.parseInt(view.getMMSize());
        int setSize = Integer.parseInt(view.getSetSize());
        int blockSize = Integer.parseInt(view.getBlockSize());
        int cacheAccessTime = Integer.parseInt(view.getCacheAccTime());
        int memoryAccessTime = Integer.parseInt(view.getMemoryAccTime());

        String cacheSizeType = view.getCacheSizeType();
        String mmSizeType = view.getMMSizeType();
        String readType = view.getReadType();
        String addressType = view.getAddressType();
        String inputType = view.getInputType();

        ArrayList<String> programFlow;

        if (cacheSizeType.equals("Blocks"))
            cacheMemorySize *= blockSize;

        if (mmSizeType.equals("Blocks"))
            mainMemorySize *= blockSize;

        if (addressType.equals("Contiguous"))
            programFlow = convertContiguousProgramFlow(view.getProgramFlowContiguous());
        else
            programFlow = view.getProgramFlowNonContiguous();

        this.simulator = new Simulator(blockSize, setSize, mainMemorySize, cacheMemorySize, programFlow, inputType);
        simulator.simulate();

        String[] colNames = new String[setSize];

        for (int i = 0 ; i < colNames.length ; i++)
            colNames[i] = "Block " + i;

        view.addColsToTable(colNames);

        String[][] snapshot = simulator.getSnapshotOfCacheMemory();
        this.outputTable = new String[snapshot.length][snapshot[0].length + 1];

        for (int i = 0 ; i < outputTable.length ; i++) {
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

    private void saveToTextFile () { // TODO: Should we show a dialog box upon successful export?
        try { // TODO: Export cache snapshot array
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showSaveDialog(null);

            String filename = fileChooser.getSelectedFile()+"\\" + "BSA_LRU_Cache_Simulator_Output.txt";
            FileWriter writer = new FileWriter(filename);
            String stringToWrite = "CACHE SIMULATOR OUTPUT - BSA / LRU\n\n" +
                    "Cache Memory Snapshot:\n" + writeOutputTable() + "\n\n" +
                    "Cache Hits: " + simulator.getNumOfCacheHit() + "\n\n" +
                    "Cache Misses: " + simulator.getNumOfCacheMiss() + "\n\n" +
                    "Miss Penalty: " + missPenalty + "\n\n" +
                    "Average Memory Access Time: " + aveMemoryAccessTime + "\n\n" +
                    "Total Memory Access Time: " + totalMemoryAccessTime + "\n\n";

            writer.write(stringToWrite);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private ArrayList<String> convertContiguousProgramFlow (ArrayList<String[]> programFlow) {
        ArrayList<String> result;

        return result;
    }

    private String writeOutputTable () {
        StringBuilder outputString = new StringBuilder("SET\t\t");

        for (int i = 1 ; i < outputTable.length - 1 ; i++)
            outputString.append("BLOCK ").append(i - 1).append("\t");

        outputString.append("\n");

        for (String[] rows : outputTable) {
            for (String string : rows) outputString.append(string).append("\t\t");
            outputString.append("\n");
        }

        return outputString.toString();
    }
}
