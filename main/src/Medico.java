import java.util.ArrayList;

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
}
