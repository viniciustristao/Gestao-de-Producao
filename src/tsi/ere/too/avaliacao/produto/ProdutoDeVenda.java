package tsi.ere.too.avaliacao.produto;

public class ProdutoDeVenda{

	public int codigo;
	
	private int codProduto;
	private Double qtd, precoUnitario;
	private int codVendafk;
	
	public ProdutoDeVenda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProdutoDeVenda(int codProduto, Double precoUnitario, Double qtd, int codVendafk) {
		this.codProduto = codProduto;
		this.precoUnitario = precoUnitario;
		this.qtd = qtd;
		this.codVendafk = codVendafk;
	}

	public int getCodVendafk() {
		return codVendafk;
	}

	public void setCodVendafk(int codVendafk) {
		this.codVendafk = codVendafk;
	}

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Double getQtdProdutoVenda() {
		return qtd;
	}

	public void setQtdProdutoVenda(Double qtd) {
		this.qtd = qtd;
	}

	@Override
	public String toString() {
		return String.format("Produto: %d - Preco unitário: R$%1.2f - Quantidade %1.2f",codProduto, precoUnitario, qtd);
	}
	
}