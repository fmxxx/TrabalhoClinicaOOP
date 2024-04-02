import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
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

  private ArrayList<Consulta> consultasMarcadas = new ArrayList<>();

  public void adicionarConsulta(int idMedico, Consulta consulta) {
    consulta.setIdMedico(idMedico);
    // Buscar medico pelo ID
    for (Medico medico : Main.listaMedicos) {
      if (medico.getId() == idMedico) {
        consulta.setNomeMedico(medico.getNome());
        break;
      }
    }
    // Buscar paciente pelo ID
    for (Paciente paciente : Main.listaPacientes) {
      if (paciente.getId() == consulta.getIdPaciente()) {
        consulta.setNomePaciente(paciente.getNome());
        break;
      }
    }

    // Check for existing consultations with the same date and time
    for (Consulta consultaExistente : consultasMarcadas) {
      if (consultaExistente.getData() != null && consultaExistente.getHorario() != null &&
          consultaExistente.getData().equals(consulta.getData()) &&
          consultaExistente.getHorario().equals(consulta.getHorario())) {
        System.out.println(RED + "ERRO: Conflito de horarios." + RESET_COLOR);
        return;
      }
    }

    consultasMarcadas.add(consulta);
  }

  public ArrayList<Consulta> getConsultasMarcadas() {
    return consultasMarcadas;
  }

  public void verAgendaMedica(Scanner scanner) {
    System.out.print("Informe o ID do medico: ");
    int idMedico = scanner.nextInt();
    scanner.nextLine();

    System.out.println(Main.BLUE + "\n ========== Agenda do Medico ==========" + Main.RESET_COLOR);
    for (Consulta consulta : consultasMarcadas) {
      if (consulta.getIdMedico() == idMedico) {
        System.out.println("Data: " + consulta.getData());
        System.out.println("Horario: " + consulta.getHorario());
        System.out.println("Paciente: " + consulta.getNomePaciente());
        System.out.println("Motivo: " + consulta.getMotivo());
        System.out.println("Eh retorno: " + (consulta.isEhRetorno() ? "Sim" : "Nao"));
        System.out.println("---------------------------------------");
      }
    }
  }

}