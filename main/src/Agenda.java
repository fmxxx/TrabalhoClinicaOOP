import java.util.ArrayList;
import java.util.Scanner;

public class Agenda {
  private ArrayList<Consulta> consultasMarcadas = new ArrayList<>();

  public ArrayList<Consulta> getConsultasMarcadas() {
    return consultasMarcadas;
  }

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
        System.out.println(Main.RED + "ERRO: Conflito de horarios." + Main.RESET_COLOR);
        return;
      }
    }

    consultasMarcadas.add(consulta);
  }

  public void verAgendaMedica(Scanner scanner) {
    System.out.print("Informe o ID do medico: ");
    int idMedico = scanner.nextInt();
    scanner.nextLine(); // Limpar o buffer

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