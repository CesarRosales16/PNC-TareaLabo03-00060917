package com.uca.capas.tarealabo3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.tarealabo3.domain.Product;

@Controller
public class ProductController {

	private List<Product> productos = new ArrayList<Product>();

	@GetMapping("/comprarProducto")
	public ModelAndView comprarProducto() {

		ModelAndView mav = new ModelAndView();

		productos.add(new Product(0, "12 pack cerveza Regia", 30));
		productos.add(new Product(1, "6 pack Regia Chola", 90));
		productos.add(new Product(2, "Jose Cuervo Litro", 50));
		productos.add(new Product(3, "Absolut Vodka 750ml", 70));
		productos.add(new Product(4, "Caja cerveza Corona Extra", 10));
		productos.add(new Product(5, "Smirnoff Ice Manzana verde", 30));

		mav.setViewName("productos");
		mav.addObject("product", new Product());
		mav.addObject("productos", productos);

		return mav;
	}

	@PostMapping("/validar")
	public ModelAndView validar(Product product) {
		ModelAndView mav = new ModelAndView();

		System.out.println("Cantidad ingresada: " + product.getCantidad() + "\n");
		System.out.println("Cantidad en stock: " + productos.get(product.getId()).getCantidad() + "\n");

		if (product.getCantidad() > 0 && product.getCantidad() <= productos.get(product.getId()).getCantidad()) {

			int newStock = productos.get(product.getId()).getCantidad() - product.getCantidad();
			System.out.println("Stock - ingresada: " + newStock + "\n");
			productos.get(product.getId()).setCantidad(newStock);
			System.out.println("Stock modificado del producto: " + productos.get(product.getId()).getCantidad() + "\n");

			mav.setViewName("compra");
		} else
			mav.setViewName("error");

		mav.addObject("nombre", productos.get(product.getId()).getNombre());
		return mav;
	}
}
