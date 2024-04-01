import java.util.ArrayList;

public class Paciente {
    private int id;
    private String nome;
    private ArrayList<String> historicoMedico;
    private ArrayList<Medicamento> medicamentosUsadosAtualmente;
    // TODO: COLOCAR dados pos consulta/diagnositco, sao incializados vazios, mas
    // dps da pra colocar um array list (so o medico coloca): FELIPE M

    public Paciente(int id, String nome, ArrayList<String> historicoMedico,
            ArrayList<Medicamento> medicamentosUsadosAtualmente) {
        this.id = id;
        this.nome = nome;
        this.historicoMedico = historicoMedico;
        this.medicamentosUsadosAtualmente = medicamentosUsadosAtualmente;
    }

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
}
