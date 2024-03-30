import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
  // MACROS CORES ANSI
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

  private static ArrayList<Medico> listaMedicos = new ArrayList<>();
  private static ArrayList<Paciente> listaPacientes = new ArrayList<>();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println(BLUE + "\n ========== Menu ==========" + RESET_COLOR);
      System.out.println("1. Funcionario (Medico)");
      System.out.println("2. Paciente");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opcao: ");
      int opcao = scanner.nextInt();
      scanner.nextLine(); // Clear the buffer

      switch (opcao) {
        case 1:
          menuMedico(scanner);
          break;
        case 2:
          menuPaciente(scanner);
          break;
        case 0:
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
    System.out.println("0. Voltar");
    System.out.print("Escolha uma opcao: ");
    int opcao = scanner.nextInt();
    scanner.nextLine(); // Clear the buffer

    switch (opcao) {
      case 1:
        cadastrarMedico(scanner);
        break;
      case 2:
        // verAgendaMedico(scanner);
        break;
      case 0:
        System.err.println("Voltando...");
        break;
      default:
        System.out.println(RED + "Opcao invalida! Tente novamente." + RESET_COLOR); // ERRO
    }
  }

  private static void cadastrarMedico(Scanner scanner) {
    // Gera ID aleatorio comecando com 1
    Random random = new Random();
    int id = 10000000 + random.nextInt(90000000);

    System.out.print("Informe o nome do medico: ");
    String nome = scanner.nextLine();

    ArrayList<String> especialidades = new ArrayList<>();
    ArrayList<String> convenios = new ArrayList<>();

    System.out.println("\nInforme as especialidades do medico (digite '0' para parar): ");
    while (true) {
      System.out.print("Especialidade: ");
      String especialidade = scanner.nextLine();
      if (especialidade.equals("0")) {
        break;
      }
      especialidades.add(especialidade);
    }

    System.out.println("\nInforme os convenios do medico (digite '0' para parar): ");
    while (true) {
      System.out.print("Convenio: ");
      String convenio = scanner.nextLine();
      if (convenio.equals("0")) {
        break;
      }
      convenios.add(convenio);
    }

    System.out.println(GREEN + "Medico cadastrado com ID: " + id + RESET_COLOR); // Sucesso
    listaMedicos.add(new Medico(nome, id, especialidades, convenios));
  }

  private static void menuPaciente(Scanner scanner) {
    System.out.println(BLUE + "\n ========== Menu do Paciente ==========" + RESET_COLOR);
    System.out.println("1. Cadastrar Paciente");
    System.out.println("2. Ver Consultas Marcadas");
    System.out.println("0. Voltar");
    System.out.print("Escolha uma opcao: ");
    int opcao = scanner.nextInt();
    scanner.nextLine(); // Clear the buffer

    switch (opcao) {
      case 1:
        cadastrarPaciente(scanner);
        break;
      case 2:
        // verConsultasMarcadas(scanner);
        break;
      case 0:
        System.err.println("Voltando...");
        break;
      default:
        System.out.println(RED + "Opcao invalida! Tente novamente." + RESET_COLOR); // ERRO
    }
  }

  private static void cadastrarPaciente(Scanner scanner) {
    Random random = new Random();
    int id = 20000000 + random.nextInt(90000000); // Gera ID aleatorio de 8 digitos, comecando com 2

    System.out.print("Informe o nome do paciente: ");
    String nome = scanner.nextLine();

    ArrayList<String> historicoMedico = new ArrayList<>();
    ArrayList<Medicamento> medicamentosUsadosAtualmente = new ArrayList<>();

    System.out.println("\nInforme o historico medico do paciente (digite '0' para parar): ");
    while (true) {
      System.out.print("Historico medico: ");
      String historico = scanner.nextLine();
      if (historico.equals("0")) {
        break;
      }
      historicoMedico.add(historico);
    }

    System.out.println("\nInforme os medicamentos usados atualmente pelo paciente (digite '0' para parar): ");
    while (true) {
      System.out.print("Medicamento: ");
      String nomeMedicamento = scanner.nextLine();
      if (nomeMedicamento.equals("0")) {
        break;
      }
      System.out.print("Uso: ");
      String uso = scanner.nextLine();
      System.out.print("Descricao: ");
      String descricao = scanner.nextLine();
      System.out.print("Periodo de uso: ");
      String periodoDeUso = scanner.nextLine();

      medicamentosUsadosAtualmente.add(new Medicamento(uso, descricao, periodoDeUso));
    }

    // Criar o paciente com as informacoes coletadas
    Paciente paciente = new Paciente(id, nome, historicoMedico, medicamentosUsadosAtualmente);
    listaPacientes.add(paciente);
    System.out.println(GREEN + "Paciente cadastrado com ID: " + id + RESET_COLOR); // Sucesso
  }

}
