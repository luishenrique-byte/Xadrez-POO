package pecas;

import tabuleiro.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Cavalo extends Peca{
    public Cavalo(Cor cor, Posicao posicao, Tabuleiro tabuleiro) {
        super(cor, posicao, tabuleiro);
    }

    @Override
    public void movimentosPossiveis() {

        Posicao posDestino = new Posicao();

        int linha;
        int coluna;

        //Nordeste 1
        linha = this.posicao.getLinha() - 2;
        coluna = this.posicao.getColuna() + 1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //Nordeste 2
        linha = this.posicao.getLinha() - 1;
        coluna = this.posicao.getColuna() + 2;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //Noroeste 1
        linha = this.posicao.getLinha() - 1;
        coluna = this.posicao.getColuna() - 2;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //Noroeste 2
        linha = this.posicao.getLinha() - 2;
        coluna = this.posicao.getColuna() - 1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //Sudeste 1
        linha = this.posicao.getLinha() + 1;
        coluna = this.posicao.getColuna() + 2;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //Sudeste 2
        linha = this.posicao.getLinha() + 2;
        coluna = this.posicao.getColuna() + 1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //Sudoeste 1
        linha = this.posicao.getLinha() + 2;
        coluna = this.posicao.getColuna() - 1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

        //Sudoeste 2
        linha = this.posicao.getLinha() + 1;
        coluna = this.posicao.getColuna() - 2;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            } else if (tabuleiro.getPeca(posDestino).cor != this.cor) {
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }
    }
}
