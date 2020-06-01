package br.com.agenda.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.agenda.entity.Contato;
import br.com.agenda.util.HibernateUtil;

public class ContatoDAOImp implements ContatoDAO{
	
	public void save(Contato contato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(contato);
		t.commit();
		
	}
	
	public List getPesquisa(String nome) {		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Query qr = session.createQuery("from Contato where nome like ?");
		qr.setParameter(0, "%"+nome+"%");
		t.commit();
		System.out.println(qr.list());		
		return qr.list();
	}
	
	public Contato getContato(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();		
		return (Contato) session.load(Contato.class, id);
	}

	public List<Contato> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from Contato").list();
		t.commit();		
		
		return lista;
	}
	
	public void remove(Contato contato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(contato);
		t.commit();		
	}
	public void update(Contato contato) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(contato);
		t.commit();		
	}

	

}
