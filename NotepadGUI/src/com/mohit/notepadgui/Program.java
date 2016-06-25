/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohit.notepadgui;

import com.mohit.notepadgui.support.FileSupport;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Mohit
 */
public class Program extends JFrame {

    JMenuBar menuBar;
    JMenu file, help;
    JMenuItem newFile, openFile, clear, saveAs, exit, about;
    JTextArea txtArea;
    JFileChooser fileChooser;
    JScrollPane scrollPane;

    public Program() {

        initComponents();

    }

    public void initComponents() {
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        file = new JMenu("File");
        help = new JMenu("Help");
        menuBar.add(file);
        menuBar.add(help);
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File...");
        clear = new JMenuItem("Clear Text");
        saveAs = new JMenuItem("Save File As...");
        exit = new JMenuItem("Exit");
        about = new JMenuItem("About");
        txtArea = new JTextArea(40, 40);

        scrollPane = new JScrollPane(txtArea);

        file.add(newFile);
        file.add(openFile);
        file.add(clear);
        file.add(saveAs);
        file.add(exit);
        help.add(about);

        exit.addActionListener(new Exit());
        about.addActionListener(new About());
        clear.addActionListener(new Clear());

        saveAs.addActionListener(new SaveAs());
        openFile.addActionListener(new OpenFile());
        newFile.addActionListener(new NewFile());

        setTitle("Notepad");
        setSize(500, 500);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        Container container = getContentPane();
        container.add(scrollPane);
        setVisible(true);

    }

    public static void main(String[] args) {
        Program program = new Program();
    }

    public class Exit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int select = JOptionPane.showConfirmDialog(null, "Do you really want to exit?");
            if (select == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

    }

    public class About implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "Version: 1.1" + "\t\n" + "Author: Mohit Paudel");
        }

    }

    public class Clear implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtArea.setText("");
        }

    }

    public class NewFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
             fileChooser = new JFileChooser();
               
            if (!txtArea.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "You need to save the file before opening another one");
                      int select = fileChooser.showSaveDialog(saveAs);
                      if (select == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileSupport.fileWriter(fileChooser.getSelectedFile().getPath(), txtArea.getText());

                    } catch (IOException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                }
                }
                else
                {
                   int dialog=JOptionPane.showConfirmDialog(null, "Do you want to save an empty file??");
                        if(dialog==JOptionPane.YES_OPTION)
                        {
                             JOptionPane.showMessageDialog(null, "Choose the file you want to save in");
                             int select = fileChooser.showSaveDialog(saveAs);
                      if (select == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileSupport.fileWriter(fileChooser.getSelectedFile().getPath(), txtArea.getText());

                    } catch (IOException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                }
                
                        }
                    
                }
                
        }

    }

    public class SaveAs implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (txtArea.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Sorry.You cannot save an empty file in my notepad");
            } else {
                fileChooser = new JFileChooser();
                int select = fileChooser.showSaveDialog(saveAs);
                if (select == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileSupport.fileWriter(fileChooser.getSelectedFile().getPath(), txtArea.getText());

                    } catch (IOException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                }
            }
        }

    }

    public class OpenFile implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            fileChooser = new JFileChooser();
            int select = fileChooser.showOpenDialog(openFile);
            if (select == JFileChooser.APPROVE_OPTION) {
                try {
                    txtArea.setText(FileSupport.fileReader(fileChooser.getSelectedFile().getPath()));

                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }
        }

    }

}
