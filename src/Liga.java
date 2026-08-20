import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Gerenciador central da liga esportiva.
 * Responsável por: cadastro de times e jogadores, transferências,
 * cálculo salarial, sorteio de partidas, estatísticas e persistência.
 */
public class Liga {

    private String nome;
    private List<Time> times;
    private List<Jogador> jogadores;
    private List<Partida> partidas;

    public Liga(String nome) {
        this.nome = nome;
        this.times = new ArrayList<>();
        this.jogadores = new ArrayList<>();
        this.partidas = new ArrayList<>();
    }

    public void cadastrarTime(String nome, String cidade, int capacidade) {
        Time t = new Time();
        t.setId(times.size() + 1);
        t.setNome(nome);
        t.setCidade(cidade);
        t.setCapacidade(capacidade);
        times.add(t);
        System.out.println("Time cadastrado: " + nome);
    }

    public void cadastrarJogador(String nome, String posicao, double salario, int timeId) {
        Time time = buscarTime(timeId);
        if (time == null) {
            System.out.println("Erro: time " + timeId + " nao encontrado.");
            return;
        }
        Jogador j = new Jogador();
        j.setId(jogadores.size() + 1);
        j.setNome(nome);
        j.setPosicao(posicao);
        j.setSalario(salario);
        j.setTimeId(timeId);
        jogadores.add(j);
        System.out.println("Jogador cadastrado: " + nome + " -> " + time.getNome());
    }

    /**
     * Transfere jogador entre times.
     * BUG: nao valida capacidade do time de destino.
     * BUG: atualiza apenas o timeId do jogador — nao reflete corretamente
     *      na folha salarial do time de origem (calculo continua contando).
     */
    public void transferirJogador(int jogadorId, int timeOrigemId, int timeDestinoId) {
        Jogador jogador = buscarJogador(jogadorId);
        if (jogador == null) {
            System.out.println("Jogador nao encontrado.");
            return;
        }
        if (jogador.getTimeId() != timeOrigemId) {
            System.out.println("Jogador nao pertence ao time de origem informado.");
            return;
        }
        // BUG: nao verifica capacidade do time de destino
        jogador.setTimeId(timeDestinoId);
        System.out.println("Transferencia realizada: jogador " + jogadorId
                + " -> time " + timeDestinoId);
    }

    public double calcularFolhaSalarial(int timeId) {
        double total = 0;
        for (Jogador j : jogadores) {
            if (j.getTimeId() == timeId) {
                total += j.getSalario();
            }
        }
        return total;
    }

    /**
     * Sorteio round-robin: todos os times se enfrentam uma vez.
     */
    public void sortearPartidas() {
        partidas.clear();
        for (int i = 0; i < times.size(); i++) {
            for (int k = i + 1; k < times.size(); k++) {
                Partida p = new Partida(
                        times.get(i).getId(),
                        times.get(k).getId(),
                        "TBD",
                        0, 0,
                        times.get(i).getCidade(),
                        0
                );
                partidas.add(p);
            }
        }
        System.out.println("Partidas sorteadas: " + partidas.size());
    }

    public void calcularEstatisticas() {
        System.out.println("\n=== Estatisticas da Liga: " + nome + " ===");
        for (Time t : times) {
            int totalJogadores = 0;
            double folha = 0;
            for (Jogador j : jogadores) {
                if (j.getTimeId() == t.getId()) {
                    totalJogadores++;
                    folha += j.getSalario();
                }
            }
            System.out.printf("%-20s | Jogadores: %2d | Folha: R$ %,.2f%n",
                    t.getNome(), totalJogadores, folha);
        }
    }

    public void gerarClassificacao() {
        System.out.println("\n=== Classificacao (ordem de cadastro) ===");
        for (int i = 0; i < times.size(); i++) {
            System.out.println((i + 1) + ". " + times.get(i).getNome()
                    + " (" + times.get(i).getCidade() + ")");
        }
    }

    public void salvarEmArquivo(String caminho) {
        try (FileWriter fw = new FileWriter(caminho)) {
            fw.write("Liga: " + nome + "\n");
            fw.write("Times: " + times.size() + "\n");
            fw.write("Jogadores: " + jogadores.size() + "\n");
            fw.write("Partidas: " + partidas.size() + "\n");
            for (Time t : times) {
                fw.write("  " + t.getNome() + " | Folha: R$"
                        + calcularFolhaSalarial(t.getId()) + "\n");
            }
            System.out.println("Dados salvos em: " + caminho);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public Jogador buscarJogador(int id) {
        for (Jogador j : jogadores) {
            if (j.getId() == id) return j;
        }
        return null;
    }

    public Time buscarTime(int id) {
        for (Time t : times) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public String getNome() { return nome; }
    public List<Time> getTimes() { return times; }
    public List<Jogador> getJogadores() { return jogadores; }
    public List<Partida> getPartidas() { return partidas; }
}
