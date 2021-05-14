package tsi.ere.too.avaliacao.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tsi.ere.too.avaliacao.produto.Producao;
import tsi.ere.too.avaliacao.produto.Produto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class RelatorioProducaoGUI extends JFrame {

	private JPanel contentPane;
	private JTable tabelaProducao;
	private JFormattedTextField txtDataFinal;
	private JFormattedTextField txtDataInicial;
	private DefaultTableModel dtm;
	/**
	 * Create the frame.
	 */
	public RelatorioProducaoGUI() {
		inicializaComponentes();
	}
	
	private void inicializaComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1208, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 688, 92);
		panel.setBorder(new TitledBorder(null, "Periodo de Produ\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtDataInicial = new JFormattedTextField();
		txtDataInicial.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDataInicial.setBounds(98, 33, 110, 30);
		panel.add(txtDataInicial);
		
		JLabel lblNewLabel = new JLabel("Data Inicial:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(12, 46, 90, 17);
		panel.add(lblNewLabel);
		
		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDataFinal.setBounds(234, 46, 78, 17);
		panel.add(lblDataFinal);
		
		txtDataFinal = new JFormattedTextField();
		txtDataFinal.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtDataFinal.setBounds(311, 33, 110, 30);
		panel.add(txtDataFinal);
		
		JButton btObterDados = new JButton("Obter dados de Produ\u00E7\u00E3o");
		btObterDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregaDadosTabela();
			}
		});
		btObterDados.setFont(new Font("Tahoma", Font.BOLD, 14));
		btObterDados.setBounds(449, 36, 215, 25);
		panel.add(btObterDados);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 119, 688, 308);
		scrollPane.setBorder(new TitledBorder(null, "Dados Produção", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(scrollPane);
		
		tabelaProducao = new JTable();
		tabelaProducao.setEnabled(false);
		scrollPane.setViewportView(tabelaProducao);
		
		JButton btnNewButton = new JButton("Sair");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(601, 439, 99, 25);
		contentPane.add(btnNewButton);
	}

	private void carregaDadosTabela() {
		Date dataI=null,dataF=null,dataP=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		ArrayList<Producao> producao = GestaoDeProducao.producao;
		String[] colunas = {"Nome do Produto", "Quantidade Produzida", "Custo Total da Produção", "Data Produção"};
		Object[][] dados = new Object[producao.size()][colunas.length];
		int i = 0;
		
		for (Producao pdc : producao) {
			try {
				dataI = sdf.parse(txtDataInicial.getText());
				dataF = sdf.parse(txtDataFinal.getText());
				dataP = sdf.parse(pdc.getData());
				if(dataP.after(dataI)&&dataP.before(dataF)) {
					Produto produto = GestaoDeProducao.buscaProdutoCod(pdc.getCodProdutofk());
					dados[i][0] = produto.getNome();
					dados[i][1] = pdc.getQtd();
					dados[i][2] = pdc.calculaCusto(produto);
					dados[i][3] = pdc.getData();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			i++;
		}
		dtm = new DefaultTableModel(dados,colunas);
		tabelaProducao.setModel(dtm);
	}
}
