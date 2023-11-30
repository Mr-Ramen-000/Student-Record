package Assignment;

import static Assignment.StudentInformationPanel.listOfStudentsInfo;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class MainFrame extends Frame {

    public MainFrame() {
        super("Student Record");

        String username = "Admin";
        String password = "Admin";

        Color fontColor = new Color(41, 50, 80);
        Color backgroundColor = new Color(255, 213, 90);
        Color headColor = new Color(109, 212, 126);

        setLayout(new BorderLayout());
        setSize(500, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(backgroundColor);

        Panel loginPanel = new Panel();
        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Label loginLabel = new Label("LOGIN");
        loginLabel.setFont(new Font("Garamond", Font.BOLD, 25));
        loginLabel.setForeground(fontColor);

        Label usernameLabel = new Label("Username");
        usernameLabel.setForeground(fontColor);
        Label passLabel = new Label("Password");
        passLabel.setForeground(fontColor);

        TextField usernameField = new TextField(30);
        TextField passField = new TextField(30);
        passField.setEchoChar('\u2022');

        Checkbox showCheckbox = new Checkbox("Show Password", false);
        Button loginBtn = new Button("Submit");
        loginBtn.setBackground(headColor);
        loginBtn.setForeground(fontColor);
        loginBtn.setFont(new Font("Garamond", Font.BOLD, 12));
        loginBtn.setPreferredSize(new Dimension(0, 35));

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(loginLabel, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 0, 0, 0);
        loginPanel.add(usernameLabel, gbc);
        gbc.gridy++;
        loginPanel.add(usernameField, gbc);
        gbc.gridy++;
        loginPanel.add(passLabel, gbc);
        gbc.gridy++;
        loginPanel.add(passField, gbc);
        gbc.gridy++;
        loginPanel.add(showCheckbox, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(5, 0, 50, 0);
        loginPanel.add(loginBtn, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String msg = "Are you sure do you want to exit?";

                int isConfirm = JOptionPane.showConfirmDialog(MainFrame.this, msg, "Confirmation", JOptionPane.YES_NO_OPTION);

                if (isConfirm == 0) {
                    System.exit(0);
                }
            }
        });

        showCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (showCheckbox.getState()) {
                    passField.setEchoChar((char) 0);
                    return;
                }
                passField.setEchoChar('\u2022');
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().equals(username) && passField.getText().equals(password)) {
                    removeAll();
                    setSize(1100, 700);
                    setLocationRelativeTo(null);

                    MenuBar menuBar = new MenuBar();

                    Menu fileMenu = new Menu("File");
                    MenuItem openfile = new MenuItem("Open");
                    openfile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            openCSVFileDialog();
                        }
                    });
                    MenuItem savefile = new MenuItem("Save");
                    savefile.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            saveCSVFileDialog();
                        }
                    });
                    MenuItem resetItem = new MenuItem("Reset");
                    resetItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String msg = "Do you want to reset application?";

                            int isConfirm = JOptionPane.showConfirmDialog(MainFrame.this, msg, "Confirmation", JOptionPane.YES_NO_OPTION);

                            if (isConfirm == 0) {
                                listOfStudentsInfo.clear();
                                ListOfStudentPanel.listOfStudentsName.removeAll();
                            }
                        }
                    });

                    MenuItem exitItem = new MenuItem("Exit");
                    exitItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String msg = "Are you sure do you want to exit?";

                            int isConfirm = JOptionPane.showConfirmDialog(MainFrame.this, msg, "Confirmation", JOptionPane.YES_NO_OPTION);

                            if (isConfirm == 0) {
                                System.exit(0);
                            }
                        }
                    });

                    fileMenu.add(openfile);
                    fileMenu.add(savefile);
                    fileMenu.add(resetItem);
                    fileMenu.add(exitItem);

                    menuBar.add(fileMenu);
                    setMenuBar(menuBar);

                    Panel headPanel = new Panel();
                    headPanel.setLayout(new GridBagLayout());
                    headPanel.setPreferredSize(new Dimension(1100, 70));
                    headPanel.setBackground(headColor);

                    Label headerLabel = new Label("STUDENT RECORD");
                    headerLabel.setFont(new Font("Garamond", Font.BOLD, 25));
                    headerLabel.setForeground(fontColor);

                    GridBagConstraints headerGbc = new GridBagConstraints();
                    headerGbc.gridx = 0;
                    headerGbc.gridy = 0;
                    headPanel.add(headerLabel, headerGbc);

                    Panel mainPanel = new Panel();
                    mainPanel.setLayout(new BorderLayout());
                    mainPanel.add(new StudentInformationPanel(MainFrame.this), BorderLayout.WEST);
                    mainPanel.add(new ListOfStudentPanel(), BorderLayout.EAST);

                    add(headPanel, BorderLayout.NORTH);
                    add(mainPanel, BorderLayout.CENTER);
                    return;
                }
                JOptionPane.showMessageDialog(MainFrame.this, "Username and password not match!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(loginPanel);
    }

    public void openCSVFileDialog() {
        FileDialog fileDialog = new FileDialog(this, "Open CSV File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String file = fileDialog.getFile();

        if (directory != null && file != null) {
            String filePath = directory + file;

            if (file.toLowerCase().endsWith(".csv")) {
                String msg = "Loading new data will overwrite existing data. Proceed?";

                int isConfirm = JOptionPane.showConfirmDialog(this, msg, "Confirmation", JOptionPane.YES_NO_OPTION);

                if (isConfirm == 0) {
                    readCSVFile(filePath);
                    JOptionPane.showMessageDialog(this, "File loaded Successfull.", "Load File", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Unsupported file format.", "Load File", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    public void saveCSVFileDialog() {
        FileDialog fileDialog = new FileDialog(this, "Save As", FileDialog.SAVE);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String file = fileDialog.getFile();

        if (directory != null && file != null) {
            if (!file.toLowerCase().endsWith(".csv")) {
                file += ".csv";
            }
            String saveLocation = directory + file;
            writeCSVFile(saveLocation);
            System.out.println("Save Location: " + saveLocation);
        } else {
            System.out.println("Save canceled.");
        }
    }

    public void readCSVFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] fields = line.split(",");

                int id = Integer.parseInt(fields[0]);
                String firstName = fields[1].strip();
                String lastName = fields[2].strip();
                Integer age = fields[3].strip().equals("null") ? null : Integer.valueOf(fields[3].strip());
                String gender = fields[4].strip();
                String address = fields[5].strip();
                String course = fields[6].strip();
                String note = fields[7].strip().replaceAll("~", "\n");

                Student newStudent = new Student(id, firstName, lastName, age, gender, address, note, course);
                listOfStudentsInfo.add(newStudent);
                ListOfStudentPanel.listOfStudentsName.add("Id: " + id + " Name: " + firstName + " " + lastName);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Unsupported file format.", "Load File", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void writeCSVFile(String filePath) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);

            PrintWriter writer = new PrintWriter(fileWriter);

            writer.println("Id, First Name, Last Name, Age, Gender, Address, Course, Note");

            for (Student student : StudentInformationPanel.listOfStudentsInfo) {
                String note = student.getNote().replace("\n", "~").replace("\r", "~");
                writer.printf("%s, %s, %s, %s, %s, %s, %s, %s\n",
                        student.getId(), student.getFirstName(), student.getLastName(), student.getAge(), student.getGender(), student.getAddress(), student.getCourse(), note);

            }

            JOptionPane.showMessageDialog(this, "File Save Successfull.", "Save File", JOptionPane.INFORMATION_MESSAGE);
            fileWriter.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "File Save Unsuccessful.", "Save File", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        MainFrame app = new MainFrame();
        app.setVisible(true);
    }
}
