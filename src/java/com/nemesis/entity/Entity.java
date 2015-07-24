/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.entity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author shareppy
 */
public class Entity {

    private long id;

    public Entity() {
        super();
    }

    public boolean isEdit() {
        return getId() > 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSaveSQL() {
        List<Field> fields = EntityUtil.getFields(getClass());
        StringBuilder sql = new StringBuilder();
        if (isEdit()) {
            sql.append("UPDATE ").append(EntityUtil.getFullTableName(getClass())).append(" SET ");
        } else {
            sql.append("INSERT INTO ").append(EntityUtil.getFullTableName(getClass()));
        }
        StringJoiner cols = new StringJoiner(",");
        StringJoiner values = new StringJoiner(",");
        for (Field field : fields) {
            if ("id".equals(field.getName())) {
                continue;
            }
            if (isEdit()) {
                cols.add(" " + field.getName().concat(" = ?"));
            } else {
                cols.add(field.getName());
                values.add("?");
            }
        }
        if (isEdit()) {
            sql.append(cols.toString()).append(" WHERE id = ?");
        } else {
            sql.append("(").append(cols.toString()).append(") VALUES (").append(values.toString()).append(")");
        }
        return sql.toString();
    }

    public String getSchemaDDL() {
        return "CREATE SCHEMA ".concat(EntityUtil.getSchemaName(getClass()));
    }

    public String getDDL() {
        StringBuilder ddl = new StringBuilder("CREATE TABLE ");
        ddl.append(EntityUtil.getFullTableName(getClass())).append("(id bigserial NOT NULL");
        List<Field> fields = EntityUtil.getFields(getClass());
        for (Field field : fields) {
            if (EntityUtil.isId(field)) {
                continue;
            }
            final Class<?> type = field.getType();
            ddl.append(",").append(field.getName()).append(" ").append(EntityUtil.getSQLType(type));
        }
        ddl.append(",CONSTRAINT ").append(getClass().getSimpleName()).append("_pk PRIMARY KEY (id))");
        return ddl.toString();
    }

    public String getSelectSQL() {
        return getSelectSQL(false);
    }

    public String getSelectSQL(boolean isSearch) {
        StringBuilder ddl = new StringBuilder("SELECT ");
        StringBuilder fieldssb = new StringBuilder();
        List<Field> fields = EntityUtil.getFields(getClass());
        fields.stream().forEach((field) -> {
            fieldssb.append(",").append(field.getName());
        });
        ddl.append(fieldssb.substring(1)).append(" FROM ").append(EntityUtil.getFullTableName(getClass()));
        if (!isSearch) {
            ddl.append(" WHERE id = ?");
        }
        return ddl.toString();
    }
}
