package tsi.ere.too.avaliacao.dados;

import java.io.IOException;

import tsi.ere.too.avaliacao.produto.ProdutoDeVenda;

public class ArquivoProdutoDevenda extends BinaryFile{

	@Override
	public int recordSize() {
		return 24;
	}

	@Override
	public void writeObject(Object objeto) throws IOException {
		ProdutoDeVenda pdv =(ProdutoDeVenda) objeto;
		randomAccessFile.writeInt(pdv.getCodVendafk());//4
		randomAccessFile.writeInt(pdv.getCodProduto());//4
		randomAccessFile.writeDouble(pdv.getQtdProdutoVenda());//8
		randomAccessFile.writeDouble(pdv.getPrecoUnitario());//8
		
	}

	@Override
	public Object readObject() throws IOException {
		ProdutoDeVenda pdv = new ProdutoDeVenda();
		pdv.setCodVendafk(randomAccessFile.readInt());//4
		pdv.setCodProduto(randomAccessFile.readInt());//4
		pdv.setQtdProdutoVenda(randomAccessFile.readDouble());//8
		pdv.setPrecoUnitario(randomAccessFile.readDouble());//8
		return pdv;
	}

}
