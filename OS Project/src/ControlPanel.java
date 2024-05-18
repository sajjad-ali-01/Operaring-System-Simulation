import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ControlPanel {
    private JFrame frame;
    private JFrame Cframe;

    // Data structure to store created processes
    private ArrayList<Process> processList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable processTable;  // Declare processTable as a class member variable

    public ControlPanel() {
        ControlPanel();
    }

    public void ControlPanel() {
        JPanel cpanel = new JPanel(null);
        Cframe = new JFrame("ControlPanel");
        JButton procManage = new JButton("Process Management");
        JButton momeryManage = new JButton("Memory Management");
        JButton sync = new JButton("Syncronization");
        JButton otherOption = new JButton("Other options");

        procManage.setBounds(10, 50, 250, 50);
        momeryManage.setBounds(350, 50, 250, 50);
        sync.setBounds(10, 150, 250, 50);
        otherOption.setBounds(350, 150, 250, 50);

        cpanel.add(procManage);
        cpanel.add(momeryManage);
        cpanel.add(sync);
        cpanel.add(otherOption);

        procManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcessManage();
                Cframe.setVisible(false);
            }
        });
        momeryManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemoryManage();
                Cframe.setVisible(false);
            }
        });
        sync.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Semaphores();
            }
        });
        otherOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel pane = new JPanel(null);
                JFrame fram = new JFrame("Socket programing");
                JButton b1 = new JButton("Server");
                JButton b2 = new JButton("Client");

                b1.setBounds(30, 50, 250, 30);
                b2.setBounds(300, 50, 250, 30);

                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Server();
                    }
                });
                b2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new Client();
                    }
                });
                pane.add(b1);
                pane.add(b2);

                fram.add(pane);
                fram.setSize(400, 400);
                fram.setLocationRelativeTo(null);
                fram.setVisible(true);
            }
        });


        Cframe.add(cpanel);
        Cframe.setSize(700, 450);
        Cframe.setLocationRelativeTo(null);
        Cframe.setVisible(true);
    }

    // process Management start
    public void ProcessManage() {
        JPanel panel = new JPanel(null);
        frame = new JFrame("Process Management");
        JButton b1 = new JButton("Create Process");
        JButton b2 = new JButton("Show PCB");
        JButton b3 = new JButton("Destroy Process");
        JButton b4 = new JButton("Suspend Process");
        JButton b5 = new JButton("Resume Process");
        JButton b6 = new JButton("Block Process");
        JButton b7 = new JButton("Wakeup Process");
        JButton b8 = new JButton("Dispatch Process");
        JButton b9 = new JButton("Change process priority");
        JButton b10 = new JButton("Process communication with other processes");

        b1.setBounds(30, 50, 250, 30);
        b2.setBounds(300, 50, 250, 30);
        b3.setBounds(30, 150, 250, 30);
        b4.setBounds(300, 150, 250, 30);
        b5.setBounds(30, 250, 250, 30);
        b6.setBounds(300, 250, 250, 30);
        b7.setBounds(30, 350, 250, 30);
        b8.setBounds(300, 350, 250, 30);
        b9.setBounds(30, 450, 250, 30);
        b10.setBounds(300, 450, 250, 30);
        // Add ActionListener to the "Create Process" button
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_process();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPCB();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destroy_process();
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suspendProcess();
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeProcess();
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blockProcess();
            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wakeupProcess();
            }
        });

        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dispatch();
            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePriority();
            }
        });
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCommunicationPanel();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.setBounds(30, 10, 80, 30);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ControlPanel();
               frame.setVisible(false);
            }
        });

        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(b10);
        panel.add(backButton);
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void MemoryManage() {
        JPanel panel = new JPanel(null);
        frame = new JFrame("Memmory Management");
        JButton b1 = new JButton("Paging");
        JButton b2 = new JButton("LRU");

        b1.setBounds(30, 50, 250, 30);
        b2.setBounds(300, 50, 250, 30);

        // Add ActionListener to the "Create Process" button
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new PagingSimulation();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LRUPagingGUI lruPagingGUI = new LRUPagingGUI();
            }
        });


        panel.add(b1);
        panel.add(b2);

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void create_process() {
        JFrame frame = new JFrame("Create process");
        JLabel label = new JLabel("Enter Number of Process:");
        JTextField text = new JTextField();
        JButton button = new JButton("Create");
        JLabel ArivalTime = new JLabel();
        JTextField text2 = new JTextField();
        JLabel BurstTime = new JLabel();
        JTextField text3 = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(null);
        JButton backButton = new JButton("Back");
        backButton.setBounds(30, 10, 80, 30);

        label.setBounds(20, 20, 200, 30);
        text.setBounds(250, 20, 200, 30);
        button.setBounds(20, 60, 100, 30);
        ArivalTime.setBounds(20, 100, 200, 30);
        text2.setBounds(250, 100, 200, 30);
        BurstTime.setBounds(20, 140, 200, 30);
        text3.setBounds(250, 140, 200, 30);

        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        tableModel = new DefaultTableModel(null, columnNames);  // Assign to the class member variable
        processTable = new JTable(tableModel);  // Assign to the class member variable
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 200, 500, 200);
        scrollPane.setViewportView(processTable);
        panel.add(scrollPane);

        final int[] id = {0};
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num = Integer.parseInt(text.getText());

                // Move label setting outside the loop

                for (int i = 0; i < num; i++) {
                    // Prompt the user for arrival time and burst time inside the loop
                    int at = Integer.parseInt(JOptionPane.showInputDialog("Enter Arrival Time for Process " + (i + 1) + ":"));
                    int bt = Integer.parseInt(JOptionPane.showInputDialog("Enter Burst Time for Process " + (i + 1) + ":"));
                    int pri = Integer.parseInt(JOptionPane.showInputDialog("Enter Priority Time for Process " + (i + 1) + ":"));

                    id[0] = id[0] + 1;
                    Process p = new Process(id[0], at, bt, pri);
                    processList.add(p);
                    tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
                }
            }
        });


        panel.add(label);
        panel.add(text);
        panel.add(button);
        //panel.add(backButton);
