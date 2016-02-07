/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nemesis.entity;

import com.nemesis.metadata.Fts;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author shareppy
 */
public class EntityUtil {

    public static List<Field> getFields(Class<? extends Entity> clazz) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        fields.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
        return fields;
    }

    public static List<Object> getFieldValues(Entity entity) throws IllegalArgumentException, IllegalAccessException {
        List<Object> fieldValues = new ArrayList<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldValues.add(field.get(entity));
        }
        Field[] superFields = entity.getClass().getSuperclass().getDeclaredFields();
        for (Field superField : superFields) {
            if (entity.isEdit()) {
                if (EntityUtil.isId(superField)) {
                    continue;
                }
                superField.setAccessible(true);
                fieldValues.add(superField.get(entity));
            }
        }
        if (entity.isEdit()) {
            fieldValues.add(entity.getId());
        }
        return fieldValues;
    }
    
    public static Map<Field, Object> getFieldValuesMap(Entity entity) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Map<Field, Object> fieldValues = new LinkedHashMap<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldValues.put(field, field.get(entity));
        }
        Field[] superFields = entity.getClass().getSuperclass().getDeclaredFields();
        Field fieldId = null;
        for (Field superField : superFields) {
            if (entity.isEdit()) {
                if (EntityUtil.isId(superField)) {
                    fieldId = superField;
                    continue;
                }
                superField.setAccessible(true);
                fieldValues.put(superField, superField.get(entity));
            }
        }
        if (entity.isEdit()) {
            fieldValues.put( fieldId , entity.getId());
        }
        return fieldValues;
    }

    public static <T extends Entity> T getEntity(Class<T> clazz, HttpServletRequest req) throws InstantiationException, IllegalAccessException {
        List<Field> fields = getFields(clazz);
        final T entity = clazz.newInstance();
        for (Field superField : fields) {
            superField.setAccessible(true);
            Class<?> type = superField.getType();
            if (EntityUtil.isLong(type) || isInt(type)) {
                superField.setLong(entity, getLong(req, superField.getName()));
            } else if (EntityUtil.isString(type)) {
                superField.set(entity, req.getParameter(superField.getName()));
            }
        }
        return entity;
    }

    public static boolean isLong(Class<?> type) {
        return type.isAssignableFrom(Long.TYPE) || type.isAssignableFrom(Long.class);
    }

    public static boolean isInt(Class<?> type) {
        return type.isAssignableFrom(Integer.TYPE) || type.isAssignableFrom(Integer.class);
    }

    public static boolean isString(Class<?> type) {
        return type.isAssignableFrom(String.class);
    }

    public static String getSQLType(Class<?> type) {
        if (EntityUtil.isLong(type) || EntityUtil.isInt(type)) {
            return "integer";
        }
        return "character varying";
    }

    public static long getLong(HttpServletRequest req, String key) {
        String parameter = req.getParameter(key);
        return getLong(parameter);
    }

    public static long getLong(String parameter) {
        try {
            return Long.parseLong(parameter);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    public static int getInt(HttpServletRequest req, String key) {
        String parameter = req.getParameter(key);
        try {
            return Integer.parseInt(parameter);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    public static String getFullTableName(Class<? extends Entity> clazz) {
        return getSchemaName(clazz).concat(".").concat(clazz.getSimpleName());
    }

    public static String getSchemaName(Class<? extends Entity> clazz) {
        return clazz.getPackage().getName().replace(".", "_");
    }

    public static boolean isId(Field field) {
        return "id".equalsIgnoreCase(field.getName());
    }

    public static <T extends Entity> void setValues(PreparedStatement prepareStatement, T entity) throws IllegalArgumentException, IllegalAccessException, SQLException, NoSuchFieldException {
        Map<Field, Object> fieldValues = EntityUtil.getFieldValuesMap(entity);
        System.err.println(fieldValues);
        int parameterIndex = 1;
        for (Map.Entry<Field, Object> fieldValue : fieldValues.entrySet()) {
            final Class<? extends Object> clazz = fieldValue.getValue().getClass();
            if (EntityUtil.isLong(clazz) || EntityUtil.isInt(clazz)) {
                prepareStatement.setLong(parameterIndex, (Long) fieldValue.getValue());
            } else if (EntityUtil.isString(fieldValue.getValue().getClass())) {
                prepareStatement.setString(parameterIndex, (String) fieldValue.getValue());
                if( fieldValue.getKey().isAnnotationPresent(Fts.class) ){
                    parameterIndex++;
                    prepareStatement.setString(parameterIndex, (String) fieldValue.getValue());
                }
            }
            parameterIndex++;
        }
    }

    public static <T extends Entity> void setValues(PreparedStatement prepareStatement, Object[] values) throws IllegalArgumentException, IllegalAccessException, SQLException {
        if (values != null) {
            int parameterIndex = 1;
            for (Object fieldValue : values) {
                if (fieldValue == null) {
                    prepareStatement.setNull(parameterIndex, Types.VARCHAR);
                } else if( fieldValue instanceof Number ){
                    prepareStatement.setLong(parameterIndex, (Long) fieldValue);
                } else if (fieldValue instanceof String) {
                    prepareStatement.setString(parameterIndex, (String) fieldValue);
                }
                parameterIndex++;
            }
        }
    }

    public static <T extends Entity> void setLongValue(PreparedStatement prepareStatement, long value) throws SQLException {
        setLongValue(prepareStatement, value, 1);
    }

    public static <T extends Entity> void setLongValue(PreparedStatement prepareStatement, long value, int index) throws SQLException {
        prepareStatement.setLong(index, value);
    }

    public static <T extends Entity> void setStringValue(PreparedStatement prepareStatement, String value) throws SQLException {
        setStringValue(prepareStatement, value, 1);
    }

    public static <T extends Entity> void setStringValue(PreparedStatement prepareStatement, String value, int index) throws SQLException {
        prepareStatement.setString(index, value);
    }
}
