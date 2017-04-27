package org.michu.webstore.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.michu.webstore.domain.Product;
import org.michu.webstore.domain.repository.ProductRepository;
import org.michu.webstore.exception.NoProductFoundUnderCategoryException;
import org.michu.webstore.exception.ProductNotFoundException;
import org.michu.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products") // class level mapping
public class ProductController {
	
	@Autowired		
	private ProductService productService;
	//default method for url .../products
	@RequestMapping
	public String list(Model model){
		
		model.addAttribute("products", productService.getAllProducts() );
		return "products";
	}
	
	@RequestMapping("/all")
	public String allProducts(Model model){
		
		model.addAttribute("products", productService.getAllProducts() );
		return "products";
	}
	
	@RequestMapping("/{category}")
	public String getProductByCategory(Model model, @PathVariable("category") String productCategory){
		List<Product> products= productService.getProductByCategory(productCategory); 
		if(products == null || products.isEmpty()){
			throw new NoProductFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductByFilter(Model model, @MatrixVariable(pathVar="ByCriteria") Map<String, List<String>> filterParams){
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	@RequestMapping("/product")
	public String getProductById(Model model, @RequestParam("id") String productId){
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
	
	@RequestMapping(value ="/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){		
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	
	@RequestMapping(value ="/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product productToBeAdded, BindingResult result, HttpServletRequest request){			
		String[] suppressedFields = result.getSuppressedFields();
		if(suppressedFields.length >0){
			throw new RuntimeException("Próba wiązania niedozwolonych pól: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		MultipartFile productImage = productToBeAdded.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		if(productImage!=null && !productImage.isEmpty()){
			try{
				productImage.transferTo(new File(rootDirectory+"resources\\images\\"+productToBeAdded.getProductID() + ".png"));
			}catch(Exception e){
				throw new RuntimeException("Niepowodzenie podczas próby zapisu obrazka produktu",e);
			}
		}
		productService.addProduct(productToBeAdded);
		return "redirect:/products";
	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, ProductNotFoundException exception){
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductID());
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder){
		binder.setDisallowedFields("unitsInOrder","discontinued");		
	}
}
