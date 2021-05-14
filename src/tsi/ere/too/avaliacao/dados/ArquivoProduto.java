package tsi.ere.too.avaliacao.dados;

import java.io.IOException;

import tsi.ere.too.avaliacao.produto.Produto;

public class ArquivoProduto extends BinaryFile {

	
	@Override
	public int recordSize() {
		return 104;
	}

	@Override
	public void writeObject(Object objeto) throws IOException {
		Produto produto = (Produto)objeto;
		
		randomAccessFile.writeInt(produto.getCodigo());//4
		randomAccessFile.writeChars(setStringLength(produto.getNome(),40));//80
		randomAccessFile.writeInt(produto.getUnidade());//4
		randomAccessFile.writeDouble(produto.getMargemLucro());//8
		randomAccessFile.writeDouble(produto.getTamanhoUnidade()!=null?produto.getTamanhoUnidade():0);//8
	}

	@Override
	public Object readObject() throws IOException {
		Produto produto = new Produto();
		
		produto.setCodigo(randomAccessFile.readInt());
		produto.setNome(readString(40));
		produto.setUnidade(randomAccessFile.readInt());
		produto.setMargemLucro(randomAccessFile.readDouble());
		produto.setTamanhoUnidade(randomAccessFile.readDouble());
		return produto;
	}

}
