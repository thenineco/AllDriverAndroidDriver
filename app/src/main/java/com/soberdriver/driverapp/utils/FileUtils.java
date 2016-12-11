package com.soberdriver.driverapp.utils;

import android.content.Context;
import android.os.Environment;

import com.soberdriver.driverapp.DriverApp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class FileUtils {

    public static String getAbsolutePath() {
        return DriverApp.getContext().getExternalFilesDir(null).getAbsolutePath();
    }

    public static String getAppDataPath() {
        return Environment.getDownloadCacheDirectory().getAbsolutePath();
    }


    public static void deleteFolder(String path) {
        String deleteCmd = "rm -r " + path;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(deleteCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteFile(File file) {
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    private static void copyRAWtoSDCard(Context context, int id, String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return;
        }
        InputStream in = context.getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }

    public static String createFileNameByTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd-HHmmss");
        return format.format(date);
    }

    public static String createVideoFileNameByTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd-HHmmss");
        return format.format(date) + ".mp4";
    }


    public static void copyFile(String inputFilePath, String outputFilePath) throws IOException {
        File outputFile = new File(outputFilePath);
        File inputFile = new File(inputFilePath);
        if (outputFile.exists()) {
            return;
        }
        InputStream in = new FileInputStream(inputFile);
        FileOutputStream out = new FileOutputStream(outputFile);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }
    }


    public static void addTextToFile(String fileUri, String text) {
        File logFile = new File(fileUri);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
