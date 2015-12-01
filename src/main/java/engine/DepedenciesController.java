/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author tegarnization
 */
public class DepedenciesController {

    public DepedenciesController() throws IOException {
        assetsDepedenciesResolving();
        indexHtmlCopy();
    }

    public void assetsDepedenciesResolving() {
        File srcFolder = new File(System.getProperty("user.dir")+"/assets");
        File destFolder = new File(System.getProperty("user.dir") + "/result");

        //make sure source exists
        if (!srcFolder.exists()) {

            System.err.println("Directory does not exist.");
            //just exit
            System.exit(0);

        } else {

            try {
                copyFolder(srcFolder, destFolder);
            } catch (IOException e) {
                e.printStackTrace();
                //error, just exit
                System.exit(0);
            }
        }

        System.out.println("Done");
    }

    public void indexHtmlCopy() throws FileNotFoundException, IOException {
        String src = System.getProperty("user.dir")+"/index.html";
        String dest = System.getProperty("user.dir")+"/result/index.html";
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);

        byte[] buffer = new byte[1024];

        int length;
        //copy the file content in bytes 
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        in.close();
        out.close();
        System.out.println("File copied from " + src + " to " + dest);
    }

    public static void main(String[] args) {

    }

    public void copyFolder(File src, File dest)
            throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Directory copied from "
                        + src + "  to " + dest);
            }

            //list all the directory contents
            String files[] = src.list();

            for (String file : files) {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyFolder(srcFile, destFile);
            }

        } else {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
            System.out.println("File copied from " + src + " to " + dest);
        }
    }
}
