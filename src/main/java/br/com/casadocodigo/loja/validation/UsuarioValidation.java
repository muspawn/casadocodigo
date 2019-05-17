package br.com.casadocodigo.loja.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator{
	
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "nome"			, "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email"			, "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senha"			, "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senharepetir"	, "field.required");
		
		try {

			Usuario usuario = (Usuario) target;
			
			if (!usuario.getSenha().equals(usuario.getSenharepetir())) {
				errors.rejectValue("senha", "notmatch.password");
			}
			
			if (usuario.toString().length() < 6) {
				errors.rejectValue("senha", "number.smallnumber");;
			}
			
			System.out.println(usuario.getEmail());
			
	
			

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	
	
}
