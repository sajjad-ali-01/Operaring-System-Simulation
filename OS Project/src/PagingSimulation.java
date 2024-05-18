import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PagingSimulation extends JFrame {

    private JTextField processSizeField;
    private JTextField frameSizeField;
    private JTextField memorySizeField;  // New field for entering memory size
    private JLabel maxPageTableEntrySizeLabel;
    private JLabel pageTableSizeLabel;
    private JLabel totalFramesLabel;

    public PagingSimulation() {
        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JFrame frame = new JFrame("Paging");
        // Input panel
        JPanel inputPanel = new JPanel(null);


        // Labels and text fields
        JLabel processSizeLabel = new JLabel("Enter the process size (in MB):");
        processSizeLabel.setBounds(10, 10, 200, 25);
        inputPanel.add(processSizeLabel);

        processSizeField = new JTextField(10);
        processSizeField.setBounds(220, 10, 100, 25);
        inputPanel.add(processSizeField);

        JLabel frameSizeLabel = new JLabel("Enter the frame size (in KB):");
        frameSizeLabel.setBounds(10, 40, 200, 25);
        inputPanel.add(frameSizeLabel);

        frameSizeField = new JTextField(10);
        frameSizeField.setBounds(220, 40, 100, 25);
        inputPanel.add(frameSizeField);

        JLabel memorySizeLabel = new JLabel("Enter the memory size (in MB):");
        memorySizeLabel.setBounds(10, 70, 200, 25);
        inputPanel.add(memorySizeLabel);

        memorySizeField = new JTextField(10);
        memorySizeField.setBounds(220, 70, 100, 25);
        inputPanel.add(memorySizeField);

        // Buttons
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 100, 150, 25);
        calculateButton.addActionListener(this::calculate);
        inputPanel.add(calculateButton);

        JButton visualizeMemoryButton = new JButton("Visualize Memory");
        visualizeMemoryButton.setBounds(170, 100, 150, 25);
        visualizeMemoryButton.addActionListener(this::visualizeMemory);
        inputPanel.add(visualizeMemoryButton);

        // Result panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(3, 1, 5, 5));
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        maxPageTableEntrySizeLabel = new JLabel("Maximum page table entry size: ");
        pageTableSizeLabel = new JLabel("Page table size: ");
        totalFramesLabel = new JLabel("Total number of frames: ");
        resultPanel.add(totalFramesLabel);

        // Set font style for result labels
        Font resultFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        maxPageTableEntrySizeLabel.setFont(resultFont);
        pageTableSizeLabel.setFont(resultFont);
        totalFramesLabel.setFont(resultFont);

        resultPanel.add(maxPageTableEntrySizeLabel);
        resultPanel.add(pageTableSizeLabel);

        // Add components to the main frame
        add(inputPanel);
        add(resultPanel);

        frame.setSize(500,500);
        frame.add(inputPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void calculate(ActionEvent e) {
        try {
            int processSizeMB = Integer.parseInt(processSizeField.getText());
            int frameSizeKB = Integer.parseInt(frameSizeField.getText());
            int memorySizeMB = Integer.parseInt(memorySizeField.getText());  // New line to get memory size

            if (frameSizeKB <= 0 || processSizeMB <= 0 || memorySizeMB <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid positive values for process size, frame size, and memory size.");
                return;
            }

            int numberOfPages = (processSizeMB * 1024) / frameSizeKB;
            int maxPageTableEntrySize = calculateMaxPageTableEntrySize(numberOfPages);
            int pageTableSize = numberOfPages * maxPageTableEntrySize;

            maxPageTableEntrySizeLabel.setText("Maximum page table entry size: " + maxPageTableEntrySize + " bytes.");
            pageTableSizeLabel.setText("Page table size: " + pageTableSize + " bytes.");

            // Calculate total number of frames
            int totalFrames = (memorySizeMB * 1024) / frameSizeKB;  // Using memory size for total frames
            totalFramesLabel.setText("Total number of frames: " + totalFrames);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for process size, frame size, and memory size.");
        }
    }

    private void visualizeMemory(ActionEvent e) {
        try {
            int processSizeMB = Integer.parseInt(processSizeField.getText());
            int frameSizeKB = Integer.parseInt(frameSizeField.getText());
            int memorySizeMB = Integer.parseInt(memorySizeField.getText());

            if (frameSizeKB <= 0 || processSizeMB <= 0 || memorySizeMB <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter valid positive values for process size, frame size, and memory size.");
                return;
            }

            int numberOfPages = (processSizeMB * 1024) / frameSizeKB;
            int numberOfFrames = (memorySizeMB * 1024) / frameSizeKB;

            // Open the memory visualization in a new frame with a JTable
            MemoryPanelFrame memoryFrame = new MemoryPanelFrame(numberOfFrames, memorySizeMB);
            memoryFrame.setMemoryData(createMemoryData(numberOfFrames, frameSizeKB));
            memoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Open the frame table visualization in a new frame
            FrameTableFrame frameTableFrame = new FrameTableFrame(numberOfPages, frameSizeKB);
            frameTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for process size, frame size, and memory size.");
        }
    }

    private Object[][] createMemoryData(int numberOfFrames, int frameSizeKB) {
        Object[][] data = new Object[numberOfFrames][2];
        for (int i = 0; i < numberOfFrames; i++) {
            data[i][0] = "Frame " + (i + 1);
            data[i][1] = frameSizeKB + " KB";
        }
        return data;
    }
    private int calculateMaxPageTableEntrySize(int numberOfPages) {
        return Integer.SIZE / 8; // Assuming 32-bit address space
    }
    // Frame table visualization
    class FrameTableFrame extends JFrame {
        private JPanel frameTablePanel;
        private JScrollPane frameTableScrollPane;
        private int frameSizeKB;
        public FrameTableFrame(int numberOfPages, int frameSizeKB) {
            this.frameSizeKB = frameSizeKB;
            frameTablePanel = new JPanel();
            frameTablePanel.setLayout(new GridLayout(numberOfPages, 2, 5, 5));

            for (int i = 0; i < numberOfPages; i++) {
                frameTablePanel.add(new JLabel("Page " + (i + 1)));
                frameTablePanel.add(new JLabel("Page Size: " + frameSizeKB + " KB"));
            }

            frameTableScrollPane = new JScrollPane(frameTablePanel);
            frameTableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            frameTableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            add(frameTableScrollPane);
            setSize(300, 400);
            setLocationRelativeTo(null);
            setTitle("Frame Table");
            setVisible(true);
        }
    }
    class MemoryPanelFrame extends JFrame {
        private MemoryPanel memoryPanel;
        private JScrollPane scrollPane;
        private int memorySizeMB;

        public MemoryPanelFrame(int numberOfFrames, int memorySizeMB) {
            this.memorySizeMB = memorySizeMB;
            memoryPanel = new MemoryPanel();
            memoryPanel.setNumberOfFrames(numberOfFrames);

            // Creating a scroll pane that contains the memory panel
            scrollPane = new JScrollPane(memoryPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            add(scrollPane);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(500, 400); // Adjusted for better visibility with the scroll pane
            setLocationRelativeTo(null);
            setTitle("Memory Panel");
            setVisible(true);
        }

        public void setMemoryData(Object[][] data) {
            memoryPanel.setMemoryData(data);
        }

        class MemoryPanel extends JPanel {
            private int numberOfFrames = 0;
            private JTable memoryTable;

            public void setNumberOfFrames(int numberOfFrames) {
                this.numberOfFrames = numberOfFrames;
                createMemoryTable();
                int preferredHeight = Math.max(200, 20 * numberOfFrames);
                setPreferredSize(new Dimension(400, preferredHeight));
                revalidate(); // To update the scroll pane
            }

            public void setMemoryData(Object[][] data) {
                DefaultTableModel model = new DefaultTableModel(data, new Object[]{"Frame", "Frame Size"});
                memoryTable.setModel(model);
            }

            private void createMemoryTable() {
                memoryTable = new JTable(new Object[numberOfFrames][2], new Object[]{"Frame", "Frame Size"});
                add(new JScrollPane(memoryTable));
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PagingSimulation());
    }
}
