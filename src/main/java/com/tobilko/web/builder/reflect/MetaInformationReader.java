package com.tobilko.web.builder.reflect;

import com.tobilko.web.builder.reflect.annotation.Column;
import com.tobilko.web.builder.reflect.annotation.Identifier;
import com.tobilko.web.builder.reflect.annotation.Table;
import com.tobilko.web.builder.reflect.information.Id;

import java.lang.reflect.Field;

/**
 *
 * Created by Andrew Tobilko on 12/29/2016.
 *
 */
public class MetaInformationReader {

    public RelationalClassMetaInformation read(Class<?> c) {
        int hasIdentifier = 0;
        RelationalClassMetaInformation information = new RelationalClassMetaInformation();

        // table
        if (c.isAnnotationPresent(Table.class)) {
            information.setTable(new com.tobilko.web.builder.reflect.information.Table(c.getAnnotation(Table.class).value()));
        } else {
            throw new IllegalArgumentException("The class is not marked by the @Table annotation.");
        }

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // id
            if (field.isAnnotationPresent(Identifier.class)) {
                ++hasIdentifier;
                if (hasIdentifier > 1) {
                    throw new IllegalArgumentException("The class " + c.getName() + " has more than one @Identifier annotation.");
                } else {
                    Identifier id = field.getAnnotation(Identifier.class);
                    information.setId(new Id(id.value()));
                }
            }

            // columns
            if (field.isAnnotationPresent(Column.class)) {
                information.getColumnNames().add(new com.tobilko.web.builder.reflect.information.Column(field.getAnnotation(Column.class).value()));
            }
        }

        if (hasIdentifier == 0) {
            throw new IllegalArgumentException("The class " + c.getName() + " has no @Identifier annotations.");
        }

        return information;
    }

}