package model;

import java.util.List;

import interfaces.IConversor;
import util.Retorno;

public class Celular extends Modelo<Integer>{
	
	private String imei;
	private String modelo;
	private String cor;
	private String ano;
	
	
	@Override
	public String getModeloNome() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getModeloPKNome() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Object> getCamposValor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getUsarPkNaInsercao() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<IConversor> getCamposConversor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getCamposNome() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected List<String> getCamposObrigatorios() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected Retorno setCamposValor(List<Object> list) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Modelo<?> getNovoObjeto() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
