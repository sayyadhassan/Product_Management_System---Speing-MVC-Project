package productcrudapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrudapp.dao.ProductDao;
import productcrudapp.model.Product;

@Controller
public class MainController {
	
	@Autowired
	private ProductDao productdao;
	
	@RequestMapping("/")
	public String home(Model m) {
		
		List<Product> products = productdao.getProduct();
		m.addAttribute("products", products);
		
		return "index";
	}

	
	// show add product form
	@RequestMapping ("/add-product")
	public String addProduct(Model model) {
		
		model.addAttribute("title","Add Product");
		return "add_Product_form";
	}
	
	// handel add product form
	@RequestMapping(value="/handel-product", method=RequestMethod.POST)
	public RedirectView hanelProduct(@ModelAttribute Product product, HttpServletRequest request) {
		
		System.out.println(product);
		this.productdao.createProduct(product);
		
		RedirectView redirectview = new RedirectView();
		redirectview.setUrl(request.getContextPath()+"/");
		
		return redirectview;
		
	}
	
	
	// delete handler
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId,HttpServletRequest request) {
		
		this.productdao.deleteProduct(productId);
		
		RedirectView redirectview = new RedirectView();
		redirectview.setUrl(request.getContextPath()+"/");
		
		return redirectview;
		
	}
	
	
	
	
}
