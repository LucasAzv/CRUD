package DAOop;

import Entidades.Professor;
import java.util.List;

// DAO generico para implementar os meus metodos em outra classe
public interface ProfessorDao {
	void add(Professor professor);

	Professor findById(Integer id);

	List<Professor> findAll();

	void listarProfessoresComMaterias();

	void update(Professor professor);

	void delete(Integer id);
}
