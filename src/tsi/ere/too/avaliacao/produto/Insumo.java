package tsi.ere.too.avaliacao.produto;

import java.util.ArrayList;

public class Insumo {
	
	private int codigo,unidade;//4bytes+4unidade
	private String descricao;//80bytes_nome
	
	private ArrayList<CompradeInsumo> compradeInsumos;
	
	public Insumo() {
		compradeInsumos = new ArrayList<CompradeInsumo>();
	}

	public Insumo(int codigo, int unidade, String descricao) {
		super();
		this.codigo = codigo;
		this.unidade = unidade;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getUnidade() {
		return unidade;
	}

	public void setUnidade(int unidade) {
		this.unidade = unidade;
	}

	public String getdescricao() {
		return descricao;
	}

	public void setdescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<CompradeInsumo> getCompradeInsumos() {
		return compradeInsumos;
	}

	/**
	 * Atualiza o valor do produto e adiciona uma compra ao insumo,
	 * armanzenando a data atual valor unitario e quantidade da compra.
	 * @param valor Valor unitario de compra do produto
	 * @param quantidade Quantidade do produto comprado
	 */
	public void adicionarCompra(Double valor, Double qtd, String dataCompra) {
		int codigoC;
		codigoC = (compradeInsumos.size()>0)?compradeInsumos.get(compradeInsumos.size()-1).getCodigo()+1:1;
		CompradeInsumo compradeInsumo = new CompradeInsumo(codigoC, valor, qtd,dataCompra,codigo);
		this.compradeInsumos.add(compradeInsumo);
	}
	
	public void adicionarCompra(CompradeInsumo ci) {
		this.compradeInsumos.add(ci);
	}
	
	@Override
	public String toString() {
		return String.format("%d - Descrição: %s - Unidade de Medida: %s", codigo, descricao,Unidade.values()[unidade]);
	}

}//fim classe insumo
