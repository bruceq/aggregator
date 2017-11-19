package com.aggregator.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CopyFile {
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JFileChooser chooser;
    private String readPath;
    private String writePath;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CopyFile window = new CopyFile();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public CopyFile() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 545, 277);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel label = new JLabel("\u6587\u4EF6\uFF1A");
        label.setFont(new Font("黑体", Font.BOLD, 18));
        label.setBounds(26, 68, 57, 25);
        frame.getContentPane().add(label);

        JLabel lblNewLabel = new JLabel("\u4FDD\u5B58\u76EE\u5F55\uFF1A");
        lblNewLabel.setFont(new Font("黑体", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 119, 95, 25);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(105, 68, 299, 25);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(105, 121, 299, 25);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// 设置选择模式，既可以选择文件又可以选择文件夹

        JButton button = new JButton("\u6253\u5F00");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = chooser.showOpenDialog(null);
                chooser.setDialogType(JFileChooser.OPEN_DIALOG);
                chooser.setMultiSelectionEnabled(false);
                chooser.setAcceptAllFileFilterUsed(false);
                if (index == JFileChooser.APPROVE_OPTION) {
                    // 把获取到的文件的绝对路径显示在文本编辑框中
                    textField.setText(chooser.getSelectedFile()
                            .getAbsolutePath());
                    readPath = textField.getText();
                }
            }
        });
        button.setFont(new Font("黑体", Font.BOLD, 18));
        button.setBounds(432, 67, 87, 26);
        frame.getContentPane().add(button);

        JButton button_1 = new JButton("\u6D4F\u89C8");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = chooser.showSaveDialog(null);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setDialogType(JFileChooser.SAVE_DIALOG);
                chooser.setMultiSelectionEnabled(false);
                chooser.setAcceptAllFileFilterUsed(false);
                if (index == JFileChooser.APPROVE_OPTION) {
                    // 把获取到的文件的绝对路径显示在文本编辑框中
                    textField_1.setText(chooser.getSelectedFile()
                            .getAbsolutePath());
                    writePath = textField_1.getText() + "\\";
                }
            }
        });
        button_1.setFont(new Font("黑体", Font.BOLD, 18));
        button_1.setBounds(432, 118, 87, 26);
        frame.getContentPane().add(button_1);

        JButton button_2 = new JButton("\u53E6\u5B58\u4E3A");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                readPath = textField.getText();
                writePath = textField_1.getText() + "\\";
                if (copyFile(readPath, writePath) == -1) {//原文件不存在
                    JOptionPane.showMessageDialog(null, "源文件不存在", "警告", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button_2.setForeground(Color.RED);
        button_2.setFont(new Font("黑体", Font.BOLD, 18));
        button_2.setBounds(213, 180, 93, 34);
        frame.getContentPane().add(button_2);
    }

    /*
     * *
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     *
     * @param newPath String 复制后路径 如：f:/fgf.txt
     *
     * @return int 0表示成功，-1表示原文件不存在,-2表示未知错误。
     */
    public int copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                System.out.println(newPath);
                if (isExist(newPath)) {
                    FileOutputStream fs = new FileOutputStream(newPath);
                    byte[] buffer = new byte[1444];
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum += byteread; // 字节数 文件大小
                        System.out.println(bytesum);
                        fs.write(buffer, 0, byteread);
                    }
                    inStream.close();
                    fs.close();
                    return 0;
                } else {
                    return -2;
                }
            }
            return -1;
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
            return -2;
        }
    }

    public static boolean isExist(String filePath) {
        String paths[] = filePath.split("\\\\");
        String dir = paths[0];
        for (int i = 0; i < paths.length - 2; i++) {// 注意此处循环的长度
            try {
                dir = dir + "/" + paths[i + 1];
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                    System.out.println("创建目录为：" + dir);
                }
            } catch (Exception err) {
                System.err.println("ELS - Chart : 文件夹创建发生异常");
            }
        }
        File fp = new File(filePath);
        if (!fp.exists()) {
            return true; // 文件不存在，执行下载功能
        } else {
            return false; // 文件存在不做处理
        }
    }
}
