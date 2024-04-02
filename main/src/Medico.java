import java.util.ArrayList;
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

  public ArrayList<String> getEspecialidades() {
    return especialidades;
  }

  public void setEspecialidades(ArrayList<String> especialidades) {
    this.especialidades = especialidades;
  }

  public ArrayList<String> getConvenios() {
    return convenios;
  }

  public void setConvenios(ArrayList<String> convenios) {
    this.convenios = convenios;
  }

  public static void menuMedico(Scanner scanner) {
    System.out.println(Main.BLUE + "\n ========== Menu do Medico ==========" + Main.RESET_COLOR);
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
    scanner.nextLine(); // Limpar o buffer

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
    Main.listaMedicos.add(medico);
    System.out.println(Main.GREEN + "Medico cadastrado com ID: " + id + Main.RESET_COLOR);
  }

}
