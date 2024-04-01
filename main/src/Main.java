
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
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

  static ArrayList<Medico> listaMedicos = new ArrayList<>();
  static ArrayList<Paciente> listaPacientes = new ArrayList<>();
  private static Agenda agenda = new Agenda();

  public static void main(String[] args) {
    // Medicos default
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
    System.out.println(BLUE + "\n ========== Menu do Medico ==========" + RESET_COLOR);
    System.out.println("1. Cadastrar Medico");
    System.out.println("2. Ver Agenda do Medico"); // TODO: MEDICO ESCOLHE O PERIODO
    // Precisa de criar outro metodo que é chamado nesse menu: HENRIQUE
    System.out.println("3. Ver Detalhes do Paciente");
    // TODO: Adicionar opcao "diagnostico pos consulta" que recebe paciente e guarda
    // nele um array de (bpm, pressao, temperatura, diagnositco, array
    // list(medicacao receitada), observacoes) : FELIPE M
    System.out.println("0. Voltar");
    System.out.print("Escolha uma opcao: ");
    int opcao = scanner.nextInt();
    scanner.nextLine();

    switch (opcao) {
      case 1:
        cadastrarMedico(scanner);
        break;
      case 2:
        visualizarAgendaMedico(scanner);
        break;
      case 3:
        verDetalhesDoPaciente(scanner);
        break;
      case 0:
        System.err.println("Voltando...");
        break;
      default:
        System.out.println(RED + "Opcao invalida! Tente novamente." + RESET_COLOR);
    }
  }

  private static void cadastrarMedico(Scanner scanner) {
    Random random = new Random();
    int id = 10000 + random.nextInt(90000);
    id = id + 100000;

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

    // TODO: Adicionar horarios disponiveis pra consulta (adicionar na classe
    // medico)
    Medico medico = new Medico(nome, id, especialidades, convenios);
    listaMedicos.add(medico);
    System.out.println(GREEN + "Medico cadastrado com ID: " + id + RESET_COLOR);
  }

  private static void visualizarAgendaMedico(Scanner scanner) {
    System.out.print("Informe o ID do medico: ");
    int idMedico = scanner.nextInt();
    scanner.nextLine();

    agenda.visualizarConsultasMarcadas(idMedico);
  }

  private static void menuPaciente(Scanner scanner) {
    System.out.println(BLUE + "\n ========== Menu do Paciente ==========" + RESET_COLOR);
    System.out.println("1. Cadastrar Paciente");
    System.out.println("2. Ver Consultas Marcadas");
    System.out.println("3. Solicitar Consulta");
    System.out.println("0. Voltar");
    // TODO: Opcao para remover informacoes como doencas e medicamentos usados:
    // CARDOSO OU CUNHA
    System.out.print("Escolha uma opcao: ");
    int opcao = scanner.nextInt();
    scanner.nextLine();

    switch (opcao) {
      case 1:
        cadastrarPaciente(scanner);
        break;
      case 2:
        verConsultasMarcadasPaciente(scanner);
        break;
      case 3:
        solicitarConsulta(scanner);
        break;
      case 0:
        System.err.println("Voltando...");
        break;
      default:
        System.out.println(RED + "Opcao invalida! Tente novamente." + RESET_COLOR);
    }
  }

  private static void cadastrarPaciente(Scanner scanner) {
    // Gera ID aleatorio comecando com 2
    Random random = new Random();
    int id = 10000 + random.nextInt(90000);
    id = id + 200000;

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

    // Criar e adicionar o paciente a lista
    Paciente paciente = new Paciente(id, nome, historicoMedico, medicamentosUsadosAtualmente);
    listaPacientes.add(paciente);
    System.out.println(GREEN + "Paciente cadastrado com ID: " + id + RESET_COLOR); // Sucesso
  }

  private static void solicitarConsulta(Scanner scanner) {
    System.out.println(BLUE + "\n ========== Solicitar Consulta ==========" + RESET_COLOR);
    System.out.print("Informe o ID do medico: ");
    int idMedico = scanner.nextInt();
    scanner.nextLine();

    // Solicitar o ID do paciente
    System.out.print("Informe o ID do paciente: ");
    int idPaciente = scanner.nextInt();
    scanner.nextLine();

    // TODO: Encapsular data e horario (ex: 10/03/2000 tem que ser igual a 1030200):
    // FELIPE M
    // Tirar tudo q n for numero dps adiconar '/' dps de 2 e 2 numeros
    System.out.print("Data da consulta (DD/MM/AAAA): ");
    String data = scanner.nextLine();
    System.out.print("Horario da consulta (HH:MM): ");
    String horario = scanner.nextLine();
    System.out.print("Motivo da consulta: ");
    String motivo = scanner.nextLine();
    System.out.print("A consulta eh um retorno? (sim/nao): ");
    String entradaBool = scanner.nextLine().toLowerCase();
    boolean ehRetorno = parseBooleanInput(entradaBool);
    // TODO: Verificar se o horario de consulta bate com o horario de atendimento do
    // medico especificado: CARDOSO OU CUNHA

    Consulta consulta = new Consulta(data, horario, idPaciente, "", idMedico, "", motivo, ehRetorno);
    agenda.adicionarConsulta(idMedico, consulta);
    System.out.println(GREEN + "Consulta solicitada com sucesso!" + RESET_COLOR);
  }

  public static boolean parseBooleanInput(String entrada) {
    // Define quais strings sao true e false
    String[] trueValues = { "sim", "s", "yes", "y", "true", "t", "v" };
    String[] falseValues = { "nao", "n", "no", "false", "f" };

    // Testa se a entrada eh algum dos valores acima
    for (String trueValue : trueValues) {
      if (entrada.equals(trueValue)) {
        return true;
      }
    }
    for (String falseValue : falseValues) {
      if (entrada.equals(falseValue)) {
        return false;
      }
    }

    // Retorna false caso os valores nao batam
    return false;
  }

  private static void verConsultasMarcadasPaciente(Scanner scanner) { // TODO Mover pra classe Paciente: FELIPE M
    System.out.print("Insira o ID do paciente: ");
    int idPaciente = scanner.nextInt();
    scanner.nextLine();

    for (Consulta consulta : agenda.getConsultasMarcadas()) {
      if (idPaciente == consulta.getIdPaciente()) {
        System.out.println(BLUE + "\n ========== Consultas Marcadas ==========" + RESET_COLOR);
        System.out.println("Data: " + consulta.getData());
        System.out.println("Horario: " + consulta.getHorario());
        System.out.println("Medico: " + consulta.getNomeMedico());
        System.out.println("Motivo: " + consulta.getMotivo());
        System.out.println();
      }
    }
  }

  private static void verDetalhesDoPaciente(Scanner scanner) {
    System.out.print("Insira o ID do paciente: ");
    int idBuscado = scanner.nextInt();
    scanner.nextLine(); // Clear the buffer

    // Encontrar o paciente
    for (Paciente paciente : listaPacientes) {
      if (idBuscado == paciente.getId()) {
        System.out.println(BLUE + "\n ========== Detalhes Do Paciente ==========" + RESET_COLOR);
        System.out.println("ID: " + paciente.getId());
        System.out.println("Nome: " + paciente.getNome());
        System.out.println("Historico Medico:");
        for (String historico : paciente.getHistoricoMedico()) {
          System.out.println("- " + historico);
        }
        System.out.println("Medicamentos Usados Atualmente:");
        for (Medicamento medicamento : paciente.getMedicamentosUsadosAtualmente()) {
          System.out.println(
              "- " + medicamento.getUso() + ", " + medicamento.getDescricao() + ", " + medicamento.getPeriodoDeUso());
        }
        // VER CONSULTAS
      }
    }
  }
}
