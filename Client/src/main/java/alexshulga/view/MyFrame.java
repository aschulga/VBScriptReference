package alexshulga.view;

import alexshulga.controller.Controller;
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
    private JButton offButton = new JButton(new ImageIcon("images/exit.png"));

    private JMenuBar menubar = new JMenuBar();
    private JMenu fileMenu = new JMenu("Файл");
    private JMenu searchMenu = new JMenu("Поиск");
    private JMenu deleteMenu = new JMenu("Удаление");

    private JMenuItem createMenuItem = new JMenuItem("Создать");
    private JMenuItem openMenuItem = new JMenuItem("Открыть");
    private JMenuItem saveMenuItem = new JMenuItem("Сохранить");
    private JMenuItem exitMenuItem = new JMenuItem("Выход");

    private JMenuItem changeMenuItem = new JMenuItem("Найти студента");
    private JMenuItem deleteMenuItem = new JMenuItem("Удалить студента");

    private ModelTable model = new ModelTable();
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
        tb.add(offButton);

        frame.getContentPane().add(tb, BorderLayout.NORTH);

        createButton.addActionListener(new createActionListener());
        createMenuItem.addActionListener(new createActionListener());


        saveButton.addActionListener(new saveActionListener());
        saveMenuItem.addActionListener(new saveActionListener());

        changeButton.addActionListener(new changeActionListener());
        changeMenuItem.addActionListener(new changeActionListener());

        openButton.addActionListener(new openActionListener());
        openMenuItem.addActionListener(new openActionListener());

        deleteButton.addActionListener(new deleteActionListener());
        deleteMenuItem.addActionListener(new deleteActionListener());

        offButton.addActionListener(new offActionListener());
        exitMenuItem.addActionListener(new offActionListener());

        createMenuItem.setIcon(new ImageIcon("1.png"));
        openMenuItem.setIcon(new ImageIcon("2.png"));
        saveMenuItem.setIcon(new ImageIcon("3.png"));
        exitMenuItem.setIcon(new ImageIcon("6.png"));

        fileMenu.add(createMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        changeMenuItem.setIcon(new ImageIcon("4.png"));
        deleteMenuItem.setIcon(new ImageIcon("5.png"));

        searchMenu.add(changeMenuItem);
        deleteMenu.add(deleteMenuItem);

        menubar.add(fileMenu);
        menubar.add(searchMenu);
        menubar.add(deleteMenu);
        frame.setJMenuBar(menubar);

        controller.connect();

        frame.setVisible(true);
        frame.pack();

    }

    public class openActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.addNotation(controller.getAllNotations());
            } catch (TException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreate addStudentDialog = new DialogCreate(controller);
            addStudentDialog.create();
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            DialogDelete searchStudentDialog = new DialogDelete(controller);
            searchStudentDialog.delete();
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            DialogChange changeDialog = new DialogChange(controller);
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

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.close();
            System.exit(0);
        }
    }

}
