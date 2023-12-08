package org.example.dao;

import org.example.anotations.Column;
import org.example.anotations.Table;
import org.example.dao.interfaceDao.CrudDao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CRUDDaoImplements<T> implements CrudDao<T> {
    private final Class<T> type;
    private final String url = "jdbc:postgresql://yourhost:port/yourdb";
    private final String user = "yourusername";
    private final String password = "yourpassword";

    protected CRUDDaoImplements(Class<T> type) {
        this.type = type;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void insert(T entity) throws IllegalAccessException {
        Class<?> clazz = entity.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("A classe não possui anotação @Table.");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        List<String> columnNames = new ArrayList<>();
        List<Object> columnValues = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                columnNames.add(columnAnnotation.name());

                field.setAccessible(true);
                columnValues.add(field.get(entity));
            }
        }

        buildAndExecuteInsertQuery(tableName, columnNames, columnValues);
    }

    private void buildAndExecuteInsertQuery(String tableName, List<String> columnNames, List<Object> columnValues) {
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (int i = 0; i < columnNames.size(); i++) {
            columns.append(columnNames.get(i));
            placeholders.append("?");

            if (i < columnNames.size() - 1) {
                columns.append(", ");
                placeholders.append(", ");
            }
        }

        String SQL = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, placeholders);


        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            for (int i = 0; i < columnValues.size(); i++) {
                pstmt.setObject(i + 1, columnValues.get(i));
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> selectAll() {
        List<T> entities = new ArrayList<>();
        String tableName = getTableName();

        String SQL = "SELECT * FROM " + tableName;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                T entity = createEntityFromResultSet(rs);
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private String getTableName() {
        if (!type.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("A classe não possui anotação @Table.");
        }
        Table tableAnnotation = type.getAnnotation(Table.class);
        return tableAnnotation.name();
    }

    private T createEntityFromResultSet(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException {
        T entity = type.newInstance();
        for (Field field : type.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Object value = rs.getObject(field.getAnnotation(Column.class).name());
                field.setAccessible(true);
                field.set(entity, value);
            }
        }
        return entity;
    }

    @Override
    public boolean deleteById(UUID id) {
        String tableName = getTableName();
        String SQL = "DELETE FROM " + tableName + " WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setObject(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateById(UUID id, T entity) throws IllegalAccessException {
        String tableName = getTableName();
        StringBuilder SQL = new StringBuilder("UPDATE " + tableName + " SET ");

        List<Object> columnValues = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                SQL.append(columnAnnotation.name()).append(" = ?, ");
                field.setAccessible(true);
                columnValues.add(field.get(entity));
            }
        }

        SQL = new StringBuilder(SQL.substring(0, SQL.length() - 2)); // Remove a última vírgula e espaço
        SQL.append(" WHERE id = ?");
        columnValues.add(id);

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL.toString())) {
            for (int i = 0; i < columnValues.size(); i++) {
                pstmt.setObject(i + 1, columnValues.get(i));
            }
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<T> selectById(UUID id) {
        String tableName = getTableName();
        String SQL = "SELECT * FROM " + tableName + " WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                T entity = createEntityFromResultSet(rs);
                return Optional.of(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }



}
