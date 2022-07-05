package com.luthfi.task;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class User extends JFrame {
    private JPanel MainPanel;
    private JLabel MainHeader;
    private JTable table1;
    private JButton viewButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton addButton;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("User");
        jFrame.setContentPane(new User().MainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setBounds(100, 100, 600, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);
    }
    public User() {
        connect();
        table_load();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    AddUser addUser = new AddUser();
                    addUser.setVisible(true);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
    static Connection con;
    PreparedStatement pst;

    public static void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/quiz2", "root", "");
            System.out.println("Success");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void table_load(){
        try {
            pst = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

