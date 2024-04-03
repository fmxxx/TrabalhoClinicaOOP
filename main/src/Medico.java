import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Medico extends Funcionario {
  private ArrayList<String> especialidades;
  private ArrayList<String> convenios;

  public Medico(String nome, int id, ArrayList<String> especialidades, ArrayList<String> convenios) {
    super(nome, id);
    this.especialidades = especialidades;
    this.convenios = convenios;
  }

  // Metodos
  public static void menuMedico(Scanner scanner) {
    System.out.println(Main.BLUE + "\n ========== Menu do Medico ==========" + Main.RESET_COLOR);
    System.out.println("1. Cadastrar Medico");
    System.out.println("2. Ver Agenda do Medico");
    System.out.println("3. Ver Detalhes do Paciente");
    System.out.println("4. Adicionar Relatorio Pos Consulta ao um Paciente");
    System.out.println("0. Voltar");
    int opcao;
    while (true) {
      try {
        System.out.print("Escolha uma opcao: ");
        opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar Buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }

    switch (opcao) {
      case 1:
        Medico.cadastrarMedico(scanner);
        break;
      case 2:
        Main.listaAgenda.verAgendaMedica(scanner);
        break;
      case 3:
        Paciente.verDetalhesDoPaciente(scanner);
        break;
      case 4:
        Paciente.adicionarRelatorioAoPaciente(scanner);
        break;
      case 0:
        System.out.println("Voltando...");
        break;
      default:
        EncapsulamentoAndErros.ErroOpcaoInvalida();
    }
  }

  public static void cadastrarMedico(Scanner scanner) {
    Random random = new Random();
    int id = 10000 + random.nextInt(90000);
    id = id + 100000;

    System.out.print("Informe o nome do medico: ");
    String nome = scanner.nextLine();

    ArrayList<String> especialidades = new ArrayList<>();
    System.out.println("\nInforme as especialidades do medico (digite '0' para parar): ");
    while (true) {
      System.out.print("Especialidade: ");
      String especialidade = scanner.nextLine();
      if (especialidade.equals("0")) {
        break;
      }
      especialidades.add(especialidade);
    }

    ArrayList<String> convenios = new ArrayList<>();
    System.out.println("\nInforme os convenios do medico (digite '0' para parar): ");
    while (true) {
      System.out.print("Convenio: ");
      String convenio = scanner.nextLine();
      if (convenio.equals("0")) {
        break;
      }
      convenios.add(convenio);
    }

    Medico medico = new Medico(nome, id, especialidades, convenios);
    Main.listaMedicos.add(medico);
    System.out.println(Main.GREEN + "Medico cadastrado com ID: " + id + Main.RESET_COLOR);
  }

  public static void exibirMedicosDisponiveis(ArrayList<Medico> medicos) {
    System.out.println("\n========== Lista Dos Medicos Disponiveis ==========");
    for (Medico medico : medicos) {
      System.out.println("Nome: " + medico.getNome());
      System.out.println("ID: " + medico.getId());
      System.out.println("Especialidades:");
      for (String especialidade : medico.getEspecialidades()) {
        System.out.println("- " + especialidade);
      }
      System.out.println("Convenios:");
      for (String convenio : medico.getConvenios()) {
        System.out.println("- " + convenio);
      }
      System.out.print("==============================================\n");
    }
    System.out.println();
  }

  // Getters
  public ArrayList<String> getEspecialidades() {
    return especialidades;
  }

  public ArrayList<String> getConvenios() {
    return convenios;
  }

  public void setEspecialidades(ArrayList<String> especialidades) {
    this.especialidades = especialidades;
  }

  // Setters
  public void setConvenios(ArrayList<String> convenios) {
    this.convenios = convenios;
  }

}
