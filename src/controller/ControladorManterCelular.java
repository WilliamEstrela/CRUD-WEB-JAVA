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

    //lista o celular com base no imei
    public ArrayList<Celular> ListarCelular(String imei) {
    	
    	ArrayList<Celular> listaCelulares = dao.buscar(imei);
    	
    	return (listaCelulares);
    }
    
}
