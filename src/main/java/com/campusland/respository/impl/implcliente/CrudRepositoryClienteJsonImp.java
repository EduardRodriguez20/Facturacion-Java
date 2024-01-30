package com.campusland.respository.impl.implcliente;

import java.util.List;

import com.campusland.respository.CrudRepositoryCliente;
import com.campusland.respository.models.Cliente;
import com.campusland.utils.conexionesdb.conexiondbjson.ConexionBDJson;

public class CrudRepositoryClienteJsonImp implements CrudRepositoryCliente {

    ConexionBDJson conexion = ConexionBDJson.getConexion();

    @Override
    public List<Cliente> listar() {
        return conexion.getDataClientes();

    }

    @Override
    public Cliente porDocumento(String documento) {
        Cliente resultado = null;
        for (Cliente cliente : conexion.getDataClientes()) {
            if (cliente.getDocumento().equals(documento)) {
                resultado = cliente;
                break;
            }
        }
        return resultado;

    }

    @Override
    public void crear(Cliente cliente) {
        List<Cliente> listaClientes=conexion.getDataClientes();
        listaClientes.add(cliente);
        conexion.saveDateClientes(listaClientes);        
    }

    @Override
    public void editar(Cliente cliente) {
        List<Cliente> listClientes = conexion.getDataClientes();
        boolean change = false;
        for (Cliente clienteCurrent : listClientes) {
            if (clienteCurrent.getDocumento().equals(cliente.getDocumento())) {
                clienteCurrent.setNombre(cliente.getNombre());
                clienteCurrent.setApellido(cliente.getApellido());
                clienteCurrent.setDireccion(cliente.getDireccion());
                clienteCurrent.setEmail(cliente.getEmail());
                clienteCurrent.setCelular(cliente.getCelular());
                change = true;
                break;
            }
        }
        if (change)
            conexion.saveDateClientes(listClientes);
       
    }

    @Override
    public void eliminar(Cliente cli) {
        List<Cliente> listClientes = conexion.getDataClientes();
        boolean change = false;
        for (Cliente cliente : listClientes) {
            if (cliente.getDocumento().equals(cli.getDocumento())) {
                listClientes.remove(cliente);
                change = true;
                break;
            }

        }
        if (change)
            conexion.saveDateClientes(listClientes);
        
    }

}
