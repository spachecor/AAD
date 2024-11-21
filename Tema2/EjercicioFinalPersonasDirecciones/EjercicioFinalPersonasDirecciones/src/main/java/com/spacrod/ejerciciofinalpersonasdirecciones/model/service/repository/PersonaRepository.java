package com.spacrod.ejerciciofinalpersonasdirecciones.model.service.repository;

import com.spacrod.ejerciciofinalpersonasdirecciones.model.entity.Persona;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class PersonaRepository implements CrudRepository<Persona>{
    private DatabaseConection databaseConection;

    public PersonaRepository() {
        databaseConection = new DatabaseConection();
    }

    @Override
    public Persona findById(int id) throws SQLException {
        String sql = "select * from persona where id = ?";
        Persona persona = new Persona();
        PreparedStatement preparedStatement = databaseConection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            persona.setId(resultSet.getInt("id"));
            persona.setNombre(resultSet.getString("nombre"));
            persona.setEdad(resultSet.getInt("edad"));
        }
        preparedStatement.close();
        resultSet.close();
        return persona;
    }

    @Override
    public List<Persona> findAll() throws SQLException {
        String sql = "select * from persona";
        List<Persona> personas = new ArrayList<>();
        Statement statement = databaseConection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Persona persona = new Persona();
            persona.setId(resultSet.getInt("id"));
            persona.setNombre(resultSet.getString("nombre"));
            persona.setEdad(resultSet.getInt("edad"));
            personas.add(persona);
        }
        statement.close();
        resultSet.close();
        return personas;
    }

    @Override
    public void save(Persona persona) throws SQLException {
        String sql = "insert into persona (nombre, edad) values (?, ?)";
        PreparedStatement preparedStatement = databaseConection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, persona.getNombre());
        preparedStatement.setInt(2, persona.getEdad());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from persona where id = ?";
        PreparedStatement preparedStatement = databaseConection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void update(Persona persona) throws SQLException {
        String sql = "update persona set nombre = ?, edad = ? where id = ?";
        PreparedStatement preparedStatement = databaseConection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, persona.getNombre());
        preparedStatement.setInt(2, persona.getEdad());
        preparedStatement.setInt(3, persona.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
