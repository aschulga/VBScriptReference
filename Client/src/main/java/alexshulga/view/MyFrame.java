package alexshulga.view;

import alexshulga.controller.Controller;
import alexshulga.view.book.*;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame {

    private String title;
    private Dimension d;
    private Controller controller;
    private JFrame frame = new JFrame();

    private JToolBar tb = new JToolBar();
    private JButton createButton = new JButton(new ImageIcon("images/add.png"));
    private JButton openButton = new JButton(new ImageIcon("images/show.png"));
    private JButton saveButton = new JButton(new ImageIcon("images/save.png"));
    private JButton changeButton = new JButton(new ImageIcon("images/change.png"));
    private JButton deleteButton = new JButton(new ImageIcon("images/delete.png"));
    private JButton openBookButton = new JButton(new ImageIcon("images/openBook.png"));
    private JButton offButton = new JButton(new ImageIcon("images/exit.png"));

    private ModelTableBooks model = new ModelTableBooks();
    private JTable table = new JTable(model);
    private JScrollPane jsp = new JScrollPane(table);

    public MyFrame(String title, Dimension d, Controller controller) {
        this.title = title;
        this.d = d;
        this.controller = controller;
    }

    public void init() throws TException {

        frame.setTitle(title);
        frame.setSize(d);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(jsp, BorderLayout.CENTER);
        jsp.setPreferredSize(new Dimension(700, 500));

        tb.add(createButton);
        tb.add(openButton);
        tb.add(saveButton);
        tb.add(changeButton);
        tb.add(deleteButton);
        tb.add(openBookButton);
        tb.add(offButton);

        frame.getContentPane().add(tb, BorderLayout.NORTH);

        createButton.addActionListener(new createActionListener());
        saveButton.addActionListener(new saveActionListener());
        changeButton.addActionListener(new changeActionListener());
        openButton.addActionListener(new openActionListener());
        deleteButton.addActionListener(new deleteActionListener());
        openBookButton.addActionListener(new openBookButtonActionListener());
        offButton.addActionListener(new offActionListener());

        controller.connect();

        frame.setVisible(true);
        frame.pack();
    }

    public class openActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.addNotation(controller.getAllBooks());
            } catch (TException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreateBook addStudentDialog = new DialogCreateBook(controller);
            addStudentDialog.create();
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogDeleteBook searchStudentDialog = new DialogDeleteBook(controller);
            searchStudentDialog.delete();
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangeBook changeDialog = new DialogChangeBook(controller);
            changeDialog.change();
        }
    }

    public class saveActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                controller.saveChanging();
            } catch (TException e1) {
                e1.printStackTrace();
                controller.close();
            }
        }
    }

    public class openBookButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogOpenBook openBookDialog = new DialogOpenBook(controller);
            openBookDialog.openBook();
        }
    }

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.close();
            System.exit(0);
        }
    }

}
