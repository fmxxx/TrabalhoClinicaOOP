import java.util.ArrayList;

public class Paciente {
  private int id;
  private String nome;
  private ArrayList<String> historicoMedico;
  private ArrayList<Medicamento> medicamentosUsadosAtualmente;
  private ArrayList<String> consultasMarcadas; // Assumi que a consulta é uma string formatada

  public Paciente(int id, String nome, ArrayList<String> historicoMedico,
      ArrayList<Medicamento> medicamentosUsadosAtualmente) {
    this.id = id;
    this.nome = nome;
    this.historicoMedico = historicoMedico;
    this.medicamentosUsadosAtualmente = medicamentosUsadosAtualmente;
  }

  // Getters e setters
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

  public ArrayList<String> getConsultasMarcadas() {
    return consultasMarcadas;
  }

  public void setConsultasMarcadas(ArrayList<String> consultasMarcadas) {
    this.consultasMarcadas = consultasMarcadas;
  }

  // Método para adicionar consulta marcada
  public void adicionarConsultaMarcada(String horario, String motivo, boolean retorno) {
    String consulta = horario + ", " + motivo + ", Retorno: " + retorno;
    this.consultasMarcadas.add(consulta);
  }

  // Método para adicionar dados após a consulta
  public void adicionarDadosPosConsulta(int bpmCardiaco, String pressaoSanguinea, float temperatura,
      String diagnostico, ArrayList<Medicamento> medicacaoJaUsada,
      ArrayList<Medicamento> medicacaoReceitada) {
    // Aqui você pode adicionar os dados pos consulta conforme necessário
  }
}