package tsi.ere.too.avaliacao.produto;

import java.util.ArrayList;

public class Produto {
	
	private int codigo;
	private Double margemLucro, tamanhoUnidade;
	private String nome;
	private int unidade;
	private ArrayList<InsumoDeProduto> insumos;
	
	public Produto() {
		insumos = new ArrayList<InsumoDeProduto>();
	}

	public Produto(int codigo, Double precoUnitario, Double margemLucro, String nome, int unidade) {
		this();
		this.margemLucro = margemLucro;
		this.nome = nome;
		this.unidade = unidade;
		this.codigo = codigo;
		
	}

	public void setTamanhoUnidade(Double quantidade) {
		this.tamanhoUnidade = quantidade;
	}

	public Double getTamanhoUnidade() {
		return tamanhoUnidade;
	}

	public Double getMargemLucro() {
		return margemLucro;
	}

	public void setMargemLucro(Double margemLucro) {
		this.margemLucro = margemLucro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getUnidade() {
		return unidade;
	}

	public void setUnidade (int unidade) {
		this.unidade = unidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ArrayList<InsumoDeProduto> getInsumos() {
		return insumos;
	}

	public boolean cadastraInsumo(InsumoDeProduto insumo) {
		if(insumo==null)
			return false;
		insumos.add(insumo);
		return true;
	}
	
	public Double calcValorUnitario(){
		Double valorTotal=0d;
		for (InsumoDeProduto insumoDeProduto : insumos) {
			valorTotal+=insumoDeProduto.calculaPrecoUnitarioIP()*insumoDeProduto.getQtd();
		}
		return valorTotal;
	}
	
	@Override
	public String toString() {
		return String.format("%d-%s - Tamanho da unidade = %1.2f %s - Margem de Lucro = %1.2f", codigo, nome, tamanhoUnidade, Unidade.values()[unidade],margemLucro);
	}
}//fim classe produto
