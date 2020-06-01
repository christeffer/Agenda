package br.com.agenda.dao;

import java.util.List;

import br.com.agenda.entity.Contato;

public interface ContatoDAO {

	public void save(Contato contato);
	public Contato getContato(long id);
	public List getPesquisa(String nome);
	public List<Contato> list();
	public void remove(Contato contato);
	public void update(Contato contato);
}
