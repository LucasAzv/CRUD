package DAOop;

import Entidades.Materia;
import java.util.List;

// DAO generico para implementar os meus metodos em outra classe
public interface MateriaDao {
	void add(Materia materia);

	Materia findById(Integer id);

	List<Materia> findAll();

	void update(Materia materia);

	void delete(Integer id);
}