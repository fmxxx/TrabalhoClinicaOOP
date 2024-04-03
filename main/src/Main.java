
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
  // MACROS CORES ANSI (NAO FUNCIONA DO CMD, mas funciona no alacritty e terminal
  // do vscode)
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

  static ArrayList<Medico> listaMedicos = new ArrayList<>();
  static ArrayList<Paciente> listaPacientes = new ArrayList<>();
  public static Agenda listaAgenda = new Agenda();

  public static void main(String[] args) {
    criarEntidadesPadrao();
    menuPrincipal();
  }

  public static void criarEntidadesPadrao() {
    // Medico padrao
    ArrayList<String> especialidadesPadrao = new ArrayList<>();
    especialidadesPadrao.add("Cardiologista");
    ArrayList<String> conveniosPadrao = new ArrayList<>();
    conveniosPadrao.add("UNIMED");

    Medico medico = new Medico("Dr. Willem Default", 1, especialidadesPadrao, conveniosPadrao);
    listaMedicos.add(medico);

    // Paciente padrao
    ArrayList<String> historicoMedicoPadrao = new ArrayList<>();
    historicoMedicoPadrao.add("Diabetes");
    ArrayList<Medicamento> medicamentosUsadosAtualmentePadrao = new ArrayList<>();
    Medicamento medicamento = new Medicamento("Dipirona", "Oral", "De 3 em 3 horas");
    medicamentosUsadosAtualmentePadrao.add(medicamento);

    Paciente paciente = new Paciente(2, "Joao", historicoMedicoPadrao, medicamentosUsadosAtualmentePadrao);
    listaPacientes.add(paciente);
  }

  private static void menuPrincipal() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println(BLUE + "\n ========== Menu ==========" + RESET_COLOR);
      System.out.println("1. Medico");
      System.out.println("2. Paciente");
      System.out.println("0. Sair");
      int opcao;
      while (true) {
        try {
          System.out.print("Escolha uma opcao: ");
          opcao = scanner.nextInt();
          scanner.nextLine(); // Limpar o buffer
          break;
        } catch (InputMismatchException e) {
          EncapsulamentoAndErros.ErroEntradaInvalida();
          scanner.nextLine(); // Limpar o buffer
        }
      }

      switch (opcao) {
        case 1:
          Medico.menuMedico(scanner);
          break;
        case 2:
          Paciente.menuPaciente(scanner);
          break;
        case 0:
          System.out.println("Saindo...");
          scanner.close(); // Fechar o Scanner antes de sair
          return;
        default:
          EncapsulamentoAndErros.ErroOpcaoInvalida();
      }
    }
  }
}
