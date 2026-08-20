import java.util.List;

public class GerenciadorEstatisticas {

    private List<Time> times;
    private List<Jogador> jogadores;

    public GerenciadorEstatisticas(List<Time> times,
                                   List<Jogador> jogadores) {
        this.times = times;
        this.jogadores = jogadores;
    }

    public double calcularFolhaSalarial(int timeId) {

        double total = 0;

        for (Jogador jogador : jogadores) {

            if (jogador.getTimeId() == timeId) {
                total += jogador.getSalario();
            }
        }

        return total;
    }

    public void calcularEstatisticas(String nomeLiga) {

        System.out.println(
                "\n=== Estatisticas da Liga: " +
                        nomeLiga + " ==="
        );

        for (Time time : times) {

            int totalJogadores = 0;
            double folha = 0;

            for (Jogador jogador : jogadores) {

                if (jogador.getTimeId() == time.getId()) {

                    totalJogadores++;
                    folha += jogador.getSalario();
                }
            }

            System.out.printf(
                    "%-20s | Jogadores: %2d | Folha: R$ %,.2f%n",
                    time.getNome(),
                    totalJogadores,
                    folha
            );
        }
    }
}