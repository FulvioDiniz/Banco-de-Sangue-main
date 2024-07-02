package poov.controledoacaosangue.model;

public class Doador {

	private Long codigo;
	private String nome;
	private String cpf;
	private String contato;
	private TipoSanguineo tipoSanguineo;
	private RH rh;
	private boolean tipoERhCorretos = false;
	private Situacao situacao = Situacao.ATIVO;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public RH getRh() {
		return rh;
	}

	public void setRh(RH rh) {
		this.rh = rh;
	}

	public boolean isTipoERhCorretos() {
		return tipoERhCorretos;
	}

	public void setTipoERhCorretos(boolean tipoERhCorretos) {
		this.tipoERhCorretos = tipoERhCorretos;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	

	@Override
	public String toString() {
		return "Doador [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", contato=" + contato
				+ ", tipoSanguineo=" + tipoSanguineo + ", rh=" + rh + ", tipoERhCorretos=" + tipoERhCorretos
				+ ", situacao=" + situacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doador other = (Doador) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
