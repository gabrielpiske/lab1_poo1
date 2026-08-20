import java.util.List;

public class GerenciadorPartidas {

    private List<Time> times;
    private List<Partida> partidas;

    public GerenciadorPartidas(List<Time> times,
                               List<Partida> partidas) {
        this.times = times;
        this.partidas = partidas;
    }

    public void sortearPartidas() {

        partidas.clear();

        for (int i = 0; i < times.size(); i++) {

            for (int k = i + 1; k < times.size(); k++) {

                Partida partida = new Partida(
                        times.get(i).getId(),
                        times.get(k).getId(),
                        "TBD",
                        0,
                        0,
                        times.get(i).getCidade(),
                        0
                );

                partidas.add(partida);
            }
        }

        System.out.println(
                "Partidas sorteadas: " + partidas.size()
        );
    }
}