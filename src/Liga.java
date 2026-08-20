import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma liga esportiva.
 * Responsabilidade: manter os dados principais da liga
 * e coordenar os seus gerenciadores.
 */
public class Liga {

    private String nome;
    private List<Time> times;
    private List<Jogador> jogadores;
    private List<Partida> partidas;

    private GerenciadorTimes gerenciadorTimes;
    private GerenciadorJogadores gerenciadorJogadores;
    private GerenciadorPartidas gerenciadorPartidas;
    private GerenciadorEstatisticas gerenciadorEstatisticas;

    public Liga(String nome) {
        this.nome = nome;
        this.times = new ArrayList<>();
        this.jogadores = new ArrayList<>();
        this.partidas = new ArrayList<>();

        this.gerenciadorTimes = new GerenciadorTimes(times);
        this.gerenciadorJogadores = new GerenciadorJogadores(jogadores, times);
        this.gerenciadorPartidas = new GerenciadorPartidas(times, partidas);
        this.gerenciadorEstatisticas =
                new GerenciadorEstatisticas(times, jogadores);
    }

    public void cadastrarTime(String nome, String cidade, int capacidade) {
        gerenciadorTimes.cadastrarTime(nome, cidade, capacidade);
    }

    public void cadastrarJogador(String nome, String posicao,
                                 double salario, int timeId) {
        gerenciadorJogadores.cadastrarJogador(
                nome, posicao, salario, timeId
        );
    }

    public void transferirJogador(int jogadorId,
                                  int timeOrigemId,
                                  int timeDestinoId) {
        gerenciadorJogadores.transferirJogador(
                jogadorId, timeOrigemId, timeDestinoId
        );
    }

    public double calcularFolhaSalarial(int timeId) {
        return gerenciadorEstatisticas.calcularFolhaSalarial(timeId);
    }

    public void sortearPartidas() {
        gerenciadorPartidas.sortearPartidas();
    }

    public void calcularEstatisticas() {
        gerenciadorEstatisticas.calcularEstatisticas(nome);
    }

    public void gerarClassificacao() {
        System.out.println("\n=== Classificacao (ordem de cadastro) ===");

        for (int i = 0; i < times.size(); i++) {
            System.out.println(
                    (i + 1) + ". " +
                            times.get(i).getNome() +
                            " (" + times.get(i).getCidade() + ")"
            );
        }
    }

    public Jogador buscarJogador(int id) {
        return gerenciadorJogadores.buscarJogador(id);
    }

    public Time buscarTime(int id) {
        return gerenciadorTimes.buscarTime(id);
    }

    public String getNome() {
        return nome;
    }

    public List<Time> getTimes() {
        return times;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }
}