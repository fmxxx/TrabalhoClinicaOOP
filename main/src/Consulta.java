public class Consulta {
  private String data;
  private String horario;
  private int idPaciente;
  private String nomePaciente;
  private int idMedico;
  private String nomeMedico;
  private String motivo;
  private boolean ehRetorno;

  public Consulta(String data, String horario, int idPaciente, String nomePaciente, int idMedico, String nomeMedico,
      String motivo, boolean ehRetorno) {
    this.data = data;
    this.horario = horario;
    this.idPaciente = idPaciente;
    this.nomePaciente = nomePaciente;
    this.idMedico = idMedico;
    this.nomeMedico = nomeMedico;
    this.motivo = motivo;
    this.ehRetorno = ehRetorno;
  }

  // Getters
  public String getData() {
    return data;
  }

  public String getHorario() {
    return horario;
  }

  public int getIdPaciente() {
    return idPaciente;
  }

  public String getNomePaciente() {
    return nomePaciente;
  }

  public int getIdMedico() {
    return idMedico;
  }

  public String getNomeMedico() {
    return nomeMedico;
  }

  public String getMotivo() {
    return motivo;
  }

  public boolean isEhRetorno() {
    return ehRetorno;
  }

  // Setters
  public void setData(String data) {
    this.data = data;
  }

  public void setHorario(String horario) {
    this.horario = horario;
  }

  public void setIdPaciente(int idPaciente) {
    this.idPaciente = idPaciente;
  }

  public void setNomePaciente(String nomePaciente) {
    this.nomePaciente = nomePaciente;
  }

  public void setEhRetorno(boolean ehRetorno) {
    this.ehRetorno = ehRetorno;
  }

  public void setIdMedico(int idMedico) {
    this.idMedico = idMedico;
  }

  public void setNomeMedico(String nomeMedico) {
    this.nomeMedico = nomeMedico;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }
}