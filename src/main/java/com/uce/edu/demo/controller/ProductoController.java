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
import com.uce.edu.demo.service.IProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private IProductoService iProductoService;

	// GET
		@GetMapping("/buscar")
		public String buscarTodos(Model modelo) {
			List<Producto> lista = this.iProductoService.buscarTodos();
			modelo.addAttribute("productos", lista);
			return "vistaListaProductos";
		}

	@GetMapping("/buscarUno/{codigoProducto}")
	public String buscarPersona(@PathVariable("codigoProducto") String codigo, Model modelo) {
		System.out.println("El codigo es: " + codigo);
		Producto pro = this.iProductoService.buscarCodigoBarras(codigo);
		modelo.addAttribute("producto", pro);
		return "vistaProducto";
	}

	@PutMapping("/actualizar/{idPersona}")
	public String actualizarPersona(@PathVariable("idPersona") Integer id, Producto producto) {
		producto.setId(id);
		this.iProductoService.actualizar(producto);
		return "redirect:/productos/buscar";
	}

//	@DeleteMapping("/borrar/{idPersona}")
//	public String borrarPersona(@PathVariable("idPersona") Integer id) {
//		this.iProductoService.eliminar(id);
//		return "redirect:/personas/buscar";
//	}

	@PostMapping("/insertar")
	public String insertarProductos(Producto producto) {
		this.iProductoService.ingresarProducto(producto);
		return "redirect:/productos/buscar";
	}

	// Métodos de redireccionamientos a páginas
	@GetMapping("/nuevoProducto")
	public String paginaNuevoProducto(Producto producto) {
		return "vistaNuevoProducto";
	}

}
