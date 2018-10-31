package by.iba.markovsky.festival.service.filework;

import by.iba.markovsky.festival.exception.NoExtensionFoundException;

public interface FileWorker<T> {
    void writeToFile(T object, String filePath);
    T readFromFile(String filePath);
    void sortFiles(String packagePath) throws NoExtensionFoundException;
}
