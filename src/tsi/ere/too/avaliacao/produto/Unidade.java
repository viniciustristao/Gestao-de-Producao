package tsi.ere.too.avaliacao.produto;
public enum Unidade {
	
	GRAMA("Grama","(G)"),
	QUILOGRAMA("Quilograma","(Kg)"),
	MILILITRO("Mililitro","(Ml)"),
	LITRO("Litro","(L)");
	
	private String unidadedemedida, umed;

	private Unidade(String unidadedemedida, String um) {
		this.unidadedemedida = unidadedemedida;
		this.umed = um;
	}

	public String getUnidadedemedida() {
		return unidadedemedida;
	}

	public void setUnidadedemedida(String unidadedemedida) {
		this.unidadedemedida = unidadedemedida;
	}

	public String getUmed() {
		return umed;
	}

	public void setUmed(String umed) {
		this.umed = umed;
	}
	
	@Override
	public String toString() {
		return String.format("%s%s",unidadedemedida,umed);
	}
	
	
}
