package Implementação;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAOop.ProfessorDao;
import Entidades.Professor;

public class ProfessorImple implements ProfessorDao {
	private Connection connection;

	public ProfessorImple(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void add(Professor professor) {
		String sql = "INSERT INTO Professor (nome, idMateria) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, professor.getNome());
			stmt.setInt(2, professor.getMateriaId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Professor findById(Integer id) {
		String sql = "SELECT * FROM Professor WHERE id = ?";
		Professor professor = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				professor = new Professor(rs.getString("nome"), rs.getInt("id"), rs.getInt("idMateria"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professor;
	}

	@Override
	public List<Professor> findAll() {
		String sql = "SELECT * FROM Professor";
		List<Professor> professores = new ArrayList<>();
		try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				
				Professor professor = new Professor(rs.getString("nome"), rs.getInt("id"), rs.getInt("idMateria"));
				professores.add(professor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professores;
	}

	@Override
	public void update(Professor professor) {
		String sql = "UPDATE Professor SET nome = ?, idMateria = ? WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, professor.getNome());
			stmt.setInt(2, professor.getMateriaId());
			stmt.setInt(3, professor.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Aqui utlizamos o select para selecionar as colunas das tabelas, o FROM vem
	// para chamar todos os dados do professor,
	// pois o nosso foco é mostrar qual materia aquele professor leciona. Depois
	// utilizamos da FOREIGN KEY materia id para linkar as duas tabelas
	// E o left join vem para chamar o professor mesmo que ele não tenha uma materia
	// alocada ainda.
	@Override
	public void listarProfessoresComMaterias() {
		String sql = "SELECT Professor.id, Professor.nome, Materia.nomeM, Materia.cargaHoraria " + "FROM Professor "
				+ "LEFT JOIN Materia ON Professor.idMateria = Materia.id";
		// Percorremos as colunas utilizando o resultset
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			// Enquanto tiver dados na tabela, eles vão continuar sendo expostos no console
			while (rs.next()) {
				int professorId = rs.getInt("id");
				String professorNome = rs.getString("nome");
				String materiaNome = rs.getString("nomeM");
				int cargaHoraria = rs.getInt("cargaHoraria");

				System.out.print("Professor ID: " + professorId + ", Nome: " + professorNome);

				if (materiaNome != null) {
					System.out.print(", Matéria: " + materiaNome + ", Carga Horária: " + cargaHoraria + "\n");
				} else {
					System.out.print(", Matéria: N/A, Carga Horária: 0" + "\n");
				} // Aqui fazemos a checagem se o professor tem uma materia ou não
					// Se não houver ele ira sair como null
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar professores com matérias: " + e.getMessage());
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM Professor WHERE id = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}