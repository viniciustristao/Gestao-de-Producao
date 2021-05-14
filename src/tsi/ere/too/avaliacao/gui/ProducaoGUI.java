package tsi.ere.too.avaliacao.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import tsi.ere.too.avaliacao.dados.ArquivoCompraInsumo;
import tsi.ere.too.avaliacao.dados.ArquivoInsProd;
import tsi.ere.too.avaliacao.dados.ArquivoProducao;
import tsi.ere.too.avaliacao.produto.CompradeInsumo;
import tsi.ere.too.avaliacao.produto.InsumoDeProduto;
import tsi.ere.too.avaliacao.produto.Producao;
import tsi.ere.too.avaliacao.produto.Produto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProducaoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtDataProducao;
	private JTextField txtQtdProduzida;
	private JTextField txtCustoProducao;
	private JTextField txtCustoAcumulado;
	private JTextField txtValorVenda;
	private JTextField txtVendaAcumulado;
	private JComboBox<String> cbNomeProduto;
	private Produto produto;
	private Producao producao;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProducaoGUI frame = new ProducaoGUI();
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
	public ProducaoGUI() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Produ\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 33, 503, 312);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNomeProduto = new JLabel("Nome do Produto:");
		lblNomeProduto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomeProduto.setBounds(0, 37, 262, 17);
		panel.add(lblNomeProduto);
		
		cbNomeProduto = new JComboBox();
		cbNomeProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setaProduto();
			}
		});
		cbNomeProduto.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbNomeProduto.setBounds(265, 24, 228, 30);
		panel.add(cbNomeProduto);
		ProdutosGUI.atualizaProdCB(cbNomeProduto);
		
		JLabel lblDataProducao = new JLabel("Data da Producao:");
		lblDataProducao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataProducao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataProducao.setBounds(0, 76, 262, 17);
		panel.add(lblDataProducao);
		
		txtDataProducao = new JTextField();
		txtDataProducao.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDataProducao.setColumns(10);
		txtDataProducao.setBounds(265, 63, 96, 30);
		panel.add(txtDataProducao);
		
		txtQtdProduzida = new JTextField();
		txtQtdProduzida.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				completaProducao();
			}
		});
		txtQtdProduzida.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtQtdProduzida.setColumns(10);
		txtQtdProduzida.setBounds(265, 103, 96, 30);
		panel.add(txtQtdProduzida);
		
		JLabel lblQuantidadeProduzida = new JLabel("Quantidade Produzida:");
		lblQuantidadeProduzida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidadeProduzida.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantidadeProduzida.setBounds(0, 116, 262, 17);
		panel.add(lblQuantidadeProduzida);
		
		JLabel lblCustoProducao = new JLabel("Custo da Produ\u00E7\u00E3o: R$");
		lblCustoProducao.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustoProducao.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustoProducao.setBounds(0, 156, 262, 17);
		panel.add(lblCustoProducao);
		
		txtCustoProducao = new JTextField();
		txtCustoProducao.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCustoProducao.setColumns(10);
		txtCustoProducao.setBounds(265, 143, 96, 30);
		panel.add(txtCustoProducao);
		
		txtCustoAcumulado = new JTextField();
		txtCustoAcumulado.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCustoAcumulado.setColumns(10);
		txtCustoAcumulado.setBounds(265, 183, 96, 30);
		panel.add(txtCustoAcumulado);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Custo da Produ\u00E7\u00E3o Acumulado: R$");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(0, 196, 262, 17);
		panel.add(lblNewLabel_1_1_1_1);
		
		txtValorVenda = new JTextField();
		txtValorVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtValorVenda.setColumns(10);
		txtValorVenda.setBounds(265, 223, 96, 30);
		panel.add(txtValorVenda);
		
		JLabel txtValorTotalVenda = new JLabel("Valor total de Venda: R$");
		txtValorTotalVenda.setHorizontalAlignment(SwingConstants.RIGHT);
		txtValorTotalVenda.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtValorTotalVenda.setBounds(0, 236, 262, 17);
		panel.add(txtValorTotalVenda);
		
		txtVendaAcumulado = new JTextField();
		txtVendaAcumulado.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtVendaAcumulado.setColumns(10);
		txtVendaAcumulado.setBounds(265, 263, 96, 30);
		panel.add(txtVendaAcumulado);
		
		JLabel lblVendaAcumulado = new JLabel("Valor Venda Acumulado: R$");
		lblVendaAcumulado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVendaAcumulado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVendaAcumulado.setBounds(0, 276, 262, 17);
		panel.add(lblVendaAcumulado);
		
		JLabel lblNewLabel_2 = new JLabel("Gestao de Produ\u00E7\u00E3o");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 10, 503, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Gravar Produ\u00E7\u00E3o");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravarProducao();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(250, 351, 155, 21);
		contentPane.add(btnNewButton);
		
		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSair.setFont(new Font("Tahoma", Font.BOLD, 14));
		btSair.setBounds(415, 351, 98, 21);
		contentPane.add(btSair);
		
	}//fim inicializa componentes

	protected void gravarProducao() {
		ArquivoProducao aproducao= new ArquivoProducao();
		if(producao!=null) {
			try {
				aproducao.openFile("arquivos\\tbpdc.gpv");
				for (InsumoDeProduto ip : produto.getInsumos()) {
					ip.consomeInsumo();
				}
				aproducao.setFilePointer(aproducao.recordQuantity());
				aproducao.writeObject(producao);
				aproducao.closeFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void completaProducao() {
		if(produto!=null) {
			
			producao = new Producao();
			
			producao.setCodProdutofk(produto.getCodigo());
			producao.setData(txtDataProducao.getText());
			producao.setQtd(Double.valueOf(txtQtdProduzida.getText()));
			producao.setQtdATual(producao.getQtd());
			txtCustoProducao.setText(String.format("%1.2f",producao.calculaCusto(produto)));
			txtValorVenda.setText(String.format("%1.2f",producao.calculaValorTotalVenda(produto)));
			txtCustoAcumulado.setText(String.format("%1.2f",producao.calculaCustoEstoque(produto,GestaoDeProducao.producao)));
			txtVendaAcumulado.setText(String.format("%1.2f", producao.calculaVendaEstoque(produto, GestaoDeProducao.producao)));
		}
		
	}

	protected void setaProduto() {
		produto = cbNomeProduto.getSelectedIndex()>0?GestaoDeProducao.produtos.get(cbNomeProduto.getSelectedIndex()-1):null;
	}
}
