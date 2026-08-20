import java.util.List;

public class GerenciadorJogadores {

    private List<Jogador> jogadores;
    private List<Time> times;

    public GerenciadorJogadores(List<Jogador> jogadores,
                                List<Time> times) {
        this.jogadores = jogadores;
        this.times = times;
    }

    public void cadastrarJogador(String nome, String posicao,
                                 double salario, int timeId) {

        Time time = buscarTime(timeId);

        if (time == null) {
            System.out.println(
                    "Erro: time " + timeId + " nao encontrado."
            );
            return;
        }

        Jogador jogador = new Jogador();

        jogador.setId(jogadores.size() + 1);
        jogador.setNome(nome);
        jogador.setPosicao(posicao);
        jogador.setSalario(salario);

        if (time.adicionarJogador(jogador)) {
            jogadores.add(jogador);

            System.out.println(
                    "Jogador cadastrado: " +
                            nome + " -> " + time.getNome()
            );
        }
    }

    public void transferirJogador(int jogadorId,
                                  int timeOrigemId,
                                  int timeDestinoId) {

        Jogador jogador = buscarJogador(jogadorId);

        if (jogador == null) {
            System.out.println("Jogador nao encontrado.");
            return;
        }

        if (jogador.getTimeId() != timeOrigemId) {
            System.out.println(
                    "Jogador nao pertence ao time de origem informado."
            );
            return;
        }

        Time destino = buscarTime(timeDestinoId);

        if (destino == null) {
            System.out.println("Time de destino nao encontrado.");
            return;
        }

        jogador.transferirPara(timeDestinoId);

        System.out.println(
                "Transferencia realizada: jogador " +
                        jogadorId + " -> time " + timeDestinoId
        );
    }

    public Jogador buscarJogador(int id) {
        for (Jogador jogador : jogadores) {
            if (jogador.getId() == id) {
                return jogador;
            }
        }

        return null;
    }

    private Time buscarTime(int id) {
        for (Time time : times) {
            if (time.getId() == id) {
                return time;
            }
        }

        return null;
    }
}