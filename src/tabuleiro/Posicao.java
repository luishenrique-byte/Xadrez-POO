package tabuleiro;

public class Posicao {
    protected int linha, coluna;

    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void setLinha(int linha) { this.linha = linha; }

    public void setColuna(int coluna) { this.coluna = coluna; }

    public int getLinha() { return linha; }

    public int getColuna() { return coluna; }
}
