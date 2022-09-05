package aula07;
public class Time {
	//Atributos
	private String nome;
	private String nacionalidade;
	private int vitorias;
	private int derrotas;
	
	// Métodos Públicos
	public void apresentar() {
		System.out.println("CHEGOU A HORA! Apresentamos o time "+ this.getNome());
		System.out.println("Diretamente de "+ this.getNacionalidade());
		System.out.println(this.getVitorias()+" Vitorias");
		System.out.println(this.getDerrotas()+ " Derrotas");
		System.out.println("----------------------------------");
	}
	public void status() {
		System.out.println("-----------------------------------");
		System.out.println(this.getNome());
		System.out.println("Ganhou "+ this.getVitorias()+ " vezes");
		System.out.println("Perdeu "+ this.getDerrotas()+ " vezes");
	}
	
	public void ganharPartida() {
		this.setVitorias(this.getVitorias() + 1);
	}
	public void perderPartida() {
		this.setDerrotas(this.getDerrotas()+1);
	}
	
	// Métodos Especiais
	public Time() {
		super();
		this.nacionalidade = "Brasil";
		this.vitorias = 0;
		this.derrotas = 0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public int getVitorias() {
		return vitorias;
	}

	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
}