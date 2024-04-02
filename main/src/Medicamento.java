public class Medicamento {
  private String uso;
  private String descricao;
  private String periodoDeUso;

  public Medicamento(String uso, String descricao, String periodoDeUso) {
    this.uso = uso;
    this.descricao = descricao;
    this.periodoDeUso = periodoDeUso;
  }

  // Getters
  public String getUso() {
    return uso;
  }

  public String getDescricao() {
    return descricao;
  }

  public String getPeriodoDeUso() {
    return periodoDeUso;
  }

  // Setters
  public void setUso(String uso) {
    this.uso = uso;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setPeriodoDeUso(String periodoDeUso) {
    this.periodoDeUso = periodoDeUso;
  }
}
