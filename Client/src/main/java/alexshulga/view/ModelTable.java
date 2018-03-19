package alexshulga.view;

import alexshulga.notation.Notation;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelTable extends AbstractTableModel {

    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int FOURTH_COLUMN = 3;
    private static final int FIFTH_COLUMN = 4;
    private static final int COLUMN_COUNT = 5;
    private List<Notation> tableData;

    public ModelTable()
    {
        tableData = new ArrayList<>();
    }

    public String getColumnName(int columnIndex)
    {
        switch(columnIndex)
        {
            case FIRST_COLUMN: return "Номер";
            case SECOND_COLUMN: return "Тип";
            case THIRD_COLUMN: return "Вид";
            case FOURTH_COLUMN: return "Подтип";
            case FIFTH_COLUMN: return "Описание";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Notation rows = tableData.get(rowIndex);

        String str = null;

        if(columnIndex == FIRST_COLUMN)
        {
            str = String.valueOf(rows.getId());
        }
        if(columnIndex == SECOND_COLUMN)
        {
            str = rows.getType();
        }
        else if(columnIndex == THIRD_COLUMN)
        {
            str = rows.getKind().getKind();
        }
        else if(columnIndex == FOURTH_COLUMN)
        {
            str = rows.getSubtype();
        }
        else if(columnIndex == FIFTH_COLUMN)
        {
            str = rows.getDescription();
        }

        return str;
    }

    public int getRowCount()
    {
        return tableData.size();
    }

    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }

    public void addDate(Notation notation)
    {
        tableData.add(notation);
        fireTableDataChanged();
    }

    public void addNotation(List<Notation> list)
    {
        deleteAllNotations();

        for(int i = 0; i < list.size(); i++){
            addDate(list.get(i));
        }
    }

    public void deleteAllNotations()
    {
        tableData.clear();
        fireTableDataChanged();
    }
}