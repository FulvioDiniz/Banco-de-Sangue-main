package poov.controledoacaosangue.model;

public enum TipoSanguineo {

	A("A"),
	B("B"),
	AB("AB"),
	O("O"),
	DESCONHECIDO("Não sei");
	
	private String descricao;
	
	private TipoSanguineo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
