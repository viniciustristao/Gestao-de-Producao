package tsi.ere.too.avaliacao.produto;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import tsi.ere.too.avaliacao.dados.ArquivoCompraInsumo;

public class InsumoDeProduto {
	private int codprod, codinsumo;//4+4
	private Double qtd;//8
	public InsumoDeProduto() {
		super();
	}
	
	public InsumoDeProduto(int codprod, int codinsumo, Double qtd) {
		super();
		this.codinsumo = codinsumo;
		this.qtd = qtd;
	}
	
	public int getCodprod() {
		return codprod;
	}
	
	public void setCodprod(int codprod) {
		this.codprod = codprod;
	}
	
	public int getCodinsumo() {
		return codinsumo;
	}
	
	public void setCodinsumo(int codinsumo) {
		this.codinsumo = codinsumo;
	}
	
	public Double getQtd() {
		return qtd;
	}
	
	public void setQtd(Double qtd) {
		this.qtd = qtd;
	}
	
	private ArrayList<CompradeInsumo> compraInsumoporData(ArquivoCompraInsumo aci) throws IOException{
		ArrayList<CompradeInsumo> comprasI = new ArrayList<CompradeInsumo>();
		CompradeInsumo ci;
		long qtd = aci.recordQuantity();
		for (long pos = 0; pos < qtd; pos++) {
			aci.setFilePointer(pos);
			ci=(CompradeInsumo)aci.readObject();
			if(getCodinsumo()==ci.getCodInsumofk())
				if(ci.getQtdAtual()>0)
					comprasI.add(ci);
		}
		comprasI.sort(new Comparator<CompradeInsumo>() {

			@Override
			public int compare(CompradeInsumo o1, CompradeInsumo o2) {
				Date d1,d2;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					d1=sdf.parse(o1.getDataCompra());
					d2=sdf.parse(o2.getDataCompra());
					return d1.compareTo(d2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		});
		if(comprasI.size()==0) {
			aci.setFilePointer(aci.recordQuantity()-1);
			ci=(CompradeInsumo)aci.readObject();
			comprasI.add(ci);
		}
		return comprasI;
	}
	
	public Double calculaPrecoUnitarioIP() {
		ArrayList<CompradeInsumo> comprasI;
		ArquivoCompraInsumo aci = new ArquivoCompraInsumo();
		try {
			aci.openFile("arquivos\\tbci.gpv");
			
			comprasI = compraInsumoporData(aci);
			
			Double valorUnit = 0d,
					qtdNescessaria = this.getQtd();
			for (CompradeInsumo compradeInsumo : comprasI) {
				if(compradeInsumo.getQtdAtual()>=qtdNescessaria) {
					valorUnit+= this.getQtd()*compradeInsumo.getvalorUnitario();
					return valorUnit/this.getQtd();
				}
				else {
					valorUnit+= compradeInsumo.getQtdAtual()*compradeInsumo.getvalorUnitario();
					qtdNescessaria-=compradeInsumo.getQtdAtual();
				}
			}
			if(qtdNescessaria>0) {
				valorUnit+=qtdNescessaria*comprasI.get(comprasI.size()).getValor();
				return valorUnit/this.getQtd();
			}
			aci.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void consomeInsumo() {
		ArrayList<CompradeInsumo> comprasI = new ArrayList<CompradeInsumo>();
		ArquivoCompraInsumo aci = new ArquivoCompraInsumo();
		CompradeInsumo ci;
		try {
			aci.openFile("arquivos\\tbci.gpv");
			comprasI.addAll(compraInsumoporData(aci));
			Double qtdAconsumir=qtd;
			for (long pos = 0; pos < aci.recordQuantity(); pos++) {
				aci.setFilePointer(pos);
				ci=(CompradeInsumo)aci.readObject();
				for (CompradeInsumo compradeInsumo : comprasI) {
					if(compradeInsumo.getCodigo()==ci.getCodigo()) {
						aci.setFilePointer(pos);
						if(ci.getQtdAtual()>=qtdAconsumir) {
							ci.setQtdAtual(ci.getQtdAtual()-qtdAconsumir);
							continue;
						}
						else {
							qtdAconsumir-=ci.getQtdAtual();
							ci.setQtdAtual(0d);
						}
					}
				}
				aci.setFilePointer(pos);
				aci.writeObject(ci);
			}
			aci.closeFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "InsumoDeProduto [codprod=" + codprod + ", codinsumo=" + codinsumo + ", qtd="
				+ qtd + "]";
	}

}
