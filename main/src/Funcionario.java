public class Funcionario {
  private String nome;
  private int id;

  public Funcionario(String nome, int id) {
    this.nome = nome;
    this.id = id;
  }

  // Getters
  public String getNome() {
    return nome;
  }

  public int getId() {
    return id;
  }

  // Setters
  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}