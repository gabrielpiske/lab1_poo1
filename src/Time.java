import java.util.ArrayList;
import java.util.List;

public class Time {

    private int id;
    private String nome;
    private String cidade;
    private int capacidade;

    private List<Jogador> jogadores;

    public Time() {
        jogadores = new ArrayList<>();
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade deve ser maior que zero."
            );
        }

        this.capacidade = capacidade;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public boolean adicionarJogador(Jogador jogador) {

        if (jogador == null) {
            return false;
        }

        if (jogadores.size() >= capacidade) {
            System.out.println(
                    "O time " + nome + " atingiu sua capacidade."
            );
            return false;
        }

        jogadores.add(jogador);
        jogador.setTimeId(id);

        return true;
    }

    public boolean removerJogador(Jogador jogador) {

        if (jogador == null) {
            return false;
        }

        return jogadores.remove(jogador);
    }

    public boolean estaLotado() {
        return jogadores.size() >= capacidade;
    }

    public int getQuantidadeJogadores() {
        return jogadores.size();
    }

    public double calcularFolhaSalarial() {

        double total = 0;

        for (Jogador jogador : jogadores) {
            total += jogador.getSalario();
        }

        return total;
    }
}