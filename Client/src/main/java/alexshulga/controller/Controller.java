package alexshulga.controller;

import alexshulga.book.Book;
import alexshulga.bookservice.BookService;
import alexshulga.theme.Theme;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.List;

public class Controller {

    private TTransport transport;
    private BookService.Client client;

    public void connect() throws TException {
        transport = new TSocket("localhost", 8000);
        transport.open();

        TProtocol protocol = new TBinaryProtocol(transport);
        client = new BookService.Client(protocol);
    }

    public void close(){
        transport.close();
    }

    public List<Book> getAllBooks() throws TException {
        return client.getAllBooks();
    }

    public void addBook(Book book) throws TException {
        client.addBook(book);
    }

    public void deleteBook(String number) throws TException {
        client.deleteBook(Integer.parseInt(number));
    }

    public void changeBook(int index, Book book) throws TException {
        client.changeBook(index, book);
    }

    public void saveChanging() throws TException {
        client.saveChanging();
    }

    public void addTheme(Theme theme, int index) throws TException {
        client.addTheme(theme, index);
    }

    public List<Theme> getAllThemesInBock(int index) throws TException {
        return client.getAllThemes(index);
    }

    public void deleteTheme(int index, int indexInBook) throws TException {
        System.out.println(index+" "+indexInBook);
        client.deleteTheme(index, indexInBook);
    }

    public void changeTheme(int index, Theme theme, int indexInBook) throws TException {
        client.changeTheme(index, theme, indexInBook);
    }
}
