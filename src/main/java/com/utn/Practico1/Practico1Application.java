package com.utn.Practico1;

import com.utn.Practico1.entidades.*;
import com.utn.Practico1.enums.*;
import com.utn.Practico1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication

public class Practico1Application {
    @Autowired
    ClienteRepositorio clienteRepositorio;
    @Autowired
    DomicilioRepositorio domicilioRepositorio;
    @Autowired
    FacturaRepositorio facturaRepositorio;
    @Autowired
    PedidoRepositorio pedidoRepositorio;
    @Autowired
    RubroRepositorio rubroRepositorio;
    @Autowired
    ProductoRepositorio productoRepositorio;
    @Autowired
    DetallePedidoRepositorio detallePedidoRepositorio;
    public static void main(String[] args) {
        SpringApplication.run(Practico1Application.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            // Código a ejecutar después de la inicialización de la aplicación
            System.out.println("La aplicación se ha iniciado. Realizando tareas iniciales...");

            //creo dos domicilios para el cliente de prueba

            Domicilio domicilio1 = Domicilio.builder()
                    .calle("Calle 1")
                    .localidad("Godoy Cruz")
                    .numero("123")
                    .build();

            Domicilio domicilio2 = Domicilio.builder()
                    .calle("Calle 2")
                    .localidad("Lujan de Cuyo")
                    .numero("456")
                    .build();

            //creo una factura y dos productos con dos detalles de pedido para el pedido

            SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy/MM/dd");
            String fechaString = "2022/09/06";
            Date fecha = formatFecha.parse(fechaString);

            Factura factura1 = Factura.builder()
                    .numero(15)
                    .fechaFactura(fecha)
                    .descuento(10.0)
                    .formaPago(FormaPago.mercadoPago)
                    .total(21600)
                    .build();

            Producto producto1 =Producto.builder()
                    .denominacion("Hamburguesa")
                    .tipoProducto(TipoProducto.manufacturado)
                    .stockActual(13)
                    .stockMinimo(5)
                    .tiempoEstimadoCocina(20)
                    .precioCompra(1500.0)
                    .precioVenta(3000.0)
                    .build();

            Producto producto2 =Producto.builder()
                    .denominacion("Pizza")
                    .tipoProducto(TipoProducto.manufacturado)
                    .stockActual(11)
                    .tiempoEstimadoCocina(15)
                    .stockMinimo(5)
                    .precioCompra(1200.0)
                    .precioVenta(4500.0)
                    .build();

            Pedido pedido1 = Pedido.builder()
                    .estadoPedido(EstadoPedido.entregado)
                    .fechaPedido(fecha)
                    .tipoEnvio(TipoEnvio.retira)
                    .factura(factura1)
                    .total(24000.0)
                    .build();

            DetallePedido detaPedi1 = DetallePedido.builder()
                    .cantidad(5)
                    .subtotal(15000.0)
                    .producto(producto1)
                    .build();

            DetallePedido detaPedi2 = DetallePedido.builder()
                    .cantidad(2)
                    .subtotal(9000.0)
                    .producto(producto2)
                    .build();

            Rubro rubro1 = Rubro.builder()
                    .denominacion("Burgers")
                    .build();

            Rubro rubro2 = Rubro.builder()
                    .denominacion("Pizza")
                    .build();

            // Crear instancia de Persona y agregar domicilios

            Cliente cliente = Cliente.builder()
                    .nombre("Juan")
                    .apellido("Perez")
                    .email("juan.perez@gmail.com")
                    .telefono("2616659286")
                    .build();

            cliente.agregarDomicilio(domicilio1);
            cliente.agregarDomicilio(domicilio2);
            cliente.agregarPedido(pedido1);
            pedido1.agregarDetallePedido(detaPedi1);
            pedido1.agregarDetallePedido(detaPedi2);
            rubro1.agregarProdcutos(producto1);
            rubro2.agregarProdcutos(producto2);

            // Guardar los objetos creados en la base de datos

            clienteRepositorio.save(cliente);

            rubroRepositorio.save(rubro1);
            rubroRepositorio.save(rubro2);

            //Aca recupero los objetos creados y guardados en la base de datos

            Cliente clienteRecuperado = clienteRepositorio.findById(cliente.getIdCliente()).orElse(null);

            DetallePedido detalleRecuperado1 = detallePedidoRepositorio.findById(detaPedi1.getIdDetallePedido()).orElse(null);
            DetallePedido detalleRecuperado2 = detallePedidoRepositorio.findById(detaPedi2.getIdDetallePedido()).orElse(null);

            Pedido pedidoRecuperado = pedidoRepositorio.findById(pedido1.getIdPedido()).orElse(null);

            Rubro rubroRecuperado1 = rubroRepositorio.findById(rubro1.getIdRubro()).orElse(null);
            Rubro rubroRecuperado2 = rubroRepositorio.findById(rubro2.getIdRubro()).orElse(null);

            // Muestro el objeto cliente recuperado

            System.out.println("--------Cliente--------");

            if (clienteRecuperado != null) {
                System.out.println("Nombre: " + clienteRecuperado.getNombre());
                System.out.println("Apellido: " + clienteRecuperado.getApellido());
                System.out.println("Email: " + clienteRecuperado.getEmail());
                System.out.println("Telefono: " + clienteRecuperado.getTelefono());
                clienteRecuperado.mostrarDomicilios();
                clienteRecuperado.mostrarPedido();
            }

            // Muestro los objetos detalles recuperados

            System.out.println("--------DetallePedido--------");

            System.out.println("-detalle 1-");

            if (detalleRecuperado1 != null ){
                System.out.println("Cantidad: " + detalleRecuperado1.getCantidad());
                System.out.println("Subtotal: " + detalleRecuperado1.getSubtotal());
                System.out.println("Producto: " + detalleRecuperado1.getProducto().getDenominacion());
            }

            System.out.println("-detalle 2-");

            if (detalleRecuperado2 != null ){
                System.out.println("Cantidad: " + detalleRecuperado2.getCantidad());
                System.out.println("Subtotal: " + detalleRecuperado2.getSubtotal());
                System.out.println("Producto: " + detalleRecuperado2.getProducto().getDenominacion());
            }

            // Muestro el objeto pedido recuperado

            System.out.println("--------Pedido--------");

            if (pedidoRecuperado != null ){
                System.out.println("Estado del pedido: " + pedidoRecuperado.getEstadoPedido().name());
                System.out.println("Fecha del pedido: " + pedidoRecuperado.getFechaPedido());
                System.out.println("Tipo de envio: " + pedidoRecuperado.getTipoEnvio().name());
                System.out.println("Total: " + pedidoRecuperado.getTotal());
                System.out.println("Factura: " + pedidoRecuperado.getFactura().getNumero() + ", Fecha de la factura: " + pedidoRecuperado.getFactura().getFechaFactura());
                System.out.println("Descuento aplicado: " + pedidoRecuperado.getFactura().getDescuento() + ", forma de pago: " + pedidoRecuperado.getFactura().getFormaPago().name() + ", Total: " + pedidoRecuperado.getFactura().getTotal());
                pedidoRecuperado.mostrarDetallePedido();
            }

            // Muestro el objeto rubro recuperado

            System.out.println("--------Rubro--------");

            if (rubroRecuperado1 != null){
                System.out.println("Denominacion del rubro: " + rubroRecuperado1.getDenominacion());
                rubroRecuperado1.mostrarProductos();
            }

            if (rubroRecuperado2 != null){
                System.out.println("Denominacion del rubro: " + rubroRecuperado2.getDenominacion());
                rubroRecuperado2.mostrarProductos();
            }

        };
    }
}
