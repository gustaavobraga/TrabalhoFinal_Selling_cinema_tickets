
public class Filme{
	
	private int idFilme;
	private String nomeFilme;
	private	double valorFilme;
	private String tempoFilme;
	private int classificacaoIdade;
	private Sessao[] sessoes = new Sessao[10];
	
	
	public String getNomeFilme() {
		return nomeFilme;
	}
	
	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}
	
	public int getIdFilme() {
		return idFilme;
	}
	
	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}
	
	public double getValorFilme() {
		return valorFilme;
	}
	
	public void setValorFilme(double valorFilme) {
		this.valorFilme = valorFilme;
	}
	
	public String getTempoFilme() {
		return tempoFilme;
	}

	public void setTempoFilme(String tempoFilme) {
		this.tempoFilme = tempoFilme;
	}

	public int getClassificacaoIdade() {
		return classificacaoIdade;
	}

	public void setClassificacaoIdade(int classificacaoIdade) {
		this.classificacaoIdade = classificacaoIdade;
	}
	
	public Sessao[] getSessoes() {
		return sessoes;
	}

	public void setSessoes(Sessao[] sessoes) {
		//Cada filme deve receber uma lista de sessoes e cada sessao deve ter uma lista de poltronas
		//For-each 
		for (Sessao valor: sessoes) {
			
			loopInterno:
			for (int i = 0; i < 10; i++) {
				if (this.sessoes[i] == null) {
					this.sessoes[i] = valor;
					break loopInterno;
				}
			}
		}
		this.sessoes = sessoes;
	}

	public void adicionarFilme(int idFilme, String nomeFilme,  double valorFilme, 
							   String tempoFilme, int classificaçãoIdade, Sessao[] sessoes) {
		setNomeFilme(nomeFilme);
		setIdFilme(idFilme);
		setValorFilme(valorFilme);
		setTempoFilme(tempoFilme);
		setClassificacaoIdade(classificaçãoIdade);
		setSessoes(sessoes);
	}
	
	public void adicionarFilme(int idFilme, String nomeFilme,  double valorFilme, 
			   String tempoFilme, int classificaçãoIdade) {
		setNomeFilme(nomeFilme);
		setIdFilme(idFilme);
		setValorFilme(valorFilme);
		setTempoFilme(tempoFilme);
		setClassificacaoIdade(classificaçãoIdade);
	}	
		
	public String verFilme() {
		String informacoes = "Nome: " + this.getNomeFilme() + "\nValor: " + this.getValorFilme() + " reais";
		return informacoes;
	}
}