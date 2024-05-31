package fileio;

import java.io.*;
import java.util.ArrayList;

// Author: Semykopenko Ihor
// Date 20.05.2024

public class  MyIO {

    public static void readTXTRecord(String dataFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            writeTXTRecord(dataFilePath, true, "");
            readTXTRecord(dataFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeTXTRecord(String dataFilePath, boolean append, Object data) {
        try (FileWriter file = new FileWriter(dataFilePath, append)) {
            file.write(data.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> ArrayList<T> readSerializerRecord(String dataFilePath) {
        try {
            FileInputStream is = new FileInputStream(dataFilePath);
            ObjectInputStream ois = new ObjectInputStream(is);
            ArrayList<T> obj = (ArrayList<T>) ois.readObject();

            ois.close();
            is.close();

            return obj;

        } catch (IOException | ClassNotFoundException e) {
            writeSerializerRecord(dataFilePath, new ArrayList<>());
            return new ArrayList<>();
        }
    }

    public static <T> void writeSerializerRecord(String dataFilePath, ArrayList<T> obj) {
        try {
            FileOutputStream fos = new FileOutputStream(dataFilePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(obj);

            oos.close();
            fos.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
