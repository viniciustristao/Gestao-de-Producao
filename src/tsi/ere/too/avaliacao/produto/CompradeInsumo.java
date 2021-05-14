package tsi.ere.too.avaliacao.produto;
/**
 * Classe que representa uma compra de insumo contendo data, quantidade e valor de compra
 * @author tivin
 *
 */
public class CompradeInsumo{
	
	private int codigo;
	private Double valor,qtd,qtdAtual;
	private String dataCompra;
	private int codInsumofk;
	

	public CompradeInsumo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompradeInsumo(int codigo, Double valor, Double qtd, String dataCompra, int codInsumofk) {
		super();
		this.codigo = codigo;
		this.valor = valor;
		this.qtd = qtd;
		this.qtdAtual = qtd;
		this.dataCompra = dataCompra;
		this.codInsumofk = codInsumofk;
	}

	public int getCodigo() {
		return codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getQtd() {
		return qtd;
	}

	public void setQtd(Double qtd) {
		this.qtd = qtd;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public int getCodInsumofk() {
		return codInsumofk;
	}

	public void setCodInsumofk(int codInsumofk) {
		this.codInsumofk = codInsumofk;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Double getQtdAtual() {
		return qtdAtual;
	}

	public void utilizarInsumo(Double qtd) {
		qtdAtual-=qtd;
	}

	public void setQtdAtual(Double qtdAtual) {
		this.qtdAtual = qtdAtual;
	}
	
	public Double getvalorUnitario() {
		return valor/qtd;
	}

	@Override
	public String toString() {
		return String.format("CompradeInsumo %d - Insumo %d - Data: %s - Quantidade %1.2f - Valor unitario: R$%1.2f", codigo, codInsumofk, dataCompra, qtd, valor);
	}	
}//fim classe interna compra