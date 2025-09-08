package Entidades;

import java.io.Serializable;
import java.util.Objects;

//Entidade com seus atributos, toString, equals e hascode alem do serial para armazenar e recuperar facilmente os dados.

public class Materia implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nomeM;
	private int cargaHoraria;

	public Materia(Integer id, String nomeM, int cargaHoraria) {
		this.id = id;
		this.nomeM = nomeM;
		this.cargaHoraria = cargaHoraria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeM() {
		return nomeM;
	}

	public void setNomeM(String nomeM) {
		this.nomeM = nomeM;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Matéria:" + nomeM + " ID:" + id + " Carga Horaria:" + cargaHoraria;
	}
}
	
	
	
	


