package Assignment;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class StudentInformationPanel extends Panel {

    static ArrayList<Student> listOfStudentsInfo = new ArrayList<>();

    TextField firstNameField, lastNameField, ageField, addressField;
    TextArea noteField;
    Choice courseField;
    CheckboxGroup checkboxGroup;
    Checkbox maleCheckbox;
    Checkbox femaleCheckbox;
    Integer selectedId;

    public StudentInformationPanel(MainFrame mainFrame) {
        Color fontColor = new Color(41, 50, 80);
        Color headColor = new Color(109, 212, 126);

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        setPreferredSize(new Dimension(550, 700));

        Panel formPanel = new Panel();
        formPanel.setLayout(new GridBagLayout());
        Label studentInfoLabel = new Label("Student Information");
        studentInfoLabel.setFont(new Font("Garamond", Font.BOLD, 25));
        studentInfoLabel.setForeground(fontColor);
        Label firstNameLabel = new Label("First Name");
        firstNameLabel.setForeground(fontColor);
        Label lastNameLabel = new Label("Last Name");
        lastNameLabel.setForeground(fontColor);
        Label ageLabel = new Label("Age");
        ageLabel.setForeground(fontColor);
        Label genderLabel = new Label("Gender");
        genderLabel.setForeground(fontColor);
        Label addressLabel = new Label("Address");
        addressLabel.setForeground(fontColor);
        Label courseLabel = new Label("Course");
        courseLabel.setForeground(fontColor);
        Label noteLabel = new Label("Note");
        noteLabel.setForeground(fontColor);

        firstNameField = new TextField(20);
        lastNameField = new TextField(20);
        ageField = new TextField(3);
        addressField = new TextField(20);
        noteField = new TextArea(5, 10);

        courseField = new Choice();
        courseField.add("Bachelor of Science in Information Technology (BSIT)");
        courseField.add("Bachelor of Science in Computer Science (BSCS)");
        courseField.add("Bachelor of Science in Information Systems (BSIS)");
        courseField.add("Bachelor of Science in Mathematics (BS Mathematics)");
        courseField.add("Bachelor of Science in Applied Mathematics (BS Applied Math)");
        courseField.add("Bachelor of Science in Statistics (BS Stat)");

        checkboxGroup = new CheckboxGroup();
        maleCheckbox = new Checkbox("Male", checkboxGroup, false);
        femaleCheckbox = new Checkbox("Female", checkboxGroup, false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15, 5, 5, 0);
        formPanel.add(firstNameLabel, gbc);
        gbc.gridx++;
        formPanel.add(firstNameField, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(lastNameLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        formPanel.add(lastNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(ageLabel, gbc);
        gbc.gridx++;
        gbc.gridwidth = 3;
        formPanel.add(ageField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        formPanel.add(genderLabel, gbc);
        gbc.gridx++;
        formPanel.add(maleCheckbox, gbc);
        gbc.gridx++;
        formPanel.add(femaleCheckbox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(addressLabel, gbc);
        gbc.gridx++;
        gbc.gridwidth = 3;
        formPanel.add(addressField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        formPanel.add(courseLabel, gbc);
        gbc.gridx++;
        gbc.gridwidth = 3;
        formPanel.add(courseField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        formPanel.add(noteLabel, gbc);
        gbc.gridx++;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        formPanel.add(noteField, gbc);

        Panel actionsBtnPanel = new Panel();
        actionsBtnPanel.setLayout(new GridLayout(1, 4, 10, 0));

        Button addButton = new Button("Add");
        addButton.setBackground(headColor);
        addButton.setForeground(fontColor);
        addButton.setFont(new Font("Garamond", Font.BOLD, 12));
        addButton.setPreferredSize(new Dimension(0, 35));

        Button updateButton = new Button("Update");
        updateButton.setBackground(Color.ORANGE);
        updateButton.setForeground(fontColor);
        updateButton.setFont(new Font("Garamond", Font.BOLD, 12));
        updateButton.setPreferredSize(new Dimension(0, 35));

        Button deleteButton = new Button("Delete");
        deleteButton.setBackground(new Color(250, 52, 52));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Garamond", Font.BOLD, 12));
        deleteButton.setPreferredSize(new Dimension(0, 35));

        Button clearButton = new Button("Clear");
        clearButton.setBackground(Color.LIGHT_GRAY);
        clearButton.setForeground(fontColor);
        clearButton.setFont(new Font("Garamond", Font.BOLD, 12));
        clearButton.setPreferredSize(new Dimension(0, 35));

        Dimension buttonSize = new Dimension(115, 28);
        addButton.setPreferredSize(buttonSize);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formFields(mainFrame, "Add");
            }
        });

        updateButton.setPreferredSize(buttonSize);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formFields(mainFrame, "Update");
            }
        });

        deleteButton.setPreferredSize(buttonSize);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(listOfStudentsInfo);
                String selectedStudent = ListOfStudentPanel.listOfStudentsName.getSelectedItem();
                String msg = "Do you want to delete " + selectedStudent;

                if (selectedStudent != null) {
                    int isConfirm = JOptionPane.showConfirmDialog(mainFrame, msg, "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (isConfirm == 0) {
                        ListOfStudentPanel.listOfStudentsName.remove(selectedStudent);
                        for (Student student : listOfStudentsInfo) {
                            if (selectedStudent.equals("Id: " + student.getId() + " Name: " + student.getFirstName() + " " + student.getLastName())) {
                                listOfStudentsInfo.remove(student);
                                break;
                            }
                        }
                        resetFields();
                    }
                    return;
                }
                JOptionPane.showMessageDialog(mainFrame, "Select the student you want to delete.", "Delete Error", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        ListOfStudentPanel.listOfStudentsName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedItem = ListOfStudentPanel.listOfStudentsName.getSelectedItem();

                for (Student student : listOfStudentsInfo) {
                    if (selectedItem != null && selectedItem.equals("Id: " + student.getId() + " Name: " + student.getFirstName() + " " + student.getLastName())) {
                        selectedItem = null;
                        selectedId = student.getId();
                        firstNameField.setText(student.getFirstName());
                        lastNameField.setText(student.getLastName());
                        ageField.setText(student.getAge() == null ? "" : String.valueOf(student.getAge()));
                        addressField.setText(student.getAddress());
                        noteField.setText(student.getNote());
                        courseField.select(student.getCourse());
                        if (student.getGender().equals("Male")) {
                            maleCheckbox.setState(true);
                            return;
                        }
                        femaleCheckbox.setState(true);
                    }
                }
            }
        });

        clearButton.setPreferredSize(buttonSize);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        actionsBtnPanel.add(addButton);
        actionsBtnPanel.add(updateButton);
        actionsBtnPanel.add(deleteButton);
        actionsBtnPanel.add(clearButton);

        add(studentInfoLabel);
        add(formPanel);
        add(actionsBtnPanel);
    }

    public void formFields(MainFrame mainFrame, String btn) {
        String fname = firstNameField.getText();
        String lname = lastNameField.getText();
        String address = addressField.getText();
        String note = noteField.getText();
        String course = courseField.getSelectedItem();
        Integer age = null;

        if (btn.equals("Update")) {
            if (selectedId == null) {
                JOptionPane.showMessageDialog(mainFrame, "Select the student you want to Update.", "Update Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        try {
            if (fname.isEmpty() && lname.isEmpty()) {
                JOptionPane.showMessageDialog(mainFrame, "Give your first name and last name.", "Form Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!ageField.getText().isEmpty()) {
                age = Integer.valueOf(ageField.getText());
            }

            if (checkboxGroup.getSelectedCheckbox() == null) {
                JOptionPane.showMessageDialog(mainFrame, "Gender not specified.", "Form Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String gender = checkboxGroup.getSelectedCheckbox().getLabel();

            if (btn.equals("Add")) {
                int randomId = createUniqueId();

                Student newStudent = new Student(randomId, fname, lname, age, gender, address, note, course);

                listOfStudentsInfo.add(newStudent);
                ListOfStudentPanel.listOfStudentsName.add("Id: " + randomId + " Name: " + newStudent.getFirstName() + " " + newStudent.getLastName());
                JOptionPane.showMessageDialog(mainFrame, "Student added Successfully.", "Add Success", JOptionPane.INFORMATION_MESSAGE);
                selectedId = null;
            } else if (btn.equals("Update")) {
                for (Student student : listOfStudentsInfo) {
                    if (student.getId() == selectedId) {
                        student.setFirstName(fname);
                        student.setLastName(lname);
                        student.setAge(age);
                        student.setGender(gender);
                        student.setAddress(address);
                        student.setCourse(course);
                        student.setNote(note);
                        break;
                    }
                }
                int selectedItem = ListOfStudentPanel.listOfStudentsName.getSelectedIndex();
                ListOfStudentPanel.listOfStudentsName.replaceItem("Id: " + selectedId + " Name: " + fname + " " + lname, selectedItem);
                JOptionPane.showMessageDialog(mainFrame, "Student Update Successfully.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
                selectedId = null;
            }
            resetFields();

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(mainFrame, "Invalid age information provided.", "Form Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int createUniqueId() {
        int randomId;
        Random random = new Random();
        while (true) {
            boolean isUniqueId = true;
            randomId = random.nextInt(100000) + 1000;
            for (Student student : listOfStudentsInfo) {
                if (student.getId() == randomId) {
                    isUniqueId = false;
                    break;
                }
            }
            if (isUniqueId) {
                break;
            }
        }
        return randomId;
    }

    public void resetFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        addressField.setText("");
        noteField.setText("");
        courseField.select(0);
        checkboxGroup.setSelectedCheckbox(null);
        selectedId = null;
        ListOfStudentPanel.listOfStudentsName.deselect(ListOfStudentPanel.listOfStudentsName.getSelectedIndex());
    }
}
