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

        Posicao posNE_1 = new Posicao();
        Posicao posNE_2 = new Posicao();

        Posicao posNO_1 = new Posicao();
        Posicao posNO_2 = new Posicao();

        Posicao posSE_1 = new Posicao();
        Posicao posSE_2 = new Posicao();

        Posicao posSO_1 = new Posicao();
        Posicao posSO_2 = new Posicao();

        int linha;
        int coluna;

        //Nordeste 1 (posNE_1)
        linha = this.posicao.getLinha() - 2;
        coluna = this.posicao.getColuna() + 1;

        posNE_1.setLinha(linha);
        posNE_1.setColuna(coluna);

        if (tabuleiro.existePosicao(posNE_1)){
            if (!tabuleiro.existePeca(posNE_1)){
                matrizMovimentos[posNE_1.getLinha()][posNE_1.getColuna()] = true;
            } else if (tabuleiro.getPeca(posNE_1).cor != this.cor) {
                matrizMovimentos[posNE_1.getLinha()][posNE_1.getColuna()] = true;
            }
        }

        //Nordeste 2 (posNE_2)
        linha = this.posicao.getLinha() - 1;
        coluna = this.posicao.getColuna() + 2;

        posNE_2.setLinha(linha);
        posNE_2.setColuna(coluna);

        if (tabuleiro.existePosicao(posNE_2)){
            if (!tabuleiro.existePeca(posNE_2)){
                matrizMovimentos[posNE_2.getLinha()][posNE_2.getColuna()] = true;
            } else if (tabuleiro.getPeca(posNE_2).cor != this.cor) {
                matrizMovimentos[posNE_2.getLinha()][posNE_2.getColuna()] = true;
            }
        }

        //Noroeste 1 (posNO_1)
        linha = this.posicao.getLinha() - 1;
        coluna = this.posicao.getColuna() - 2;

        posNO_1.setLinha(linha);
        posNO_1.setColuna(coluna);

        if (tabuleiro.existePosicao(posNO_1)){
            if (!tabuleiro.existePeca(posNO_1)){
                matrizMovimentos[posNO_1.getLinha()][posNO_1.getColuna()] = true;
            } else if (tabuleiro.getPeca(posNO_1).cor != this.cor) {
                matrizMovimentos[posNO_1.getLinha()][posNO_1.getColuna()] = true;
            }
        }

        //Noroeste 2 (posNO_2)
        linha = this.posicao.getLinha() - 2;
        coluna = this.posicao.getColuna() - 1;

        posNO_2.setLinha(linha);
        posNO_2.setColuna(coluna);

        if (tabuleiro.existePosicao(posNO_2)){
            if (!tabuleiro.existePeca(posNO_2)){
                matrizMovimentos[posNO_2.getLinha()][posNO_2.getColuna()] = true;
            } else if (tabuleiro.getPeca(posNO_2).cor != this.cor) {
                matrizMovimentos[posNO_2.getLinha()][posNO_2.getColuna()] = true;
            }
        }

        //Sudeste 1 (posSE_1)
        linha = this.posicao.getLinha() + 1;
        coluna = this.posicao.getColuna() + 2;

        posSE_1.setLinha(linha);
        posSE_1.setColuna(coluna);

        if (tabuleiro.existePosicao(posSE_1)){
            if (!tabuleiro.existePeca(posSE_1)){
                matrizMovimentos[posSE_1.getLinha()][posSE_1.getColuna()] = true;
            } else if (tabuleiro.getPeca(posSE_1).cor != this.cor) {
                matrizMovimentos[posSE_1.getLinha()][posSE_1.getColuna()] = true;
            }
        }

        //Sudeste 2 (posSE_2)
        linha = this.posicao.getLinha() + 2;
        coluna = this.posicao.getColuna() + 1;

        posSE_2.setLinha(linha);
        posSE_2.setColuna(coluna);

        if (tabuleiro.existePosicao(posSE_2)){
            if (!tabuleiro.existePeca(posSE_2)){
                matrizMovimentos[posSE_2.getLinha()][posSE_2.getColuna()] = true;
            } else if (tabuleiro.getPeca(posSE_2).cor != this.cor) {
                matrizMovimentos[posSE_2.getLinha()][posSE_2.getColuna()] = true;
            }
        }

        //Sudoeste 1 (posSO_1)
        linha = this.posicao.getLinha() + 2;
        coluna = this.posicao.getColuna() - 1;

        posSO_1.setLinha(linha);
        posSO_1.setColuna(coluna);

        if (tabuleiro.existePosicao(posSO_1)){
            if (!tabuleiro.existePeca(posSO_1)){
                matrizMovimentos[posSO_1.getLinha()][posSO_1.getColuna()] = true;
            } else if (tabuleiro.getPeca(posSO_1).cor != this.cor) {
                matrizMovimentos[posSO_1.getLinha()][posSO_1.getColuna()] = true;
            }
        }

        //Sudoeste 2 (posSO_2)
        linha = this.posicao.getLinha() + 1;
        coluna = this.posicao.getColuna() - 2;

        posSO_2.setLinha(linha);
        posSO_2.setColuna(coluna);

        if (tabuleiro.existePosicao(posSO_2)){
            if (!tabuleiro.existePeca(posSO_2)){
                matrizMovimentos[posSO_2.getLinha()][posSO_2.getColuna()] = true;
            } else if (tabuleiro.getPeca(posSO_2).cor != this.cor) {
                matrizMovimentos[posSO_2.getLinha()][posSO_2.getColuna()] = true;
            }
        }
    }
}
