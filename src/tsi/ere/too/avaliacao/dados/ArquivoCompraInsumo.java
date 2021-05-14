package tsi.ere.too.avaliacao.dados;

import java.io.IOException;

import tsi.ere.too.avaliacao.produto.CompradeInsumo;

public class ArquivoCompraInsumo extends BinaryFile {

	@Override
	public int recordSize() {
		// TODO Auto-generated method stub
		return 52;
	}

	@Override
	public void writeObject(Object objeto) throws IOException {
		CompradeInsumo ci = (CompradeInsumo)objeto;
		randomAccessFile.writeInt(ci.getCodigo());//4
		randomAccessFile.writeInt(ci.getCodInsumofk());//4
		randomAccessFile.writeDouble(ci.getValor());//8
		randomAccessFile.writeDouble(ci.getQtd());//8
		randomAccessFile.writeDouble(ci.getQtdAtual());//8
		randomAccessFile.writeChars(setStringLength(ci.getDataCompra(),10));//20
	}

	@Override
	public Object readObject() throws IOException {
		CompradeInsumo ci = new CompradeInsumo();
		ci.setCodigo(randomAccessFile.readInt());
		ci.setCodInsumofk(randomAccessFile.readInt());
		ci.setValor(randomAccessFile.readDouble());
		ci.setQtd(randomAccessFile.readDouble());
		ci.setQtdAtual(randomAccessFile.readDouble());
		ci.setDataCompra(readString(10));
		return ci;
	}

}
