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

        Posicao posDestino = new Posicao();


        //MOVIMENTO PADRÃO DO PEÃO (1 CASA)
        posDestino.setLinha(this.posicao.getLinha() + direcao);
        posDestino.setColuna(this.posicao.getColuna());

        if (tabuleiro.existePosicao(posDestino) && !tabuleiro.existePeca(posDestino)) {
            matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
        }

        //MOVIMENTO DUPLO DO PEÃO (PRIMEIRA JOGADA - 2 CASA)
        if (primeiroMovimento) {

            posDestino.setLinha(this.posicao.getLinha() + (2 * direcao));
            posDestino.setColuna(this.posicao.getColuna());

            if (tabuleiro.existePosicao(posDestino) &&
                    !tabuleiro.existePeca(posDestino) &&
                    !tabuleiro.existePeca(posDestino)) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //MOVIMENTO CAPTURA DIAGONAL ESQUERDA (1 CASA)
        posDestino.setLinha(this.posicao.getLinha() + direcao);
        posDestino.setColuna(this.posicao.getColuna() - 1);

        if (tabuleiro.existePosicao(posDestino) &&
                tabuleiro.existePeca(posDestino) &&
                (tabuleiro.getPeca(posDestino).cor != this.cor)) {
            matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
        }

        //MOVIMENTO CAPTURA DIAGONAL DIREITA (1 CASA)
        posDestino.setLinha(this.posicao.getLinha() + direcao);
        posDestino.setColuna(this.posicao.getColuna() + 1);

        if (tabuleiro.existePosicao(posDestino) &&
                tabuleiro.existePeca(posDestino) &&
                (tabuleiro.getPeca(posDestino).cor != this.cor)) {
            matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
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