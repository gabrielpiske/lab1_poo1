public class Partida {

    private int timeAId;
    private int timeBId;
    private String data;
    private int golsTimeA;
    private int golsTimeB;
    private String local;
    private int arbitroId;

    public Partida(int timeAId, int timeBId, String data,
                   int golsTimeA, int golsTimeB,
                   String local, int arbitroId) {
        this.timeAId = timeAId;
        this.timeBId = timeBId;
        this.data = data;
        this.golsTimeA = golsTimeA;
        this.golsTimeB = golsTimeB;
        this.local = local;
        this.arbitroId = arbitroId;
    }

    public int getTimeAId() { return timeAId; }
    public int getTimeBId() { return timeBId; }
    public String getData() { return data; }
    public int getGolsTimeA() { return golsTimeA; }
    public int getGolsTimeB() { return golsTimeB; }
    public String getLocal() { return local; }
    public int getArbitroId() { return arbitroId; }
}
