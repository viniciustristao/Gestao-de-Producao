package tsi.ere.too.avaliacao.dados;

import java.io.IOException;

import tsi.ere.too.avaliacao.produto.InsumoDeProduto;

public class ArquivoInsProd extends BinaryFile{

	@Override
	public int recordSize() {
		// TODO Auto-generated method stub
		return 16;
	}

	@Override
	public void writeObject(Object objeto) throws IOException {
		InsumoDeProduto insumoDeProduto = (InsumoDeProduto) objeto;
		
		randomAccessFile.writeInt(insumoDeProduto.getCodinsumo());
		randomAccessFile.writeInt(insumoDeProduto.getCodprod());
		randomAccessFile.writeDouble(insumoDeProduto.getQtd());
	}

	@Override
	public Object readObject() throws IOException {
		InsumoDeProduto insumoDeProduto = new InsumoDeProduto();
		insumoDeProduto.setCodinsumo(randomAccessFile.readInt());
		insumoDeProduto.setCodprod(randomAccessFile.readInt());
		insumoDeProduto.setQtd(randomAccessFile.readDouble());
		return insumoDeProduto;
	}

}
