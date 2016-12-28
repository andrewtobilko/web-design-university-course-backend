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
public class EntityQueryBuilder<T> implements QueryBuilder<Class<T>, T> {

    @Override
    public SelectQuery<T> createSelectQuery(Class<T> source) {
        return null;
    }

    @Override
    public UpdateQuery<T> createUpdateQuery(Class<T> source) {
        return null;
    }

    @Override
    public InsertQuery<T> createInsertQuery(Class<T> source) {
        return null;
    }

    @Override
    public DeleteQuery<T> createDeleteQuery(Class<T> source) {
        return null;
    }

}
