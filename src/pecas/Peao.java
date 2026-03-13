package pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Peca;

public class Peao extends Peca {

    private boolean primeiroMovimento;

    private final int direcao;

    public Peao(Cor cor, Tabuleiro tabuleiro) {

        super(cor, tabuleiro);

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

        int linhaAtual = this.posicao.getLinha();
        int colunaAtual = this.posicao.getColuna();


        //MOVIMENTO PADRÃO DO PEÃO (1 CASA)
        Posicao posFrente = new Posicao(linhaAtual  + direcao,colunaAtual);

        if (tabuleiro.existePosicao(posFrente) && !tabuleiro.existePeca(posFrente)) {

            this.matrizMovimentos[posFrente.getLinha()][posFrente.getColuna()] = true;

            //MOVIMENTO DUPLO DO PEÃO (PRIMEIRA JOGADA - 2 CASA)
            if (primeiroMovimento) {

                Posicao posDupla = new Posicao(linhaAtual + 2*direcao,colunaAtual);

                if (tabuleiro.existePosicao(posDupla) && !tabuleiro.existePeca(posFrente) && !tabuleiro.existePeca(posDupla)) {
                    this.matrizMovimentos[posDupla.getLinha()][posDupla.getColuna()] = true;
                }
            }
        }
    }

    public void movimentoPossivelCaptura(){

        int linhaAtual = this.posicao.getLinha();
        int colunaAtual = this.posicao.getColuna();


        //MOVIMENTO CAPTURA DIAGONAL ESQUERDA (1 CASA)
        Posicao posDestino = new Posicao(linhaAtual + direcao,colunaAtual - 1);

        if (tabuleiro.existePosicao(posDestino) && tabuleiro.existePeca(posDestino) && (tabuleiro.getPeca(posDestino).getCor() != this.cor)) {
            this.matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
        }

        //MOVIMENTO CAPTURA DIAGONAL DIREITA (1 CASA)
        posDestino.setLinha(linhaAtual + direcao);
        posDestino.setColuna(colunaAtual + 1);

        if (tabuleiro.existePosicao(posDestino) && tabuleiro.existePeca(posDestino) && (tabuleiro.getPeca(posDestino).getCor() != this.cor)) {
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

    public boolean[][] movimentosDeAtaque() {
        movimentoPossivelCaptura();
        return this.matrizMovimentos;
    }

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }
}