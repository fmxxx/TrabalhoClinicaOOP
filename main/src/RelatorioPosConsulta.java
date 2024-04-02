import java.util.ArrayList;

public class RelatorioPosConsulta {
  private String dataDoDiagnostico;
  private int idMedicoResponsavel;
  private double batimentosCardiacosPorMin;
  private double temperaturaCelsius;
  private String pressaoArterial;
  private ArrayList<String> diagnostico;
  private ArrayList<Medicamento> medicamentosReceitados;

  public RelatorioPosConsulta(String dataDoDiagnostico, int idMedicoResponsavel, double batimentosCardiacosPorMin,
      double temperaturaCelsius, String pressaoArterial, ArrayList<String> diagnostico,
      ArrayList<Medicamento> medicamentosReceitados) {
    this.dataDoDiagnostico = dataDoDiagnostico;
    this.idMedicoResponsavel = idMedicoResponsavel;
    this.batimentosCardiacosPorMin = batimentosCardiacosPorMin;
    this.temperaturaCelsius = temperaturaCelsius;
    this.pressaoArterial = pressaoArterial;
    this.diagnostico = diagnostico;
    this.medicamentosReceitados = medicamentosReceitados;
  }

  // Getters
  public String getDataDoDiagnostico() {
    return dataDoDiagnostico;
  }

  public int getIdMedicoResponsavel() {
    return idMedicoResponsavel;
  }

  public double getBatimentosCardiacosPorMin() {
    return batimentosCardiacosPorMin;
  }

  public ArrayList<String> getDiagnostico() {
    return diagnostico;
  }

  public ArrayList<Medicamento> getMedicamentosReceitados() {
    return medicamentosReceitados;
  }

  public String getPressaoArterial() {
    return pressaoArterial;
  }

  public double getTemperaturaCelsius() {
    return temperaturaCelsius;
  }

  // Setters
  public void setDataDoDiagnostico(String dataDoDiagnostico) {
    this.dataDoDiagnostico = dataDoDiagnostico;
  }

  public void setIdMedicoResponsavel(int idMedicoResponsavel) {
    this.idMedicoResponsavel = idMedicoResponsavel;
  }

  public void setBatimentosCardiacosPorMin(double batimentosCardiacosPorMin) {
    this.batimentosCardiacosPorMin = batimentosCardiacosPorMin;
  }

  public void setDiagnostico(ArrayList<String> diagnostico) {
    this.diagnostico = diagnostico;
  }

  public void setMedicamentosReceitados(ArrayList<Medicamento> medicamentosReceitados) {
    this.medicamentosReceitados = medicamentosReceitados;
  }

  public void setPressaoArterial(String pressaoArterial) {
    this.pressaoArterial = pressaoArterial;
  }

  public void setTemperaturaCelsius(double temperaturaCelsius) {
    this.temperaturaCelsius = temperaturaCelsius;
  }
}
