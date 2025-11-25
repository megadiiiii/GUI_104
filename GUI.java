package com.mycompany.w3.hw;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame {
    private ArrayList<Student> list = new ArrayList<>();
    private DefaultTableModel model;

    public GUI() {
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setAlwaysOnTop(true);
        setLocationRelativeTo(null);

        JLabel lblName = new JLabel("Họ và tên:");
        JTextField txtName = new JTextField();

        JLabel lblDob = new JLabel("Ngày sinh:");
        JTextField txtDob = new JTextField();

        JLabel lblAddress = new JLabel("Địa chỉ:");
        JTextField txtAddress = new JTextField();

        JLabel lblGender = new JLabel("Giới tính:");
        JRadioButton rdoMale = new JRadioButton("Nam");
        JRadioButton rdoFemale = new JRadioButton("Nữ");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdoMale);
        genderGroup.add(rdoFemale);

        JLabel lblId = new JLabel("MSSV:");
        JTextField txtId = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnImport = new JButton("Import");

        //Form
        JPanel formPanel = new JPanel();
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        formLayout.setAutoCreateContainerGaps(true);
        formLayout.setAutoCreateGaps(true);

        formLayout.setHorizontalGroup(
                formLayout.createSequentialGroup()
                        .addGap(20)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(lblName)
                                .addComponent(lblDob)
                                .addComponent(lblAddress)
                                .addComponent(lblGender)
                                .addComponent(lblId)
                                .addComponent(lblEmail)
                        )
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(txtName)
                                .addComponent(txtDob)
                                .addComponent(txtAddress)
                                .addGroup(formLayout.createSequentialGroup()  // Add cả 2 radio theo chiều ngang
                                        .addComponent(rdoMale)
                                        .addComponent(rdoFemale)
                                )
                                .addComponent(txtId)
                                .addComponent(txtEmail)
                                .addComponent(btnImport, GroupLayout.Alignment.CENTER)
                        )
                        .addGap(20)
        );


        formLayout.setVerticalGroup(
                formLayout.createSequentialGroup()
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblName)
                                .addComponent(txtName)
                        )
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblDob)
                                .addComponent(txtDob)
                        )
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblAddress)
                                .addComponent(txtAddress)
                        )
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblGender)
                                .addComponent(rdoMale)
                                .addComponent(rdoFemale)
                        )
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblId)
                                .addComponent(txtId)
                        )
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblEmail)
                                .addComponent(txtEmail)
                        )
                        .addComponent(btnImport)
                        .addGap(20)
        );

        //Table
        String[] header = {"Họ và tên", "Ngày sinh", "Địa chỉ", "Giới tính", "MSSV", "Email"};
        model = new DefaultTableModel(header, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30),  // Padding
                scrollPane.getBorder()
        ));

        btnImport.addActionListener(e -> importFile());

        this.setLayout(new BorderLayout());
        this.add(formPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }

    public void importFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("DAT Files", "dat"));
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                reader(file);
                JOptionPane.showMessageDialog(null, "Import complete");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

    }

    public void reader(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split("\\$");
            if (data.length == 6) {
                String name = data[0].trim();
                String dob = data[1].trim();
                String address = data[2].trim();
                String gender = data[3].trim();
                String id = data[4].trim();
                String email = data[5].trim();

                Student st = new Student(name, dob, address, gender, id, email);
                list.add(st);

                model.addRow(new Object[]{name, dob, address, gender, id, email});
            }

        }
    }
}

