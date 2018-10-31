package by.iba.markovsky.festival.service.parser;

import by.iba.markovsky.festival.exception.UtilException;

import java.io.File;

public interface JAXBParser {
    Object getObject(File file, Class c) throws UtilException;
    void saveObject(File file, Object o) throws UtilException;
}
