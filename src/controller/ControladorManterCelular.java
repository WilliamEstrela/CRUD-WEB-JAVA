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
    
}
