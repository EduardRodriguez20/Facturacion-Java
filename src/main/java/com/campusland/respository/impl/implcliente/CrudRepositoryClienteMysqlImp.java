package com.campusland.respository.impl.implcliente;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.CrudRepositoryCliente;
import com.campusland.respository.models.Cliente;
import com.campusland.utils.conexionesdb.conexiondbmysql.ConexionBDMysql;

public class CrudRepositoryClienteMysqlImp implements CrudRepositoryCliente {

    private Connection getConnection() throws SQLException {
        return ConexionBDMysql.getInstance();
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> listClientes = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM cliente");) {
            while (rs.next()) {
                listClientes.add(crearCliente(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listClientes;

    }

    @Override
    public Cliente porDocumento(String documento) {
         Cliente cliente = null;

        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM cliente WHERE documento=?")) {
            stmt.setString(1, documento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = crearCliente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;

    }

    @Override
    public void crear(Cliente cliente) {
        String sql = "INSERT INTO cliente(nombre, apellido,email,direccion,celular,documento) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getCelular());
            stmt.setString(6, cliente.getDocumento());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void editar(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, apellido=?, email=?, direccion=?, celular=? WHERE id=?";       
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getCelular());
            stmt.setInt(6, cliente.getId());          
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }        
    }

    @Override
    public void eliminar(Cliente cliente) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM cliente WHERE id=?")) {
            stmt.setInt(1, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }        
    }

    private Cliente crearCliente(ResultSet rs)throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setEmail(rs.getString("email"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setCelular(rs.getString("celular"));
        cliente.setDocumento(rs.getString("documento"));
        return cliente;

    }

}
