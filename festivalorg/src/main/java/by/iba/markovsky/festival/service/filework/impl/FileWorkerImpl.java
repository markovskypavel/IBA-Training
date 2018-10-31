package by.iba.markovsky.festival.service.filework.impl;

import by.iba.markovsky.festival.exception.NoExtensionFoundException;
import by.iba.markovsky.festival.service.filework.FileWorker;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fileworker with serialization
 * */
@Service("FileWorkerImpl")
public class FileWorkerImpl<T> implements FileWorker<T> {

    @Override
    public void writeToFile(T object, String filePath) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
            oos.writeObject(object);
            oos.flush();
            oos.reset();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public T readFromFile(String filePath) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream((new File(filePath))));
            T object = (T) ois.readObject();
            ois.close();
            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sortFiles(String packagePath) throws NoExtensionFoundException {
        File sourceDirectoryPath = new File(packagePath);
        List<File> fileList = new ArrayList<>(Arrays.asList(sourceDirectoryPath.listFiles()));
        for (File file : fileList) {
            Path newDirectoryPath = Paths.get(packagePath, getFileExtension(file.getPath()));
            File newDirectory = new File(newDirectoryPath.toString());
            if (!newDirectory.exists()) {
                newDirectory.mkdir();
            }
            try {
                Files.copy(file.toPath(), newDirectoryPath.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            file.delete();
        }
    }
    private String getFileExtension(String path) throws NoExtensionFoundException {
        int lastIndex = path.lastIndexOf(".");
        if (lastIndex < 0) {
            throw new NoExtensionFoundException("No file extension was found");
        }
        return path.substring(lastIndex);
    }

}
