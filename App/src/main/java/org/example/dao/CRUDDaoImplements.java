package org.example.dao;

import org.example.dao.interfaceDao.CrudDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class CRUDDaoImplements<T> implements CrudDao<T> {
    private final String url = "jdbc:postgresql://yourhost:port/yourdb";
    private final String user = "yourusername";
    private final String password = "yourpassword";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public List<T> selectAll() {
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        return false;
    }

    @Override
    public boolean updateById(UUID id, T entity) {
        return false;
    }

    @Override
    public void insert(T entity){
        String SQL = "INSERT INTO pessoa (nome, cpf_cnpj, tipo) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, pessoa.getNome());
            pstmt.setString(2, pessoa.getCpfCnpj());
            pstmt.setString(3, String.valueOf(pessoa.getTipo()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public Optional<T> selectById(UUID id){
        return null;
    }




}
