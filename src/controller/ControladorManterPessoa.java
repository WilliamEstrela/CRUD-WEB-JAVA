package controller;


import java.util.ArrayList;

import model.Pessoa;
import persistence.DAOPessoa;

public class ControladorManterPessoa{

	
    DAOPessoa dao;

    public ControladorManterPessoa() {
        dao = new DAOPessoa();
    }

    public void incluirPessoa(Pessoa pessoa){
        dao.inserir(pessoa);
    }

    /**
     * Lista de pessoas com base na PK
     * @param cpf
     * @return
     */
    public Pessoa ListarPessoa(int id) {
    	Pessoa pessoa = new Pessoa();
    	pessoa = dao.buscar(id);
    	
    	return pessoa;
    }
    
    /**
     * Lista celulares com base no IMEI
     * @param cpf
     * @return
     */
    public ArrayList<Pessoa> ListarPessoa(String cpf) {
    	
    	ArrayList<Pessoa> listaPessoas = dao.buscar(cpf);
    	
    	return (listaPessoas);
    }
	
    
    /**
     * Lista todas as pessoas
     * @return
     */
    public ArrayList<Pessoa> ListarPessoa() {
    	
    	ArrayList<Pessoa> listaPessoas = dao.buscar();
    	
    	return (listaPessoas);
    }

    /**
     * Atualiza uma pessoa no banco de dados
     * @param pessoa
     */
	public void atualizar(Pessoa pessoa) {
		dao.atualizar(pessoa);
		
	}
	
	public void remover(Pessoa pessoa) {
		dao.remover(pessoa);
	}
    
}
