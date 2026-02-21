package pecas;

import tabuleiro.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Bispo extends Peca{

    public Bispo(Cor cor, Posicao posicao, Tabuleiro tabuleiro) {
        super(cor, posicao, tabuleiro);
    }

    @Override
    public void movimentosPossiveis() {

        limparMatrizMovimentos();

        Posicao posDestino = new Posicao();

        int linha;
        int coluna;

        //Nordeste
        linha = this.posicao.getLinha()-1;
        coluna = this.posicao.getColuna()+1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        while (tabuleiro.existePosicao(posDestino)){

            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posDestino).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha--;
            coluna++;
            posDestino.setLinha(linha);
            posDestino.setColuna(coluna);
        }

        //Noroeste

        linha = this.posicao.getLinha() - 1;
        coluna = this.posicao.getColuna() - 1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        while (tabuleiro.existePosicao(posDestino)){

            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posDestino).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha--;
            coluna--;
            posDestino.setLinha(linha);
            posDestino.setColuna(coluna);

        }

        //Sudeste
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna()+1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        while (tabuleiro.existePosicao(posDestino)){

            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posDestino).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha++;
            coluna++;
            posDestino.setLinha(linha);
            posDestino.setColuna(coluna);

        }

        //Sudoeste
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna()-1;

        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        while (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posDestino).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha++;
            coluna--;
            posDestino.setLinha(linha);
            posDestino.setColuna(coluna);

        }
    }
}