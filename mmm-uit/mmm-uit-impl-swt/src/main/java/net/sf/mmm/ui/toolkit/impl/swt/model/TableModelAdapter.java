/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.swt.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import net.sf.mmm.ui.toolkit.api.event.UITableModelListener;
import net.sf.mmm.ui.toolkit.api.model.data.UiTableMvcModel;
import net.sf.mmm.ui.toolkit.base.event.UITableModelEvent;
import net.sf.mmm.ui.toolkit.impl.swt.view.sync.SyncTableAccess;

/**
 * This class adapts from {@link net.sf.mmm.ui.toolkit.api.model.data.UiTableMvcModel}
 * to an {@link org.eclipse.swt.widgets.Table}. It is the controler of the MVC
 * pattern.
 * 
 * @param <C> is the templated type of the objects in the table cells.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class TableModelAdapter<C> implements UITableModelListener, Listener, Runnable {

  /** the sync access to the table */
  private final SyncTableAccess syncAccess;

  /** the current table model */
  private UiTableMvcModel<C> model;

  /** the table columns */
  private final List<TableColumn> columns;

  /** the table rows */
  private final List<TableItem> rows;

  /** the event to handle synchron */
  private UITableModelEvent event;

  /**
   * The constructor.
   * 
   * @param tableAccess is the sync access to the native SWT table widget for
   *        this adapter.
   */
  public TableModelAdapter(SyncTableAccess tableAccess) {

    super();
    this.syncAccess = tableAccess;
    this.syncAccess.addListener(SWT.SetData, this);
    this.columns = new ArrayList<TableColumn>();
    this.rows = new ArrayList<TableItem>();
    this.model = null;
    this.event = null;
  }

  /**
   * This method gets the adapted model.
   * 
   * @return the model or <code>null</code> if not set.
   */
  public UiTableMvcModel<C> getModel() {

    return this.model;
  }

  /**
   * This method sets the model to adapt.
   * 
   * @param newModel is the model to set.
   */
  public void setModel(UiTableMvcModel<C> newModel) {

    if (this.model != null) {
      this.model.removeListener(this);
      this.syncAccess.removeAll();
      this.columns.clear();
      this.rows.clear();
    }
    this.model = newModel;
    initialize();
  }

  /**
   * 
   */
  public void initialize() {

    if (this.syncAccess.getDelegate() != null) {
      this.event = null;
      this.syncAccess.getDisplay().invokeSynchron(this);
    }
  }

  /**
   * 
   */
  /*
   * public void updateColumnWiths() { Point size = this.table.getSize(); int
   * count = this.columns.size(); int colWidth; if (size.x == 0) { //dummy
   * colWidth = 50; } else { colWidth = size.x / count; } for (int i = 0; i <
   * count; i++) { this.columns.get(i).setWidth(colWidth); } }
   */

  /**
   * {@inheritDoc}
   */
  public void handleEvent(Event swtEvent) {

    TableItem item = (TableItem) swtEvent.item;
    int columnCount = this.model.getColumnCount();
    int row = this.syncAccess.getDelegate().indexOf(item);
    if (this.rows.size() <= row) {
      // list is to small, need to be increased
      // List does not support setSize as Vector did, so we have to fake this
      int sizeLess = row - this.rows.size();
      while (sizeLess >= 0) {
        this.rows.add(null);
        sizeLess--;
      }
    }
    this.rows.set(row, item);
    for (int col = 0; col < columnCount; col++) {
      String value = this.model.getCellValueAsString(row, col);
      item.setText(col, value);
    }
  }

  /**
   * {@inheritDoc}
   */
  public synchronized void tableModelChanged(UITableModelEvent changeEvent) {

    this.event = changeEvent;
    this.syncAccess.getDisplay().invokeSynchron(this);
  }

  /**
   * This method is called synchron to handle an model update event.
   * 
   * @see java.lang.Runnable#run()
   */
  public void run() {

    if (this.event == null) {
      // initialize
      int columnCount = this.model.getColumnCount();
      int rowCount = this.model.getRowCount();
      Table table = this.syncAccess.getDelegate();
      table.setItemCount(rowCount);
      for (int i = 0; i < columnCount; i++) {
        TableColumn tableColumn = new TableColumn(table, SWT.LEFT);
        tableColumn.setText(this.model.getColumnName(i));
        tableColumn.setWidth(50);
        this.columns.add(tableColumn);
      }
      this.model.addListener(this);
    } else {
      switch (this.event.getType()) {
        case ADD:
          // TODO
          break;
        case REMOVE:
          // TODO
          break;
        case UPDATE:
          int colStart,
          colEnd;
          if (this.event.getColumnIndex() == -1) {
            colStart = 0;
            colEnd = this.model.getColumnCount() - 1;
          } else {
            colStart = this.event.getColumnIndex();
            colEnd = colStart;
          }
          if (this.event.getRowStartIndex() == -1) {
            for (int col = colStart; col <= colEnd; col++) {
              this.columns.get(col).setText(this.model.getColumnName(col));
            }
          } else {
            int rowEnd = this.event.getRowEndIndex();
            for (int row = this.event.getRowStartIndex(); row <= rowEnd; row++) {
              TableItem item = this.rows.get(row);
              for (int col = colStart; col <= colEnd; col++) {
                item.setText(col, this.model.getCellValueAsString(row, col));
              }
            }
          }
          break;
      }
    }
  }

}
