package aula07;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class LigaDasLegendas {

	public static void main(String[] args) {
		
Connection conecta = null;
        
        try {
            /*Indicação da conexão com o MySQL através do protocolo JDBC
            //além do IP do servidor (localhost)
            //base de dados ou schema (javamysql)
            //usuário para autenticação (root)
            senha do usuário root */
            
            conecta = DriverManager.getConnection("jdbc:mysql://localhost/ProjetoCat1?" +"user=root&password=1234567");
            
              
           System.out.println("Conexão realizada com sucesso.");
		
        }catch (SQLException ex) {
        	    //Caso ocorram erros na tentativa de conex�o com o MySQL
        	    System.out.println("SQLException: " + ex.getMessage());
        	    System.out.println("SQLState: " + ex.getSQLState());
        	    System.out.println("VendorError: " + ex.getErrorCode());
        	}
        int op;
	do {		
		Scanner recebe = new Scanner(System.in);
		
		System.out.println("---- Escolha uma acao ----");
		System.out.println("*1 - Cadastrar um time\n*2 - Deletar um time da tabela\n*3 - Marcar Jogo\n*4 - Consultar tabela de times");
		op= recebe.nextInt();
		
		switch(op) {
		
		case 1:
			Time[] time = new Time[100];
	        int i=0;
	        int num=-1;
			do {
	            System.out.println("Informe o nome do Time ");
	            String nomeTime = new Scanner(System.in).nextLine();
	            
	            time[i]= new Time();
	            time[i].setNome(nomeTime);
	            
	            //Cadastro do time no banco de dados;
	            try {  
	              PreparedStatement stmt = conecta.prepareStatement("INSERT INTO time(nome, nacionalidade, vitorias, derrotas) VALUES(?,?,?,?);");  
	              stmt.setString(1, time[i].getNome());  
	              stmt.setString(2, time[i].getNacionalidade());  
	               stmt.setInt(3, time[i].getVitorias());  
	              stmt.setInt(4, time[i].getDerrotas());  



	             stmt.execute();  
	              stmt.close();  


	        } catch (SQLException u) {  
	              throw new RuntimeException(u);  
	      }     
	            System.out.println("Time cadastrado com sucesso!!");
	            i++;
	            System.out.println("Digite 0 para sair e 1 para continuar");
	            num=recebe.nextInt();
	        }while(num!=0);
			 System.out.println("Acao Finalizada!");
			break;
			
			case 2:
				int num2 = -1;
				do {
				System.out.println("Digite o ID do time que deseja deletar: ");
				String id = new Scanner(System.in).nextLine();
				
				try {  
		              PreparedStatement stmt = conecta.prepareStatement("DELETE FROM time WHERE id = ?;"); 
		              stmt.setString(1, id);
		              
		              stmt.execute();  
		              stmt.close();  
		              System.out.println("Deletado com sucesso!");
		              
		        } catch (SQLException u) { 
		        	throw new RuntimeException(u);
		        }
				
				System.out.println("Digite 0 para sair e 1 para continuar");
				num2 = new Scanner(System.in).nextInt();
				}while(num2 != 0);
				System.out.println("Acao Finalizada!");
								
				break;
			
			case 3:
				Time ta = new Time();
				Time tv = new Time();
				
				System.out.println("Digite o ID do time do lado Azul: ");
				String ida = new Scanner(System.in).nextLine();
				try {  
					  					  				  
					  PreparedStatement comando = conecta.prepareStatement("SELECT * FROM time WHERE id = ?"); 		 
		              comando.setString(1, ida);
			       
		              ResultSet retorno = comando.executeQuery();
		              
		              while(retorno.next()) {		            
		            	  ta.setNome(retorno.getString("nome"));
		            	  ta.setNacionalidade(retorno.getString("nacionalidade"));
		            	  ta.setVitorias(retorno.getInt("vitorias"));
		            	  ta.setDerrotas(retorno.getInt("derrotas"));
		       
		              }
		              
		              
		        } catch (SQLException u) { 
		        	throw new RuntimeException(u);
		        }
				 System.out.println("Digite o ID do time do lado Vermelho: ");
					  String idv = new Scanner(System.in).nextLine();
				try {  
					 
					  PreparedStatement comando = conecta.prepareStatement("SELECT * FROM time WHERE id = ?"); 		 
		              comando.setString(1, idv);
			       
		              ResultSet retorno = comando.executeQuery();
		              
		              while(retorno.next()) {
		            	  
		            	  tv.setNome(retorno.getString("nome"));
		            	  tv.setNacionalidade(retorno.getString("nacionalidade"));
		            	  tv.setVitorias(retorno.getInt("vitorias"));
		            	  tv.setDerrotas(retorno.getInt("derrotas"));
		            	  		            
		              }
		              
		        } catch (SQLException u) { 
		        	throw new RuntimeException(u);
		        }
				
					Partida Cbolao = new Partida();
		            Cbolao.marcarPartida(ta, tv);
		            Cbolao.iniciarPartida();   	  
				
		            
		            try {  
		            	//Atualização das vitorias e derrotas do Time Azul
		            	
			              PreparedStatement stmtv = conecta.prepareStatement("UPDATE time SET vitorias= ? WHERE nome= ?;"); 
			              PreparedStatement stmtd = conecta.prepareStatement("UPDATE time SET derrotas= ? WHERE nome= ?;");  	
			               stmtv.setInt(1, ta.getVitorias());
			               stmtv.setString(2, ta.getNome());
			               
			               stmtd.setInt(1, ta.getDerrotas());
			               stmtd.setString(2, ta.getNome());
			               
			               stmtv.execute(); 
						   stmtv.close();
						   
						   stmtd.execute(); 
						   stmtd.close();
			              
			              			             						  
						  //Atualização das vitorias e derrotas do Time Vermelho
						   
			              PreparedStatement stmt2v = conecta.prepareStatement("UPDATE time SET vitorias= ? WHERE nome= ?;");  
			              PreparedStatement stmt2d = conecta.prepareStatement("UPDATE time SET derrotas= ? WHERE nome= ?;");
			               stmt2v.setInt(1, tv.getVitorias());
			               stmt2v.setString(2, tv.getNome());
			               
			               stmt2d.setInt(1, tv.getDerrotas());  
			               stmt2d.setString(2, tv.getNome());
			               
			               stmt2v.execute(); 
						   stmt2v.close();
						   
			               stmt2d.execute();			             
			               stmt2d.close();
			             } catch (SQLException u) {  
			              throw new RuntimeException(u);  
			             }     
			              
			               //Atualização da tabela Partida
			               
			               try {  
			 	              PreparedStatement stmt = conecta.prepareStatement("INSERT INTO partida(azul, vermelho) VALUES(?,?);");  
			 	              stmt.setString(1, ida);  
			 	              stmt.setString(2, idv);  			 	              

			 	             stmt.execute();  
			 	              stmt.close();  


			 	        } catch (SQLException u) {  
			 	              throw new RuntimeException(u);  
			              			             			              
			 	        }
			        
		            
		            
				break;
			case 4:
				
				try {  
					Statement comando = conecta.prepareStatement("SELECT * FROM time;"); 		        
				       
		              ResultSet retorno = comando.executeQuery("SELECT * FROM time;");
		              
		      
			            
		              System.out.println("Tabela de Times:");
		              System.out.println("ID    NOME     NACIONALIDADE  V  D");
		              System.out.println("---------------------------------------------");
		                while(retorno.next()){ //Enquanto o retorno possuir linhas 
		                	
		                	System.out.print(retorno.getString("id") + "   ");
		                    System.out.print(retorno.getString("nome") + "   ");
		                    System.out.print(retorno.getString("nacionalidade") + "     ");
		                    System.out.print(retorno.getString("vitorias") + " ");
		                    System.out.println(retorno.getString("derrotas"));
		             		               
		                }
		                
		        } catch (SQLException u) { 
		        	throw new RuntimeException(u);
		        }
				
				break;
			case 5:	
				System.out.println("Encerrando programa!");
				break;
			default:
				System.out.println("Opcao invalida");
				break;
		}
	}while(op!=5);
/*      
          
          //Configuracao do comando SQL a ser executado no banco de dados
            String sql1 = "SELECT nacionalidade from time;";
              
              //Criacao do Statement para execucao de comandos SQL e preparacao para execucao
              //O metodo preparedStatement e utilizado para enviar na conexao o comando SQL
              //e fazer o vinculo do comando com a conexao
              Statement comando = conecta.prepareStatement(sql1);
              
              //Criacao do ResultSet para armazenar o retorno do banco de dados
              //Apos a criacao e requisitado a execucao efetiva do comando no
              //banco de dados pelo metodo exeuteQuery()
              ResultSet retorno = comando.executeQuery(sql1);*/
	}
}