package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peca;

public class Peao extends Peca {

    private boolean primeiroMovimento;

    private int direcao;

    public Peao(Cor cor, Posicao posicao, Tabuleiro tabuleiro) {

        super(cor, posicao, tabuleiro);

        this.primeiroMovimento = true;

        if (cor == Cor.BRANCO) {
            direcao = -1;
        } else {
            direcao = 1;
        }
    }

    public void movimentosPossiveis() {

        limparMatrizMovimentos(); // Primeiro ele "limpa" a matriz (preeenche tudo com false)

        Posicao posicaoFrente = new Posicao();  //move padrão
        Posicao posicaoDupla = new Posicao();   //move somente na primeira jogada
        Posicao posicaoDiagEsq = new Posicao(); //move de captura diagonal esquerda
        Posicao posicaoDiagDir = new Posicao(); //move de captura diagonal direita


        //MOVIMENTO PARDRÃO DO PEÃO (1 CASA)
        posicaoFrente.setLinha(this.posicao.getLinha() + direcao);
        posicaoFrente.setColuna(this.posicao.getColuna());

        if (tabuleiro.existePosicao(posicaoFrente) && !tabuleiro.existePeca(posicaoFrente)) {
            matrizMovimentos[posicaoFrente.getLinha()][posicaoFrente.getColuna()] = true;
        }

        //MOVIMENTO DUPLO DO PEÃO (PRIMEIRA JOGADA - 2 CASA)
        if (primeiroMovimento == true) {

            posicaoDupla.setLinha(this.posicao.getLinha() + (2 * direcao));
            posicaoDupla.setColuna(this.posicao.getColuna());

            if (tabuleiro.existePosicao(posicaoDupla) &&
                    !tabuleiro.existePeca(posicaoFrente) &&
                    !tabuleiro.existePeca(posicaoDupla)) {
                matrizMovimentos[posicaoDupla.getLinha()][posicaoDupla.getColuna()] = true;
            }
        }

        //MOVIMENTO CAPTURA DIAGONAL ESQUERDA (1 CASA)
        posicaoDiagEsq.setLinha(this.posicao.getLinha() + direcao);
        posicaoDiagEsq.setColuna(this.posicao.getColuna() - 1);

        if (tabuleiro.existePosicao(posicaoDiagEsq) &&
                tabuleiro.existePeca(posicaoDiagEsq) &&
                (tabuleiro.getPeca(posicaoDiagEsq).cor != this.cor)) {
            matrizMovimentos[posicaoDiagEsq.getLinha()][posicaoDiagEsq.getColuna()] = true;
        }

        //MOVIMENTO CAPTURA DIAGONAL DIREITA (1 CASA)
        posicaoDiagDir.setLinha(this.posicao.getLinha() + direcao);
        posicaoDiagDir.setColuna(this.posicao.getColuna() + 1);

        if (tabuleiro.existePosicao(posicaoDiagDir) &&
                tabuleiro.existePeca(posicaoDiagDir) &&
                (tabuleiro.getPeca(posicaoDiagDir).cor != this.cor)) {
            matrizMovimentos[posicaoDiagDir.getLinha()][posicaoDiagDir.getColuna()] = true;
        }

    }


    public boolean podePromover(Posicao destino) {

        if (((this.cor == Cor.BRANCO) && (destino.getLinha() == 0)) || ((this.cor == Cor.PRETO) && (destino.getLinha() == 7))) {
            return true;
        } else {
            return false;
        }
    }

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }
}