package aula07;

import java.util.Random;

public class Partida {
	//Atributos
	private Time azul;
	private Time vermelho;
	private boolean aprovada;
	
	//Métodos Públicos
	public void marcarPartida(Time t1, Time t2) {
		if(t1 != t2){
			this.aprovada = true;
			this.azul = t1;
			this.vermelho = t2;	
		}else {
			this.aprovada = false;
			this.azul = null;
			this.vermelho = null;
		}
	}
	public void iniciarPartida() {
		if(this.aprovada == true) {
			System.out.println("### Lado Azul ###");
			azul.apresentar();
			System.out.println("### Lado Vermelho ###");
			vermelho.apresentar();
			
			int t1 = 0;
			int t2 = 0;
			while(!(t1 == 3 || t2 == 3)) {
			Random aleatorio = new Random();
			int vencedor = aleatorio.nextInt(2); // 0 1 
			System.out.println("========= Resultado da Partida =========");
			switch(vencedor) {
			case 0:
				System.out.println("Vitoria da equipe "+this.azul.getNome());
				t1++;
				System.out.println(t1);
				break;
			case 1:
				System.out.println("Vitoria da equipe "+this.vermelho.getNome());
				t2++;
				System.out.println(t2);
				break;
				}
			}
			if(t1 == 3) {
				System.out.println("=========== Resultado Final ============");
				this.azul.ganharPartida();
				this.vermelho.perderPartida();
				System.out.println("Vitoria da equipe "+this.azul.getNome());
				System.out.println("Com um placar de \n"+this.azul.getNome()+" "+t1+"x"+t2+" "+this.vermelho.getNome());
			}else if(t2 == 3) {
				System.out.println("=========== Resultado Final ============");
				this.vermelho.ganharPartida();
				this.azul.perderPartida();
				System.out.println("Vitoria da equipe "+this.vermelho.getNome());
				System.out.println("Com um placar de \n"+this.vermelho.getNome()+" "+t2+"x"+t1+" "+this.azul.getNome());
			}else {
				System.out.println("Sem Resultados");
			}
		}else {
			System.out.println("A partida nao pode acontecer!");
		}
		System.out.println("========================================");
	}
	
	//Métodos Especiais
	public Time getAzul() {
		return azul;
	}
	public void setAtacante(Time azul) {
		this.azul = azul;
	}
	public Time getDefensor() {
		return vermelho;
	}
	public void setDesafiante(Time defensor) {
		this.vermelho = defensor;
	}
	public boolean isAprovada() {
		return aprovada;
	}
	public void setAprovada(boolean aprovada) {
		this.aprovada = aprovada;
	}
}