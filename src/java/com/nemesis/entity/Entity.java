/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Field;
import java.util.List;
import java.util.StringJoiner;
import com.nemesis.admin.utils.Constants;
import com.nemesis.metadata.Fts;
import java.io.Serializable;

/**
 *
 * @author shareppy
 */
public class Entity implements Serializable{
    
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

    public String getDeleteSQL() {
        StringBuilder sql = new StringBuilder("DELETE FROM ");
        sql.append(EntityUtil.getFullTableName(getClass()))
                .append(" WHERE id = ? ");
        return sql.toString();
    }
    
    public String getCountSQL(String condition){
        StringBuilder sql = new StringBuilder("SELECT COUNT(id) FROM ");
        sql.append(EntityUtil.getFullTableName(getClass()))
                .append(condition);
        return sql.toString();
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
        fields.stream().filter((field) -> !("id".equals(field.getName()))).forEach((field) -> {
            if (isEdit()) {
                cols.add(" " + field.getName().concat(" = ?"));
            } else {
                cols.add(field.getName());
                values.add("?");
                if( field.isAnnotationPresent(Fts.class) ){
                    cols.add(field.getName().concat("_ts"));
                    values.add("to_tsvector('spanish', ?)");
                }
            }
        });
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
            if( field.isAnnotationPresent(Fts.class) ){
                ddl.append(",").append(field.getName()).append("_ts TSVECTOR");
            }
        }
        ddl.append(",CONSTRAINT ").append(getClass().getSimpleName()).append("_pk PRIMARY KEY (id))");
        return ddl.toString();
    }

    public String getSelectSQL() {
        return getSelectSQL(false);
    }

    public String getSelectSQL(boolean isSearch) {
        return getSelectSQL(isSearch ? Constants.SEARCH : Constants.LOAD);
    }

    public String getSelectSQL(int type) {
        if (type == Constants.LOAD) {
            return getSelectByCondition( " WHERE id = ?" );
        }
        if( type == Constants.LIST ){
        return getSelectByCondition( " WHERE UPPER(name) like UPPER(?)" );
        }
        return getSelectByCondition( "" );
    }
    
    public String getSelectByCondition(String condition) {
        StringBuilder ddl = new StringBuilder("SELECT ");
        StringBuilder fieldssb = new StringBuilder();
        List<Field> fields = EntityUtil.getFields(getClass());
        fields.stream().forEach((field) -> {
            fieldssb.append(",").append(field.getName());
        });
        ddl.append(fieldssb.substring(1)).append(" FROM ").append(EntityUtil.getFullTableName(getClass()));
        ddl.append(condition);
        return ddl.toString();
    }

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }
}
