import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LRUPagingGUI {
    LRUPagingGUI(){
        createAndShowInputFrame();
    }
    private static void createAndShowInputFrame() {
        JFrame inputFrame = new JFrame("LRU Paging Example - Input");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setLayout(new FlowLayout());

        JLabel referenceLabel = new JLabel("Enter the reference string (maximum 2 elements, end with -1):");
        JTextField referenceField = new JTextField(20);
        JLabel framesLabel = new JLabel("Enter the number of frames:");
        JTextField framesField = new JTextField(5);
        JButton startButton = new JButton("Start");

        inputFrame.add(referenceLabel);
        inputFrame.add(referenceField);
        inputFrame.add(framesLabel);
        inputFrame.add(framesField);
        inputFrame.add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFrame.dispose(); // Close the input frame
                createAndShowOutputFrame(referenceField.getText(), Integer.parseInt(framesField.getText()));
            }
        });

        inputFrame.setSize(300, 150);
        inputFrame.setLocationRelativeTo(null);
        inputFrame.setVisible(true);
    }
    private static void createAndShowOutputFrame(String referenceStringText, int numFrames) {
        JFrame outputFrame = new JFrame("LRU Paging Example - Output");
        outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outputFrame.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        tableModel.addColumn("Frame");
        tableModel.addColumn("Page");
        //tableModel.addColumn("Recent Access");

        JScrollPane scrollPane = new JScrollPane(table);
        outputFrame.add(scrollPane, BorderLayout.CENTER);

        int[] referenceString = Arrays.stream(referenceStringText.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int pageFaults = 0, pageHits = 0;
        int[] pageFrames = new int[numFrames];
        int[] recentAccess = new int[numFrames];

        for (int i = 0; i < numFrames; ++i) {
            pageFrames[i] = -1;
            recentAccess[i] = 0;
        }

        for (int i = 0; i < referenceString.length; ++i) {
            boolean pageFault = true;
            for (int j = 0; j < numFrames; ++j) {
                if (pageFrames[j] == referenceString[i]) {
                    pageFault = false;
                    recentAccess[j] = i + 1;
                    pageHits++;
                    break;
                }
            }
            if (pageFault) {
                pageFaults++;

                int minRecent = recentAccess[0];
                int replaceIndex = 0;
                for (int j = 1; j < numFrames; ++j) {
                    if (recentAccess[j] < minRecent) {
                        minRecent = recentAccess[j];
                        replaceIndex = j;
                    }
                }
                pageFrames[replaceIndex] = referenceString[i];
                recentAccess[replaceIndex] = i + 1;
            }

            Object[] rowData = new Object[]{Arrays.toString(pageFrames), referenceString[i]/*, Arrays.toString(recentAccess)*/};
            tableModel.addRow(rowData);
        }

        JLabel resultLabel = new JLabel("Total number of page faults: " + pageFaults + ", Total number of page hits: " + pageHits);
        outputFrame.add(resultLabel, BorderLayout.SOUTH);

        outputFrame.setSize(500, 300);
        outputFrame.setLocationRelativeTo(null);
        outputFrame.setVisible(true);
    }
}