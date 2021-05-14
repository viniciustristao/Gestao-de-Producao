package tsi.ere.too.avaliacao.dados;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Fornece operações para um arquivo binário usando os serviços de um arquivo com acesso aleatório.
 * As operações definidas por esta classse são independentes da estrutura de um arquivo específico. 
 * Portanto, os serviços desta classe abstrata podem ser utilizados por qualquer classe que queira usar
 * a estrutura e implementar os serviços de um arquivo com acesso aleatório.
 * 
 * <p><b>ATENÇÃO:</b> A estrutura do arquivo em relação a quantidade e os tipos de dados do registro é
 * definida pelo usuário da classe. 
 * No entanto, exige-se que o tamanho dos registros seja fixo. 
 * Essa restrição é fundamental para o correto funcionamento dos métodos da classe <code>BinaryFile</code>.</p>
 * 
 * @author Prof. Márlon Oliveira da Silva
 * 
 * @version 0.35
 */
public abstract class BinaryFile {

	private String fileName; // Nome do arquivo em disco.
	
	/** 
	 * Arquivo binário de acesso aleatório para escrita e leitura de dados.
	 */
     protected RandomAccessFile randomAccessFile; 

	 /**
	  * Obtém o nome do arquivo.
	  *
	  * @return um <code>String</code> com o nome do arquivo em disco.
	  **/
	 public String getFileName() {
		 return fileName;
	 }
	 
	 /**
	  * Retorna um canal para leitura e escrita no arquivo.
	  * 
	  * @return o canal associado com este arquivo.
	  */
	public FileChannel getChannel() {
		return randomAccessFile.getChannel();
	}

     /**
      * Calcula o tamanho em bytes do arquivo.
      *
      *  @return um <code>long</code> com o tamanho em bytes do arquivo.
      *  
      *  @throws IOException se ocorrer um erro ao tentar acessar o arquivo.
      **/
	 public long fileSize() throws IOException {
 	     return randomAccessFile.length();
	 } 

     /**
      * Abre o arquivo para a escrita e a leitura de dados. Se o arquivo não existir ele será criado.
      *
      * @param fileName um <code>String</code> com o nome do arquivo em disco.
      * 
      * @throws FileNotFoundException se o arquivo não puder ser aberto ou criado para entrada e saída de dados.
      **/
	 public void openFile(String fileName) throws FileNotFoundException {
		 // Obtém o nome do arquivo.
		 this.fileName = fileName;

	     // Abre o arquivo para leitura e gravação.
		 randomAccessFile = new RandomAccessFile(fileName, "rw");
	 } 

     /**
      * Fecha o arquivo.
      * 
      * @throws IOException se ocorrer um erro ao tentar fechar o arquivo.
      **/
	 public void closeFile() throws IOException {
		 if (randomAccessFile != null) randomAccessFile.close();
	 } 

	 /**
	  * Ajusta o tamanho da <i>string</i> para um número fixo de caracteres.
	  *
	  * @param str <code>String</code> que terá o tamanho ajustado para o valor especificado por <code>size</code>.
	  * @param size um <code>int</code> com o novo tamanho da string <i>str</i>.
	  *
	  * @return uma <code>String</code> com o conteúdo especificado por <i>str</i> e tamanho definido por <i>size</i>.
	  **/
	 public String setStringLength(String str, int size) {
		 StringBuffer buffer = null;

		 // Cria uma nova string com os caracteres do argumento str.
		 if (str != null) {
		   buffer = new StringBuffer(str);

		   // Configura o tamanho da nova string para o número de caracteres desejado.
		   buffer.setLength(size);
		 }
		 else // Cria uma nova string sem caracteres e com a quantidade especificada no argumento size. 
			buffer = new StringBuffer(size);

		 return buffer.toString();
	 } 

