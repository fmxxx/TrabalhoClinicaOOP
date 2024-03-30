import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
  // CORES ANSI
  public static final String RESET_COLOR = "\u001B[0m";
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";

  // ESTILOS ANSI
  public static final String BOLD = "\u001B[1m";
  public static final String UNDERLINE = "\u001B[4m";
  public static final String REVERSED = "\u001B[7m";

  private static ArrayList<Medico> medicos = new ArrayList<>();
  private static ArrayList<Paciente> pacientes = new ArrayList<>();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println(BLUE + "\n ========== Menu ==========" + RESET_COLOR);
      System.out.println("1. Funcionario (Medico)");
      System.out.println("2. Paciente");
      System.out.println("3. Sair");
      System.out.print("Escolha uma opcao: ");
      int opcao = scanner.nextInt();

      switch (opcao) {
        case 1:
          menuMedico(scanner);
          break;
        case 2:
          menuPaciente(scanner);
          break;
        case 3:
          System.out.println("Saindo...");
          scanner.close(); // Fechar o Scanner antes de sair
          return;
        default:
          System.out.println(RED + "Opcao invalida! Tente novamente." + RESET_COLOR); // ERRO
      }
    }
  }

  private static void menuMedico(Scanner scanner) {
    System.out.println(BLUE + "\n ========== Menu Do Medico ==========" + RESET_COLOR);
    System.out.println("1. Cadastrar Medico");
    System.out.println("2. Ver Agenda do Medico");
    System.out.print("Escolha uma opcao: ");
    int opcao = scanner.nextInt();

    switch (opcao) {
      case 1:
        cadastrarMedico(scanner);
        break;
      case 2:
        verAgendaMedico(scanner);
        break;
      default:
        System.out.println(RED + "Opcao invalida! Tente novamente." + RESET_COLOR); // ERRO
    }
  }

  private static void cadastrarMedico(Scanner scanner) {
    Random random = new Random();
    int id = 10000000 + random.nextInt(90000000); // Gera ID aleatorio de 8 digitos

    System.out.print("Informe o nome do medico: ");
    String nome = scanner.next();

    ArrayList<String> especialidades = new ArrayList<>();
    ArrayList<String> convenios = new ArrayList<>();

    System.out.println("\nInforme as especialidades do medico (digite '0' para parar): ");
    while (true) {
      System.out.print("Especialidade: ");
      String especialidade = scanner.next();
      if (especialidade.equals("0")) {
        break;
      }
      especialidades.add(especialidade);
    }

    System.out.println("\nInforme os convenios do medico (digite '0' para parar): ");
    while (true) {
      System.out.print("Convenio: ");
      String convenio = scanner.next();
      if (convenio.equals("0")) {
        break;
      }
      convenios.add(convenio);
    }

    System.out.println(GREEN + "Medico cadastrado com ID: " + id + RESET_COLOR); // Sucesso
    medicos.add(new Medico(nome, id, especialidades, convenios));
  }

  private static void verAgendaMedico(Scanner scanner) {
    System.out.print("Digite o ID do Medico: ");
    int idMedico = scanner.nextInt();
    boolean encontrado = false;

    for (Medico medico : medicos) {
      if (medico.getId() == idMedico) {
        encontrado = true;
        // Aqui voce pode implementar a logica para exibir a agenda do medico
        System.out.println("\nAgenda do Medico " + medico.getNome() + ":");
        // Implementacao da logica de exibicao da agenda
        break;
      }
    }

    if (!encontrado) {
      System.out.println(RED + "Acesso negado! ID do Medico nao encontrado." + RESET_COLOR);
    }
  }

  private static void menuPaciente(Scanner scanner) {
    System.out.println(BLUE + "\n ========== Menu Do Paciente ==========" + RESET_COLOR);
    System.out.println("1. Cadastrar Paciente");
    System.out.println("2. Solicitar Consulta por Medico");
    System.out.print("Escolha uma opcao: ");
    int opcao = scanner.nextInt();

    switch (opcao) {
      case 1:
        // cadastrarPaciente();
        break;
      case 2:
        // solicitarConsulta(scanner);
        break;
      default:
        System.out.println(RED + "Opcao invalida! Tente novamente." + RESET_COLOR); // ERRO
    }
  }
}