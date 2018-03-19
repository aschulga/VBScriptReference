package alexshulga.controller;

import alexshulga.model.BaseNotations;
import alexshulga.notation.Notation;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private BaseNotations base;
    private final static String FILE_NAME = "fileWithData/notations.xml";

    public Controller(BaseNotations base){
        this.base = base;
    }

    public void addNotation(Notation notation){
        base.getList().add(notation);
    }

    public void fillDataBase() {
        File file = new File(FILE_NAME);
        SAXParserFactory parserF = SAXParserFactory.newInstance();
        Handler handler = new Handler(this);
        try {
            SAXParser saxParser = parserF.newSAXParser();
            saxParser.parse(file, handler);
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public List<Notation> getListNotations() {
        return base.getList();
    }
}
