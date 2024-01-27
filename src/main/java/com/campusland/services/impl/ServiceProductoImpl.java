package com.campusland.services.impl;

import java.util.List;

import com.campusland.exceptiones.clienteexceptions.ProductoNullException;
import com.campusland.respository.CrudRepositoryProducto;
import com.campusland.respository.models.Producto;
import com.campusland.services.ServiceProducto;

public class ServiceProductoImpl implements ServiceProducto {

    private final CrudRepositoryProducto crudRepositoryProducto;

    public ServiceProductoImpl(CrudRepositoryProducto crudRepositoryProducto){
        this.crudRepositoryProducto=crudRepositoryProducto;
    }

    @Override
    public List<Producto> listar() {
         return this.crudRepositoryProducto.listar();
        
    }

    @Override
    public Producto porCodigo(int codigo)throws ProductoNullException  {
        Producto producto = this.crudRepositoryProducto.porCodigo(codigo);
        if (producto != null) {
            return producto;
        } else {
            throw new ProductoNullException("No se encontro producto por codigo");
        }
    }

    @Override
    public void crear(Producto producto) {
        this.crudRepositoryProducto.crear(producto);        
    }

    @Override
    public void editar(Producto producto) {
        this.crudRepositoryProducto.editar(producto);
       
    }

    @Override
    public void eliminar(int codigo) {
        this.crudRepositoryProducto.eliminar(codigo);
    }
    
}
