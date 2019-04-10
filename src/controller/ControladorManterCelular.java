package controller;

import controle.ControleCRUD;
import model.Celular;

public class ControladorManterCelular extends ControleCRUD{

	@Override
	public Celular getNewObjeto() {
		return new Celular();
	}

}
