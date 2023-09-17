package com.utn.tpPersistenciaJPA;

import com.utn.tpPersistenciaJPA.TpPersistenciaJpaApplication.*;
import entidades.*;
import enumeraciones.Estado;
import enumeraciones.FormaDePago;
import enumeraciones.TipoDeEnvio;
import enumeraciones.TipoProducto;
import repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collections;



@SpringBootApplication
public class TpPersistenciaJpaApplication {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	DomicilioRepository domicilioRepository;

	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	RubroRepository rubroRepository;

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	PedidoRepository pedidoRepository;




	public static void main(String[] args) {
		SpringApplication.run(TpPersistenciaJpaApplication.class,args);
		System.out.println("I'm working, hello world!");
	}

	@Bean
CommandLineRunner init(ClienteRepository clienteRepository, RubroRepository rubroRepository){
	return args -> {
		System.out.println("******Funciono super bien******");

		Rubro rubro1 = new Rubro();
		rubro1.setDenominación("pizzas");

		Rubro rubro2 = new Rubro();
		rubro1.setDenominación("Milanesas");

		Producto producto1 = new Producto();
		producto1.setTipo(TipoProducto.Manufacturado);
		producto1.setTiempoEstimadoCocina(60);
		producto1.setDenominacion("Pizzas veganas");
		producto1.setPrecioVenta(600);
		producto1.setPrecioCompra(700);
		producto1.setStockMinimo(5);
		producto1.setStockActual(6);
		producto1.setUnidadMedida("unidad1");
		producto1.setReceta("Cocinar con mucho amor");

		Producto producto2 = new Producto();
		producto2.setTipo(TipoProducto.Manufacturado);
		producto2.setTiempoEstimadoCocina(60);
		producto2.setDenominacion("Pizzas veganas con tomate");
		producto2.setPrecioVenta(600);
		producto2.setPrecioCompra(700);
		producto2.setStockMinimo(5);
		producto2.setStockActual(6);
		producto2.setUnidadMedida("unidad1");
		producto2.setReceta("Cocinar con mucho mas amor");

		rubro1.agregarProductos(producto1);
		rubro1.agregarProductos(producto2);
		rubroRepository.save(rubro1);

		Cliente cliente1 = new Cliente();
		cliente1.setNombre("Vincent");
		cliente1.setApellido("Van gogh");
		cliente1.setEmail("illanesdaniela28@gmail.com");
		cliente1.setTelefono("01101100 01101111 01101100");

		Domicilio domicilio1 = new Domicilio();
		domicilio1.setCalle("Avenida Siempreviva");
		domicilio1.setNumero("742");
		domicilio1.setLocalidad("Springfield");

		Domicilio domicilio2 = new Domicilio();
		domicilio2.setCalle("Calle falsa");
		domicilio2.setNumero("123");
		domicilio2.setLocalidad("Arles");

		cliente1.agregarDomicilio(domicilio1);
		cliente1.agregarDomicilio(domicilio2);

		Pedido pedido1= new Pedido();
		pedido1.setTipoEnvio(TipoDeEnvio.Delivery);
		pedido1.setTotal(1500);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		String fechaPeroString = "18.02-1974";
		Date fecha3 = formatoFecha.parse(fechaPeroString);
		pedido1.setFecha(fecha3);
		pedido1.setEstado(Estado.Iniciado);

		Pedido pedido2= new Pedido();
		pedido2.setTipoEnvio(TipoDeEnvio.Delivery);
		pedido2.setTotal(1500);
		SimpleDateFormat formatoFecha1 = new SimpleDateFormat("dd-MM-yyyy");
		String fechaPeroString1 = "10-12-2008";
		Date fecha4 = formatoFecha.parse(fechaPeroString1);
		pedido2.setFecha(fecha4);
		pedido2.setEstado(Estado.Entregado);

		cliente1.agregarPedido(pedido1);
		cliente1.agregarPedido(pedido2);


		Factura factura1 = new Factura();
		factura1.setDescuento(0);
		factura1.setNumero(1234);
		SimpleDateFormat formatoFecha2 = new SimpleDateFormat("dd-MM-yyyy");
		String fechaPeroString2 = "02-08-2022";
		Date fecha = formatoFecha.parse(fechaPeroString2);
		factura1.setFecha(fecha);
		factura1.setFormaPago(FormaDePago.efectivo);
		factura1.setTotal(1000);

		Factura factura2 = new Factura();
		factura2.setDescuento(10);
		factura2.setNumero(123);
		SimpleDateFormat formatoFecha3 = new SimpleDateFormat("dd-MM-yyyy");
		String fechaPeroString3 = "28-06-2003";
		Date fecha2 = formatoFecha.parse(fechaPeroString3);
		factura2.setFecha(fecha2);
		factura2.setFormaPago(FormaDePago.efectivo);
		factura2.setTotal(500);

		DetallePedido detallePedido1 = new DetallePedido();
		detallePedido1.setCantidad(1);
		detallePedido1.setProducto(producto1);
		detallePedido1.setSubtotal(500);

		clienteRepository.save(cliente1);
		detallePedidoRepository.save(detallePedido1);
		domicilioRepository.save(domicilio1);
		domicilioRepository.save(domicilio2);
		facturaRepository.save(factura2);
		facturaRepository.save(factura1);
		pedidoRepository.save(pedido1);
		pedidoRepository.save(pedido2);

		Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
		if (clienteRecuperado != null) {
			System.out.println("Nombre: " + clienteRecuperado.getNombre());
			System.out.println("Apellido: " + clienteRecuperado.getApellido());
			System.out.println("Telefono: " + clienteRecuperado.getTelefono());
			System.out.println("Email: "+ clienteRecuperado.getEmail());
			clienteRecuperado.mostrarDomicilios();
			clienteRecuperado.mostrarPedidos();
		}

		Rubro rubrorecu=  rubroRepository.findById(rubro1.getId()).orElse(null);
		if (clienteRecuperado != null) {
			System.out.println("Denominación: " + rubrorecu.getDenominación());
			rubrorecu.mostrarProductos();
		}

		Rubro rubrorecu2=  rubroRepository.findById(rubro2.getId()).orElse(null);
		if (clienteRecuperado != null) {
			System.out.println("Denominación: " + rubrorecu2.getDenominación());
			rubrorecu2.mostrarProductos();
		}



	};
	}
}



