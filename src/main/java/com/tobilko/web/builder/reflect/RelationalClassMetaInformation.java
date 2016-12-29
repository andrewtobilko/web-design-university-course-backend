package com.tobilko.web.builder.reflect;

import com.tobilko.web.builder.reflect.information.Column;
import com.tobilko.web.builder.reflect.information.Id;
import com.tobilko.web.builder.reflect.information.Table;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Andrew Tobilko on 12/28/2016.
 *
 */
public class RelationalClassMetaInformation {

    private Table table;
    private Id id;
    private List<Column> columnNames;

    public RelationalClassMetaInformation() {
        columnNames = new ArrayList<>();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public List<Column> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<Column> columnNames) {
        this.columnNames = columnNames;
    }

}