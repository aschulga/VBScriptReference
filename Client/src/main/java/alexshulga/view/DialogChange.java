package alexshulga.view;

import alexshulga.controller.Controller;
import alexshulga.kind.Kind;
import alexshulga.notation.Notation;
import org.apache.thrift.TException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogChange {

    private JDialog dialog = new JDialog();
    private Controller controller;

    public DialogChange(Controller controller) {
        this.controller = controller;
    }

    public void change(){

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel numberLabel = new JLabel("Номер записи: ");
        JTextField numberTextField = new JTextField(10);

        dialog.add(numberLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(numberTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel typeLabel = new JLabel("Тип: ");
        JTextField typeTextField = new JTextField(10);

        dialog.add(typeLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(typeTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel kindLabel = new JLabel("Вид: ");
        JTextField kindTextField = new JTextField(10);

        dialog.add(kindLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(kindTextField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel subTypeLabel = new JLabel("Подтип: ");
        JTextField subTypeTextField = new JTextField(10);

        dialog.add(subTypeLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(subTypeTextField, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel descriptionLabel = new JLabel("Описание: ");
        JTextField descriptionTextField = new JTextField(10);

        dialog.add(descriptionLabel, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(descriptionTextField, new GridBagConstraints(1, 4, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton changeNotation = new JButton("Изменить запись");

        dialog.add(changeNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);


        changeNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numberTextField.getText().trim().isEmpty() || typeTextField.getText().trim().isEmpty() ||
                        kindTextField.getText().trim().isEmpty() || subTypeTextField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                } else {
                    try
                    {
                        Notation notation = new Notation(Integer.parseInt(numberTextField.getText()), typeTextField.getText(), new Kind(kindTextField.getText()),
                                subTypeTextField.getText(), descriptionTextField.getText());

                        controller.changeNotation(Integer.parseInt(numberTextField.getText()), notation);
                        JOptionPane.showMessageDialog(dialog, "Запись успешно изменена. Для продолжения работы нажмите \"ОК\"");
                        dialog.dispose();
                    } catch (TException e1) {
                        e1.printStackTrace();
                        controller.close();
                    }
                }
            }
        });
    }
}
