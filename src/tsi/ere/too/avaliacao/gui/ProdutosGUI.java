package tsi.ere.too.avaliacao.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import mos.es.EntradaESaida;
import tsi.ere.too.avaliacao.dados.ArquivoInsProd;
import tsi.ere.too.avaliacao.dados.ArquivoProduto;
import tsi.ere.too.avaliacao.produto.InsumoDeProduto;
import tsi.ere.too.avaliacao.produto.Produto;
import tsi.ere.too.avaliacao.produto.Unidade;

public class ProdutosGUI extends JFrame {
	
	private DefaultTableModel dtm;
	private JTable tabelaInsumo;
	private JButton btnSair;
	private JButton btnInserirInsumo;
	private JScrollPane scpTabela;
	private JButton btnGravaProd;
	private JPanel pnInsumo;
	private JTextField txtTamanhoUnidade;
	private JLabel lblTamanhoUnidade;
	private JTextField txtQtdInsumo;
	private JTextField txtValorUnitInsu;
	private JLabel lblnomeInsumo;
	private JLabel lblqtdInsumo;
	private JLabel lblvalorUnitarioInsumo;
	private JLabel lblTituloPagina;
	private JComboBox<String> cbNomeProd;
	private JComboBox<Unidade> cbUnidade;
	private JSpinner spnLucro;
	private JComboBox<String> cbNomeInsumo;
	private Produto produto;
	private JLabel lblunidadeMendidaInsumo;
	private JButton btnAlterarInsumo;
	private JButton btnExcluirInsumo;
	private JComboBox<String> cbNomeInsumosProduto;
	private JTextField txtQtdAlterada;
	private JButton btnAlteraProd;
	private JButton btnExcluir;
	private InsumoDeProduto ip; 
	private JLabel lblNewLabel_3;
	private JTextField txtValorProd;
	
	/**
	 * Create the frame.
	 */
	public ProdutosGUI() {
		inicializarComponentes();
	}//fim construtor
	
	private void inicializarComponentes() {
		// TODO Auto-generated method stubgetContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1117, 534);
		setTitle("Produto");
		getContentPane().setLayout(null);
		
		JPanel pnProduto = new JPanel();
		pnProduto.setBorder(new TitledBorder(null, "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnProduto.setBounds(10, 33, 483, 241);
		getContentPane().add(pnProduto);
		pnProduto.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(115, 41, 59, 17);
		pnProduto.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Unidade de Medida:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(25, 79, 149, 17);
		pnProduto.add(lblNewLabel_1);
		
		cbNomeProd = new JComboBox<String>();
		cbNomeProd.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbNomeProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolhaCbproduto();
			}
		});
		cbNomeProd.setEditable(true);
		cbNomeProd.setBounds(174, 26, 291, 30);
		pnProduto.add(cbNomeProd);
		
		cbUnidade = new JComboBox<Unidade>();
		cbUnidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbUnidade.setModel(new DefaultComboBoxModel<Unidade>(Unidade.values()));
		cbUnidade.setBounds(174, 66, 291, 30);
		pnProduto.add(cbUnidade);
		atualizaProdCB(cbNomeProd);
		
		spnLucro = new JSpinner(new SpinnerNumberModel(0, 0, Double.MAX_VALUE, 1));
		spnLucro.setFont(new Font("Tahoma", Font.BOLD, 14));
		spnLucro.setBounds(174, 106, 90, 30);
		pnProduto.add(spnLucro);
		
		JLabel lblNewLabel_2 = new JLabel("Margem de Lucro:");
		lblNewLabel_2.setBounds(36, 119, 138, 17);
		pnProduto.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_4 = new JLabel("%");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(274, 119, 27, 17);
		pnProduto.add(lblNewLabel_4);
		
		JButton btnCadInsumo = new JButton("Cadastrar Insumo");
		btnCadInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCadInsumo.setBounds(285, 156, 180, 21);
		pnProduto.add(btnCadInsumo);
		
		lblTamanhoUnidade = new JLabel("Tamanho da Unidade:");
		lblTamanhoUnidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTamanhoUnidade.setBounds(10, 158, 164, 17);
		pnProduto.add(lblTamanhoUnidade);
		lblTamanhoUnidade.setVisible(false);
		
		txtTamanhoUnidade = new JTextField();
		txtTamanhoUnidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtTamanhoUnidade.setBounds(174, 146, 96, 30);
		pnProduto.add(txtTamanhoUnidade);
		txtTamanhoUnidade.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Valor Unitario:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(62, 200, 105, 17);
		pnProduto.add(lblNewLabel_3);
		
