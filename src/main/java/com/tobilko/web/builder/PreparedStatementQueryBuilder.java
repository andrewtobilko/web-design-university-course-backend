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
public class PreparedStatementQueryBuilder implements QueryBuilder {

    @Override
    public <O, I> SelectQuery<O> createSelectQuery(I source) {
        return null;
    }

    @Override
    public <O, I> UpdateQuery<O> createUpdateQuery(I source) {
        return null;
    }

    @Override
    public <O, I> InsertQuery<O> createInsertQuery(I source) {
        return null;
    }

    @Override
    public <O, I> DeleteQuery<O> createDeleteQuery(I source) {
        return null;
    }
}