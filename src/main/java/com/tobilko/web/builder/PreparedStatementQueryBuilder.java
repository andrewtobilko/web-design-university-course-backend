package com.tobilko.web.builder;

import com.tobilko.web.builder.query.DeleteQuery;
import com.tobilko.web.builder.query.InsertQuery;
import com.tobilko.web.builder.query.SelectQuery;
import com.tobilko.web.builder.query.UpdateQuery;

import java.sql.PreparedStatement;

/**
 *
 * Created by Andrew Tobilko on 12/28/2016.
 *
 */
public class PreparedStatementQueryBuilder implements QueryBuilder<PreparedStatement, PreparedStatement> {

    @Override
    public SelectQuery<PreparedStatement> createSelectQuery(PreparedStatement inputSource) {
        return null;
    }

    @Override
    public UpdateQuery<PreparedStatement> createUpdateQuery(PreparedStatement source) {
        return null;
    }

    @Override
    public InsertQuery<PreparedStatement> createInsertQuery(PreparedStatement source) {
        return null;
    }

    @Override
    public DeleteQuery<PreparedStatement> createDeleteQuery(PreparedStatement source) {
        return null;
    }

}
