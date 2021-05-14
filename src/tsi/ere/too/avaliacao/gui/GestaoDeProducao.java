package tsi.ere.too.avaliacao.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tsi.ere.too.avaliacao.dados.ArquivoCompraInsumo;
import tsi.ere.too.avaliacao.dados.ArquivoInsProd;
import tsi.ere.too.avaliacao.dados.ArquivoInsumo;
import tsi.ere.too.avaliacao.dados.ArquivoProducao;
import tsi.ere.too.avaliacao.dados.ArquivoProduto;
import tsi.ere.too.avaliacao.dados.ArquivoProdutoDevenda;
import tsi.ere.too.avaliacao.dados.ArquivoVenda;
import tsi.ere.too.avaliacao.produto.CompradeInsumo;
import tsi.ere.too.avaliacao.produto.Insumo;
import tsi.ere.too.avaliacao.produto.InsumoDeProduto;
import tsi.ere.too.avaliacao.produto.Producao;
import tsi.ere.too.avaliacao.produto.Produto;
import tsi.ere.too.avaliacao.produto.ProdutoDeVenda;
import tsi.ere.too.avaliacao.produto.Venda;
import javax.swing.SwingConstants;

public class GestaoDeProducao extends JFrame {
	
	protected static ArrayList<Produto> produtos;
	protected static ArrayList<Insumo> insumos;
	protected static ArrayList<Producao> producao;
	protected static ArrayList<Venda> vendas;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestaoDeProducao frame = new GestaoDeProducao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestaoDeProducao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		produtos = new ArrayList<Produto>();
		insumos = new ArrayList<Insumo>();
		vendas = new ArrayList<Venda>();
		producao = new ArrayList<Producao>();
		carregadados();
		
