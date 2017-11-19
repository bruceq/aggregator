package com.aggregator.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScanComputer {
    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
//                System.out.println("文件");
//                System.out.println("path=" + file.getPath());
//                System.out.println("absolutepath=" + file.getAbsolutePath());
//                System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
//                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.getAbsolutePath().contains(".exe") &&
                            !readfile.getAbsolutePath().contains(".bat") &&
                            !readfile.getAbsolutePath().contains(".yml") &&
                            !readfile.getAbsolutePath().contains(".lock") &&
                            !readfile.getAbsolutePath().contains(".ckp") &&
                            !readfile.getAbsolutePath().contains(".jar") &&
                            !readfile.getAbsolutePath().contains(".policy") &&
                            !readfile.getAbsolutePath().contains(".properties") &&
                            !readfile.getAbsolutePath().contains(".log") &&
                            !readfile.getAbsolutePath().contains(".dic") &&
                            !readfile.getAbsolutePath().contains(".txt") &&
                            !readfile.getAbsolutePath().contains(".st") &&
                            !readfile.getAbsolutePath().contains(".tlog") &&
                            !readfile.getAbsolutePath().contains(".textile") &&
                            !readfile.getAbsolutePath().contains(".cfs") &&
                            !readfile.getAbsolutePath().contains(".si") &&
                            !readfile.getAbsolutePath().contains(".cfe") &&
                            !readfile.getAbsolutePath().contains(".dvm") &&
                            !readfile.getAbsolutePath().contains(".sh") &&
                            !readfile.getAbsolutePath().contains(".dii") &&
                            !readfile.getAbsolutePath().contains(".dim") &&
                            !readfile.getAbsolutePath().contains(".fdt") &&
                            !readfile.getAbsolutePath().contains(".fdx") &&
                            !readfile.getAbsolutePath().contains(".fnm") &&
                            !readfile.getAbsolutePath().contains(".nvd") &&
                            !readfile.getAbsolutePath().contains(".doc") &&
                            !readfile.getAbsolutePath().contains(".pos") &&
                            !readfile.getAbsolutePath().contains(".tim") &&
                            !readfile.getAbsolutePath().contains(".tip") &&
                            !readfile.getAbsolutePath().contains(".dvd")&&
                            !readfile.getAbsolutePath().contains(".nvm")&&
                            !readfile.getAbsolutePath().contains(".liv")) {
                        if (!readfile.isDirectory()) {
//                        System.out.println("path=" + readfile.getPath());
                            System.out.println("absolutepath="
                                    + readfile.getAbsolutePath());
//                        System.out.println("name=" + readfile.getName());

                        } else if (readfile.isDirectory()) {
                            readfile(filepath + "\\" + filelist[i]);
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        String filepath = "D:\\elasticsearch-5.0.0";
        readfile(filepath);
    }
}
