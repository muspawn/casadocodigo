package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@Transactional
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}
	
	
	@RequestMapping(value="/form")
	public ModelAndView form(Usuario usuario) {
		
		ModelAndView modelAndView = new ModelAndView("/usuarios/form");
		
		return modelAndView;
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravarUsuario(@Valid Usuario usuario, BindingResult result, 
			RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			return form(usuario);
		}
		
		try {
			usuarioDAO.gravarUsuario(usuario);
			redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			return form(usuario);
		}
			return new ModelAndView("redirect:/usuarios/lista");
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listarUsuario() throws Exception {
		List<Usuario> usuarios = usuarioDAO.listarUsuarios();
		ModelAndView modelAndView = new ModelAndView("/usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
		
	}
	
	@RequestMapping("/detalhe/{email}")
	public ModelAndView detalhe(@PathVariable("email") String email){
	    ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
	    Usuario usuario = usuarioDAO.findEmail(email);
	    
//	    if(true) throw new RuntimeException("Excessão Genérica Acontecendo!!!!");
	    
	    modelAndView.addObject("usuarios", usuario);
	    return modelAndView;
	}	

	
	
	

}
