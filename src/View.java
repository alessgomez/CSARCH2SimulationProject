package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class View extends JFrame{

    public JPanel pnlTitle;
    public JLabel lblTitle;

    public JPanel pnlBottom;

    public JPanel pnlLeft;
    public JPanel pnlRight;

    public JLabel lblCacheSize;
    public JLabel lblMMSize;
    public JLabel lblSetSize;
    public JLabel lblBlockSize;
    public JLabel lblCacheAccTime;
    public JLabel lblMemoryAccTime;
    public JLabel lblSetSizeBlocks;
    public JLabel lblBlockSizeWords;
    public JLabel lblCacheAccTimeNS;
    public JLabel lblMemoryAccTimeNS;
    public JLabel lblReadType;
    public JLabel lblProgramFlow;
    public JLabel lblContiguous;
    public JLabel lblInputType;
    public JLabel lblOutput;

    public JLabel lblCacheHits;
    public JLabel lblCacheMisses;
    public JLabel lblMissPenalty;
    public JLabel lblAveMemAccTime1;
    public JLabel lblAveMemAccTime2;
    public JLabel lblTotalMemAccTime1;
    public JLabel lblTotalMemAccTime2;

    public JLabel lblCacheHitsRes;
    public JLabel lblCacheMissesRes;
    public JLabel lblMissPenaltyRes;
    public JLabel lblAveMemAccTimeRes;
    public JLabel lblTotalMemAccTimeRes;

    public JTextField tfCacheSize;
    public JTextField tfMMSize;
    public JTextField tfSetSize;
    public JTextField tfBlockSize;
    public JTextField tfCacheAccTime;
    public JTextField tfMemoryAccTime;

    public JComboBox<String> cmbBoxCacheSizeType;
    public JComboBox<String> cmbBoxMMSizeType;
    public JComboBox<String> cmbBoxReadType;
    public JComboBox<String> cmbBoxAddressType;
    public JComboBox<String> cmbBoxInputType;

    public JTextArea taProgramFlow;

    public JScrollPane spProgramFlow;
    public JScrollPane spSnapshot;

    public JButton btnSimulate;
    public JButton btnClear;
    public JButton btnSave;

    public JTable tblSnapshot;

    DefaultTableModel tblModel;

    public View () {
        setTitle("CSARCH2 Simulation Project");
        setSize(1230, 750);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        String[] optionsBlockOrWord = {"Blocks", "Words"};
        String[] optionsLoadThroughOrNot = {"Non Load-Through", "Load-Through"};
        String[] optionsContiguousOrNot = {"Non Contiguous", "Contiguous"};
        String[] optionsAddressOrBlock = {"Addresses", "Blocks"};

        //Title Panel
        pnlTitle = new JPanel();
        pnlTitle.setBounds(0, 0, 1230, 60);
        pnlTitle.setBackground(Color.decode("#112341"));
        pnlTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(pnlTitle);

        lblTitle = new JLabel("BSA-LRU Cache Simulator");
        lblTitle.setFont(new Font("Arial Black", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.WHITE);
        pnlTitle.add(lblTitle);

        // Bottom Panel
        pnlBottom = new JPanel();
        pnlBottom.setBounds(0, 690, 1230, 40);
        pnlBottom.setBackground(Color.decode("#112341"));
        pnlBottom.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(pnlBottom);

        //Left Panel
        pnlLeft = new JPanel();
        pnlLeft.setBounds(0, 60, 685, 650);
        pnlLeft.setBackground(Color.decode("#C0C9D0"));
        pnlLeft.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 0));
        pnlLeft.setLayout(null);
        add(pnlLeft);

        //Right Panel
        pnlRight = new JPanel();
        pnlRight.setBounds(695, 60, 590, 650);
        pnlRight.setBackground(Color.decode("#7398BA"));
        pnlRight.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        pnlRight.setLayout(null);
        add(pnlRight);

        // Cache Size Input
        lblCacheSize = new JLabel("Cache Size");
        lblCacheSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblCacheSize.setBounds(60, 10, 100, 50);
        lblCacheSize.setForeground(Color.BLACK);
        pnlLeft.add(lblCacheSize);

        tfCacheSize = new JTextField();
        tfCacheSize.setBounds(60, 55, 170, 25);
        pnlLeft.add(tfCacheSize);

        cmbBoxCacheSizeType = new JComboBox<>(optionsBlockOrWord);
        cmbBoxCacheSizeType.setBounds(250, 55, 80, 25);
        pnlLeft.add(cmbBoxCacheSizeType);

        // Main Memory Size Input
        lblMMSize = new JLabel("Main Memory Size");
        lblMMSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblMMSize.setBounds(60, 90, 150, 50);
        lblMMSize.setForeground(Color.BLACK);
        pnlLeft.add(lblMMSize);

        tfMMSize = new JTextField();
        tfMMSize.setBounds(60, 135, 170, 25);
        pnlLeft.add(tfMMSize);

        cmbBoxMMSizeType = new JComboBox<>(optionsBlockOrWord);
        cmbBoxMMSizeType.setBounds(250, 135, 80, 25);
        pnlLeft.add(cmbBoxMMSizeType);

        // Set Size
        lblSetSize = new JLabel("Set Size");
        lblSetSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblSetSize.setBounds(60, 170, 150, 50);
        lblSetSize.setForeground(Color.BLACK);
        pnlLeft.add(lblSetSize);

        tfSetSize = new JTextField();
        tfSetSize.setBounds(60, 215, 170, 25);
        pnlLeft.add(tfSetSize);

        lblSetSizeBlocks = new JLabel("Blocks");
        lblSetSizeBlocks.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSetSizeBlocks.setBounds(250, 202, 150, 50);
        lblSetSizeBlocks.setForeground(Color.BLACK);
        pnlLeft.add(lblSetSizeBlocks);

        // Block Size
        lblBlockSize = new JLabel("Block Size");
        lblBlockSize.setFont(new Font("Arial", Font.BOLD, 16));
        lblBlockSize.setBounds(60, 250, 250, 50);
        lblBlockSize.setForeground(Color.BLACK);
        pnlLeft.add(lblBlockSize);

        tfBlockSize = new JTextField();
        tfBlockSize.setBounds(60, 295, 170, 25);
        pnlLeft.add(tfBlockSize);

        lblBlockSizeWords = new JLabel("Words");
        lblBlockSizeWords.setFont(new Font("Arial", Font.PLAIN, 14));
        lblBlockSizeWords.setBounds(250, 282, 150, 50);
        lblBlockSizeWords.setForeground(Color.BLACK);
        pnlLeft.add(lblBlockSizeWords);

        // Cache Access Time
        lblCacheAccTime = new JLabel("Cache Access Time");
        lblCacheAccTime.setFont(new Font("Arial", Font.BOLD, 16));
        lblCacheAccTime.setBounds(60, 330, 350, 50);
        lblCacheAccTime.setForeground(Color.BLACK);
        pnlLeft.add(lblCacheAccTime);

        tfCacheAccTime = new JTextField();
        tfCacheAccTime.setBounds(60, 375, 170, 25);
        pnlLeft.add(tfCacheAccTime);

        lblCacheAccTimeNS = new JLabel("Nanoseconds");
        lblCacheAccTimeNS.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCacheAccTimeNS.setBounds(250, 362, 150, 50);
        lblCacheAccTimeNS.setForeground(Color.BLACK);
        pnlLeft.add(lblCacheAccTimeNS);

        // Memory Access Time
        lblMemoryAccTime = new JLabel("Memory Access Time");
        lblMemoryAccTime.setFont(new Font("Arial", Font.BOLD, 16));
        lblMemoryAccTime.setBounds(60, 410, 350, 50);
        lblMemoryAccTime.setForeground(Color.BLACK);
        pnlLeft.add(lblMemoryAccTime);

        tfMemoryAccTime = new JTextField();
        tfMemoryAccTime.setBounds(60, 455, 170, 25);
        pnlLeft.add(tfMemoryAccTime);

        lblMemoryAccTimeNS = new JLabel("Nanoseconds");
        lblMemoryAccTimeNS.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMemoryAccTimeNS.setBounds(250, 442, 150, 50);
        lblMemoryAccTimeNS.setForeground(Color.BLACK);
        pnlLeft.add(lblMemoryAccTimeNS);

        // Read Type
        lblReadType = new JLabel("Read Type");
        lblReadType.setFont(new Font("Arial", Font.BOLD, 16));
        lblReadType.setBounds(400, 10, 100, 50);
        lblReadType.setForeground(Color.BLACK);
        pnlLeft.add(lblReadType);

        cmbBoxReadType = new JComboBox<>(optionsLoadThroughOrNot);
        cmbBoxReadType.setBounds(400, 55, 170, 25);
        pnlLeft.add(cmbBoxReadType);

        // Address Type
        lblContiguous = new JLabel("Address Type");
        lblContiguous.setFont(new Font("Arial", Font.BOLD, 16));
        lblContiguous.setBounds(400, 90, 150, 50);
        lblContiguous.setForeground(Color.BLACK);
        pnlLeft.add(lblContiguous);

        cmbBoxAddressType = new JComboBox<>(optionsContiguousOrNot);
        cmbBoxAddressType.setBounds(400, 135, 170, 25);
        pnlLeft.add(cmbBoxAddressType);

        // Input Type
        lblInputType = new JLabel("Input Type");
        lblInputType.setFont(new Font("Arial", Font.BOLD, 16));
        lblInputType.setBounds(400, 170, 150, 50);
        lblInputType.setForeground(Color.BLACK);
        pnlLeft.add(lblInputType);

        cmbBoxInputType = new JComboBox<>(optionsAddressOrBlock);
        cmbBoxInputType.setBounds(400, 215, 170, 25);
        pnlLeft.add(cmbBoxInputType);

        // Program Flow
        lblProgramFlow = new JLabel("Program Flow");
        lblProgramFlow.setFont(new Font("Arial", Font.BOLD, 16));
        lblProgramFlow.setBounds(400, 250, 150, 50);
        lblProgramFlow.setForeground(Color.BLACK);
        pnlLeft.add(lblProgramFlow);

        taProgramFlow = new JTextArea();
        taProgramFlow.setLineWrap(true);

        spProgramFlow = new JScrollPane(taProgramFlow);
        spProgramFlow.setBounds(400, 295, 200, 200);
        pnlLeft.add(spProgramFlow);


        // Reset
        btnClear = new JButton("Clear");
        btnClear.setBounds(120, 540, 200, 45);
        btnClear.setFont(new Font("Arial", Font.BOLD, 16));
        pnlLeft.add(btnClear);

        // Simulate
        btnSimulate = new JButton("Simulate");
        btnSimulate.setBounds(370, 540, 200, 45);
        btnSimulate.setFont(new Font("Arial", Font.BOLD, 16));
        pnlLeft.add(btnSimulate);

        // Output
        lblOutput = new JLabel("Output");
        lblOutput.setFont(new Font("Arial", Font.BOLD, 16));
        lblOutput.setBounds(60, 10, 150, 50);
        lblOutput.setForeground(Color.BLACK);
        pnlRight.add(lblOutput);

        // snapshot
        String[] colNames = {"Set", "Block", "Data"};

        tblModel = new DefaultTableModel(colNames, 0);
        tblSnapshot = new JTable(tblModel);
        tblSnapshot.setEnabled(false);

        spSnapshot = new JScrollPane(tblSnapshot);
        spSnapshot.setBounds(60, 90, 190, 400);
        spSnapshot.setBackground(Color.decode("#7398BA"));
        pnlRight.add(spSnapshot);

        // Cache Hits
        lblCacheHits = new JLabel("Cache Hits");
        lblCacheHits.setFont(new Font("Arial", Font.BOLD, 16));
        lblCacheHits.setBounds(290, 90, 100, 50);
        lblCacheHits.setForeground(Color.BLACK);
        pnlRight.add(lblCacheHits);

        lblCacheHitsRes = new JLabel("5");
        lblCacheHitsRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCacheHitsRes.setBounds(440, 90, 150, 50);
        lblCacheHitsRes.setForeground(Color.BLACK);
        pnlRight.add(lblCacheHitsRes);

        // Cache Misses
        lblCacheMisses = new JLabel("Cache Misses");
        lblCacheMisses.setFont(new Font("Arial", Font.BOLD, 16));
        lblCacheMisses.setBounds(290, 140, 150, 50);
        lblCacheMisses.setForeground(Color.BLACK);
        pnlRight.add(lblCacheMisses);

        lblCacheMissesRes = new JLabel("5");
        lblCacheMissesRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCacheMissesRes.setBounds(440, 140, 150, 50);
        lblCacheMissesRes.setForeground(Color.BLACK);
        pnlRight.add(lblCacheMissesRes);

        // Miss Penalty
        lblMissPenalty = new JLabel("Miss Penalty");
        lblMissPenalty.setFont(new Font("Arial", Font.BOLD, 16));
        lblMissPenalty.setBounds(290, 190, 150, 50);
        lblMissPenalty.setForeground(Color.BLACK);
        pnlRight.add(lblMissPenalty);

        lblMissPenaltyRes = new JLabel("5");
        lblMissPenaltyRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMissPenaltyRes.setBounds(440, 190, 150, 50);
        lblMissPenaltyRes.setForeground(Color.BLACK);
        pnlRight.add(lblMissPenaltyRes);

        // Average Memory Access Time
        lblAveMemAccTime1 = new JLabel("Average Memory");
        lblAveMemAccTime1.setFont(new Font("Arial", Font.BOLD, 16));
        lblAveMemAccTime1.setBounds(290, 240, 150, 50);
        lblAveMemAccTime1.setForeground(Color.BLACK);
        pnlRight.add(lblAveMemAccTime1);

        lblAveMemAccTime2 = new JLabel("Access Time");
        lblAveMemAccTime2.setFont(new Font("Arial", Font.BOLD, 16));
        lblAveMemAccTime2.setBounds(290, 260, 150, 50);
        lblAveMemAccTime2.setForeground(Color.BLACK);
        pnlRight.add(lblAveMemAccTime2);

        lblAveMemAccTimeRes = new JLabel("5.25");
        lblAveMemAccTimeRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblAveMemAccTimeRes.setBounds(440, 240, 150, 50);
        lblAveMemAccTimeRes.setForeground(Color.BLACK);
        pnlRight.add(lblAveMemAccTimeRes);

        // Total Memory Access Time
        lblTotalMemAccTime1 = new JLabel("Total Memory");
        lblTotalMemAccTime1.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalMemAccTime1.setBounds(290, 310, 150, 50);
        lblTotalMemAccTime1.setForeground(Color.BLACK);
        pnlRight.add(lblTotalMemAccTime1);

        lblTotalMemAccTime2 = new JLabel("Access Time");
        lblTotalMemAccTime2.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalMemAccTime2.setBounds(290, 330, 150, 50);
        lblTotalMemAccTime2.setForeground(Color.BLACK);
        pnlRight.add(lblTotalMemAccTime2);

        lblTotalMemAccTimeRes = new JLabel("5.25");
        lblTotalMemAccTimeRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTotalMemAccTimeRes.setBounds(440, 310, 150, 50);
        lblTotalMemAccTimeRes.setForeground(Color.BLACK);
        pnlRight.add(lblTotalMemAccTimeRes);

        // Save To Text File
        btnSave = new JButton("Save to Text File");
        btnSave.setBounds(165, 540, 200, 45);
        btnSave.setFont(new Font("Arial", Font.BOLD, 16));
        pnlRight.add(btnSave);

        // Hide Output
        hideOutput();

        setVisible(true);
    }

    public void showOutput() {
        spSnapshot.setVisible(true);
        lblCacheHits.setVisible(true);
        lblCacheHitsRes.setVisible(true);
        lblCacheMisses.setVisible(true);
        lblCacheMissesRes.setVisible(true);
        lblMissPenalty.setVisible(true);
        lblMissPenaltyRes.setVisible(true);
        lblAveMemAccTime1.setVisible(true);
        lblAveMemAccTime2.setVisible(true);
        lblAveMemAccTimeRes.setVisible(true);
        lblTotalMemAccTime1.setVisible(true);
        lblTotalMemAccTime2.setVisible(true);
        lblTotalMemAccTimeRes.setVisible(true);
        btnSave.setVisible(true);
    }

    public void hideOutput() {
        spSnapshot.setVisible(false);
        lblCacheHits.setVisible(false);
        lblCacheHitsRes.setVisible(false);
        lblCacheMisses.setVisible(false);
        lblCacheMissesRes.setVisible(false);
        lblMissPenalty.setVisible(false);
        lblMissPenaltyRes.setVisible(false);
        lblAveMemAccTime1.setVisible(false);
        lblAveMemAccTime2.setVisible(false);
        lblAveMemAccTimeRes.setVisible(false);
        lblTotalMemAccTime1.setVisible(false);
        lblTotalMemAccTime2.setVisible(false);
        lblTotalMemAccTimeRes.setVisible(false);
        btnSave.setVisible(false);
    }

    public void clearInput() {
        tfCacheSize.setText("");
        tfMMSize.setText("");
        tfSetSize.setText("");
        tfBlockSize.setText("");
        tfCacheAccTime.setText("");
        tfMemoryAccTime.setText("");
        taProgramFlow.setText("");
    }

    void addClearListener(ActionListener clearListener){
        btnClear.addActionListener(clearListener);
    }

    void addSimulateListener(ActionListener simulateListener){
        btnSimulate.addActionListener(simulateListener);
    }

    void addSaveListener(ActionListener saveListener){
        btnSave.addActionListener(saveListener);
    }

    public String getCacheSize () {return tfCacheSize.getText();}

    public String getCacheSizeType () {return Objects.requireNonNull(cmbBoxCacheSizeType.getSelectedItem()).toString();}

    public String getMMSize () {return tfMMSize.getText();}

    public String getMMSizeType () {return Objects.requireNonNull(cmbBoxMMSizeType.getSelectedItem()).toString();}

    public String getSetSize () {return tfSetSize.getText();}

    public String getBlockSize () {return tfBlockSize.getText();}

    public String getCacheAccTime () {return tfCacheAccTime.getText();}

    public String getMemoryAccTime () {return tfMemoryAccTime.getText();}

    public String getReadType () {return Objects.requireNonNull(cmbBoxReadType.getSelectedItem()).toString();}

    public String getAddressType () {return Objects.requireNonNull(cmbBoxAddressType.getSelectedItem()).toString();}

    public String getInputType () {return Objects.requireNonNull(cmbBoxInputType.getSelectedItem()).toString();}

    public String getProgramFlow () {return taProgramFlow.getText();}

    public void addRowToTable(String[] row) {
        tblModel.addRow(row);
    }

    public void setTable(String[][] cacheSnapshot){
        for (String[] strings : cacheSnapshot) addRowToTable(strings);
    }

    public void addRowToTable(int[] row) {
        String[] rowString = {"", "", ""};
        for (int i = 0; i < row.length; i++) {
            rowString[i] = String.valueOf(row[i]);
        }
        tblModel.addRow(rowString);
    }

    public void setTable(int[][] cacheSnapshot){
        for (int[] values : cacheSnapshot) addRowToTable(values);
    }


    public void setCacheHits(int res){lblCacheHitsRes.setText(String.valueOf(res));}

    public void setCacheMisses(int res) {lblCacheMissesRes.setText(String.valueOf(res));}

    public void setMissPenalty(int res) {lblMissPenaltyRes.setText(String.valueOf(res));}

    public void setAveMemAccTime(int res) {lblAveMemAccTimeRes.setText(String.valueOf(res));}

    public void setTotalMemAccTime(int res) {lblTotalMemAccTimeRes.setText(String.valueOf(res));}
}
