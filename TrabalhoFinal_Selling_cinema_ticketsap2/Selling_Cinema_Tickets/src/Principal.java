import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Map; 

public class Principal {
	public static void main(String[] args) {
	    Connection conn = null;
		try {
			String url = "jdbc:sqlite:C:\\Users\\dougl\\TrabalhoFinalSIBD.db";
			conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			//statement.executeUpdate("insert into Usuario values('Douglas', '073234', 05102001, 'd@gmail.com', 123)");
		
		
		Input input = new Input();
		Cartaz cartaz = new Cartaz();
		
		Filme filme1 = new Filme();
		Filme filme2 = new Filme();
		Filme filme3 = new Filme();
		Filme filme4 = new Filme();
		Filme filme5 = new Filme();
		Filme filme6 = new Filme();
		
		Sessao sessao1 = new Sessao();
		Sessao sessao2 = new Sessao();
		Sessao sessao3 = new Sessao();
		Sessao sessao4 = new Sessao();
		Sessao sessao5 = new Sessao();

		
		sessao1.adicionarSessao(1, "10/06 10:00h", "Sala 01");
		sessao2.adicionarSessao(2, "10/06 13:00h", "Sala 06");
		sessao3.adicionarSessao(3, "10/06 18:00h", "Sala 03");
		sessao4.adicionarSessao(4, "10/06 20:00h", "Sala 05");
		
		Sessao[] listaDeSessoes = {sessao1, sessao2, sessao3, sessao4}; 

		
		/*Valor esperado pelo metodo:
		 *
		 *adicionarFilme(
		 * 		int idFilme,
		 * 		String nomeFilme,  
		 * 		double valorFilme, 
		 * 		String tempoFilme, 
		 * 		int classificacaoIdade,
		 * 		Sessao[] sessoes
		 */
		
		filme1.adicionarFilme(1, "Carros2", 30.3, "2:22", 15, listaDeSessoes);
		filme2.adicionarFilme(2, "Homem de Ferro 3", 20.1, "1:42", 18, listaDeSessoes);
		filme3.adicionarFilme(4, "The Batman", 30.3, "2:22", 15, listaDeSessoes);
		filme4.adicionarFilme(7, "Tenet", 20.1, "1:42", 18, listaDeSessoes);
		filme5.adicionarFilme(6, "Segredos de Dumbledore", 20.1, "1:42", 18, listaDeSessoes);
		
		//Add Filmes ao Cartaz
		cartaz.setFilmes(filme1);
		cartaz.setFilmes(filme2);
		cartaz.setFilmes(filme3);
		cartaz.setFilmes(filme4);
		cartaz.setFilmes(filme5);
			
		//Logica para roda o codigo
		while (true) {
			int resposta;
			int idFilmeEscolhido;
			
			//Nome da aplicacao
			System.out.println("____________________________________________________");
			System.out.println("                                                    ");
			System.out.format("%40s" , "Venda de Tickets Para Cinema\n");
			System.out.println("____________________________________________________\n");
			
			{//Primeiro input. 
				//A variavel opcoes contem os nomes que devem ser impressos para o usuário
				String[] opcoes = {"Cliente","Administrador"};
				String mensagem = "-Ola, escolhar uma opcao abaixo. \n-E digite o numero da opcao:";
				resposta = input.inputInt(opcoes, 0, mensagem);
			}
			
			//IF Nº 1
			if (resposta == 1) {
				int quantidadeFilme = cartaz.listarFilmes();
				
				{//Input que recebe o filme escolhido pelo user.
					String[] opcoes = new String[0];
					String mensagem = "-Escolhar um dos filmes citadas acima. \n-E digite o id do Filme:";
					idFilmeEscolhido = input.inputInt(opcoes, quantidadeFilme, mensagem);
					cartaz.setFilmeEscolhido(idFilmeEscolhido);
				}
				
				//Vai retornar o nome do filme escolhido
				String filmeEscolhido = cartaz.listarFilme();
				
				System.out.println("\n-Voce escolheu o filme: ");
				System.out.println("   " + filmeEscolhido + "\n");
				
				{//Input lista detalhes do filme ou Voltar para o inicio
					String[] opcoes = {"Detalhes do Filme \"" + filmeEscolhido+"\"","Voltar"};
					String mensagem = "-Escolhar uma opcao abaixo. \n-E digite o numero da opcao:";
					resposta = input.inputInt(opcoes, 0, mensagem);
				}
				
				//IF Nº 2
				if (resposta == 1) {
					cartaz.detalhesDoFilmeEscolhido();
					
					{//Input Comprar ingresso ou Voltar para o inicio
						String[] opcoes = {"Comprar ingresso","Cancelar"};
						String mensagem = "-Escolhar uma opcao abaixo. \n-E digite o numero da opcao:";
						resposta = input.inputInt(opcoes, 0, mensagem);
					}
					
					//IF Nº 3
					if (resposta == 1) {
						interno:
						while (true) {
							int respostaDoUser2;
							int numDeSessoes = cartaz.listarSessoesDoFilmeEscolhido();
							
							{//Input escolher sessao
								String[] opcoes = new String[0];
								String mensagem = "-Escolhar uma sessao listada acima. \n-E digite o numero da opcao:";
								resposta = input.inputInt(opcoes, numDeSessoes, mensagem);
								cartaz.setSessaoEscolhida(resposta);
							}
							
							{//Input continuar ou trocar sessao ou voltar para inicio
								String[] opcoes = {"Continuar", "Trocar Sessao", "Cancelar"};
								String mensagem = "\n-Escolhar uma opcao listada abaixo:";
								respostaDoUser2 = input.inputInt(opcoes, 0, mensagem);
							}
							
							//IF Nº 4
							if (respostaDoUser2 == 1) { //Continuar
								Boolean finalizarCompra = false;
								Integer idDaPoltrona = null;
								System.out.println(finalizarCompra);
								
								
								interno2:
								while (true) {
									if (finalizarCompra == false) {
										System.out.println();
										System.out.println("-Escolhar uma poltrona listada abaixo.\n-E digite o nome dela:");
										
										//key=numPoltrona e values=idPoltrona
										Map <String, Integer> numDasPoltronasLivres = cartaz.listarPoltronasDoFilmeEscolhido();
										idDaPoltrona = input.inputStr(numDasPoltronasLivres);
									}
									
									
									if (idDaPoltrona != null) {
										if (finalizarCompra == false) {
											cartaz.setDicPoltronasEscolhidas(idDaPoltrona);
											//OBS: Resolver bug das poltronas: Se a comprar for cancelar as poltronas escolhidas pelo cliente devem voltar ao estado anterior "false".
											
											cartaz.imprimirPoltronasEscolhidas();
											
											{//Input escolher mais uma poltrona ou finalizar comprar ou voltar para inicio
												String[] opcoes = {"Finalizar Comprar", "Escolher outra poltrona", "Remover Poltrona Escolhida","Cancelar"};
												String mensagem = "\n-Escolhar uma opcao listada abaixo:";
												resposta = input.inputInt(opcoes, 0, mensagem);
											}
										}
										
										
										//IF Nº 5
										if (resposta == 1) { //Finalizar Comprar
											if(cartaz.sizeDePoltronasEscolhidas()>0) {
												//Imprimir nota fiscal
												
												System.out.println("______________________________________");
												System.out.println("                                                    ");
												System.out.format("%38s","-------------Nota Fiscal--------------\n\n");
												String[] dadosDaCompra = cartaz.dadosDaCompra();
												
												System.out.format("%-7s%31s\n","Filme: ",dadosDaCompra[0]);
												System.out.format("%-10s%28s\n","Sessao: ",dadosDaCompra[1]);
												System.out.format("%-11s%27s\n","Poltronas: ",dadosDaCompra[2]);
												System.out.format("%-16s%22s\n\n\n\n","Valor Do Filme: ","R$ "+dadosDaCompra[3]);
												System.out.format("%-10s%28s\n","Total: ","R$ "+dadosDaCompra[4]);
												System.out.println("______________________________________");
												
												{//Input pagar compra ou cancelar compra
													String[] opcoes = {"Pagar", "Cancelar compra"};
													String mensagem = "\n-Escolhar uma opcao listada abaixo:";
													resposta = input.inputInt(opcoes, 0, mensagem);
												}
												if(resposta == 1) {//Pagar
													System.out.println("Informe alguns dados, para podermos finalizar a compra.");
													System.out.print("Nome Completo: ");
													String nomeCliente = input.inputStrLogin();
													System.out.print("Data de Nascimento: ");
													String dataCliente = input.inputStrLogin();
													System.out.print("CPF: ");
													String cpfCliente = input.inputStrLogin();
													
													Cliente cliente1 = new Cliente(nomeCliente, cpfCliente, dataCliente);
													
													System.out.print("Numero do Cartao de Credito: ");
													String numeroCartao = input.inputStrLogin();
													System.out.print("Vencimento do Cartao de Credito: ");
													String vencimentoCartao = input.inputStrLogin();
													System.out.print("CVV do Cartao de Credito: ");
													String cvvCartao = input.inputStrLogin();
													
													
													System.out.println("Comprar realizada com sucesso");
													
													//Implementar funcionalidade de imprimir os tickets depois da compra ser efetuada
													
												} else if(resposta == 2) {//Cancelar compra
													break interno;
												}
												
												break interno2;
											}else {
												System.out.println("e preciso escolher uma poltrona para poder finalizar a compra.");
												finalizarCompra = false;
											}
											
											
										} else if(resposta == 2) { //Escolher outra poltrona
											//Nao faz nada, deixa o loop interno2 roda de novo
											
										} else if(resposta == 3) { //Remover Poltrona Escolhida
											
											interno3:
											while(true) {
												System.out.println();
												cartaz.imprimirPoltronasEscolhidas();
												System.out.println("-Das poltronas listadas acima. Escolhar uma para removela.\n-E digite o nome dela:");
												
												Map <String, Integer> numDasPoltronasEscolhidas = cartaz.numDasPoltronasEscolhidas();
												idDaPoltrona = input.inputStr(numDasPoltronasEscolhidas);
												
												if(idDaPoltrona != null) {
													cartaz.removerPoltronaEscolhida(idDaPoltrona);
													
													{//Input escolher mais uma poltrona ou finalizar comprar ou voltar para inicio
														String[] opcoes = {"Finalizar Comprar", "Escolher outra poltrona", "Remover Outra Poltrona Escolhida","Cancelar"};
														String mensagem = "\n-Escolhar uma opcao listada abaixo:";
														resposta = input.inputInt(opcoes, 0, mensagem);
													}
													if (resposta == 1) { //Finalizar Comprar
														finalizarCompra = true;
														resposta = 1;
														break interno3;
														
													} else if(resposta == 2) { //Escolher outra poltrona
														//Da break no loop interno3, e deixar o loop interno2 roda de novo
														break interno3;
														
													} else if(resposta == 3) { //Remover outra Poltrona Escolhida
														//Faz nada, deixar o loop interno3 roda de novo
														
													} else if(resposta == 4) {//Cancelar
														break interno;
													}
												}
											}
											
											
										} else if(resposta == 4) {//Cancelar
											break interno;
										}
										
										
									}
								}
//								
								break interno;
							} else if(respostaDoUser2 == 2) { //Trocar Sessao
								//Nao faz nada e voltar para o comeco do loop interno
							
							} else if(respostaDoUser2 == 3) { //Cancelar
								//Da brack no loop interno e voltar para o comeco do loop externo
								break interno;
							}
						}
						
						
					} else if (resposta == 2){
						//Nao faz nada e voltar para o comeco do loop
					}
				
				} else if (resposta == 2){
					//Nao faz nada e voltar para o comeco do loop
				}
				
				
			}else if (resposta == 2) {
				/*Implmentar Funcionalidades do administrador
				 *  -Cadastrar Filme
				 *  -Deletar Filme
				 *  -Criar Sessoes
				 *  -Deletar Sessoes
				 */
				String[] opcoes = {"Cadastrar Filme", "Deletar Filme", "Cadastrar Sessao", "Deletar Sessao"};
				System.out.println("______________________________________");
				System.out.println("Voc� est� acessando como administrador.");
				String mensagem = "\n-Escolha uma das op��es abaixo. \n-E digite o n�mero da op��o: ";
				resposta = input.inputInt(opcoes, 0, mensagem);
				//Cadastrar Filme
				if (resposta == 1) {
					//Scanner para inteiro
					Scanner i = new Scanner(System.in);
					//Scanner para String
					Scanner s = new Scanner(System.in);
					//Scanner para Double
					Scanner d = new Scanner(System.in);

					int idNovoFilme;
					String nomeNovoFilme;
					double valorNovoFilme;
					String tempoNovoFilme;
					int classificacaoNovoIdade;
					
					System.out.println("Digite um id v�lido para o Filme: ");
					idNovoFilme = i.nextInt();
					System.out.println("Digite o nome do Filme: ");
					nomeNovoFilme = s.nextLine();
					System.out.println("Digite o valor do Filme: ");
					valorNovoFilme = d.nextDouble();
					System.out.println("Digite o tempo do Filme: ");
					tempoNovoFilme = s.nextLine();
					System.out.println("Digite a classifica��o do Filme: ");
					classificacaoNovoIdade = i.nextInt();
			
					filme6.adicionarFilme(idNovoFilme, nomeNovoFilme, valorNovoFilme, tempoNovoFilme, classificacaoNovoIdade, listaDeSessoes);
					PreparedStatement statement1 = conn.prepareStatement("insert into Filme values(?,?,?,?,?)");
					statement1.setInt(1, idNovoFilme);
					statement1.setString(2, nomeNovoFilme);
					statement1.setDouble(3, valorNovoFilme);
					statement1.setString(4, tempoNovoFilme);
					statement1.setInt(5, classificacaoNovoIdade);
					statement1.executeUpdate();
					
				}
					//Deletar Filme
				else if (resposta == 2) {
					int iddeletar;
					
					ResultSet rs = statement.executeQuery("Select * From Filme");
					System.out.println("\n-Filmes que est�o em cartaz.");
					while(rs.next()) {
						int idFilme = rs.getInt("idfilme");
						String nomeFilme = rs.getString("nome");
						System.out.println("Nome do filme: " + nomeFilme + "       Id: " + idFilme + "\n");
					}
					System.out.println("-Digite o id do filme que deseja deletar: ");
					Scanner i = new Scanner(System.in);
					iddeletar = i.nextInt();
					
					PreparedStatement statement2 = conn.prepareStatement("Delete from Filme where idfilme = (?)");
					statement2.setInt(1, iddeletar);
					statement2.executeUpdate();

				} 
					//Cadastrar Sessoes
				else if (resposta == 3) {
					//Scanner para inteiro
					Scanner i = new Scanner(System.in);
					//Scanner para String
					Scanner s = new Scanner(System.in);
					
					int idNovaSessao;
					String dataSessao;
					String localSessao;
					
					System.out.println("-Digite um id para cadastrar a sess�o: ");
					idNovaSessao = i.nextInt();
					System.out.println("-Digite a data da sess�o: ");
					dataSessao = s.nextLine();
					System.out.println("-Digite o local (N� da Sala) da sess�o: ");
					localSessao = s.nextLine();
					
					sessao5.adicionarSessao(idNovaSessao, dataSessao, "Sala " + localSessao);
					PreparedStatement statement1 = conn.prepareStatement("insert into Sessao values(?,?,?)");
					statement1.setInt(1, idNovaSessao);
					statement1.setString(2, dataSessao);
					statement1.setString(3, localSessao);
					statement1.executeUpdate();
						
					}
					//Deletar Sessao
				else if (resposta == 4) {
					int idSessaoDel;
					
					ResultSet rs = statement.executeQuery("Select * From Sessao");
					System.out.println("\n-Sess�es Vigentes: ");
					while(rs.next()) {
						int idSessao = rs.getInt("idsessao");
						String dataSessao = rs.getString("data");
						String localSessao = rs.getString("local");
						System.out.println("Id da Sess�o: " + idSessao + "       Data: " + dataSessao + "     Local: Sala " + localSessao + "\n");
					}
					System.out.println("-Digite o id da sess�o que deseja deletar: ");
					Scanner i = new Scanner(System.in);
					idSessaoDel = i.nextInt();
					
					PreparedStatement statement2 = conn.prepareStatement("Delete from Sessao where idsessao = (?)");
					statement2.setInt(1, idSessaoDel);
					statement2.executeUpdate();
				}
				
				
				
				
			}
		
		}
		
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		
		
	}
}
