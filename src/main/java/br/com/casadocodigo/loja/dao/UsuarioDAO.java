package br.com.casadocodigo.loja.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;



@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService{

	@Autowired
	private RoleDAO roleDAO;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	
	
	public void gravarUsuario(Usuario usuario) {
		
		
		  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		  
		  String senhaCriptogradada = null;
		  
		  senhaCriptogradada = passwordEncoder.encode(usuario.getSenha());
		  usuario.setSenha(senhaCriptogradada);
		  usuario.setSenharepetir(senhaCriptogradada);
		 
		
		manager.persist(usuario);
	}
	
	
	public Usuario findEmail(String email ) {
		Usuario usuario = null;
		
		try {
			usuario =  manager.createQuery("select distinct(u) from Usuario u  where u.email = :email", 
					Usuario.class).setParameter("email", email)
					.getSingleResult();
			
		} catch (NoResultException e) {
			
			System.out.println("Usuario: " + email + " não encontrado");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	
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
		return manager.createQuery("select distinct(u) from Usuario u order by u.nome", Usuario.class).getResultList();
	}

	//select distinct(p) from Produto p join fetch p.precos precos where p.id = :id
	public List<Usuario> listarUsuariosComRoles(String email) throws Exception  {
		
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		return usuarios;
		 
		
		/*
		 * return manager.createQuery("select u from Usuario u " +
		 * " left join usuario_role ur " +
		 * " on u.email = ur.email where u.email = :email", Usuario.class)
		 * .setParameter("email", email) .getResultList();
		 */
		
		
	}


	public boolean findUsuarioRole(String email, String role) throws Exception{
		boolean boolAchou = false;
		
		if (manager.createQuery("select * from usuario_role ur "
				+ "where ur.nome = :email "
				+ "and   ur.role = :role")
				.setParameter("email", email)
				.setParameter("", role)
				.getFirstResult() > 0) {
			boolAchou =true;
		}
		
		
		return boolAchou;
		
	}
	
	public void updateUsuario(Usuario usuario) throws Exception {
		
		System.out.println(usuario.getRoles());
		List<Role> roles = roleDAO.listarRole();

		Usuario user2;
		user2 = manager.find(Usuario.class, usuario.getEmail());

		for (int i = 0; i < roles.size(); i++) {
			if (user2.getRoles().size() > i) {
				user2.getRoles().remove(i);
			}

		}

		manager.merge(user2);
		manager.flush();

		manager.merge(usuario);
		manager.flush();
			

		
		
	}



	


	

}