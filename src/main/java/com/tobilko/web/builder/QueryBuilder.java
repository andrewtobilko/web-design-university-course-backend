package com.tobilko.web.builder;

import com.tobilko.web.builder.query.DeleteQuery;
import com.tobilko.web.builder.query.InsertQuery;
import com.tobilko.web.builder.query.SelectQuery;
import com.tobilko.web.builder.query.UpdateQuery;

/**
 *
 * Created by Andrew Tobilko on 12/28/2016.
 *
 */
public interface QueryBuilder<I, O> {

    SelectQuery<O> createSelectQuery(I source);
    UpdateQuery<O> createUpdateQuery(I source);
    InsertQuery<O> createInsertQuery(I source);
    DeleteQuery<O> createDeleteQuery(I source);

}
