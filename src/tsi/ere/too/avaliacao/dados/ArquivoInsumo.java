package tsi.ere.too.avaliacao.dados;

import java.io.IOException;

import tsi.ere.too.avaliacao.produto.Insumo;

public class ArquivoInsumo extends BinaryFile {

	@Override
	public int recordSize() {
		return 88;
	}

	@Override
	public void writeObject(Object objeto) throws IOException {
		Insumo insumo  =(Insumo) objeto;
		randomAccessFile.writeInt(insumo.getCodigo());
		 randomAccessFile.writeChars(setStringLength(insumo.getdescricao(), 40));
		 //randomAccessFile.writeDouble(insumo.getValor());
		 randomAccessFile.writeInt(insumo.getUnidade());
		 //randomAccessFile.writeDouble(insumo.getQtd());
		 //randomAccessFile.writeChars(setStringLength(insumo.getData(), 10));
		
	}

	@Override
	public Object readObject() throws IOException {
		Insumo insumo = new Insumo();
		insumo.setCodigo(randomAccessFile.readInt());
		insumo.setdescricao(readString(40));
		//insumo.setValor(randomAccessFile.readDouble());
		insumo.setUnidade(randomAccessFile.readInt());
		//insumo.setQtd(randomAccessFile.readDouble());
		//insumo.setData(readString(10));
		return insumo;
	}

}
