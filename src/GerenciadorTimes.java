import java.util.List;

public class GerenciadorTimes {

    private List<Time> times;

    public GerenciadorTimes(List<Time> times) {
        this.times = times;
    }

    public void cadastrarTime(String nome, String cidade, int capacidade) {
        Time time = new Time();

        time.setId(times.size() + 1);
        time.setNome(nome);
        time.setCidade(cidade);
        time.setCapacidade(capacidade);

        times.add(time);

        System.out.println("Time cadastrado: " + nome);
    }

    public Time buscarTime(int id) {
        for (Time time : times) {
            if (time.getId() == id) {
                return time;
            }
        }

        return null;
    }
}