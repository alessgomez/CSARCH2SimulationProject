package src;

import javax.swing.*;
import java.awt.*;

import static java.lang.Integer.parseInt;

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
    public JComboBox<String> cmbBoxContiguous;
    public JComboBox<String> cmbBoxInputType;

    public JTextArea taProgramFlow;

    public JScrollPane spProgramFlow;
    public JScrollPane spSnapshot;

    public JButton btnSimulate;
    public JButton btnReset;
    public JButton btnSave;

    public JTable tblSnapshot;

    public View () {
        setTitle("CSARCH2 Simulation Project");
        setSize(1230, 750);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        String[] optionsBlockOrWord = {"Blocks", "Words"};
        String[] optionsLoadThroughOrNot = {"Load-Through", "Non Load-Through"};
        String[] optionsContiguousOrNot = {"Contiguous", "Non Contiguous"};
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

        cmbBoxContiguous = new JComboBox<>(optionsContiguousOrNot);
        cmbBoxContiguous.setBounds(400, 135, 170, 25);
        pnlLeft.add(cmbBoxContiguous);

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
        btnReset = new JButton("Reset");
        btnReset.setBounds(120, 540, 200, 45);
        btnReset.setFont(new Font("Arial", Font.BOLD, 16));
        pnlLeft.add(btnReset);

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
        String[][] data = {
                { "1", "1", "1" },
                { "2", "2", "2" },
                { "", "", "" }
        };
        String[] columnNames = {"Set", "Block", "Data"};

        tblSnapshot = new JTable(data, columnNames);
        tblSnapshot.setEnabled(false);

        spSnapshot = new JScrollPane(tblSnapshot);
        spSnapshot.setBounds(60, 90, 190, 400);
        spSnapshot.setBackground(Color.decode("#7398BA"));
        pnlRight.add(spSnapshot);

        // Cache Hits
        lblCacheHits = new JLabel("Cache Hits");
        lblCacheHits.setFont(new Font("Arial", Font.BOLD, 16));
        lblCacheHits.setBounds(280, 90, 100, 50);
        lblCacheHits.setForeground(Color.BLACK);
        pnlRight.add(lblCacheHits);

        lblCacheHitsRes = new JLabel("5");
        lblCacheHitsRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCacheHitsRes.setBounds(430, 90, 150, 50);
        lblCacheHitsRes.setForeground(Color.BLACK);
        pnlRight.add(lblCacheHitsRes);

        // Cache Misses
        lblCacheMisses = new JLabel("Cache Misses");
        lblCacheMisses.setFont(new Font("Arial", Font.BOLD, 16));
        lblCacheMisses.setBounds(280, 140, 150, 50);
        lblCacheMisses.setForeground(Color.BLACK);
        pnlRight.add(lblCacheMisses);

        lblCacheMissesRes = new JLabel("5");
        lblCacheMissesRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCacheMissesRes.setBounds(430, 140, 150, 50);
        lblCacheMissesRes.setForeground(Color.BLACK);
        pnlRight.add(lblCacheMissesRes);

        // Miss Penalty
        lblMissPenalty = new JLabel("Miss Penalty");
        lblMissPenalty.setFont(new Font("Arial", Font.BOLD, 16));
        lblMissPenalty.setBounds(280, 190, 150, 50);
        lblMissPenalty.setForeground(Color.BLACK);
        pnlRight.add(lblMissPenalty);

        lblMissPenaltyRes = new JLabel("5");
        lblMissPenaltyRes.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMissPenaltyRes.setBounds(430, 190, 150, 50);
        lblMissPenaltyRes.setForeground(Color.BLACK);
        pnlRight.add(lblMissPenaltyRes);

        // Average Memory Access Time
        lblAveMemAccTime1 = new JLabel("Average Memory");
        lblAveMemAccTime1.setFont(new Font("Arial", Font.BOLD, 16));
        lblAveMemAccTime1.setBounds(280, 240, 150, 50);
        lblAveMemAccTime1.setForeground(Color.BLACK);
        pnlRight.add(lblAveMemAccTime1);

        lblAveMemAccTime2 = new JLabel("Access Time");
        lblAveMemAccTime2.setFont(new Font("Arial", Font.BOLD, 16));
        lblAveMemAccTime2.setBounds(280, 260, 150, 50);
        lblAveMemAccTime2.setForeground(Color.BLACK);
        pnlRight.add(lblAveMemAccTime2);

        lblAveMemAccTime2 = new JLabel("5");
        lblAveMemAccTime2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblAveMemAccTime2.setBounds(430, 240, 150, 50);
        lblAveMemAccTime2.setForeground(Color.BLACK);
        pnlRight.add(lblAveMemAccTime2);

        // Total Memory Access Time
        lblTotalMemAccTime1 = new JLabel("Total Memory");
        lblTotalMemAccTime1.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalMemAccTime1.setBounds(280, 310, 150, 50);
        lblTotalMemAccTime1.setForeground(Color.BLACK);
        pnlRight.add(lblTotalMemAccTime1);

        lblTotalMemAccTime2 = new JLabel("Access Time");
        lblTotalMemAccTime2.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotalMemAccTime2.setBounds(280, 330, 150, 50);
        lblTotalMemAccTime2.setForeground(Color.BLACK);
        pnlRight.add(lblTotalMemAccTime2);

        lblTotalMemAccTime2 = new JLabel("5");
        lblTotalMemAccTime2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTotalMemAccTime2.setBounds(430, 310, 150, 50);
        lblTotalMemAccTime2.setForeground(Color.BLACK);
        pnlRight.add(lblTotalMemAccTime2);

        // Save To Text File
        btnSave = new JButton("Save to Text File");
        btnSave.setBounds(165, 540, 200, 45);
        btnSave.setFont(new Font("Arial", Font.BOLD, 16));
        pnlRight.add(btnSave);

        setVisible(true);
    }


	public static void main(String[] args) {
		View view = new View();

	}
}
