package tsi.ere.too.avaliacao.produto;

import java.util.ArrayList;

import tsi.ere.too.avaliacao.gui.GestaoDeProducao;

public class Producao {
	private int codigo;//4
	private String data;//20
	private Double qtd, qtdATual;//8+8
	private int codProdutofk;//4
	
	public Producao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Producao(int codigo,String data, int codProdutofk) {
		this.data = data;
		this.codProdutofk = codProdutofk;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public int getCodProdutofk() {
		return codProdutofk;
	}

	public void setCodProdutofk(int codProdutofk) {
		this.codProdutofk = codProdutofk;
	}
	
	public Double getQtd() {
		return qtd;
	}

	public void setQtd(Double qtd) {
		this.qtd = qtd;
	}

	public Double getQtdATual() {
		return qtdATual;
	}

	public void setQtdATual(Double qtdATual) {
		this.qtdATual = qtdATual;
	}

	public Double calculaValorUnitario(ArrayList<InsumoDeProduto> insumos) {
		Double valorUnitario = 0d;
		for (InsumoDeProduto ip : insumos) {
			valorUnitario+=ip.calculaPrecoUnitarioIP()*ip.getQtd();
		}
		return valorUnitario;
	}
	
	public Double calculaValorTotalVenda(Produto produto) {
		return calculaCusto(produto)+(calculaCusto(produto)*(produto.getMargemLucro()/100));
	}
	
	public Double calculaVendaEstoque(Produto produto,ArrayList<Producao>producao) {
		return calculaCustoEstoque(produto, producao)+(calculaCustoEstoque(produto,producao)*(produto.getMargemLucro()/100));
	}
	
	public Double calculaCusto(Produto produto) {
		return calculaValorUnitario(produto.getInsumos())*qtd;
	}
	
	public Double calculaCustoEstoque(Produto produto, ArrayList<Producao>producao) {
		Double totalEstoque=0d;
		for (Producao pdc : producao) {
			if(pdc.getCodProdutofk()==produto.getCodigo())
				totalEstoque += calculaValorUnitario(produto.getInsumos())*pdc.getQtdATual();
		}
		totalEstoque += calculaValorUnitario(produto.getInsumos())*getQtdATual();
		return totalEstoque;
	}
	
	public Double custoAcumulado(ArrayList<Producao> producaoG,int codProd) {
		Double cAcumulado=0d;
			for (Producao producao : producaoG) {
				if(producao.getCodProdutofk()==codProd)
					if(producao.getQtd()>0)
						cAcumulado+=producao.calculaCusto(GestaoDeProducao.buscaProdutoCod(codProd));
			}
		return cAcumulado;
	}
	
	public Double valorVendaAcumulado(ArrayList<Producao> producaoG,int codProd) {
		Double vendaAcumulado=0d;
			for (Producao producao : producaoG) {
				if(producao.getCodProdutofk()==codProd)
					if(producao.getQtd()>0)
						vendaAcumulado+=producao.calculaVendaEstoque(GestaoDeProducao.buscaProdutoCod(codProd), producaoG);
			}
		return vendaAcumulado;
	}
	
	@Override
	public String toString() {
		return String.format("%d - Nome Produto: %s - Data: %s - Quantidade: %1.2f - Quantidade Atual: %1.2f",codigo, GestaoDeProducao.buscaProdutoCod(codProdutofk).getNome() ,data, qtd, qtdATual);
	}
	
}
