package by.iba.markovsky.festival.service.parser.impl;

import by.iba.markovsky.festival.exception.UtilException;
import by.iba.markovsky.festival.service.parser.JAXBParser;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Service("JAXBParserImpl")
public class JAXBParserImpl implements JAXBParser {

    @Override
    public Object getObject(File file, Class c) throws UtilException {
        Object object = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new UtilException(e.getMessage());
        }
        return object;
    }
    @Override
    public void saveObject(File file, Object o) throws UtilException {
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(o, file);
        } catch (JAXBException e) {
            throw new UtilException(e.getMessage());
        }
    }

}
