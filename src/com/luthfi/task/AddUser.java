package com.luthfi.task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddUser extends JDialog {

    private JPanel AddPanel;
    private JLabel textLabel1;
    private JLabel textLabel2;
    private JLabel textLabel3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private User addButton;
    private JButton saveButton;
    private JButton backButton;


    public static void main(String[] args){
        JFrame frame=new JFrame();
        frame.setContentPane(new AddUser().AddPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 300,300);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.show();

    }

    PreparedStatement pst;
    static Connection con;
    public AddUser() {
        connect();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname, lname, age;
                fname = textField1.getText();
                lname = textField2.getText();
                age = textField3.getText();

                try {
                    pst = con.prepareStatement("insert into user(fname, lname, age)values(?,?,?)");
                    pst.setString(1, fname);
                    pst.setString(2, lname);
                    pst.setString(3, age);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record added");

                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField1.requestFocus();
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void dispose(){
        super.dispose();
    }

    public int doModal(){
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setVisible(true);
        return 0;
    }

    public void createGUI(){
        setPreferredSize(new Dimension(400, 600));
        setTitle(getClass().getSimpleName());
        pack();
        setLocationRelativeTo(getParent());
    }

    public static void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/quiz2", "root", "");
            System.out.println("Success");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
