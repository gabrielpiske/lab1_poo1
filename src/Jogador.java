public class Jogador {

    private int id;
    private String nome;
    private String posicao;
    private double salario;
    private int timeId;

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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException(
                    "O salario nao pode ser negativo."
            );
        }

        this.salario = salario;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    /**
     * Altera o time ao qual o jogador pertence.
     */
    public void transferirPara(int novoTimeId) {

        if (novoTimeId <= 0) {
            throw new IllegalArgumentException(
                    "O ID do time deve ser maior que zero."
            );
        }

        this.timeId = novoTimeId;
    }

    /**
     * Verifica se o jogador pertence a um determinado time.
     */
    public boolean pertenceAoTime(int timeId) {
        return this.timeId == timeId;
    }

    /**
     * Aplica um aumento percentual ao salario.
     */
    public void aplicarAumento(double percentual) {

        if (percentual < 0) {
            throw new IllegalArgumentException(
                    "O percentual de aumento nao pode ser negativo."
            );
        }

        this.salario += this.salario * (percentual / 100);
    }

    /**
     * Retorna uma descricao simples do jogador.
     */
    public String getResumo() {
        return nome + " - " + posicao +
                " | Salario: R$ " + salario;
    }
}