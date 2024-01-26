package com.campusland.views;

import java.util.List;

import com.campusland.exceptiones.clienteexceptions.ClienteNullException;
import com.campusland.respository.models.Cliente;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.ItemFactura;
import com.campusland.respository.models.Producto;

public class ViewFactura extends ViewMain{


    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearFactura();
                    break;
                case 2:
                    listarFactura();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 2);

    }


    public static int mostrarMenu() {
        System.out.println("----Menu--Factura----");
        System.out.println("1. Crear factura.");
        System.out.println("2. Listar factura.");      
        System.out.println("3. Salir ");
        return leer.nextInt();
    }

    public static void crearFactura(){
        System.out.println("Crear Factura");
        leer.nextLine();
        System.out.print("Ingrese el documento del cliente: ");
        String documento = leer.nextLine();
        Cliente cliente;
        try {
            cliente = serviceCliente.porDocumento(documento);
            System.out.println(cliente.getFullName());
        } catch (ClienteNullException e) {
            System.out.println(e.getMessage());
        }
        boolean finished = false;
        List<ItemFactura> items; 
        while (!finished) {
            System.out.println("Para salir ingrese 0");
            System.out.println("Codigo - Productos");
            for (Producto producto : serviceProducto.listar()) {
                System.out.println(producto.getCodigoNombre());
            }
            System.out.println("Ingrese el codigo del producto");
            int idProd = leer.nextInt();
            // Producto producto = serviceProducto.porCodigo(idProd);
            finished = true;
        }
    }

    public static void listarFactura() {
        System.out.println("Lista de Facturas");
        for (Factura factura : serviceFactura.listar()) {
            factura.display();
            System.out.println();
        }
    }
    
}
