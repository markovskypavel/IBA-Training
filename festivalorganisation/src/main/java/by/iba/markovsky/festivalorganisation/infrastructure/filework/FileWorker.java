package by.iba.markovsky.festivalorganisation.infrastructure.filework;

import java.io.*;

public class FileWorker {

    private static final String FILE_PATH = "D:\\newFile.txt";

    //with serialization
    public static <T> void writeToFile(T object) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(FILE_PATH)));
            oos.writeObject(object);
            oos.flush();
            oos.reset();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static <T> T readFromFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(FILE_PATH))));
            T object = (T) ois.readObject();
            ois.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
