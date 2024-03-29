package com.campusland.respository.impl.implfactura;

import java.util.List;

import com.campusland.respository.CrudRepositoryFactura;
import com.campusland.respository.models.Factura;
import com.campusland.utils.conexionesdb.conexiondblist.ConexionBDList;

public class CrudRepositoryFacturaImp implements CrudRepositoryFactura {

    ConexionBDList conexion = ConexionBDList.getConexion();

    @Override
    public List<Factura> listar() {
        return conexion.getListFacturas();
        
    }

    @Override
    public void crear(Factura factura) {
       conexion.getListFacturas().add(factura);
        
    }

}
