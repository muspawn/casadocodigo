package br.com.casadocodigo.loja.controllers;


import java.util.concurrent.Callable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.casadocodigo.loja.models.Pedidos;



@Controller



public class PedidosServicoController {

	
	@Autowired
	RestTemplate restTemplate;
	

	
	
	String uri = "https://book-payment.herokuapp.com/orders";
	
    @GetMapping
    @RequestMapping(value="/pedidos")
    public Callable<ModelAndView> pedidosListar(RedirectAttributes model){
		return () ->{
			
			try {
                Pedidos[] response = restTemplate.getForObject(uri ,Pedidos[].class);
                ModelAndView modelAndView = new ModelAndView("pedidos");

                modelAndView.addObject("response", response);
                    
                    
                    
                    return modelAndView;                    
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("redirect:/pedidos");
			}
			
			
		};
    	
    }
	
	
	
	
}