		txtValorProd = new JTextField();
		txtValorProd.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtValorProd.setBounds(174, 187, 96, 30);
		pnProduto.add(txtValorProd);
		txtValorProd.setColumns(10);
		txtTamanhoUnidade.setVisible(false);
		
		lblTituloPagina = new JLabel("Gestao de Produto");
		lblTituloPagina.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPagina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloPagina.setBounds(10, 10, 483, 13);
		getContentPane().add(lblTituloPagina);
		
		btnGravaProd = new JButton("Gravar Produto");
		btnGravaProd.setBounds(254, 284, 145, 21);
		getContentPane().add(btnGravaProd);
		btnGravaProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaProduto();
			}
		});
		btnGravaProd.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnSair = new JButton("Sair");
		btnSair.setBounds(408, 284, 85, 21);
		getContentPane().add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		pnInsumo = new JPanel();
		pnInsumo.setBorder(new TitledBorder(null, "Insumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnInsumo.setBounds(10, 282, 483, 152);
		pnInsumo.setLayout(null);
		getContentPane().add(pnInsumo);
		
		txtQtdAlterada=new JTextField();
		cbNomeInsumosProduto = new JComboBox<String>();
		
		lblnomeInsumo = new JLabel("Nome:");
		lblnomeInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnomeInsumo.setBounds(105, 35, 59, 17);
		pnInsumo.add(lblnomeInsumo);
		lblnomeInsumo.setVisible(false);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});
		btnExcluir.setBounds(151, 284, 85, 21);
		getContentPane().add(btnExcluir);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblqtdInsumo = new JLabel("Quantidade:");
		lblqtdInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblqtdInsumo.setBounds(70, 75, 90, 17);
		pnInsumo.add(lblqtdInsumo);
		lblqtdInsumo.setVisible(false);
		
		lblvalorUnitarioInsumo = new JLabel("Valor Unit\u00E1rio: R$");
		lblvalorUnitarioInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblvalorUnitarioInsumo.setBounds(36, 114, 128, 17);
		pnInsumo.add(lblvalorUnitarioInsumo);
		lblvalorUnitarioInsumo.setVisible(false);
		
		txtQtdInsumo = new JTextField();
		txtQtdInsumo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				criaInsumo();
			}
		});
		txtQtdInsumo.setToolTipText("Quantidade referente ao tamanho da unidade do produto");
		txtQtdInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtQtdInsumo.setBounds(175, 62, 96, 30);
		pnInsumo.add(txtQtdInsumo);
		txtQtdInsumo.setColumns(10);
		txtQtdInsumo.setVisible(false);
		
		txtValorUnitInsu = new JTextField();
		txtValorUnitInsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtValorUnitInsu.setEditable(false);
		txtValorUnitInsu.setBounds(175, 101, 96, 30);
		pnInsumo.add(txtValorUnitInsu);
		txtValorUnitInsu.setColumns(10);
		
		btnInserirInsumo = new JButton("Inserir insumo");
		btnInserirInsumo.setBounds(324, 111, 141, 23);
		pnInsumo.add(btnInserirInsumo);
		btnInserirInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insereInsumoTabela();
			}
		});
		btnInserirInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		cbNomeInsumo = new JComboBox<String>();
		cbNomeInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolhaCbInsumo();
			}
		});

		cbNomeInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbNomeInsumo.setBounds(174, 22, 291, 30);
		pnInsumo.add(cbNomeInsumo);
		InsumosGUI.atualizaInsumoCB(cbNomeInsumo);
		
		lblunidadeMendidaInsumo = new JLabel("");
		lblunidadeMendidaInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblunidadeMendidaInsumo.setBounds(281, 75, 154, 17);
		pnInsumo.add(lblunidadeMendidaInsumo);
		
		btnAlteraProd = new JButton("Alterar Produto");
		btnAlteraProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarProduto();
			}
		});
		btnAlteraProd.setBounds(0, 284, 143, 21);
		getContentPane().add(btnAlteraProd);
		btnAlteraProd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAlteraProd.setVisible(false);
		btnExcluir.setVisible(false);
		
		btnInserirInsumo.setVisible(false);
		pnInsumo.setVisible(false);
		txtValorUnitInsu.setVisible(false);
		
		scpTabela = new JScrollPane();
		scpTabela.setViewportBorder(null);
		scpTabela.setBounds(500, 33, 483, 349);
		scpTabela.setBorder(new TitledBorder(null, "Tabela de Insumos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(scpTabela);
		scpTabela.setVisible(false);
		
		tabelaInsumo = new JTable();
		scpTabela.setViewportView(tabelaInsumo);
		
		String[] colunas =  {"Nome", "Quantidade", "Valor Unitário", "Total"};
		dtm = new DefaultTableModel(colunas, 0);
		tabelaInsumo.setEnabled(false);
		tabelaInsumo.setModel(dtm);
		
		btnAlterarInsumo = new JButton("Alterar Insumo");
		btnAlterarInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarInsumoDeProduto();
			}
		});
		btnAlterarInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAlterarInsumo.setBounds(680, 390, 145, 21);
		getContentPane().add(btnAlterarInsumo);
		btnAlterarInsumo.setVisible(false);
		
		btnExcluirInsumo = new JButton("Excluir Insumo");
		btnExcluirInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirInsumoDeProduto();
			}
		});
		btnExcluirInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExcluirInsumo.setBounds(835, 390, 145, 21);
		getContentPane().add(btnExcluirInsumo);
		btnExcluirInsumo.setVisible(false);
		
		btnCadInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				renderizaCadastroInsumo();
				
			}
		});
		
	}//fim inicializarcomponentes()
	
	private void excluirProduto() {
		int ex = EntradaESaida.msgConfirma(String.format("Ao excluir um produto todas as referencias a esse produto tambem serão excluidas."
				+ "Deseja excluir o produto %s da base de dados?\n", produto.getNome()), "Exluir Produto");
		if (ex==0) {
			ArquivoProduto ap = new ArquivoProduto();
			try {
				ap.openFile("arquivos\\tbpr.gpv");
				ap.excludeFileRecord(cbNomeProd.getSelectedIndex()-1);
				ap.closeFile();
				for (InsumoDeProduto ip : produto.getInsumos()) {
					removeInsumoProduto(ip);
				}
				GestaoDeProducao.produtos.remove(cbNomeProd.getSelectedIndex()-1);
				atualizaProdCB(cbNomeProd);
				produto=null;
				renderizaTelaPadrao();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void alterarProduto() {
		cbUnidade.setEnabled(true);
		spnLucro.setEnabled(true);
		txtTamanhoUnidade.setEditable(true);
		
	}

	private void alterarInsumoDeProduto() {
		Double qtdAntiga=0d;
		int posInsumo=-1;
		String titulo ="Altera Quantidade do Insumo no Produto";
		if(btnAlterarInsumo.getText().equals("Alterar Insumo")) {
			btnAlterarInsumo.setText("Gravar Insumo");
			String[]insumos=new String[tabelaInsumo.getRowCount()];
			for (int i=0; i<tabelaInsumo.getRowCount();i++) {
				insumos[i]=(String)tabelaInsumo.getValueAt(i, 0);
			}
			cbNomeInsumosProduto.setModel(new DefaultComboBoxModel<String>(insumos));
			EntradaESaida.msgInfo(cbNomeInsumosProduto, titulo);
			posInsumo=cbNomeInsumosProduto.getSelectedIndex();
			EntradaESaida.msgInfo(txtQtdAlterada, titulo);
			qtdAntiga=(Double)tabelaInsumo.getValueAt(posInsumo, 1);
			tabelaInsumo.setValueAt(txtQtdAlterada.getText(), posInsumo, 1);
			txtQtdAlterada.setText(qtdAntiga.toString());
		}else {
			if(EntradaESaida.msgConfirma("Confirma Alteração no insumo do produto?", titulo)==0) {
				InsumoDeProduto ip = produto.getInsumos().get(cbNomeInsumosProduto.getSelectedIndex());
				ip.setQtd(Double.valueOf((String)tabelaInsumo.getValueAt(cbNomeInsumosProduto.getSelectedIndex(), 1)));
				produto.getInsumos().remove(cbNomeInsumosProduto.getSelectedIndex());
				produto.getInsumos().add(ip);
				gravaInsumoProd();
				txtQtdAlterada.setText("");
				cbNomeInsumosProduto.setModel(new DefaultComboBoxModel<String>());
			}else {
				tabelaInsumo.setValueAt(Double.valueOf(txtQtdAlterada.getText()), cbNomeInsumosProduto.getSelectedIndex(), 1);
				btnAlterarInsumo.setText("Alterar Insumo");
				txtQtdAlterada.setText("");
				cbNomeInsumosProduto.setModel(new DefaultComboBoxModel<String>());
			}
		}
	}//fim alterarinsumodeproduto
	
	private void excluirInsumoDeProduto() {
		int posInsumo=-1;
		String titulo ="Exclui Insumo do Produto";
			String[]insumos=new String[tabelaInsumo.getRowCount()];
			for (int i=0; i<tabelaInsumo.getRowCount();i++) {
				insumos[i]=(String)tabelaInsumo.getValueAt(i, 0);
			}
			cbNomeInsumosProduto.setModel(new DefaultComboBoxModel<String>(insumos));
			EntradaESaida.msgInfo(cbNomeInsumosProduto, titulo);
			posInsumo=cbNomeInsumosProduto.getSelectedIndex();
		
			if(EntradaESaida.msgConfirma(String.format("Confirma Exclusao do insumo %s no produto?",tabelaInsumo.getValueAt(posInsumo, 0)), titulo)==0) {
				removeInsumoProduto(produto.getInsumos().get(posInsumo));
				produto.getInsumos().remove(posInsumo);
				cbNomeInsumosProduto.setModel(new DefaultComboBoxModel<String>());
				carregaInsumosTabela();
			}else {
				cbNomeInsumosProduto.setModel(new DefaultComboBoxModel<String>());
			}
	}
	
	public static void removeInsumoProduto(InsumoDeProduto ip) {
		long pos=0;

		ArquivoInsProd arquivoInsProd = new ArquivoInsProd();
		try {
			arquivoInsProd.openFile("arquivos\\tbip.gpv");
			pos=insumoProdutoExistente(arquivoInsProd, ip);
			if(pos>=0&&pos<arquivoInsProd.recordQuantity()) {
				arquivoInsProd.excludeFileRecord((int)pos);
			}
			arquivoInsProd.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static long insumoProdutoExistente(ArquivoInsProd arquivoInsProd,InsumoDeProduto ipr) throws IOException {
		InsumoDeProduto ip = new InsumoDeProduto();
		for (long i=0; i< arquivoInsProd.recordQuantity();i++) {
			arquivoInsProd.setFilePointer(i);
			ip=(InsumoDeProduto)arquivoInsProd.readObject();
			if(ip.getCodinsumo()==ipr.getCodinsumo() && ip.getCodprod()==ipr.getCodprod())
				if(ip.getQtd().equals(ipr.getQtd()))
					return i;
		}
		return -1;
	}

	protected void escolhaCbInsumo() {
		if(cbNomeInsumo.getSelectedIndex()>0) {
			//txtValorUnitInsu.setText(GestaoDeProducao.insumos.get(cbNomeInsumo.getSelectedIndex()-1).getValor().toString());
			lblunidadeMendidaInsumo.setText(Unidade.values()[GestaoDeProducao.insumos.get(cbNomeInsumo.getSelectedIndex()-1).getUnidade()].getUnidadedemedida());
		}
		else {
			txtQtdInsumo.setText("");
			txtValorUnitInsu.setText("");
			ip=null;
		}
	}
	private void escolhaCbproduto() {
		if(cbNomeProd.getSelectedIndex()>0) {
			produto = GestaoDeProducao.produtos.get(cbNomeProd.getSelectedIndex()-1);
			if(produto!=null) {
				if(produto.getInsumos().size()>0) {
					renderizaCadastroInsumo();
					carregaInsumosTabela();
					txtTamanhoUnidade.setText(produto.getTamanhoUnidade().toString());
					txtValorProd.setText(produto.calcValorUnitario().toString());
				}else {
					renderizaTelaPadrao();
				}
				cbUnidade.setEnabled(false);
				cbUnidade.setSelectedIndex(produto.getUnidade());
				spnLucro.setEnabled(false);
				spnLucro.setValue(produto.getMargemLucro());
				txtTamanhoUnidade.setEditable(false);
				btnAlteraProd.setVisible(true);
				btnExcluir.setVisible(true);
			}
		}
		else
		{
			spnLucro.setEnabled(true);
			cbUnidade.setEnabled(true);
			btnAlteraProd.setVisible(false);
			btnExcluir.setVisible(false);
			renderizaTelaPadrao();
			produto=null;
		}
		
	}

	private void renderizaTelaPadrao() {
		String[] colunas =  {"Nome", "Quantidade", "Valor Unitário", "Total"};
		dtm = new DefaultTableModel(colunas, 0);
		tabelaInsumo.setModel(dtm);
		setBounds(100, 100, 520, 300);
		btnGravaProd.setBounds(255, 232, 145, 21);
		btnSair.setBounds(408, 232, 85, 21);
		txtTamanhoUnidade.setText("");
		txtTamanhoUnidade.setVisible(false);
		lblunidadeMendidaInsumo.setVisible(false);
		lblunidadeMendidaInsumo.setText("");
		pnInsumo.setVisible(false);
		scpTabela.setVisible(false);
		lblTamanhoUnidade.setVisible(false);
		cbUnidade.setSelectedIndex(0);
		spnLucro.setValue(0d);
		
	}

	private void carregaInsumosTabela() {
		String[] colunas =  {"Nome", "Quantidade", "Valor Unitário", "Total"};
		InsumoDeProduto ip;
		Object dados[][] = new Object[produto.getInsumos().size()][colunas.length];
		for (int reg = 0; reg < produto.getInsumos().size(); reg++) {
			ip=produto.getInsumos().get(reg);
			System.out.println(ip.getCodinsumo());
			dados[reg][0]=GestaoDeProducao.insumos.get(ip.getCodinsumo()-1).getdescricao();
			dados[reg][1]=ip.getQtd();
			dados[reg][2]=String.format("%1.2f",ip.calculaPrecoUnitarioIP());
			dados[reg][3]=String.format("%1.2f",ip.calculaPrecoUnitarioIP()*ip.getQtd());
		}

		dtm = new DefaultTableModel(dados,colunas);
		
		tabelaInsumo.setModel(dtm);
	}

	private void renderizaCadastroInsumo() {
		setBounds(100, 100, 1010, 460);

		btnGravaProd.setBounds(255, 390, 145, 21);
		btnSair.setBounds(407, 390, 85, 21);
		lblTituloPagina.setBounds(10, 10, 973, 13);
		btnExcluir.setBounds(151, 390, 85, 21);
		btnAlteraProd.setBounds(0, 390, 143, 21);

		pnInsumo.setVisible(true);
		scpTabela.setVisible(true);
		btnInserirInsumo.setVisible(true);
		lblTamanhoUnidade.setVisible(true);
		txtTamanhoUnidade.setVisible(true);
		txtTamanhoUnidade.setEditable(true);
		lblnomeInsumo.setVisible(true);
		lblqtdInsumo.setVisible(true);
		lblvalorUnitarioInsumo.setVisible(true);
		txtQtdInsumo.setVisible(true);
		txtValorUnitInsu.setVisible(true);
		cbNomeInsumo.setVisible(true);
		lblunidadeMendidaInsumo.setVisible(true);
		lblunidadeMendidaInsumo.setText("");
		btnAlterarInsumo.setVisible(true);
		btnExcluirInsumo.setVisible(true);
		
	}// fim renderizaCadastroInsumo
	
	private void gravaProduto() {
		ArquivoProduto arquivoProduto = new ArquivoProduto();
		try {
			arquivoProduto.openFile("arquivos\\tbpr.gpv");
			long posGravar= cbNomeProd.getSelectedIndex()>0?cbNomeProd.getSelectedIndex()-1:arquivoProduto.recordQuantity();
			if(produto==null) {
				produto = new Produto();
				produto.setCodigo(GestaoDeProducao.produtos.size()+1);
			}
			if(cbNomeProd.getSelectedItem()!=null)
				produto.setNome((String)cbNomeProd.getSelectedItem());
	
			if(cbUnidade.getSelectedItem()!=null)
				produto.setUnidade(cbUnidade.getSelectedIndex());
			
			if(spnLucro.getValue()!=null)
				produto.setMargemLucro(Double.valueOf((Double)spnLucro.getValue()));
			
			arquivoProduto.setFilePointer(posGravar);
			arquivoProduto.writeObject(produto);
			arquivoProduto.closeFile();

			int posProd=GestaoDeProducao.buscaPosProdutoNome(produto.getNome());

			
			if(posProd>=0) {
				GestaoDeProducao.produtos.add(posProd, produto);
				GestaoDeProducao.produtos.remove(posProd+1);
			}
			else {
				GestaoDeProducao.produtos.add(produto);
				atualizaProdCB(cbNomeProd);
			}
			cbUnidade.setSelectedIndex(0);
			spnLucro.setValue(0);
			carregaInsumosTabela();
			tabelaInsumo.setModel(dtm);
			txtTamanhoUnidade.setText("");
			cbNomeProd.setSelectedIndex(0);
			
			produto=null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//fim gravarproduto
	
	private void criaInsumo() {
		ip = new InsumoDeProduto();
		ip.setCodinsumo(GestaoDeProducao.insumos.get(cbNomeInsumo.getSelectedIndex()-1).getCodigo());
		ip.setQtd(Double.parseDouble(txtQtdInsumo.getText()));
		txtValorUnitInsu.setText(ip.calculaPrecoUnitarioIP().toString());
		
	}
	
	private void insereInsumoTabela() {
		if(produto==null) {
			produto = new Produto();
			produto.setCodigo(GestaoDeProducao.produtos.size()+1);
			if(cbNomeProd.getSelectedItem()!=null)
				produto.setNome((String)cbNomeProd.getSelectedItem());
	
			if(cbUnidade.getSelectedItem()!=null)
				produto.setUnidade(cbUnidade.getSelectedIndex());
			
			if(spnLucro.getValue()!=null)
				produto.setMargemLucro(Double.valueOf((Double)spnLucro.getValue()));
			
		}
		while(txtTamanhoUnidade.getText()==null || txtTamanhoUnidade.getText().isEmpty()) {
			txtTamanhoUnidade.setEnabled(true);
			EntradaESaida.msgInfo(txtTamanhoUnidade, "Insira o Tamanho da unidade");
		}
		produto.setTamanhoUnidade(Double.valueOf(txtTamanhoUnidade.getText()));
		
		ip.setCodprod(produto.getCodigo());
		produto.cadastraInsumo(ip);
		txtValorProd.setText(produto.calcValorUnitario().toString());
		gravaInsumoProd();
		carregaInsumosTabela();
		cbNomeInsumo.setSelectedIndex(0);
		txtQtdInsumo.setText("");
		txtValorUnitInsu.setText("");

	}//
	
	private void gravaInsumoProd() {
		long pos=0;
		for (InsumoDeProduto ip : produto.getInsumos()) {
			ip.setCodprod(produto.getCodigo());
			ArquivoInsProd arquivoInsProd = new ArquivoInsProd();
			try {
				arquivoInsProd.openFile("arquivos\\tbip.gpv");
				pos=insumoProdutoExistenteQtdDiferente(arquivoInsProd, ip);
				if(pos>=0) {
					if(pos<arquivoInsProd.recordQuantity())
						arquivoInsProd.excludeFileRecord((int)pos);
					arquivoInsProd.setFilePointer(arquivoInsProd.recordQuantity());
					arquivoInsProd.writeObject(ip);
				}
				arquivoInsProd.closeFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pos++;
		}
		
	}
	
	/**
	 * Busca por um insumo de produto no arquivo, caso encontre e objeto tenha
	 * exatamente a mesma quantidade retorna -1, caso encontre e objeto nao tenha
	 * a mesmas quantidade retorna a posição do objeto, caso nao econtre retorna
	 * o numero de objetos existentes no arquivo 
	 * @param arquivoInsProd arquivo a ser analisado
	 * @param ipr InsumoDeProduto a ser comparado
	 * @return -1 caso objeto exista e seja totalmente igual, posicao do objeto caso ele exista com valores
	 * armazenados diferentes, numero de objetos caso objeto nao exista no arquivo
	 * @throws IOException
	 */
	private long insumoProdutoExistenteQtdDiferente(ArquivoInsProd arquivoInsProd,InsumoDeProduto ipr) throws IOException {
		InsumoDeProduto ip = new InsumoDeProduto();
		for (long i=0; i< arquivoInsProd.recordQuantity();i++) {
			arquivoInsProd.setFilePointer(i);
			ip=(InsumoDeProduto)arquivoInsProd.readObject();
			if(ip.getCodinsumo()==ipr.getCodinsumo() && ip.getCodprod()==ipr.getCodprod()) {
				if(ip.getQtd().equals(ipr.getQtd()))
					return -1;
				else
					return i;
			}
		}
		return arquivoInsProd.recordQuantity();
	}
	
	public static void atualizaProdCB(JComboBox<String> cbNomeProduto) {
		Produto produto;
		String[] produtos;
		StringBuilder sb;
		int nreg;
		nreg = GestaoDeProducao.produtos.size()+1;
		produtos=new String[nreg];
		produtos[0]="";
		for (int reg = 0; reg < nreg-1; reg++) {
			sb = new StringBuilder();
			produto=GestaoDeProducao.produtos.get(reg);
			sb.append(produto.getCodigo()).append("-").append(produto.getNome());
			produtos[reg+1]=sb.toString();
		}
		cbNomeProduto.setModel(new DefaultComboBoxModel<String>(produtos));
	}
}

