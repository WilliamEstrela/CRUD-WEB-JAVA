package controller;

import java.util.ArrayList;

import model.Pessoa;
import model.Carro;
import persistence.DAOCarro;

public class ControladorManterCarro{

	DAOCarro dao = new DAOCarro();
	
	/**
	 * Retorna todos os carros cadastradas no banco
	 * @return lista de carros no banco
	 */
    public ArrayList<Carro> ListarCarro() {
    	
    	ArrayList<Carro> carros = dao.obterCarros();
    	
    	return carros;
    }
	
}
