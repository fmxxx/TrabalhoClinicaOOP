import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Paciente {
  private int id;
  private String nome;
  private ArrayList<String> historicoMedico;
  private ArrayList<Medicamento> medicamentosUsadosAtualmente;
  private static ArrayList<RelatorioPosConsulta> listaRelatoriosPosConsulta;

  public Paciente(int id, String nome, ArrayList<String> historicoMedico,
      ArrayList<Medicamento> medicamentosUsadosAtualmente) {
    this.id = id;
    this.nome = nome;
    this.historicoMedico = historicoMedico;
    this.medicamentosUsadosAtualmente = medicamentosUsadosAtualmente;
    Paciente.listaRelatoriosPosConsulta = new ArrayList<>();
  }

  // Metodos
  public static void menuPaciente(Scanner scanner) {
    System.out.println(Main.BLUE + "\n ========== Menu do Paciente ==========" + Main.RESET_COLOR);
    System.out.println("1. Cadastrar Paciente");
    System.out.println("2. Solicitar Consulta");
    System.out.println("3. Ver Consultas Marcadas");
    System.out.println("4. Ver Detalhes do Paciente");
    System.out.println("5. Ver Relatorios Pos Consulta do Paciente");
    System.out.println("0. Voltar");
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
        cadastrarPaciente(scanner);
        break;
      case 2:
        Paciente.solicitarConsulta(scanner);
        break;
      case 3:
        Paciente.verConsultasMarcadas(scanner);
        break;
      case 4:
        Paciente.verDetalhesDoPaciente(scanner);
        break;
      case 5:
        verRelatorios(scanner);
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
    System.out.println("\nInforme o historico medico do paciente (digite '0' para parar): ");
    while (true) {
      System.out.print("Historico medico: ");
      String historico = scanner.nextLine();
      if (historico.equals("0")) {
        break;
      }
      historicoMedico.add(historico);
    }

    ArrayList<Medicamento> medicamentosUsadosAtualmente = new ArrayList<>();
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
    System.out.println(Main.GREEN + "Paciente cadastrado com ID: " + id + " Lembre-se de o anotar" + Main.RESET_COLOR); // Sucesso
  }

  public static void verConsultasMarcadas(Scanner scanner) {
    int idPaciente;
    while (true) {
      try {
        System.out.print("Insira o ID do paciente: ");
        idPaciente = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }

    for (Consulta consulta : Main.listaAgenda.getConsultasMarcadas()) {
      if (idPaciente == consulta.getIdPaciente()) {
        System.out.println(Main.BLUE + "\n ========== Consultas Marcadas ==========" + Main.RESET_COLOR);
        System.out.println("Data: " + consulta.getData());
        System.out.println("Horario: " + consulta.getHorario());
        System.out.println("Medico: " + consulta.getNomeMedico());
        System.out.println("Motivo: " + consulta.getMotivo());
      }
    }
  }

  public static void solicitarConsulta(Scanner scanner) {
    System.out.println(Main.BLUE + "\n ========== Solicitar Consulta ==========" + Main.RESET_COLOR);

    Medico.exibirMedicosDisponiveis(Main.listaMedicos);
    System.out.println();
    int idMedico;
    while (true) {
      try {
        System.out.print("Insira o ID do medico: ");
        idMedico = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }
    int idPaciente;
    while (true) {
      try {
        System.out.print("Insira o ID do paciente: ");
        idPaciente = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }

    // Faz um loop para o usuario reeinserir os dados se nao estiveresm no fomato
    // correto
    String data = "";
    do {
      System.out.print("Data da consulta (DD/MM/AAAA): ");
      String temp = scanner.nextLine();
      data = EncapsulamentoAndErros.encapsularData(temp);
    } while (data.isEmpty());
    String horario = "";
    do {
      System.out.print("Horario da consulta (HH:MM): ");
      horario = EncapsulamentoAndErros.encapsularHorario(scanner.nextLine());
    } while (horario.isEmpty());

    System.out.print("Motivo da consulta: ");
    String motivo = scanner.nextLine();
    System.out.print("A consulta eh um retorno? (sim/nao): ");
    String entradaBool = scanner.nextLine().toLowerCase();
    boolean ehRetorno = EncapsulamentoAndErros.analisarEntradaBool(entradaBool);

    Consulta consulta = new Consulta(data, horario, idPaciente, "", idMedico, "", motivo, ehRetorno);
    Main.listaAgenda.adicionarConsulta(idMedico, consulta);
    System.out.println(Main.GREEN + "Consulta solicitada com sucesso!" + Main.RESET_COLOR);
  }

  public static void verDetalhesDoPaciente(Scanner scanner) {
    int idPaciente;
    while (true) {
      try {
        System.out.print("Insira o ID do paciente: ");
        idPaciente = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }

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

  public static void adicionarRelatorioAoPaciente(Scanner scanner) {
    System.out.println(Main.BLUE + "\n ========== Adicionar Diagnostico ==========" + Main.RESET_COLOR);

    int idMedico;
    while (true) {
      try {
        System.out.print("Insira o ID do medico: ");
        idMedico = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }
    int idPaciente;
    while (true) {
      try {
        System.out.print("Insira o ID do paciente: ");
        idPaciente = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }

    // Encontra o paciente pelo id
    Paciente paciente = null;
    for (Paciente p : Main.listaPacientes) {
      if (p.getId() == idPaciente) {
        paciente = p;
        break;
      }
    }

    // Se o paciente for null ele nao foi encontrado
    if (paciente == null) {
      System.out.println(Main.RED + "ERRO: Paciente com ID " + idPaciente + " nao encontrado." + Main.RESET_COLOR);
      return;
    }

    // Faz um loop para o usuario reeinserir os dados se nao estiverem no fomato
    // correto
    String data = "";
    do {
      System.out.print("Data da consulta (DD/MM/AAAA): ");
      String temp = scanner.nextLine();
      data = EncapsulamentoAndErros.encapsularData(temp);
    } while (data.isEmpty());

    double batimentosCardiacosPorMin;
    while (true) {
      try {
        System.out.print("Batimentos Cardiacos por Minuto: ");
        batimentosCardiacosPorMin = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        break; // Exit the loop if input is successful
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }
    double temperaturaCelsius;
    while (true) {
      try {
        System.out.print("Temperatura em Celsius: ");
        temperaturaCelsius = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer
        break; // Exit the loop if input is successful
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }
    System.out.print("Pressao Arterial: ");
    String pressaoArterial = scanner.nextLine();

    ArrayList<String> diagnosticos = new ArrayList<>();
    System.out.println("\nInforme os diagnosticos (digite '0' para parar): ");
    while (true) {
      System.out.print("Diagnostico: ");
      String diagnostico = scanner.nextLine();
      if (diagnostico.equals("0")) {
        break;
      }
      diagnosticos.add(diagnostico);
    }

    ArrayList<Medicamento> medicamentosReceitados = new ArrayList<>();
    System.out.println("\nInforme os medicamentos receitados para o paciente (digite '0' para parar): ");
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

      medicamentosReceitados.add(new Medicamento(uso, descricao, periodoDeUso));
    }

    RelatorioPosConsulta novoRelatorioPosConsulta = new RelatorioPosConsulta(data, idMedico, batimentosCardiacosPorMin,
        temperaturaCelsius,
        pressaoArterial, diagnosticos, medicamentosReceitados);

    paciente.adicionarRelatorio(novoRelatorioPosConsulta);
    System.out.println(Main.GREEN + "Relatorio Adicionado com sucesso!" + Main.RESET_COLOR);
  }

  public void adicionarRelatorio(RelatorioPosConsulta relatorio) {
    Paciente.listaRelatoriosPosConsulta.add(relatorio);
  }

  public static void verRelatorios(Scanner scanner) {
    int idPaciente;
    while (true) {
      try {
        System.out.print("Insira o ID do paciente: ");
        idPaciente = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        break;
      } catch (InputMismatchException e) {
        EncapsulamentoAndErros.ErroEntradaInvalida();
        scanner.nextLine(); // Limpar o buffer
      }
    }

    System.out.println(Main.BLUE + "\n ========== Relatorios do Paciente ==========" + Main.RESET_COLOR);

    for (Paciente paciente : Main.listaPacientes) {
      if (idPaciente == paciente.getId()) {
        for (RelatorioPosConsulta relatorio : Paciente.getListaRelatoriosPosConsulta()) {
          System.out.println("Data: " + relatorio.getDataDoDiagnostico());
          System.out.println("Medico Responsavel: " + relatorio.getIdMedicoResponsavel());
          System.out.println("Batimentos Cardiacos por Minuto: " + relatorio.getBatimentosCardiacosPorMin());
          System.out.println("Temperatura em Celsius: " + relatorio.getTemperaturaCelsius());
          System.out.println("Pressao Arterial: " + relatorio.getPressaoArterial());
          System.out.println("Diagnosticos: ");
          for (String diagnostico : relatorio.getDiagnostico()) {
            System.out.println("- " + diagnostico);
          }
          System.out.println("Medicamentos Receitados: ");
          for (Medicamento medicamento : relatorio.getMedicamentosReceitados()) {
            System.out.println("- " + medicamento.getUso() + ", " + medicamento.getDescricao() + ", "
                + medicamento.getPeriodoDeUso());
          }
          System.out.print("==============================================");
        }
        System.out.println();
      }
      return;
    }
    System.out.println(Main.RED + "ERRO: Paciente nao encontrado." + Main.RESET_COLOR);
  }

  // Getters
  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public ArrayList<String> getHistoricoMedico() {
    return historicoMedico;
  }

  public ArrayList<Medicamento> getMedicamentosUsadosAtualmente() {
    return medicamentosUsadosAtualmente;
  }

  public static ArrayList<RelatorioPosConsulta> getListaRelatoriosPosConsulta() {
    return listaRelatoriosPosConsulta;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setHistoricoMedico(ArrayList<String> historicoMedico) {
    this.historicoMedico = historicoMedico;
  }

  public void setMedicamentosUsadosAtualmente(ArrayList<Medicamento> medicamentosUsadosAtualmente) {
    this.medicamentosUsadosAtualmente = medicamentosUsadosAtualmente;
  }

}
