package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.casadocodigo.loja.models.Usuario;



@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService{

	
	@PersistenceContext
	private EntityManager manager;
	
	
	public void gravarUsuario(Usuario usuario) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		manager.persist(usuario);
	}
	
	
	public Usuario findEmail(String email ) {
		
		return manager.createQuery("select u from Usuario u  where u.email = :email", 
				Usuario.class).setParameter("email", email)
				.getSingleResult();
	
	}


	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}
		
		return usuarios.get(0);
	}

	public List<Usuario> listarUsuarios() throws Exception  {
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}


}