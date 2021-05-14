package tsi.ere.too.avaliacao.dados;

import java.io.IOException;

import tsi.ere.too.avaliacao.produto.Venda;

public class ArquivoVenda extends BinaryFile{

	@Override
	public int recordSize() {
		// TODO Auto-generated method stub
		return 42;
	}

	@Override
	public void writeObject(Object objeto) throws IOException {
		Venda venda = (Venda) objeto;
		randomAccessFile.writeInt(venda.getCodigo());//4
		randomAccessFile.writeChars(setStringLength(venda.getData(), 10));//20
		randomAccessFile.writeChars(setStringLength(venda.getHora(), 5));//10
		randomAccessFile.writeDouble(venda.getValorTotal());//8	
	}

	@Override
	public Object readObject() throws IOException {
		Venda venda = new Venda();
		venda.setCodigo(randomAccessFile.readInt());//4
		venda.setData(readString(10));//20
		venda.setHora(readString(5));
		venda.setValorTotal(randomAccessFile.readDouble());//8	
		return venda;
	}

}
