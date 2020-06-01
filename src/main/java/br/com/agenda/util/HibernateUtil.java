package br.com.agenda.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.agenda.entity.Contato;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration ac = new Configuration();
				ac.addAnnotatedClass(Contato.class);
				sessionFactory = ac.configure().buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed."
						+ ex);
				throw new ExceptionInInitializerError(ex);
			}
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}

}
