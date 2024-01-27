package com.campusland.views;

import java.time.LocalDateTime;

import com.campusland.exceptiones.clienteexceptions.ClienteNullException;
import com.campusland.exceptiones.clienteexceptions.ProductoNullException;
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
        try {
            Cliente cliente = serviceCliente.porDocumento(documento);
            System.out.println(cliente.getFullName());
            Factura factura = new Factura(LocalDateTime.now(), cliente);
            agregarItems(factura);
            serviceFactura.crear(factura);
        } catch (ClienteNullException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void agregarItems(Factura factura){
        boolean finished = false;
        while (!finished) {
            System.out.println("Ingreso de items");
            System.out.println("Para salir ingrese 0");
            System.out.println("Codigo - Productos");
            for (Producto producto : serviceProducto.listar()) {
                System.out.println(producto.getCodigoNombre());
            }
            System.out.println("Ingrese el codigo del producto");
            int idProd = leer.nextInt();
            if (idProd == 0) {
                finished = true;
                System.out.println("Terminaste de ingresar items");
            }else{
                try {
                    Producto producto = serviceProducto.porCodigo(idProd);
                    if(producto != null){
                        System.out.println("Ingrese la cantidad: ");
                        int quantity = leer.nextInt();
                        System.out.println("Producto: " + producto.getNombre() + " - cantidad: " + quantity);
                        ItemFactura item = new ItemFactura(quantity, producto);
                        factura.agregarItem(item);
                    }
                } catch (ProductoNullException e) {
                    System.out.println(e.getMessage());
                }
            }
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
