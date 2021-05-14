package tsi.ere.too.avaliacao.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import mos.es.EntradaESaida;
import tsi.ere.too.avaliacao.dados.ArquivoCompraInsumo;
import tsi.ere.too.avaliacao.dados.ArquivoInsumo;
import tsi.ere.too.avaliacao.produto.CompradeInsumo;
import tsi.ere.too.avaliacao.produto.Insumo;
import tsi.ere.too.avaliacao.produto.InsumoDeProduto;
import tsi.ere.too.avaliacao.produto.Produto;
import tsi.ere.too.avaliacao.produto.Unidade;


public class InsumosGUI extends JFrame {

	private JPanel cpHistoricoCompra;
	private JTextField txtValor;
	private JComboBox<String> cbNome;
	private JTextField txtQtd;
	private JFormattedTextField txtdata;
	private JComboBox<String> cbUnidade;
	private JButton btnExcluirInsumo;
	private JButton btnCompraInsumo;
	private Insumo insumo;
	private JPanel pnCompra;
	private JButton btnGravarCompra;
	private JLabel lblmedComp;
	private JButton btnHistoricoCompras;
	private JTable tabelaHistorico;
	private JScrollPane pnHistorico;
	private DefaultTableModel dtm;
	private JTextField txtvalorUnitario;
	private JLabel lblValorUniario;
	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsumosGUI frame = new InsumosGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public InsumosGUI() {
		inicializarComponentes();
	}//fim construtor

