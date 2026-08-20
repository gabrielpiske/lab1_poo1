public class Main {

    public static void main(String[] args) {
        Liga liga = new Liga("Liga Brasileira de Demonstracao");

        liga.cadastrarTime("Flamengo", "Rio de Janeiro", 25);
        liga.cadastrarTime("Palmeiras", "Sao Paulo", 25);
        liga.cadastrarTime("Gremio", "Porto Alegre", 25);

        liga.cadastrarJogador("Gabigol", "Atacante", 300000, 1);
        liga.cadastrarJogador("Diego", "Meia", 200000, 1);
        liga.cadastrarJogador("Dudu", "Atacante", 280000, 2);

        System.out.println("\n--- Folha antes da transferencia ---");
        System.out.printf("Flamengo: R$ %,.2f%n", liga.calcularFolhaSalarial(1));
        System.out.printf("Palmeiras: R$ %,.2f%n", liga.calcularFolhaSalarial(2));

        // BUG demonstrado:
        // transferirJogador apenas muda o timeId do jogador,
        // mas nao valida se o time de destino tem capacidade.
        // Alem disso, nenhuma regra de negocio de transferencia
        // (taxa de transferencia, contrato ativo) e verificada.
        liga.transferirJogador(1, 1, 2); // Gabigol vai para Palmeiras

        System.out.println("\n--- Folha apos a transferencia ---");
        System.out.printf("Flamengo: R$ %,.2f%n", liga.calcularFolhaSalarial(1));
        System.out.printf("Palmeiras: R$ %,.2f%n", liga.calcularFolhaSalarial(2));

        liga.sortearPartidas();
        liga.calcularEstatisticas();
        liga.gerarClassificacao();
        liga.salvarEmArquivo("liga_dados.txt");

        // BUG demonstrado: construtor de Partida tem 7 parametros primitivos.
        // E facil passar parametros na ordem errada sem o compilador reclamar.
        Partida p = new Partida(1, 2, "2024-06-01", 2, 1, "Maracana", 99);
        System.out.println("\nPartida: Time " + p.getTimeAId()
                + " x " + p.getTimeBId() + " | " + p.getGolsTimeA()
                + " x " + p.getGolsTimeB() + " em " + p.getLocal());
    }
}
