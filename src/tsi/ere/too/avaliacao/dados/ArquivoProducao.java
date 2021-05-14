package tsi.ere.too.avaliacao.dados;

import java.io.IOException;

import tsi.ere.too.avaliacao.produto.Producao;

public class ArquivoProducao extends BinaryFile {

	@Override
	public int recordSize() {
		return 44;
	}

	@Override
	public void writeObject(Object objeto) throws IOException {
		Producao producao =(Producao) objeto;
		randomAccessFile.writeInt(producao.getCodigo());
		randomAccessFile.writeInt(producao.getCodProdutofk());
		randomAccessFile.writeChars(setStringLength(producao.getData(), 10));
		randomAccessFile.writeDouble(producao.getQtd());
		randomAccessFile.writeDouble(producao.getQtdATual());
	}

	@Override
	public Object readObject() throws IOException {
		Producao producao = new Producao();
		producao.setCodigo(randomAccessFile.readInt());
		producao.setCodProdutofk(randomAccessFile.readInt());
		producao.setData(readString(10));
		producao.setQtd(randomAccessFile.readDouble());
		producao.setQtdATual(randomAccessFile.readDouble());
		return producao;
	}

}