	private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 255);
		cpHistoricoCompra = new JPanel();
		cpHistoricoCompra.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(cpHistoricoCompra);
		cpHistoricoCompra.setLayout(null);
		
		JLabel lbltitulo = new JLabel("Gestao De Insumo");
		lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltitulo.setBounds(10, 10, 483, 13);
		cpHistoricoCompra.add(lbltitulo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Insumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 33, 483, 154);
		cpHistoricoCompra.add(panel);
		panel.setLayout(null);
		
		cbNome = new JComboBox<String>();
		cbNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escolhaCbInsumo();
			}
		});
		cbNome.setEditable(true);
		cbNome.setBounds(174, 26, 278, 30);
		panel.add(cbNome);

		atualizaInsumoCB(cbNome);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(116, 39, 48, 17);
		panel.add(lblNome);

		JLabel lblUnidadeMedida = new JLabel("Unidade de Medida:");
		lblUnidadeMedida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUnidadeMedida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidadeMedida.setBounds(21, 79, 147, 17);
		panel.add(lblUnidadeMedida);
		
		cbUnidade = new JComboBox<String>();
		cbUnidade.setModel(new DefaultComboBoxModel(Unidade.values()));
		cbUnidade.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbUnidade.setEditable(true);
		cbUnidade.setBounds(174, 66, 139, 30);
		panel.add(cbUnidade);
		
		btnCompraInsumo = new JButton("Nova compra");
		btnCompraInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renderizaCompra();
			}
		});
		btnCompraInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCompraInsumo.setBounds(330, 118, 133, 21);
		panel.add(btnCompraInsumo);
		btnCompraInsumo.setVisible(false);
		
		btnExcluirInsumo = new JButton("Excluir Insumo");
		btnExcluirInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirInsumo();
			}
		});
		btnExcluirInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExcluirInsumo.setBounds(21, 118, 143, 21);
		panel.add(btnExcluirInsumo);
		btnExcluirInsumo.setVisible(false);
		
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btSair.setBounds(407, 192, 85, 21);
		cpHistoricoCompra.add(btSair);
		
		JButton btGravaInsumo = new JButton("Gravar Insumo");
		btGravaInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravarInsumo();
			}
		});
		btGravaInsumo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btGravaInsumo.setBounds(252, 192, 147, 21);
		cpHistoricoCompra.add(btGravaInsumo);
		try {
			
			pnCompra = new JPanel();
			pnCompra.setBorder(new TitledBorder(null, "CompradeInsumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCompra.setBounds(503, 33, 337, 180);
			cpHistoricoCompra.add(pnCompra);
			pnCompra.setLayout(null);
			pnCompra.setVisible(false);
			
			JLabel lblValor = new JLabel("Valor Compra: R$");
			lblValor.setBounds(35, 75, 128, 17);
			pnCompra.add(lblValor);
			lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			txtValor = new JTextField();
			txtValor.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					calculaValorUnitario();
				}
			});
			txtValor.setBounds(173, 62, 140, 30);
			pnCompra.add(txtValor);
			txtValor.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtValor.setColumns(10);
			
			txtQtd = new JTextField();
			txtQtd.setBounds(173, 22, 93, 30);
			pnCompra.add(txtQtd);
			txtQtd.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtQtd.setColumns(10);
			
			JLabel lblQtd = new JLabel("Quantidade:");
			lblQtd.setBounds(75, 36, 93, 17);
			pnCompra.add(lblQtd);
			lblQtd.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			txtdata = new JFormattedTextField();
			txtdata.setBounds(173, 102, 140, 30);
			pnCompra.add(txtdata);
			txtdata.setToolTipText("dd/mm/aaaa");
			txtdata.setFont(new Font("Tahoma", Font.BOLD, 14));
			txtdata.setHorizontalAlignment(SwingConstants.LEFT);
			txtdata.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
			
			JLabel lblData = new JLabel("Data:");
			lblData.setBounds(123, 115, 40, 17);
			pnCompra.add(lblData);
			lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
			
			btnGravarCompra = new JButton("Gravar CompradeInsumo");
			btnGravarCompra.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gravarCompraInsumo();
				}
			});
			btnGravarCompra.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnGravarCompra.setBounds(166, 149, 147, 21);
			pnCompra.add(btnGravarCompra);
			
			lblmedComp = new JLabel("");
			lblmedComp.setBounds(268, 39, 45, 13);
			pnCompra.add(lblmedComp);
			
			lblValorUniario = new JLabel("txt");
			lblValorUniario.setBounds(10, 119, 45, 13);
			pnCompra.add(lblValorUniario);
			
			txtvalorUnitario = new JTextField();
			txtvalorUnitario.setBounds(10, 142, 96, 30);
			pnCompra.add(txtvalorUnitario);
			txtvalorUnitario.setColumns(10);
			
			btnHistoricoCompras = new JButton("Historico Compras");
			btnHistoricoCompras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					renderizaTabela();
				}
			});
			btnHistoricoCompras.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnHistoricoCompras.setBounds(10, 192, 169, 21);
			cpHistoricoCompra.add(btnHistoricoCompras);
			btnHistoricoCompras.setVisible(false);
			
			pnHistorico = new JScrollPane();
			pnHistorico.setEnabled(false);
			pnHistorico.setBorder(new TitledBorder(null, "Historico de Compras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnHistorico.setBounds(10, 223, 483, 229);
			cpHistoricoCompra.add(pnHistorico);
			

			tabelaHistorico = new JTable();
			pnHistorico.setViewportView(tabelaHistorico);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}//FIM INICIALIZARCOMPONENTES

	private void calculaValorUnitario() {
		lblValorUniario.setText(String.format("Valor unitario(1%s)", Unidade.values()[insumo.getUnidade()]));
		txtvalorUnitario.setText(String.valueOf(Double.valueOf(txtValor.getText())/Double.valueOf(txtQtd.getText())));
		
	}

	protected void renderizaTabela() {
		pnHistorico.setVisible(true);
		setBounds(100, 100, 520, 500);
		String colunas[] = {String.format("Quantidade %s", Unidade.values()[insumo.getUnidade()].getUnidadedemedida()),
							"Valor",
							"Data"};
		CompradeInsumo ci;
		Object dados[][] = new Object[insumo.getCompradeInsumos().size()][colunas.length];
		for (int reg = 0; reg < insumo.getCompradeInsumos().size(); reg++) {
			ci=insumo.getCompradeInsumos().get(reg);
			dados[reg][0]=ci.getQtd();
			dados[reg][1]=ci.getValor();
			dados[reg][2]=ci.getDataCompra();
		}
		dtm = new DefaultTableModel(dados,colunas);
		
		tabelaHistorico.setModel(dtm);
	}

	private void gravarCompraInsumo() {
		GestaoDeProducao.insumos.get(cbNome.getSelectedIndex()-1).adicionarCompra(Double.valueOf(txtValor.getText()), Double.valueOf(txtQtd.getText()), txtdata.getText());
		insumo=GestaoDeProducao.insumos.get(cbNome.getSelectedIndex()-1);
		ArquivoCompraInsumo aci = new ArquivoCompraInsumo();
		try {
			aci.openFile("arquivos\\tbci.gpv");
			aci.setFilePointer(aci.recordQuantity());
			CompradeInsumo cp = insumo.getCompradeInsumos().get(insumo.getCompradeInsumos().size()-1);
			System.out.println(cp.getValor());
			aci.writeObject(cp);
			aci.closeFile();
			setBounds(100, 100, 840, 255);
			pnCompra.setVisible(false);
			btnHistoricoCompras.setEnabled(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void renderizaCompra() {
		setBounds(100, 100, 860, 255);
		pnCompra.setVisible(true);
		lblmedComp.setText(Unidade.values()[insumo.getUnidade()].getUnidadedemedida()+"(s)");
		
	}

	private void excluirInsumo() {
		int ex = EntradaESaida.msgConfirma(String.format("Ao excluir um insumo todas as referencias a esse insumo tambem serão excluidas.\n"
				+ "Deseja excluir o insumo %s da base de dados?\n", insumo.getdescricao()), "Exluir Insumo");
		if (ex==0) {
			ArquivoInsumo ai = new ArquivoInsumo();
			try {
				ai.openFile("arquivos\\tbins.gpv");
				ai.excludeFileRecord(cbNome.getSelectedIndex()-1);
				ai.closeFile();
				for (Produto produto : GestaoDeProducao.produtos)
					for(InsumoDeProduto ip: produto.getInsumos())
						if(insumo.getCodigo()==ip.getCodinsumo()) {
							produto.getInsumos().remove(ip);
							ProdutosGUI.removeInsumoProduto(ip);
						}
				GestaoDeProducao.insumos.remove(cbNome.getSelectedIndex()-1);
				atualizaInsumoCB(cbNome);
				cbUnidade.setSelectedIndex(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void escolhaCbInsumo() {
		if(cbNome.getSelectedIndex()>0) {
			System.err.println(cbNome.getSelectedIndex());
			insumo=GestaoDeProducao.insumos.get(cbNome.getSelectedIndex()-1);
			if(insumo.getCompradeInsumos().size()==0) {
				btnHistoricoCompras.setEnabled(false);
				pnHistorico.setVisible(false);
				setBounds(100, 100, 520, 255);
			}else
				btnHistoricoCompras.setEnabled(true);
			cbUnidade.setSelectedIndex(insumo.getUnidade());
			cbUnidade.setEnabled(false);
			btnCompraInsumo.setVisible(true);
			btnExcluirInsumo.setVisible(true);
			btnHistoricoCompras.setVisible(true);
			
		}else {
			renderizaTelaPadrao();
		}
	}

	private void renderizaTelaPadrao() {
		txtValor.setText("");
		txtdata.setText("");
		cbUnidade.setSelectedIndex(0);
		txtQtd.setText("");
		cbUnidade.setEnabled(true);
		insumo=null;
		btnCompraInsumo.setVisible(false);
		btnExcluirInsumo.setVisible(false);
		setBounds(100, 100, 520, 255);
		pnCompra.setVisible(true);
		btnHistoricoCompras.setVisible(false);		
	}

	protected void gravarInsumo() {
		if(insumo==null) {
			insumo = new Insumo();
			int codigo;
			codigo = (GestaoDeProducao.insumos.size()>0)?(GestaoDeProducao.insumos.get(GestaoDeProducao.insumos.size()-1).getCodigo()+1):
						1;
			insumo.setCodigo(codigo);
		}
		insumo.setdescricao((String) cbNome.getSelectedItem());
		///insumo.setValor(Double.valueOf(txtValor.getText()));
		insumo.setUnidade(cbUnidade.getSelectedIndex());
		//insumo.setQtd(Double.valueOf(txtQtd.getText()));
		//insumo.setData(txtdata.getText());
		ArquivoInsumo arquivoInsumo = new ArquivoInsumo();
		try {
			arquivoInsumo.openFile("arquivos\\tbins.gpv");
			arquivoInsumo.setFilePointer(arquivoInsumo.recordQuantity());
			arquivoInsumo.writeObject(insumo);
			arquivoInsumo.closeFile();
			GestaoDeProducao.insumos.add(insumo);
			atualizaInsumoCB(cbNome);
			cbNome.setSelectedIndex(0);
			txtValor.setText("");
			txtdata.setText("");
			cbUnidade.setSelectedIndex(0);
			txtQtd.setText("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void atualizaInsumoCB(JComboBox<String> cbNomeInsumo) {
		Insumo insumoCB;
		String[] insumos;
		insumos=new String[GestaoDeProducao.insumos.size()+1];
		insumos[0]="";
		StringBuilder sb;
		for (int reg = 0; reg < GestaoDeProducao.insumos.size(); reg++) {
			sb = new StringBuilder();
			insumoCB=GestaoDeProducao.insumos.get(reg);
			sb.append(insumoCB.getCodigo()).append("-").append(insumoCB.getdescricao());
			insumos[reg+1]=sb.toString();
		}
		cbNomeInsumo.setModel(new DefaultComboBoxModel<String>(insumos));
	}
}
