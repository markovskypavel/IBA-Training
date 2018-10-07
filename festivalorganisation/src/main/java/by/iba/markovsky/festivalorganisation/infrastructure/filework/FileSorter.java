package by.iba.markovsky.festivalorganisation.infrastructure.filework;

import by.iba.markovsky.festivalorganisation.infrastructure.exception.NoExtensionFoundException;
import by.iba.markovsky.festivalorganisation.view.View;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSorter {

    private static final String PACKAGE_SORT_PATH = "D:\\Directory";

    public static void sortFiles() throws NoExtensionFoundException {
        File sourceDirectoryPath = new File(PACKAGE_SORT_PATH);
        List<File> fileList = new ArrayList<>(Arrays.asList(sourceDirectoryPath.listFiles()));
        for (File file : fileList) {
            Path newDirectoryPath = Paths.get(PACKAGE_SORT_PATH, getFileExtension(file.getPath()));
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
    private static String getFileExtension(String path) throws NoExtensionFoundException {
        int lastIndex = path.lastIndexOf(".");
        if (lastIndex < 0) {
            throw new NoExtensionFoundException("No file extension was found");
        }
        return path.substring(lastIndex);
    }

    public static void main(String[] args) {
        try {
            FileSorter.sortFiles();
        } catch (NoExtensionFoundException nefe) {
            View.print(nefe.getMessage());
        }
    }

}
