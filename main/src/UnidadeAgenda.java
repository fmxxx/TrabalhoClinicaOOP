public class UnidadeAgenda {
  private boolean horarioVago;
  private int horario; // EX 10:30 => 1030
  private boolean consultaRetorno;
  private String nomePaciente;

  public UnidadeAgenda() {
    this.horarioVago = true;
    this.consultaRetorno = false;
    this.horario = 0;
    this.nomePaciente = null;
  }

  // Getters e setters
  public int getHorario() {
    return horario;
  }

  public String getNomePaciente() {
    return nomePaciente;
  }

  public void setConsultaRetorno(boolean consultaRetorno) {
    this.consultaRetorno = consultaRetorno;
  }

  public void setHorario(int horario) {
    this.horario = horario;
  }

  public void setHorarioVago(boolean horarioVago) {
    this.horarioVago = horarioVago;
  }

  public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
  }

  public boolean isConsultaRetorno() {
    return consultaRetorno;
  }

  public boolean isHorarioVago() {
    return horarioVago;
  }
}