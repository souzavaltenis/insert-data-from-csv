package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//In�cio da classe de conex�o//
public class ConexaoMySQL {

	public static String serverName = "";    //caminho do servidor do BD
	public static String mydatabase = "";        //nome do seu banco de dados
	public static String url = "";
	public static String username = "";        //nome de um usu�rio de seu BD
	public static String password = "";      //sua senha de acesso
	public static String status = "N�o conectou...";

	//M�todo Construtor da Classe//
	public ConexaoMySQL() {
		
	}
	
	public static void setInfo(String mydatabase, String username, String password) {
		ConexaoMySQL.serverName = "localhost";
		ConexaoMySQL.mydatabase = mydatabase;
		ConexaoMySQL.url = "jdbc:mysql://" + ConexaoMySQL.serverName + "/" + mydatabase;
		ConexaoMySQL.username = username;
		ConexaoMySQL.password = password;
	}

	//M�todo de Conex�o//
	public static Connection getConexaoMySQL() {

		Connection connection = null;          //atributo do tipo Connection
	
		try {
			
			//Carregando o JDBC Driver padr�o
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
	
			connection = DriverManager.getConnection(url, username, password);
	
			//Testa sua conex�o//
			if (connection != null) {
				status = ("STATUS--->Conectado com sucesso!");
			} else {
				status = ("STATUS--->N�o foi possivel realizar conex�o");
			}
			
			return connection;
	
		} catch (ClassNotFoundException e) {  //Driver n�o encontrado
	    	System.out.println("O driver expecificado nao foi encontrado.");
	    	return null;
		} catch (SQLException e) {
			//N�o conseguindo se conectar ao banco
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
	
	}
	
	// M�todo que retorna o status da sua conex�o//
	public static String statusConection() {
		return status;
	}
	
	// M�todo que fecha sua conex�o//
	public static boolean FecharConexao() {
		try {
			ConexaoMySQL.getConexaoMySQL().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	
	}

	// M�todo que reinicia sua conex�o//
	public static java.sql.Connection ReiniciarConexao() {
		FecharConexao();
		return ConexaoMySQL.getConexaoMySQL();

	}

}