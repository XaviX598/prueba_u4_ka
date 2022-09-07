package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.Venta;
import com.uce.edu.demo.service.IVentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	private IVentaService iVentaService;

	// GET
	@GetMapping("/buscar")
	// ponemos Srting porque va a mandar una vista
	public String buscarTodos(Model modelo) {
		List<Venta> lista = this.iVentaService.buscarTodos();
		modelo.addAttribute("ventas", lista); // "personas es el nombre del .html en templates
		return "vistaListaVentas";
	}

	@PutMapping("/actualizar/{idPersona}")
	public String actualizarPersona(@PathVariable("idPersona") Integer id, Venta venta) {
		venta.setId(id);
		this.iVentaService.actualizar(venta);
		return "redirect:/productos/buscar";
	}

//	@DeleteMapping("/borrar/{idPersona}")
//	public String borrarPersona(@PathVariable("idPersona") Integer id) {
//		this.iProductoService.eliminar(id);
//		return "redirect:/personas/buscar";
//	}

	@PostMapping("/insertar")
	public String insertarProductos(Producto producto) {
		this.iVentaService.realizarVenta(null, null, null);
		return "redirect:/productos/buscar";
	}

//	// Métodos de redireccionamientos a páginas
//	@GetMapping("/nuevoProducto")
//	public String paginaNuevoProducto(Producto producto) {
//		return "vistaNuevoProducto";
//	}

}
