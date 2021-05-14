package tsi.ere.too.avaliacao.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import tsi.ere.too.avaliacao.acessorios.Acessorios;
import tsi.ere.too.avaliacao.dados.ArquivoProdutoDevenda;
import tsi.ere.too.avaliacao.dados.ArquivoVenda;
import tsi.ere.too.avaliacao.produto.Produto;
import tsi.ere.too.avaliacao.produto.ProdutoDeVenda;
import tsi.ere.too.avaliacao.produto.Venda;
import javax.swing.SpinnerNumberModel;

public class VendasGUI extends JFrame {

	private final String[] COLUNAS = {"Produto","Quantidade","Preco Unitario","Valor Total"};
	
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTable tbProdutosVenda;
	private DefaultTableModel dtm;
	private JTextField txtValorTotal;
	private JButton btnCancelar;
	private JButton btnConcluir;
	private JButton btnAdicionarProduto;
	private JComboBox<String> cbProduto;
	private JFormattedTextField txtHora;
	private JFormattedTextField txtData;
	private JSpinner spnQtd;
	private Venda venda;
	private ProdutoDeVenda produtov;
	
	/**
	 * Create the frame.
	 */
	public VendasGUI() {
		inicializaComponentes();
		
	}
	private void inicializaComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 36, 471, 217);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCodigo.setBounds(10, 29, 57, 17);
		panel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCodigo.setBounds(66, 16, 70, 30);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblData.setBounds(158, 29, 40, 17);
		panel.add(lblData);
		
		txtData = new JFormattedTextField();
		txtData.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtData.setBounds(200, 16, 105, 30);
		dataAtual();
		panel.add(txtData);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHora.setBounds(329, 29, 46, 17);
		panel.add(lblHora);
		
		txtHora = new JFormattedTextField();
		txtHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtHora.setBounds(373, 16, 80, 30);
		horaAtual();
		panel.add(txtHora);
		
		JPanel pnProduto = new JPanel();
		pnProduto.setBorder(new TitledBorder(null, "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnProduto.setBounds(10, 56, 451, 151);
		panel.add(pnProduto);
		pnProduto.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProduto.setBounds(35, 30, 65, 17);
		pnProduto.add(lblProduto);
		
		cbProduto = new JComboBox<String>();
		cbProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbProduto.setBounds(101, 17, 290, 30);
		ProdutosGUI.atualizaProdCB(cbProduto);
		pnProduto.add(cbProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantidade.setBounds(10, 70, 90, 17);
		pnProduto.add(lblQuantidade);
		
		spnQtd = new JSpinner();
		spnQtd.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		spnQtd.setFont(new Font("Tahoma", Font.BOLD, 14));
		spnQtd.setBounds(101, 57, 65, 30);
		pnProduto.add(spnQtd);
		
		btnAdicionarProduto = new JButton("Adicionar Produto a venda");
		btnAdicionarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insereProdutoVenda();
			}
		});
		btnAdicionarProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdicionarProduto.setBounds(201, 111, 230, 30);
		pnProduto.add(btnAdicionarProduto);
		
		JLabel lblVenda = new JLabel("Venda");
		lblVenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVenda.setBounds(10, 10, 471, 17);
		contentPane.add(lblVenda);
		
		JScrollPane scpTabela = new JScrollPane();
		scpTabela.setBounds(10, 263, 471, 231);
		scpTabela.setBorder(new TitledBorder(null, "Tabela de Insumos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scpTabela);
		
		tbProdutosVenda = new JTable();
		scpTabela.setViewportView(tbProdutosVenda);
		dtm = new DefaultTableModel(COLUNAS,0);
		tbProdutosVenda.setModel(dtm);
		
		JLabel lblNewLabel = new JLabel("Valor Total da Venda: R$");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 517, 178, 17);
		contentPane.add(lblNewLabel);
		
		txtValorTotal = new JTextField();
		txtValorTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtValorTotal.setBounds(188, 504, 125, 30);
		contentPane.add(txtValorTotal);
		txtValorTotal.setColumns(10);
		
		btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaVenda();
			}
		});
		btnConcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConcluir.setBounds(270, 551, 97, 30);
		contentPane.add(btnConcluir);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(381, 551, 100, 30);
		contentPane.add(btnCancelar);
		
		geraVenda();
		
	}

	private void gravaVenda() {
		ArquivoProdutoDevenda apdv = new ArquivoProdutoDevenda();
		ArquivoVenda av = new ArquivoVenda();
		try {
			apdv.openFile("arquivos\\tbpdv.gpv");
			av.openFile("arquivos\\tbvd.gpv");
			for (ProdutoDeVenda pv : venda.getItensVenda()) {
				apdv.setFilePointer(apdv.recordQuantity());
				apdv.writeObject(pv);
			}
			System.out.println(venda.getData());
			av.setFilePointer(av.recordQuantity());
			av.writeObject(venda);
			av.closeFile();
			apdv.closeFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void geraVenda() {
		venda = new Venda();
		venda.setCodigo(GestaoDeProducao.vendas.size()==0?1:GestaoDeProducao.vendas.get(GestaoDeProducao.vendas.size()-1).getCodigo()+1);
		venda.setValorTotal(0.0);
		txtValorTotal.setText(venda.getValorTotal().toString());
		txtCodigo.setText(String.valueOf(venda.getCodigo()));
		venda.setData(txtData.getText());
		venda.setHora(txtHora.getText());
	}
	
	private void horaAtual() {
		try {
			txtHora.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##:##")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtHora.setText(Acessorios.horaAtualtoString());
	}
	
	private void dataAtual() {
		try {
			txtData.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtData.setText(Acessorios.dataAtualtoString());
	}
	
	private void insereProdutoVenda() {
		if(cbProduto.getSelectedIndex()>0) {
			Produto produto = GestaoDeProducao.produtos.get(cbProduto.getSelectedIndex()-1);
			if(produto!=null) {
				produtov = new ProdutoDeVenda();
				produtov.setCodProduto(produto.getCodigo());
				produtov.setCodVendafk(venda.getCodigo());
				produtov.setQtdProdutoVenda((Double)spnQtd.getValue());
				produtov.setPrecoUnitario(produto.calcValorUnitario());
				venda.adicionaIntemVenda(produtov);
			}
			venda.setValorTotal(venda.getValorTotal()+produtov.getPrecoUnitario()*produtov.getQtdProdutoVenda());
			atualizaTabela();
			txtValorTotal.setText(venda.getValorTotal().toString());
		}
	}
	private void atualizaValorTotalVenda() {
		
		
	}
	private void atualizaTabela() {
		Object[][] dados = new Object[venda.getItensVenda().size()][COLUNAS.length];
		int linha=0;
		for (ProdutoDeVenda pv : venda.getItensVenda()) {
			dados[linha][0]=GestaoDeProducao.buscaProdutoCod(pv.getCodProduto()).getNome();
			dados[linha][1]=pv.getQtdProdutoVenda();
			dados[linha][2]=pv.getPrecoUnitario();
			dados[linha][3]=pv.getPrecoUnitario()*pv.getQtdProdutoVenda();
			linha++;
		}
		dtm = new DefaultTableModel(dados,COLUNAS);
		tbProdutosVenda.setModel(dtm);
	}
}
