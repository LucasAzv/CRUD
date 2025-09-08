package Entidades;

import java.io.Serializable;
import java.util.Objects;
//Entidade com seus atributos, toString, equals e hascode alem do serial para armazenar e recuperar facilmente os dados.
public class Professor implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nome;
	private Integer id;
	private Integer materiaId; // Foreign Key que liga professor para materia pelo banco de dados

	public Professor(String nome, Integer id, int materiaId) {
		this.nome = nome;
		this.id = id;
		this.materiaId = materiaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public Integer getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Integer materiaId) {
		this.materiaId = materiaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(id, other.id);
	}

	public String toString() {
		return "Nome:" + nome + " ID:" + id + " Id da Matéria: " + materiaId;
	}

}
