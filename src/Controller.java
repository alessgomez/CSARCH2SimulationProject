package src;

import java.awt.event.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Controller implements ActionListener {
    private final View view;
    private Simulator simulator;
    private int missPenalty;
    private float aveMemoryAccessTime;
    private int totalMemoryAccessTime;

    public Controller (View view) {
        this.view = view;
        this.view.addClearListener(this);
        this.view.addSimulateListener(this);
        this.view.addSaveListener(this);
    }

    public void actionPerformed (ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Clear" -> {view.clearInput(); view.hideOutput();}
            case "Simulate" -> simulate();
            case "Save to Text File" -> saveToTextFile();
        }
    }

    // TODO: I think we may need to reset the simulator bc it will just append if "simulate" is pressed again
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

        // TODO: Figure out how to pass appropriate arraylist type depending on the addressType
        ArrayList<String> programFlow;

        if (cacheSizeType.equals("Blocks"))
            cacheMemorySize *= blockSize;

        if (mmSizeType.equals("Blocks"))
            mainMemorySize *= blockSize;

        //if (addressType.equals("Contiguous"))
        //    programFlow = view.getProgramFlowContiguous();
        //else
            programFlow = view.getProgramFlowNonContiguous();

        this.simulator = new Simulator(blockSize, setSize, mainMemorySize, cacheMemorySize, programFlow, inputType);
        simulator.simulate();


        String[][] snapshot = simulator.getSnapshotOfCacheMemory();
        int[][] age = simulator.getCacheMemoryAge();

        for (int i = 0; i < snapshot.length; i++)
        {
            for (int j = 0; j < snapshot[i].length; j++)
                System.out.print(snapshot[i][j] + ": " + age[i][j] + " ");

            System.out.println();
        }


        view.setTable(simulator.getSnapshotOfCacheMemory());
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
        try {
            FileWriter writer = new FileWriter("output.txt");
            String stringToWrite = "CACHE SIMULATOR OUTPUT - BSA / LRU\n" +
                    "Cache Memory Snapshot: \n" +   // TODO: Export cache snapshot array
                    "Cache Hits: " + simulator.getNumOfCacheHit() + "\n" +
                    "Cache Misses: " + simulator.getNumOfCacheMiss() + "\n" +
                    "Miss Penalty: " + missPenalty + "\n" +
                    "Average Memory Access Time: " + aveMemoryAccessTime + "\n" +
                    "Total Memory Access Time: " + totalMemoryAccessTime + "\n";

            writer.write(stringToWrite);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
