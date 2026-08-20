import java.util.List;

public class GerenciadorClassificacao {

    private List<Time> times;

    public GerenciadorClassificacao(List<Time> times) {
        this.times = times;
    }

    public void gerarClassificacao() {

        System.out.println("\n=== Classificacao (ordem de cadastro) ===");

        for (int i = 0; i < times.size(); i++) {

            Time time = times.get(i);

            System.out.println(
                    (i + 1) + ". " +
                            time.getNome() +
                            " (" + time.getCidade() + ")"
            );
        }
    }
}