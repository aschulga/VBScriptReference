package alexshulga.controller;

import alexshulga.book.Book;
import alexshulga.bookservice.BookService;
import alexshulga.theme.Theme;
import org.apache.thrift.TException;

import java.io.File;
import java.util.List;

public class NotationsHandler implements BookService.Iface {

    private Controller controller;
    private final static String FILE_NAME = "fileWithData/newChanging.xml";

    public NotationsHandler(Controller controller){
        this.controller = controller;
    }

    @Override
    public List<Book> getAllBooks(){
        for(int i = 0; i < controller.getListBooks().size(); i++){
            controller.getListBooks().get(i).setId(i+1);
        }
        return controller.getListBooks();
    }

    @Override
    public void addBook(Book book){
        controller.getListBooks().add(book);
    }

    @Override
    public void deleteBook(int index){
        int number = index - 1;
        controller.getListBooks().remove(number);
    }

    @Override
    public void changeBook(int index, Book book){
        int number = index - 1;
        controller.getListBooks().remove(number);
        controller.getListBooks().add(number, book);
    }

    @Override
    public void addTheme(Theme theme, int indexInListBooks){
        controller.getListBooks().get(indexInListBooks).addToListThemes(theme);
    }

    @Override
    public List<Theme> getAllThemes(int index){
        for(int i = 0; i < controller.getListBooks().get(index).getListThemes().size(); i++){
            controller.getListBooks().get(index).getListThemes().get(i).setId(i+1);
        }
        return controller.getListBooks().get(index).getListThemes();
    }

    @Override
    public void deleteTheme(int index, int indexInListBooks){
        controller.getListBooks().get(indexInListBooks).getListThemes().remove(index);
    }

    @Override
    public void changeTheme(int index, Theme theme, int indexInListBooks){
        controller.getListBooks().get(indexInListBooks).getListThemes().remove(index);
        controller.getListBooks().get(indexInListBooks).getListThemes().add(index, theme);
    }

    @Override
    public void saveChanging() {
        //SaveChanging saveStudentDialog = new SaveChanging(controller, new File(FILE_NAME));
        //saveStudentDialog.save();
    }
}
