package Implementação;

import DAOop.MateriaDao;
import Entidades.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaImple implements MateriaDao {

	private Connection connection;

	public MateriaImple(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void add(Materia materia) {
		String sql = "INSERT INTO Materia (nomeM, cargaHoraria) VALUES (?, ?)"; // Nessa String deve conter o codigo sql
																				// para fazer a ação desejada
		try (PreparedStatement stmt = connection.prepareStatement(sql)) { 
																			
			stmt.setString(1, materia.getNomeM());
			stmt.setInt(2, materia.getCargaHoraria());
			stmt.executeUpdate(); //atualização dos dados adicionados no banco
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Esse metodo Percorre o banco de dados atras da id desejada e depois o retorna
	// o resultSet com os dados da id selecionada.
	// Se a id selecionada não existe, depois de percorrer o banco ele retorna null
	@Override
	public Materia findById(Integer id) {
		String sql = "SELECT * FROM Materia WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Materia(rs.getInt("id"), rs.getString("nomeM"), rs.getInt("cargaHoraria"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//O metodo de listar as materias, nos começamos instanciando uma List, em seguida passamos a execução sql se conectando ao banco
	// Pelo PreparedStatement e utilizando o resultset(fazendo percorrer todas as
	// colunas da query criada)para salvar e mostrar a lista do objeto(materia)
	@Override
	public List<Materia> findAll() {
		List<Materia> materias = new ArrayList<>();
		String sql = "SELECT * FROM Materia";
		try (Statement stmt = connection.createStatement(); 
		ResultSet rs = stmt.executeQuery(sql)) {
			// Esse while(rs.next) percorre o banco, adicionando os parametros a nossa List,
			// para ser mostrado no console, e no fim retornamos a lista
			while (rs.next()) {
				materias.add(new Materia(rs.getInt("id"), rs.getString("nomeM"), rs.getInt("cargaHoraria")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materias;
	}

	// Aqui nos passamos o codigo sql que ira atualizar uma materia pela id,
	// utilizamos o preparedStatement para poder executar a nossa consulta no banco,
	// passamos os parametros necessários para realizar a atualização da materia e
	// executamos a atualização no banco
	@Override
	public void update(Materia materia) {
		String sql = "UPDATE Materia SET nomeM = ?, cargaHoraria = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, materia.getNomeM());
			stmt.setInt(2, materia.getCargaHoraria());
			stmt.setInt(3, materia.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Aqui nos passamos o codigo sql que ira deletar uma materia pela id,
	// utilizamos o preparedStatement para poder executar a nossa consulta no banco
	// e por fim passamos o id a ser deletado.

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM Materia WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}