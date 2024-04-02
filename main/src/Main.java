
import java.util.ArrayList;
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

  static ArrayList<Medico> listaMedicos = new ArrayList<>();
  static ArrayList<Paciente> listaPacientes = new ArrayList<>();
  public static Agenda agenda = new Agenda();

  public static void main(String[] args) {
    // Medico default
    ArrayList<String> especialidadesDefault = new ArrayList<>();
    especialidadesDefault.add("Cardiologista");
    ArrayList<String> conveniosDefault = new ArrayList<>();
    conveniosDefault.add("UNIMED");

    Medico medico = new Medico("Dr. Default", 1, especialidadesDefault, conveniosDefault);
    listaMedicos.add(medico);

    // Paciente default
    ArrayList<String> historicoMedicoDefault = new ArrayList<>();
    historicoMedicoDefault.add("Diabetes");
    ArrayList<Medicamento> medicamentosUsadosAtualmenteDefault = new ArrayList<>();
    Medicamento medicamento = new Medicamento("Dipirona", "Oral", "De 3 em 3 horas");
    medicamentosUsadosAtualmenteDefault.add(medicamento);

    Paciente paciente = new Paciente(2, "Joao", historicoMedicoDefault, medicamentosUsadosAtualmenteDefault);
    listaPacientes.add(paciente);

    Scanner scanner = new Scanner(System.in);
    // MENU INICIAL
    while (true) {
      System.out.println(BLUE + "\n ========== Menu ==========" + RESET_COLOR);
      System.out.println("1. Medico");
      System.out.println("2. Paciente");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opcao: ");
      int opcao = scanner.nextInt();
      scanner.nextLine(); // Clear the buffer

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
