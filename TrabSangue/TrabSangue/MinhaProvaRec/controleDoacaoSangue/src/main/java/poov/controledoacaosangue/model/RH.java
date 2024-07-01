package poov.controledoacaosangue.model;

public enum RH {

	POSITIVO("Rh+"),
	NEGATIVO("Rh-"),
	DESCONHECIDO("Não sei");
	
	private String descricao;
	
	private RH(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