	 /**
	  * Lê uma <i>string</i> do arquivo com o número de caracteres definido por <code>size</code>. 
	  *
	  *  @param size quantidade de caracteres da <i>string</i>.
	  *
	  *  @return a <i>string</i> lida do arquivo.  
	  *  
	  *  @throws IOException se ocorrer um erro  ao tentar ler do arquivo. 
	  **/
	 public String readString(int size) throws IOException {
		 char str[] = new char[size];

		 // Lê a string do arquivo, um caractere por vez.
		 for ( int count = 0; count < str.length; count++ )
			 str[count] = randomAccessFile.readChar();
		 
		 return new String(str).replace('\0', ' ').trim();
	 } 

	/**
      * Calcula a quantidade de registros do arquivo.
      *
      * @return um <code>long</code> com o número de registros do arquivo.
      * 
      * @throws IOException se ocorrer um erro ao tentar obter o número de registros do arquivo.
      **/
	 public long recordQuantity() throws IOException {
		 // Obtém a quantidade de registros do arquivo dividindo o tamanho do arquivo pelo tamanho do registro.
		 return fileSize() / recordSize();
	 } 

	/**
	 * Posiciona o ponteiro do arquivo na posição especificada pelo número do registro. Considere o
	 * registro zero como o primeiro registro do arquivo.
	 *
	 * @param recordNumber número inteiro correspondente a um registro do arquivo.
	 * 
	 * @throws IOException se ocorrer um erro de E/S como, por exempo, acessar um número do registro inválido.
	 **/
	public void setFilePointer(long recordNumber) throws IOException {
		 /* 
		  * Valida o número do registro e se inválido dispara a exceção. 
		  * Considera recordNumber como menor ou igual ao número total de registros do arquivo para permitir o método seek posicionar no fim do arquivo e possibilitar a inserção de novos registros no arquivo.  
		 */
	    if (recordNumber >= 0 && recordNumber <= recordQuantity())
		   randomAccessFile.seek(recordNumber * recordSize());
		else
		   throw new IOException(); // Número de registro inválido.
	} 

	 /**
	  * Exclui um registro do arquivo.
	  *
	  * @param recordNumber um número inteiro correspondente a um registro do arquivo. 
	  * O primeiro registro é de número zero.
	  * 
	  * @throws IOException se ocorrer um erro de E/S como, por exempo, acessar um número des registro inválido.
	  **/
	public void excludeFileRecord(int recordNumber) throws IOException {
		// Posiciona o ponteiro do arquivo no último registro.
		setFilePointer(recordQuantity() - 1);

		// Verifica se o registro a ser excluído não é último.
		if (recordNumber != recordQuantity() - 1) {
			// Lê os dados do objeto armazenado no último registro do arquivo.
			Object objeto = readObject(); // Exemplo de polimorfismo na chamada de readObject. 

			// Posiciona no ínicio do registro a ser excluído.
			setFilePointer(recordNumber);

			// Escreve os dados do último registro no registro a ser excluído.
			writeObject(objeto); // Exemplo de polimorfismo na chamada de writeObject.
		}

		// Trunca o arquivo, ou seja, apaga o último registro. Exemplo de polimorfismo na chamada de recordSize. 
		randomAccessFile.setLength(fileSize() - recordSize());
	} 

	/**
	 * Calcula o tamanho em bytes do registro composto por variáveis membro do objeto.
	 *
	 * @return um <code>int</code> com o tamanho em bytes do registro.
	 */
	public abstract int recordSize();

	/**
	  * Escreve o objeto como um registro do arquivo.
	  *
	  * @param objeto objeto que será armazenado no arquivo.
	  * 
	  * @throws IOException se ocorrer um erro na escrita.
	  **/
	 public abstract void writeObject(Object objeto) throws IOException;

	 /**
	  * Lê o objeto como um registro do arquivo.
	  *
	  * @return um <code>Object</code> com os atributos do objeto lido do arquivo.
	  * 
	  * @throws IOException se ocorrer um erro na leitura.
	  **/
	 public abstract Object readObject() throws IOException;
} // class BinaryFile
