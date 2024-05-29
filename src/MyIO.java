import java.io.*;
import java.util.HashMap;

// Author: Semykopenko Ihor
// Date 20.05.2024

public class MyIO {

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

    public static <K, V> HashMap<K, V> readSerializerRecord(String dataFilePath) {
        try {
            FileInputStream is = new FileInputStream(dataFilePath);
            ObjectInputStream ois = new ObjectInputStream(is);
            HashMap<K, V> obj = (HashMap<K, V>) ois.readObject();

            ois.close();
            is.close();

            return obj;

        } catch (IOException | ClassNotFoundException e) {
            writeSerializerRecord(dataFilePath, new HashMap<>());
            return new HashMap<K, V>();
        }
    }

    public static <K, V> void writeSerializerRecord(String dataFilePath, HashMap<K, V> obj) {
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