//        panel.add(ArivalTime);
//        panel.add(text2);
//        panel.add(BurstTime);
//        //panel.add(text3);
        // panel.add(backButton);

        frame.add(panel);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Add this method to the ControlPanel class
    public void showPCB() {
        JFrame showPCBFrame = new JFrame("Show PCB");
        JPanel showPCBPanel = new JPanel(null);

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Process Name", "Status", "Priority"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        JTable processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        scrollPane.setBounds(20, 80, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            tableModel.addRow(new Object[]{p.pid, "Process"+ p.pid, p.status, p.priority});
        }

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(20, 30, 100, 30);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPCBFrame.dispose();
            }
        });

        showPCBPanel.add(scrollPane);
        showPCBPanel.add(closeButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(30, 10, 80, 30);
        //showPCBPanel.add(backButton);

        showPCBFrame.add(showPCBPanel);
        showPCBFrame.setSize(600, 400);
        showPCBFrame.setLocationRelativeTo(null);
        showPCBFrame.setVisible(true);
    }


    public void destroy_process() {
        JFrame destroyFrame = new JFrame("Destroy Process");
        JLabel destroyLabel = new JLabel("Enter Process ID to Destroy:");
        JTextField destroyText = new JTextField();
        JButton destroyButton = new JButton("Destroy");

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        tableModel = new DefaultTableModel(null, columnNames);  // Assign to the class member variable
        processTable = new JTable(tableModel);  // Assign to the class member variable
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel destroyPanel = new JPanel();
        destroyPanel.setLayout(null);


        destroyLabel.setBounds(20, 20, 200, 30);
        destroyText.setBounds(250, 20, 200, 30);
        destroyButton.setBounds(20, 60, 100, 30);
        scrollPane.setBounds(20, 100, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
        }

        destroyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int destroyId = Integer.parseInt(destroyText.getText());
                boolean found = false;

                // Find and remove the process with the specified ID
                for (Process p : processList) {
                    if (p.pid == destroyId) {
                        processList.remove(p);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // Update the table after process removal
                    updateTable();
                    JOptionPane.showMessageDialog(null, "Process with ID " + destroyId + " destroyed.");
                } else {
                    JOptionPane.showMessageDialog(null, "Process with ID " + destroyId + " not found.");
                }
            }
        });

        destroyPanel.add(destroyLabel);
        destroyPanel.add(destroyText);
        destroyPanel.add(destroyButton);
        destroyPanel.add(scrollPane);

        destroyFrame.add(destroyPanel);
        destroyFrame.setSize(600, 400);
        destroyFrame.setLocationRelativeTo(null);
        destroyFrame.setVisible(true);
    }

    public void suspendProcess() {
        JFrame suspendFrame = new JFrame("Suspend Process");
        JLabel suspendLabel = new JLabel("Enter Process ID to Suspend:");
        JTextField suspendText = new JTextField();
        JButton suspendButton = new JButton("Suspend");

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        tableModel = new DefaultTableModel(null, columnNames);
        processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel suspendPanel = new JPanel();
        suspendPanel.setLayout(null);

        suspendLabel.setBounds(20, 20, 200, 30);
        suspendText.setBounds(250, 20, 200, 30);
        suspendButton.setBounds(20, 60, 100, 30);
        scrollPane.setBounds(20, 100, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            if (p.status.equals("ready") || p.status.equals("running")) {
                tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
            }
            //tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
        }

        suspendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int suspendId = Integer.parseInt(suspendText.getText());
                boolean found = false;

                // Find and suspend the process with the specified ID
                for (Process p : processList) {
                    if (p.pid == suspendId) {
                        p.status = "suspended";
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // Update the table after process suspension
                    DefaultTableModel tableModel = (DefaultTableModel) processTable.getModel();
                    tableModel.setRowCount(0);
                    // Re-populate the table with existing processes
                    for (Process p : processList) {
                        if (p.status.equals("ready") || p.status.equals("running")) {
                            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Process with ID " + suspendId + " suspended.");
                } else {
                    JOptionPane.showMessageDialog(null, "Process with ID " + suspendId + " not found.");
                }
            }
        });

        suspendPanel.add(suspendLabel);
        suspendPanel.add(suspendText);
        suspendPanel.add(suspendButton);
        suspendPanel.add(scrollPane);

        suspendFrame.add(suspendPanel);
        suspendFrame.setSize(600, 400);
        suspendFrame.setLocationRelativeTo(null);
        suspendFrame.setVisible(true);
    }

    public void resumeProcess() {
        JFrame resumeFrame = new JFrame("Resume Process");
        JLabel resumeLabel = new JLabel("Enter Process ID to Resume:");
        JTextField resumeText = new JTextField();
        JButton resumeButton = new JButton("Resume");

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel resumePanel = new JPanel();
        resumePanel.setLayout(null);

        resumeLabel.setBounds(20, 20, 200, 30);
        resumeText.setBounds(250, 20, 200, 30);
        resumeButton.setBounds(20, 60, 100, 30);
        scrollPane.setBounds(20, 100, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            if (p.status.equals("suspended")) {
                tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
            }
        }

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resumeId = Integer.parseInt(resumeText.getText());
                boolean found = false;

                // Find and resume the suspended process with the specified ID
                for (Process p : processList) {
                    if (p.pid == resumeId && p.status.equals("suspended")) {
                        p.status = "ready";
                        found = true;
                        updateTable();
                        break;
                    }
                }

                if (found) {
                    // Update the table after process resumption
                    DefaultTableModel tableModel = (DefaultTableModel) processTable.getModel();
                    tableModel.setRowCount(0);
                    // Re-populate the table with existing processes
                    for (Process p : processList) {
                        if (p.status.equals("suspended")) {
                            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Process with ID " + resumeId + " resumed.");
                } else {
                    JOptionPane.showMessageDialog(null, "Process with ID " + resumeId + " not found or not suspended.");
                }
            }
        });

        resumePanel.add(resumeLabel);
        resumePanel.add(resumeText);
        resumePanel.add(resumeButton);
        resumePanel.add(scrollPane);

        resumeFrame.add(resumePanel);
        resumeFrame.setSize(600, 400);
        resumeFrame.setLocationRelativeTo(null);
        resumeFrame.setVisible(true);
    }

    public void Dispatch() {
        JFrame resumeFrame = new JFrame("Dispatch Process");
        JLabel resumeLabel = new JLabel("Enter Process ID to Dispatch:");
        JTextField resumeText = new JTextField();
        JButton resumeButton = new JButton("Dispatch");

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel resumePanel = new JPanel();
        resumePanel.setLayout(null);

        resumeLabel.setBounds(20, 20, 200, 30);
        resumeText.setBounds(250, 20, 200, 30);
        resumeButton.setBounds(20, 60, 100, 30);
        scrollPane.setBounds(20, 100, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            if (p.status.equals("ready") || p.status.equals("running")) {
                tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
            }
        }

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resumeId = Integer.parseInt(resumeText.getText());
                boolean found = false;

                // Find and resume the suspended process with the specified ID
                for (Process p : processList) {
                    if (p.pid == resumeId && p.status.equals("ready")) {
                        p.status = "running";
                        found = true;
                        updateTable();
                        break;
                    }
                }
                if (found) {
                    // Update the table after process resumption
                    DefaultTableModel tableModel = (DefaultTableModel) processTable.getModel();
                    tableModel.setRowCount(0);
                    // Re-populate the table with existing processes
                    for (Process p : processList) {
                        if (p.status.equals("ready") || p.status.equals("running")) {
                            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Process with ID " + resumeId + " running.");
                } else {
                    JOptionPane.showMessageDialog(null, "Process with ID " + resumeId + " not found or not run.");
                }
            }
        });
        resumePanel.add(resumeLabel);
        resumePanel.add(resumeText);
        resumePanel.add(resumeButton);
        resumePanel.add(scrollPane);

        resumeFrame.add(resumePanel);
        resumeFrame.setSize(600, 400);
        resumeFrame.setLocationRelativeTo(null);
        resumeFrame.setVisible(true);
    }

    public void blockProcess() {
        JFrame blockFrame = new JFrame("Block Process");
        JLabel blockLabel = new JLabel("Enter Process ID to Block:");
        JTextField blockText = new JTextField();
        JButton blockButton = new JButton("Block");

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel blockPanel = new JPanel();
        blockPanel.setLayout(null);

        blockLabel.setBounds(20, 20, 200, 30);
        blockText.setBounds(250, 20, 200, 30);
        blockButton.setBounds(20, 60, 100, 30);
        scrollPane.setBounds(20, 100, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            if (p.status.equals("ready") || p.status.equals("running")) {
                tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
            }
        }

        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int blockId = Integer.parseInt(blockText.getText());
                boolean found = false;

                // Find and block the process with the specified ID
                for (Process p : processList) {
                    if (p.pid == blockId) {
                        p.status = "blocked";
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // Update the table after process blocking
                    DefaultTableModel tableModel = (DefaultTableModel) processTable.getModel();
                    tableModel.setRowCount(0);
                    // Re-populate the table with existing processes
                    for (Process p : processList) {
                        if (p.status.equals("ready") || p.status.equals("running")) {
                            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Process with ID " + blockId + " blocked.");
                } else {
                    JOptionPane.showMessageDialog(null, "Process with ID " + blockId + " not found.");
                }
            }
        });
        blockPanel.add(blockLabel);
        blockPanel.add(blockText);
        blockPanel.add(blockButton);
        blockPanel.add(scrollPane);

        blockFrame.add(blockPanel);
        blockFrame.setSize(600, 400);
        blockFrame.setLocationRelativeTo(null);
        blockFrame.setVisible(true);
    }

    public void wakeupProcess() {
        JFrame wakeupFrame = new JFrame("Wakeup Process");
        JLabel wakeupLabel = new JLabel("Enter Process ID to Wakeup:");
        JTextField wakeupText = new JTextField();
        JButton wakeupButton = new JButton("Wakeup");

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel wakeupPanel = new JPanel();
        wakeupPanel.setLayout(null);

        wakeupLabel.setBounds(20, 20, 200, 30);
        wakeupText.setBounds(250, 20, 200, 30);
        wakeupButton.setBounds(20, 60, 100, 30);
        scrollPane.setBounds(20, 100, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            if (p.status.equals("blocked")) {
                tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
            }
        }
        wakeupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int wakeupId = Integer.parseInt(wakeupText.getText());
                boolean found = false;

                // Find and wakeup the blocked/suspended process with the specified ID
                for (Process p : processList) {
                    if (p.pid == wakeupId && (p.status.equals("blocked") || p.status.equals("suspended"))) {
                        p.status = "ready";
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // Update the table after process wakeup
                    DefaultTableModel tableModel = (DefaultTableModel) processTable.getModel();
                    tableModel.setRowCount(0);
                    // Re-populate the table with existing processes
                    for (Process p : processList) {
                        if (p.status.equals("blocked")) {
                            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Process with ID " + wakeupId + " woken up.");
                } else {
                    JOptionPane.showMessageDialog(null, "Process with ID " + wakeupId + " not found or not blocked/suspended.");
                }
            }
        });

        wakeupPanel.add(wakeupLabel);
        wakeupPanel.add(wakeupText);
        wakeupPanel.add(wakeupButton);
        wakeupPanel.add(scrollPane);

        wakeupFrame.add(wakeupPanel);
        wakeupFrame.setSize(600, 400);
        wakeupFrame.setLocationRelativeTo(null);
        wakeupFrame.setVisible(true);
    }

    public void changePriority() {
        JFrame changePriorityFrame = new JFrame("Change Priority");
        JLabel processIdLabel = new JLabel("Enter Process ID:");
        JTextField processIdText = new JTextField();
        JLabel priorityLabel = new JLabel("Enter New Priority:");
        JTextField priorityText = new JTextField();
        JButton changePriorityButton = new JButton("Change Priority");

        // Creating a table to display the processes
        String[] columnNames = {"PID", "Arrival Time", "Burst Time", "Status", "Priority"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
        processTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(processTable);

        JPanel changePriorityPanel = new JPanel();
        changePriorityPanel.setLayout(null);

        processIdLabel.setBounds(20, 20, 150, 30);
        processIdText.setBounds(200, 20, 150, 30);
        priorityLabel.setBounds(20, 60, 150, 30);
        priorityText.setBounds(200, 60, 150, 30);
        changePriorityButton.setBounds(20, 100, 150, 30);
        scrollPane.setBounds(20, 140, 500, 200);

        // Initialize the table with existing processes
        for (Process p : processList) {
            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
        }
        changePriorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int processId = Integer.parseInt(processIdText.getText());
                    int newPriority = Integer.parseInt(priorityText.getText());
                    boolean found = false;
                    // Find and change the priority of the process with the specified ID
                    for (Process p : processList) {
                        if (p.pid == processId) {
                            p.priority = newPriority;
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        // Update the table after changing priority
                        updateTable();
                        JOptionPane.showMessageDialog(null, "Priority of Process with ID " + processId + " changed to " + newPriority + ".");
                    } else {
                        JOptionPane.showMessageDialog(null, "Process with ID " + processId + " not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric values for Process ID.");
                }
            }
        });

        changePriorityPanel.add(processIdLabel);
        changePriorityPanel.add(processIdText);
        changePriorityPanel.add(priorityLabel);
        changePriorityPanel.add(priorityText);
        changePriorityPanel.add(changePriorityButton);
        changePriorityPanel.add(scrollPane);

        changePriorityFrame.add(changePriorityPanel);
        changePriorityFrame.setSize(600, 400);
        changePriorityFrame.setLocationRelativeTo(null);
        changePriorityFrame.setVisible(true);
    }

    // Methodto update the table with the current process list
    private void updateTable() {
        DefaultTableModel tableModel = (DefaultTableModel) processTable.getModel();
        tableModel.setRowCount(0);

        // Re-populate the table with existing processes
        for (Process p : processList) {
            tableModel.addRow(new Object[]{p.pid, p.at, p.bt, p.status, p.priority});
        }
    }

    private void showCommunicationPanel() {
        JFrame communicationFrame = new JFrame("Process Communication");
        JButton fcfsButton = new JButton("FCFS");
        JButton sjfButton = new JButton("Non-preemptive SJF");
        JButton priorityButton = new JButton("Priority Scheduling");
        JButton PreemptiveSJFS = new JButton("preemptive SJF");

        JPanel communicationPanel = new JPanel();
        communicationPanel.setLayout(null);

        fcfsButton.setBounds(10,50,200,50);
        sjfButton.setBounds(300,50,200,50);
        PreemptiveSJFS.setBounds(10,200,200,50);
        priorityButton.setBounds(300,200,200,50);

        fcfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int runningCount = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningCount++;
                    }
                }

                Process[] runningProcessesArray = new Process[runningCount];
                int index = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningProcessesArray[index++] = process;
                    }
                }

                calculateTimesFCFS(runningProcessesArray, runningCount);
               // Process[] processesArray = processList.toArray(new Process[0]);
                //calculateTimesFCFS(processesArray, processList.size());
            }
        });

        sjfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int runningCount = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningCount++;
                    }
                }

                Process[] runningProcessesArray = new Process[runningCount];
                int index = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningProcessesArray[index++] = process;
                    }
                }

                calculateTimesSJF(runningProcessesArray, runningCount);
                //calculateTimesSJF(processesArray, processList.size());
            }
        });

        priorityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int runningCount = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningCount++;
                    }
                }

                Process[] runningProcessesArray = new Process[runningCount];
                int index = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningProcessesArray[index++] = process;
                    }
                }

                priorityScheduling(runningProcessesArray, runningCount);
                //priorityScheduling(processesArray, processList.size());

            }
        });

        PreemptiveSJFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int runningCount = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningCount++;
                    }
                }

                Process[] runningProcessesArray = new Process[runningCount];
                int index = 0;
                for (Process process : processList) {
                    if ("running".equals(process.getStatus())) {
                        runningProcessesArray[index++] = process;
                    }
                }

                calculateTimesSJFs(runningProcessesArray, runningCount);
            }
        });

        communicationPanel.add(fcfsButton);
        communicationPanel.add(sjfButton);
        communicationPanel.add(priorityButton);
        communicationPanel.add(PreemptiveSJFS);

        communicationFrame.add(communicationPanel);
        communicationFrame.setSize(600, 450);
        communicationFrame.setLocationRelativeTo(null);
        communicationFrame.setVisible(true);
    }

    static void calculateTimesFCFS(Process[] processes, int n) {
        // Sort processes by arrival time if not already sorted
        Arrays.sort(processes, 0, n, Comparator.comparingInt(p -> p.at));

        processes[0].ct = processes[0].at + processes[0].bt;
        processes[0].tat = processes[0].ct - processes[0].at;
        processes[0].wt = processes[0].tat - processes[0].bt;

        for (int i = 1; i < n; i++) {
            if (processes[i].at > processes[i - 1].ct) {
                processes[i].ct = processes[i].at + processes[i].bt;
            } else {
                processes[i].ct = processes[i - 1].ct + processes[i].bt;
            }

            processes[i].tat = processes[i].ct - processes[i].at;
            processes[i].wt = processes[i].tat - processes[i].bt;
        }

        // Print the calculated times for each process
        //frame or jtable add krna ha

        JFrame frame= new JFrame();

        String[] column = {"ProcessID", "Arrival Time", "Burst Time", "Completion Time", "Turn Around Time", "Waiting Time"};
        DefaultTableModel model = new DefaultTableModel(null, column);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 50, 450, 350);

        // Clear existing rows and populate the table with calculated times for each process
        model.setRowCount(0);
        for (int i = 0; i < n; i++) {
            model.addRow(new Object[]{
                    processes[i].pid,
                    processes[i].at,
                    processes[i].bt,
                    processes[i].ct,
                    processes[i].tat,
                    processes[i].wt
            });
        }

        frame.add(scrollPane);
        frame.setSize(600,500);
        frame.setVisible(true);


    }

    static void calculateTimesSJF(Process[] processes, int n) {
        // Sort processes by arrival time if not already sorted
        Arrays.sort(processes, 0, n, Comparator.comparingInt(p -> p.at));

        boolean[] completed = new boolean[n];
        int currentTime = 0;
        int completedCount = 0;

        while (completedCount != n) {
            int shortest = -1;
            int shortestBurst = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!completed[i] && processes[i].at <= currentTime && processes[i].bt < shortestBurst) {
                    shortestBurst = processes[i].bt;
                    shortest = i;
                }
            }

            if (shortest == -1) {
                currentTime++;
            } else {
                processes[shortest].ct = currentTime + processes[shortest].bt;
                processes[shortest].tat = processes[shortest].ct - processes[shortest].at;
                processes[shortest].wt = processes[shortest].tat - processes[shortest].bt;

                completed[shortest] = true;
                currentTime = processes[shortest].ct;
                completedCount++;
            }
        }

        // Displaying results in a table (Swing GUI)
        JFrame frame = new JFrame();

        String[] column = {"ProcessID", "Arrival Time", "Burst Time", "Completion Time", "Turn Around Time", "Waiting Time"};
        DefaultTableModel model = new DefaultTableModel(null, column);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 50, 450, 350);

        model.setRowCount(0);
        for (int i = 0; i < n; i++) {
            model.addRow(new Object[]{
                    processes[i].pid,
                    processes[i].at,
                    processes[i].bt,
                    processes[i].ct,
                    processes[i].tat,
                    processes[i].wt
            });
        }

        frame.add(scrollPane);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    static void calculateTimesSJFs(Process[] processes, int n) {
        Arrays.sort(processes, 0, n, Comparator.comparingInt(p -> p.at));

        boolean[] completed = new boolean[n];
        int currentTime = 0;
        int completedCount = 0;

        while (completedCount != n) {
            int shortest = -1;
            int shortestBurst = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!completed[i] && processes[i].at <= currentTime && processes[i].remainingBt < shortestBurst) {
                    shortestBurst = processes[i].remainingBt;
                    shortest = i;
                }
            }

            if (shortest == -1) {
                currentTime++;
            } else {
                processes[shortest].remainingBt--;

                if (processes[shortest].remainingBt == 0) {
                    processes[shortest].ct = currentTime + 1;
                    processes[shortest].tat = processes[shortest].ct - processes[shortest].at;
                    processes[shortest].wt = processes[shortest].tat - processes[shortest].bt;

                    completed[shortest] = true;
                    completedCount++;
                }

                currentTime++;
            }
        }

        displayResultsInTable(processes, n);
    }

    static void displayResultsInTable(Process[] processes, int n) {
        JFrame frame = new JFrame();

        String[] column = {"ProcessID", "Arrival Time", "Burst Time", "Completion Time", "Turn Around Time", "Waiting Time"};
        DefaultTableModel model = new DefaultTableModel(null, column);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 50, 450, 350);

        model.setRowCount(0);
        for (int i = 0; i < n; i++) {
            model.addRow(new Object[]{
                    processes[i].pid,
                    processes[i].at,
                    processes[i].bt,
                    processes[i].ct,
                    processes[i].tat,
                    processes[i].wt
            });
        }

        frame.add(scrollPane);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }
    static void priorityScheduling(Process[] processes, int n) {
        // Sort processes by priority if not already sorted
        Arrays.sort(processes, 0, n, Comparator.comparingInt(p -> p.priority));

        processes[0].ct = processes[0].at + processes[0].bt;
        processes[0].tat = processes[0].ct - processes[0].at;
        processes[0].wt = processes[0].tat - processes[0].bt;

        for (int i = 1; i < n; i++) {
            if (processes[i].at > processes[i - 1].ct) {
                processes[i].ct = processes[i].at + processes[i].bt;
            } else {
                processes[i].ct = processes[i - 1].ct + processes[i].bt;
            }

            processes[i].tat = processes[i].ct - processes[i].at;
            processes[i].wt = processes[i].tat - processes[i].bt;
        }

        // Print the calculated times for each process
        //frame or jtable add krna ha

        JFrame frame= new JFrame();

        String[] column = {"ProcessID", "Arrival Time", "Burst Time", "Completion Time", "Turn Around Time", "Waiting Time"};
        DefaultTableModel model = new DefaultTableModel(null, column);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 50, 450, 350);

        // Clear existing rows and populate the table with calculated times for each process
        model.setRowCount(0);
        for (int i = 0; i < n; i++) {
            model.addRow(new Object[]{
                    processes[i].pid,
                    processes[i].at,
                    processes[i].bt,
                    processes[i].ct,
                    processes[i].tat,
                    processes[i].wt
            });
        }

        frame.add(scrollPane);
        frame.setSize(600,500);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new ControlPanel();
    }

    class Process {
        int pid;
        int at;
        int bt, ct, wt, tat;
        String status;

        // Additional PCB attributes
        int priority;
        int remainingBt;  // Add remainingBt attribute

        Process(int pid, int at, int bt, int pri) {
            this.pid = pid;
            this.at = at;
            this.bt = bt;
            this.status = "ready";
            this.priority = pri;
            this.remainingBt = bt;  // Initialize remainingBt with the original burst time
        }
        public String getStatus() {
            return status;
        }
    }
}