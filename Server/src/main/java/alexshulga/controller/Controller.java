package alexshulga.controller;

import alexshulga.book.Book;
import alexshulga.model.BaseNotations;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private BaseNotations base;

    public Controller(BaseNotations base){
        this.base = base;
    }

    public List<Book> getListBooks() {
        return base.getList();
    }
}
