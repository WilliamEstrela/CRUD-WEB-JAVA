package controller;


import java.util.ArrayList;

import model.Celular;
import persistence.DAOCelular;

public class ControladorManterCelular{

	
    DAOCelular dao;

    public ControladorManterCelular() {
        dao = new DAOCelular();
    }

    public void incluirCelular(Celular celular){
        dao.inserir(celular);
    }

    /**
     * Lista celulares com base na PK
     * @param imei
     * @return
     */
    public Celular ListarCelular(int id) {
    	Celular celular = new Celular();
    	celular = dao.buscar(id);
    	
    	return celular;
    }
    
    /**
     * Lista celulares com base no IMEI
     * @param imei
     * @return
     */
    public ArrayList<Celular> ListarCelular(String imei) {
    	
    	ArrayList<Celular> listaCelulares = dao.buscar(imei);
    	
    	return (listaCelulares);
    }
	
    
    /**
     * Lista todos os celulares
     * @return
     */
    public ArrayList<Celular> ListarCelular() {
    	
    	ArrayList<Celular> listaCelulares = dao.buscar();
    	
    	return (listaCelulares);
    }

    /**
     * Atualiza um celular no banco de dados
     * @param celular
     */
	public void atualizar(Celular celular) {
		dao.atualizar(celular);
		
	}
	
	public void remover(Celular celular) {
		dao.remover(celular);
	}
    
}
