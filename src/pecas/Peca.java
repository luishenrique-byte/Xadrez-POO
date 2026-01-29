package pecas;

import tabuleiro.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class Peca {

    protected boolean[][] matrizMovimentos = new boolean[8][8]; //Uma matriz de movimentos possiveis

    public Cor cor;
    protected Posicao posicao;
    protected Tabuleiro tabuleiro;

    public Peca(Cor cor, Posicao posicao, Tabuleiro tabuleiro) {
        this.cor = cor;
        this.posicao = posicao;
        this.tabuleiro = tabuleiro;
    }

    public abstract void movimentosPossiveis();

    public boolean podeMover(Posicao destino){
        if (!tabuleiro.existePeca(destino) || tabuleiro.getPeca(destino).cor != this.cor){
            return true;
        } else {
            return false;
        }
    }

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
}