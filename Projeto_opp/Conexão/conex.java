package Conexão;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conex { // Conexão com o banco de dados, informações do local da tabela, senha do banco
						// e qual usuario;

	private static final String user = "postgres"; // usuario do banco
	private static final String pass = "!Aa32330306"; // senha do banco
	private static final String url = "jdbc:postgresql://localhost:5432/Projeto"; // Onde esta localizada a tabela do
																					// banco
	// efetuando a conexão

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, user, pass);

	}
}
