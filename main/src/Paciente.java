import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Paciente {
  private int id;
  private String nome;
  private ArrayList<String> historicoMedico;
  private ArrayList<Medicamento> medicamentosUsadosAtualmente;
  // TODO: COLOCAR dados pos consulta/diagnositco, sao incializados vazios, mas
  // dps da pra colocar um array list (so o medico coloca): FELIPE M

  public Paciente(int id, String nome, ArrayList<String> historicoMedico,
      ArrayList<Medicamento> medicamentosUsadosAtualmente) {
    this.id = id;
    this.nome = nome;
    this.historicoMedico = historicoMedico;
    this.medicamentosUsadosAtualmente = medicamentosUsadosAtualmente;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public ArrayList<String> getHistoricoMedico() {
    return historicoMedico;
  }

  public void setHistoricoMedico(ArrayList<String> historicoMedico) {
    this.historicoMedico = historicoMedico;
  }

  public ArrayList<Medicamento> getMedicamentosUsadosAtualmente() {
    return medicamentosUsadosAtualmente;
  }

  public void setMedicamentosUsadosAtualmente(ArrayList<Medicamento> medicamentosUsadosAtualmente) {
    this.medicamentosUsadosAtualmente = medicamentosUsadosAtualmente;
  }

  public static void menuPaciente(Scanner scanner) {
    System.out.println(Main.BLUE + "\n ========== Menu do Paciente ==========" + Main.RESET_COLOR);
    System.out.println("1. Cadastrar Paciente");
    System.out.println("2. Ver Consultas Marcadas");
    System.out.println("3. Solicitar Consulta"); // TODO: Adicionar lista dos medicos disponiveis CARDOSO
    System.out.println("4. Ver Detalhes do Paciente");
    System.out.println("0. Voltar");
    // TODO: Opcao para remover informacoes como doencas e medicamentos usados:
    // CUNHA
    System.out.print("Escolha uma opcao: ");
    int opcao = scanner.nextInt();
    scanner.nextLine(); // Limpar o buffer

    switch (opcao) {
      case 1:
        cadastrarPaciente(scanner);
        break;
      case 2:
        Paciente.verConsultasMarcadas(scanner);
        break;
      case 3:
        Paciente.solicitarConsulta(scanner);
        break;
      case 4:
        Paciente.verDetalhesDoPaciente(scanner);
        break;
      case 0:
        System.err.println("Voltando...");
        break;
      default:
        EncapsulamentoAndErros.ErroOpcaoInvalida();
    }
  }

  public static void cadastrarPaciente(Scanner scanner) {
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
    Main.listaPacientes.add(paciente);
    System.out.println(Main.GREEN + "Paciente cadastrado com ID: " + id + Main.RESET_COLOR); // Sucesso
  }

  public static void verConsultasMarcadas(Scanner scanner) {
    System.out.print("Insira o ID do paciente: ");
    int idPaciente = scanner.nextInt();
    scanner.nextLine(); // Limpar o buffer

    for (Consulta consulta : Main.listaAgenda.getConsultasMarcadas()) {
      if (idPaciente == consulta.getIdPaciente()) {
        System.out.println(Main.BLUE + "\n ========== Consultas Marcadas ==========" + Main.RESET_COLOR);
        System.out.println("Data: " + consulta.getData());
        System.out.println("Horario: " + consulta.getHorario());
        System.out.println("Medico: " + consulta.getNomeMedico());
        System.out.println("Motivo: " + consulta.getMotivo());
        System.out.println();
      }
    }
  }

  public static void solicitarConsulta(Scanner scanner) {
    System.out.println(Main.BLUE + "\n ========== Solicitar Consulta ==========" + Main.RESET_COLOR);
    System.out.print("Informe o ID do medico: ");
    int idMedico = scanner.nextInt();
    scanner.nextLine(); // Limpar o buffer

    // Solicitar o ID do paciente
    System.out.print("Informe o ID do paciente: ");
    int idPaciente = scanner.nextInt();
    scanner.nextLine(); // Limpar o buffer

    // Faz um loop para o usuario reeinserir os dados se nao estiverem no fomato
    // correto
    String data = "";
    do {
      System.out.print("Data da consulta (DD/MM/AAAA): ");
      data = EncapsulamentoAndErros.encapsularData(scanner.nextLine());
    } while (data.isEmpty());
    String horario;
    do {
      System.out.print("Horario da consulta (HH:MM): ");
      horario = EncapsulamentoAndErros.encapsularHorario(scanner.nextLine());
    } while (horario.isEmpty());

    System.out.print("Motivo da consulta: ");
    String motivo = scanner.nextLine();
    System.out.print("A consulta eh um retorno? (sim/nao): ");
    String entradaBool = scanner.nextLine().toLowerCase();
    boolean ehRetorno = EncapsulamentoAndErros.analisarEntradaBool(entradaBool);
    // TODO: Verificar se o horario de consulta bate com o horario de atendimento do
    // medico especificado: CARDOSO OU CUNHA

    Consulta consulta = new Consulta(data, horario, idPaciente, "", idMedico, "", motivo, ehRetorno);
    Main.listaAgenda.adicionarConsulta(idMedico, consulta);
    System.out.println(Main.GREEN + "Consulta solicitada com sucesso!" + Main.RESET_COLOR);
  }

  public static void verDetalhesDoPaciente(Scanner scanner) {
    System.out.print("Insira o ID do paciente: ");
    int idPaciente = scanner.nextInt();
    scanner.nextLine(); // Limpar o buffer

    for (Paciente paciente : Main.listaPacientes) {
      if (idPaciente == paciente.getId()) {
        System.out.println(Main.BLUE + "\n ========== Detalhes Do Paciente ==========" + Main.RESET_COLOR);
        System.out.println("ID: " + paciente.getId());
        System.out.println("Nome: " + paciente.getNome());
        System.out.println("Historico Medico:");
        for (String historico : paciente.getHistoricoMedico()) {
          System.out.println("- " + historico);
        }
        System.out.println("Medicamentos Usados Atualmente:");
        for (Medicamento medicamento : paciente.getMedicamentosUsadosAtualmente()) {
          System.out.println("- " + medicamento.getUso() + ", " + medicamento.getDescricao() + ", "
              + medicamento.getPeriodoDeUso());
        }
        // VER CONSULTAS
        return;
      }
    }
    System.out.println(Main.RED + "ERRO: Paciente não encontrado." + Main.RESET_COLOR);
  }

}
