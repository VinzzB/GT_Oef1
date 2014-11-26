package view;

import java.io.File;
import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModelTable implements TableModel

{
	/**
	 * List of event listeners. These listeners wait for something to happen
	 * with the table so that they can react. This is a must!
	 */
	private ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();

    private ArrayList<Object[]> data;
    private String[] columnNames;
    private int columnCount;

    public ModelTable(ArrayList<Object[]> data, String[] columnNames, int columnCount)
    {
    	this.data = data;
    	this.columnNames = columnNames;
    	this.columnCount = columnCount;
    }
    
    /**
     * Adds a listener to the list that is notified each time a change to the
     * data model occurs.
     *
     * @param listener
     * 			the TableModelListener
     */
	@Override
	public void addTableModelListener(TableModelListener listener)
	{
        if (listeners.contains(listener)) return;
        listeners.add(listener);	
	}
	
    /**
     * Returns the most specific superclass for all the cell values in the
     * column. This is used by the JTable to set up a default renderer and
     * editor for the column.
     *
     * @param columnIndex
     *          the index of the column
     * @return the common ancestor class of the object values in the model.
     */
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		return String.class;
	}

    /**
     * Returns the number of columns in the model. A JTable uses this method to
     * determine how many columns it should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
	@Override
	public int getColumnCount()
	{
		return columnCount;
	}

    /**
     * Returns the name of the column at columnIndex. This is used to initialize
     * the table's column header name. Note: this name does not need to be
     * unique; two columns in a table can have the same name.
     *
     * @param columnIndex
     *          the index of the column
     * @return the name of the column
     */
    @Override
    public String getColumnName(int columnIndex)
    {
        return columnNames[columnIndex];
    }

    /**
     * Returns the number of rows in the model. A JTable uses this method to
     * determine how many rows it should display. 
     *
     * @return the number of rows in the model
     */
    @Override
    public int getRowCount()
    {
        return data.size();
    }


    /**
     * Returns the value for the cell at columnIndex and rowIndex.
     *
     * @param rowIndex
     *          the row whose value is to be queried
     * @param columnIndex
     *          the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data.get(rowIndex)[columnIndex];
    }


    /**
     * Returns true if the cell at rowIndex and columnIndex is editable.
     * Otherwise, setValueAt on the cell will not change the value of that cell.
     *
     * @param rowIndex
     *          the row whose value to be queried
     * @param columnIndex
     *          the column whose value to be queried
     * @return true if the cell is editable
     * @see #setValueAt
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    /**
     * Removes a listener from the list that is notified each time a change to
     * the data model occurs.
     *
     * @param listener
     *          the TableModelListener
     */
    @Override
    public void removeTableModelListener(TableModelListener listener)
    {
        listeners.remove(listener);
    }
     
    /**
     * Sets the value in the cell at columnIndex and rowIndex to value.
     *
     * @param value
     *          the new value
     * @param rowIndex
     *          the row whose value is to be changed
     * @param columnIndex
     *          the column whose value is to be changed
     * @see #getValueAt
     * @see #isCellEditable
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        data.get(rowIndex)[columnIndex] = value;
        TableModelEvent tableModelEvent = new TableModelEvent(this, rowIndex,
                rowIndex, columnIndex, TableModelEvent.UPDATE);
        /*
         * Table has been changed,  must inform listeners about that
         */
        for (TableModelListener listener: listeners)
            listener.tableChanged(tableModelEvent);
    }
    
    /**
         * This method is used for adding a new row into your JTable
         *
         * @param checked
         *                      first column value
         * @param oldName
         *                      second column value
         * @param newName
         *                      third column value
         */
        public void addRow(String[] cells)
        {
            data.add(new Object[] {cells});
            TableModelEvent event = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, 
            		TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
            for (TableModelListener listener: listeners)
                listener.tableChanged(event);
        }
}