		JButton btnPrduto = new JButton("Produto");
		btnPrduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPrduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutosGUI pg = new ProdutosGUI();
				pg.setVisible(true);
			}
		});
		btnPrduto.setSelectedIcon(new ImageIcon("C:\\Users\\tivin\\eclipse-workspace2020-03\\Gestao de Producao\\img\\novo-produto.png"));
		btnPrduto.setBounds(75, 59, 121, 120);
		contentPane.add(btnPrduto);
		
		JButton btnInsumo = new JButton("Insumo");
		btnInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsumosGUI ig = new InsumosGUI();
				ig.setVisible(true);
			}
		});
		btnInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInsumo.setBounds(206, 59, 121, 120);
		contentPane.add(btnInsumo);
		
		JButton btnProducao = new JButton("Producao");
		btnProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProducaoGUI producaoGUI= new ProducaoGUI();
				producaoGUI.setVisible(true);
			}
		});
		btnProducao.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnProducao.setBounds(337, 59, 121, 120);
		contentPane.add(btnProducao);
		
		JButton btnVenda = new JButton("Venda");
		btnVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendasGUI vendasGUI = new VendasGUI();
				vendasGUI.setVisible(true);
			}
		});
		btnVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVenda.setBounds(468, 59, 121, 120);
		contentPane.add(btnVenda);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSair.setBounds(504, 439, 85, 25);
		contentPane.add(btnSair);
		
		JButton btnRelatorioProducao = new JButton("<html>Relatorio<br\\>Producao");
		btnRelatorioProducao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioProducaoGUI rp = new RelatorioProducaoGUI();
				rp.setVisible(true);
			}
		});
		btnRelatorioProducao.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRelatorioProducao.setBounds(138, 210, 121, 120);
		contentPane.add(btnRelatorioProducao);
		
		JButton btnRelVenda = new JButton("<html>Relatorio<br\\>Venda");
		btnRelVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRelVenda.setBounds(278, 210, 121, 120);
		contentPane.add(btnRelVenda);
		
		JButton btnOrcamento = new JButton("Or\u00E7amento");
		btnOrcamento.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOrcamento.setBounds(418, 210, 121, 120);
		contentPane.add(btnOrcamento);
	}

	private void carregadados() {
		carregaProduto();
		carregaInsumo();
		carregaVenda();
		carregaProducao();
	}
	
	private void carregaProducao() {
		ArquivoProducao apdc = new ArquivoProducao();
		Producao pdc;
		try {
			apdc.openFile("arquivos\\tbpdc.gpv");
			for (int pos = 0; pos < apdc.recordQuantity(); pos++) {
				apdc.setFilePointer(pos);
				pdc = (Producao) apdc.readObject();
				producao.add(pdc);
				System.out.println(pdc);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void carregaVenda() {
		ArquivoVenda av = new ArquivoVenda();
		Venda venda;
		try {
			av.openFile("arquivos\\tbvd.gpv");
			for (long pos = 0; pos < av.recordQuantity(); pos++) {
				av.setFilePointer(pos);
				venda = (Venda)av.readObject();
				carregaProdutoVenda(venda);
				vendas.add(venda);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void carregaProdutoVenda(Venda venda) {
		ArquivoProdutoDevenda apv = new ArquivoProdutoDevenda();
		ProdutoDeVenda pdv;
		try {
			apv.openFile("arquivos\\tbpdv.gpv");
			for (long pos = 0; pos < apv.recordQuantity(); pos++) {
				apv.setFilePointer(pos);
				pdv=(ProdutoDeVenda)apv.readObject();
				if(pdv.getCodVendafk()==venda.getCodigo())
					venda.adicionaIntemVenda(pdv);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void carregaProduto() {
		ArquivoProduto arquivoProduto = new ArquivoProduto();
		Produto produto;
		try {
			arquivoProduto.openFile("arquivos\\tbpr.gpv");
			for (long reg = 0; reg < arquivoProduto.recordQuantity(); reg++) {
				arquivoProduto.setFilePointer(reg);
				produto=(Produto)arquivoProduto.readObject();
				carregaInsProd(produto);
				produtos.add(produto);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregaInsumo() {
		ArquivoInsumo arquivoInsumo = new ArquivoInsumo();
		Insumo insumo;
		try {
			arquivoInsumo.openFile("arquivos\\tbins.gpv");
			for (long reg = 0; reg < arquivoInsumo.recordQuantity(); reg++) {
				arquivoInsumo.setFilePointer(reg);
				insumo=(Insumo)arquivoInsumo.readObject();
				carregaCompras(insumo);
				insumos.add(insumo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void carregaCompras(Insumo insumo) {
		ArquivoCompraInsumo aci = new ArquivoCompraInsumo();
		CompradeInsumo ci;
		try {
			aci.openFile("arquivos\\tbci.gpv");
			for (long reg = 0; reg < aci.recordQuantity(); reg++) {
				aci.setFilePointer(reg);
				ci=(CompradeInsumo)aci.readObject();
				if(ci.getCodInsumofk()==insumo.getCodigo())
					insumo.adicionarCompra(ci);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void carregaInsProd(Produto produto) {
		ArquivoInsProd arquivoInsProd = new ArquivoInsProd();
		InsumoDeProduto ip;
		try {
			arquivoInsProd.openFile("arquivos\\tbip.gpv");
			for (long reg = 0; reg < arquivoInsProd.recordQuantity(); reg++) {
				arquivoInsProd.setFilePointer(reg);
				ip=(InsumoDeProduto)arquivoInsProd.readObject();
				if(ip.getCodprod()==produto.getCodigo())
					produto.cadastraInsumo(ip);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * busca produto na lista de produtos cadastrados pelo nome
	 * caso encontre retorna a posição que ele se encontra na lista
	 * se nao encontrar produto o mesmo nome retorna -1
	 * @param nome nome do produto a ser presquisado
	 * @return posição do produto encontrado na lista caso encontre ou -1 se nao encontrar
	 */
	public static int buscaPosProdutoNome(String nome) {
		Produto produto;
		if(nome!=null && !nome.isEmpty()) 
			for (int pos = 0; pos<produtos.size();pos++) {
				produto = produtos.get(pos);
				if (nome.equalsIgnoreCase(produto.getNome())) 
					return pos;
			}
		return -1;
	}
	
	public static Produto buscaProdutoCod(int cod) {
		Produto produto; 
		for (int pos = 0; pos<produtos.size();pos++) {
			produto = produtos.get(pos);
			if (cod==produto.getCodigo()) 
				return produto;
		}
		return null;
	}

}
