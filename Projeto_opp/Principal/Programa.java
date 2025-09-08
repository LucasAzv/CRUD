package Principal;

import DAOop.ProfessorDao;
import DAOop.MateriaDao;
import Entidades.Professor;
import Implementação.MateriaImple;
import Implementação.ProfessorImple;
import Entidades.Materia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Conexão.conex;

public class Programa {

	public static void main(String[] args) {
		// Aqui Fazemos a conexão com o banco de dados utilizando a nossa classe de
		// conexão, instanciando ela
		// Depois instaciamos os nossos DAO como as classes de implementação utilizando
		// a conexão com o banco
		try (Connection conn = conex.getConnection()) {
			ProfessorDao professorDAO = new ProfessorImple(conn);
			MateriaDao materiaDAO = new MateriaImple(conn);
			Scanner sc = new Scanner(System.in);

			// Menu de escolhas utilizando do while() com um switch, que opera as ações CRUD
			int opcao;
			do {
				System.out.println("===== Menu =====");
				System.out.println("1: Adicionar Professor");
				System.out.println("2: Listar Professores");
				System.out.println("3: Atualizar Professor");
				System.out.println("4: Remover Professor");
				System.out.println("5: Adicionar Matéria");
				System.out.println("6: Listar Matérias");
				System.out.println("7: Atualizar Matéria");
				System.out.println("8: Remover Matéria");
				System.out.println("9: Professor com Matérias");
				System.out.println("10: Sair");
				System.out.print("Escolha uma opção: ");
				opcao = sc.nextInt();
				sc.nextLine(); 

				switch (opcao) {
				case 1:
					System.out.println("Nome do Professor:");
					String nomeP = sc.next();
					System.out.println("Digite o codigo da materia desse professor:");
					int materiaid = sc.nextInt();
					professorDAO.add(new Professor(nomeP, null, materiaid));
					

					break;
				case 2:
					// Instancia a lista de professores chamando o metodo, e logo embaixo
					// , o metodo forEach chama todos os objetos da lista
					// e com o sysout nos listamos cada elemento da lista na sua forma toString.
					List<Professor> professors = professorDAO.findAll();
					professors.forEach(System.out::println);
					break;
				case 3:
					System.out.print("ID do Professor a ser atualizado: ");
					Integer atualizaIdP = sc.nextInt(); // Qual sera o objeto alterado
					Professor professor = professorDAO.findById(atualizaIdP); // Instanciando o objeto professor e
																				// utilizando o id para encontrar O
																				// objeto especifico.
					// Se existir um objeto do tipo professor seguimos com os parametros, para fazer
					// a alteração
					if (professor != null) {
						System.out.print("Novo Nome: ");
						sc.nextLine();
						String nomeProfessor = sc.nextLine();
						System.out.print("Nova ID da Matéria: ");// Aqui fazemos a alteração da materia do professor.
						Integer newMateriaId = sc.nextInt();
						professor.setNome(nomeProfessor); // primeiro atributo
						professor.setMateriaId(newMateriaId);// Segundo atributo que contem a chave estrangeira que liga
																// professor a materia.
						professorDAO.update(professor); // O update sempre vem para poder fazer a alteração no banco de
														// dados,

					} else {
						System.out.println("Professor não encontrado.");
					}
					break;
				case 4:
					// Aqui fazemos a remoção de um objeto pelo seu id, onde novamente so
					// instanciamos uma operação dos metodos dao
					System.out.print("ID do Professor a ser removido: ");
					Integer removeProfessor = sc.nextInt();
					professorDAO.delete(removeProfessor);
					break;
				case 5:
					// Na parte de adcionar a materia passamos o segundo parametro(id) como null,
					// pois o banco de dados ja adiciona um propio por ser uma primary key.
					System.out.println("Digite o nome da materia:");
					String nomeM = sc.next();
					System.out.println("Carga Horária:");
					int cargaH = sc.nextInt();
					materiaDAO.add(new Materia(null, nomeM, cargaH));
				
					break;
				case 6:
					List<Materia> materias = materiaDAO.findAll();
					materias.forEach(System.out::println);
					break;
				case 7:
				
					System.out.print("ID da Matéria a ser atualizada: ");
					Integer atualizaId = sc.nextInt();
					Materia materia = materiaDAO.findById(atualizaId);
					if (materia != null) {
						sc.nextLine();
						System.out.print("Novo Nome: ");
						String nomeMateria = sc.nextLine();
						materia.setNomeM(nomeMateria);
						materiaDAO.update(materia);
						// Aqui realizamos o metodo update no banco de dados, para atualizar de acordo
						// com o objeto 
					} else {
						System.out.println("Matéria não encontrada.");
					}
					break;
				case 8:
					System.out.print("ID da Matéria a ser removida: ");
					Integer removeMateria = sc.nextInt();
					materiaDAO.delete(removeMateria);
					break;
				case 9:
					System.out.println("Professores com matérias:");
					professorDAO.listarProfessoresComMaterias();

					break;
				case 10:
					System.out.println("Saindo do sistema");
					break;
				default:
					System.out.println("Opção inválida.");
				}
			} while (opcao != 10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
