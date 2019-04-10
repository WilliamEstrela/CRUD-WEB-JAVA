package controller;

import controle.ControleCRUD;
import model.Proprietario;

public class ControladorManterProprietario extends ControleCRUD{

	@Override
	public Proprietario getNewObjeto() {
		return new Proprietario();
	}

}
