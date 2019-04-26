package controller;

import java.util.ArrayList;

import model.Celular;
import model.Marca;
import persistence.DAOMarca;

public class ControladorManterMarca{

	DAOMarca dao = new DAOMarca();
	
	/**
	 * Retorna todas as marcas cadastradas no banco
	 * @return lista de marcas no banco
	 */
    public ArrayList<Marca> ListarMarca() {
    	
    	ArrayList<Marca> marcas = dao.obterMarcas();
    	
    	return marcas;
    }
	
}
