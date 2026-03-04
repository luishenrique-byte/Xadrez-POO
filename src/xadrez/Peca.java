package xadrez;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class Peca {

    protected boolean[][] matrizMovimentos = new boolean[8][8]; //Uma matriz de movimentos possiveis

    protected Cor cor;
    protected Posicao posicao;
    protected Tabuleiro tabuleiro;

    public Peca(Cor cor, Tabuleiro tabuleiro) {
        this.cor = cor;
        this.tabuleiro = tabuleiro;
    }

    public abstract void movimentosPossiveis();

    public void limparMatrizMovimentos(){
        for (int i = 0; i < matrizMovimentos.length ; i++) {
            for (int j = 0; j < matrizMovimentos[i].length ; j++) {
                matrizMovimentos[i][j] = false;
            }
        }
    }

    public Posicao getPosicao() { return posicao; }

    public void setPosicao(Posicao posicao) { this.posicao = posicao; }

    public boolean[][] getMatrizMovimentos() { return matrizMovimentos; }

    public Cor getCor() {
        return cor;
    }
}