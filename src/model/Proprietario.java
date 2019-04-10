package model;

import java.util.ArrayList;
import java.util.List;

import interfaces.IConversor;
import modelo.Modelo;
import util.Retorno;

public class Proprietario extends Modelo<Integer>{
	
	private String cpf;
	private String nome;
	private String telefone;
	
	private Celular celular;
	
	
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
		ArrayList<Object> list = new ArrayList<>();
		
		list.add(this.getPk());
		list.add(this.getCpf());
		list.add(this.getNome());
		list.add(this.getTelefone());
		
		list.add(this.getCelular());
		
		return list;
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
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("pk");
		listNomes.add("cpf");
		listNomes.add("nome");
		listNomes.add("telefone");
		
		listNomes.add("celular");
		return listNomes;
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
		return new Proprietario();
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Celular getCelular() {
		return celular;
	}
	public void setCelular(Celular celular) {
		this.celular = celular;
	}

}
