package br.com.casadocodigo.loja.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;

@Repository
@Transactional
public class RoleDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Role role) {
		manager.persist(role);
	}
	
	public List<Role> listarRole() {
		
		List<Role> roles =  new ArrayList<Role>();
		
		try {
			roles = manager.createQuery("select r from Role r order by r.nome ", Role.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roles;
	}
	

	
	
}
