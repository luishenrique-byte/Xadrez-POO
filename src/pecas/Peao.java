package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Cor;

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

    @Override
    public void movimentosPossiveis() {

        limparMatrizMovimentos(); // Primeiro ele "limpa" a matriz (preeenche tudo com false)

        movimentoPossivelFrente();
        movimentoPossivelCaptura();

    }

    public void movimentoPossivelFrente(){
        Posicao posFrente = new Posicao();
        Posicao posDupla = new Posicao();

        int linhaAtual = this.posicao.getLinha();
        int colunaAtual = this.posicao.getColuna();

        //MOVIMENTO PADRÃO DO PEÃO (1 CASA)
        posFrente.setLinha(linhaAtual  + direcao);
        posFrente.setColuna(colunaAtual);

        if (tabuleiro.existePosicao(posFrente) && !tabuleiro.existePeca(posFrente)) {

            this.matrizMovimentos[posFrente.getLinha()][posFrente.getColuna()] = true;

            //MOVIMENTO DUPLO DO PEÃO (PRIMEIRA JOGADA - 2 CASA)
            if (primeiroMovimento) {

                posDupla.setLinha(linhaAtual + 2*direcao);
                posDupla.setColuna(colunaAtual);

                if (tabuleiro.existePosicao(posDupla) && !tabuleiro.existePeca(posFrente) && !tabuleiro.existePeca(posDupla)) {
                    this.matrizMovimentos[posDupla.getLinha()][posDupla.getColuna()] = true;
                }
            }
        }
    }

    public void movimentoPossivelCaptura(){
        Posicao posDestino = new Posicao();

        int linhaAtual = this.posicao.getLinha();
        int colunaAtual = this.posicao.getColuna();

        //MOVIMENTO CAPTURA DIAGONAL ESQUERDA (1 CASA)
        posDestino.setLinha(linhaAtual + direcao);
        posDestino.setColuna(colunaAtual - 1);

        if (tabuleiro.existePosicao(posDestino) && tabuleiro.existePeca(posDestino) && (tabuleiro.getPeca(posDestino).cor != this.cor)) {
            this.matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
        }

        //MOVIMENTO CAPTURA DIAGONAL DIREITA (1 CASA)
        posDestino.setLinha(linhaAtual + direcao);
        posDestino.setColuna(colunaAtual + 1);

        if (tabuleiro.existePosicao(posDestino) && tabuleiro.existePeca(posDestino) && (tabuleiro.getPeca(posDestino).cor != this.cor)) {
            this.matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
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