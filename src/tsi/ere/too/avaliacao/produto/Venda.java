package tsi.ere.too.avaliacao.produto;

import java.util.ArrayList;

public class Venda {
	private String data, hora;
	private Double valorTotal;
	private ArrayList<ProdutoDeVenda> itensVenda;
	
	private int codigo;
	
	public Venda() {
		itensVenda = new ArrayList<ProdutoDeVenda>();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ArrayList<ProdutoDeVenda> getItensVenda() {
		return itensVenda;
	}

	public void adicionaIntemVenda(ProdutoDeVenda pv) {
		itensVenda.add(pv);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
