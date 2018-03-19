package alexshulga;

import alexshulga.controller.Controller;
import alexshulga.view.MyFrame;
import org.apache.thrift.TException;

import java.awt.*;

public class Main {
    public static void main(String args[]) throws TException {
        Controller controller = new Controller();
        MyFrame frame = new MyFrame("Справочник по языку VBScript",new Dimension(600,400), controller);
        frame.init();
    }
}
