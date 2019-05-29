package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
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

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping(value="/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@InitBinder()
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}
	
	
	@RequestMapping(value="/form" )
	public ModelAndView form(Usuario usuario) {
		
		ModelAndView modelAndView = new ModelAndView("/usuarios/form");
		
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/form",method=RequestMethod.POST)
	public ModelAndView gravarUsuario(@Valid Usuario usuario, BindingResult result, 
			RedirectAttributes redirectAttributes) throws Exception {
		
		if (result.hasErrors()) {
			return form(usuario);
		}
		
		
			usuarioDAO.gravarUsuario(usuario);
			redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso!");
	
			return new ModelAndView("redirect:/usuarios");
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listarUsuario() throws Exception {
		List<Usuario> usuarios = usuarioDAO.listarUsuarios();
		ModelAndView modelAndView = new ModelAndView("/usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
		
	}
	
	@RequestMapping(value="/formRoleUsuario/{email:.+}", method=RequestMethod.GET)
	public ModelAndView detalhe( @PathVariable("email") String email){
	    ModelAndView modelAndView = new ModelAndView("/usuarios/formRoleUsuario");
	    
	    try {
		    Usuario usuarios = usuarioDAO.findEmail(email);
		    List<Role> roles = new ArrayList<Role>();
		    roles = roleDAO.listarRole();
		    
//		    if(true) throw new RuntimeException("Excessão Genérica Acontecendo!!!!");
		    
		    modelAndView.addObject("usuarios", usuarios);
		    modelAndView.addObject("roles", roles);
		    return modelAndView;	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return modelAndView;

	}	
	
	
	
	
	@RequestMapping(name = "updateRolesdoUsuario", method = RequestMethod.POST)
	public ModelAndView updateRolesdoUsuario(Usuario usuario, Role roles) {

		usuarioDAO.updateUsuario(usuario, roles);

		return new ModelAndView("redirect:/");

	}
	 
	@ExceptionHandler(ConstraintViolationException.class)
	public String trataErroEmailDuplicado() {
		
		return "errorusuarioduplicado";
	}
	
	
	

}
