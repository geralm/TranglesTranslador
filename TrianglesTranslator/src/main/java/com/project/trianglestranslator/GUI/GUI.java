package com.project.trianglestranslator.GUI;



import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import com.project.trianglestranslator.Configurations.Configurations;
import com.project.trianglestranslator.Files.FileManagement;
import com.project.trianglestranslator.Key.TextCode;

;
public class GUI extends JFrame {
    private JPanel jpanelTrianglesEncoder;
    private JTextArea textArea;
    private JSpinner spinner1;
    private JCheckBox wordWrapCheckBox;
    private JCheckBox lineBreaks;
    private JButton buttonClose;
    private JButton buttonOpenTxt;
    private JButton buttonSavePdf;
    private JPanel mainPanel;
    private JPanel trianglesText;

    public GUI(){
        this.setSize(1500, 700);
        init_components();
        setContentPane(mainPanel);
        setTitle("Triangles encoder");
        setLocationRelativeTo(null);


    }
    private void init_components(){
        textArea.setPreferredSize(new Dimension(750, 650));
        jpanelTrianglesEncoder.setPreferredSize(new Dimension(750, 650));
        trianglesText = new TextPane(jpanelTrianglesEncoder);
        jpanelTrianglesEncoder.setLayout(new BorderLayout());
        jpanelTrianglesEncoder.add(trianglesText, BorderLayout.CENTER);
        jpanelTrianglesEncoder.setBackground(Color.WHITE);
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonOpenTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File searchedFile = searchFile();
                if(searchedFile !=null) {
                    try {
                        String fileText = FileManagement.getInstance().readTextFile(searchedFile.getAbsolutePath());
                        textArea.setText(fileText);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Cannot read the file, please try again");
                    }
                }
            }
        });
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                TextCode.getInstance().parseText(textArea.getText());
                jpanelTrianglesEncoder.repaint();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                TextCode.getInstance().parseText(textArea.getText());
                jpanelTrianglesEncoder.repaint();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                TextCode.getInstance().parseText(textArea.getText());
                jpanelTrianglesEncoder.repaint();
            }
        });

        lineBreaks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configurations.getInstance().setJumpLines(lineBreaks.isSelected());
            }
        });
        wordWrapCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Configurations.getInstance().setVerifyWordSpace(wordWrapCheckBox.isSelected());
            }
        });

        buttonSavePdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int option = fileChooser.showSaveDialog(GUI.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFolder = fileChooser.getSelectedFile();
                    String folderPath = selectedFolder.getAbsolutePath();

                    // Llama a la función convertToPdf con la ruta de la carpeta seleccionada
                    try {
                        FileManagement.getInstance().convertToPdf(folderPath);
                        JOptionPane.showMessageDialog(null,"PDF saved sucessfully");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"Error to save pdf");
                    }
                }

            }
        });

    }

    //*
    // File chooser for select text
    // *

    private File searchFile() throws NullPointerException{
        File searchedFile = null;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
        int selection = chooser.showOpenDialog(this);
        if(selection == JFileChooser.APPROVE_OPTION){
            searchedFile = chooser.getSelectedFile();
        }
        return searchedFile;
    }




}
