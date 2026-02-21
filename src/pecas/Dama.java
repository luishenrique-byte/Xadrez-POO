package pecas;

import tabuleiro.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Dama extends Peca{

    public Dama(Cor cor, Posicao posicao, Tabuleiro tabuleiro) {
        super(cor, posicao, tabuleiro);
    }

    @Override
    public void movimentosPossiveis() {

        limparMatrizMovimentos(); // Primeiro ele "limpa" a matriz (preeenche tudo com false)

        Posicao posDestino = new Posicao();

        int linha;
        int coluna;



        //Norte
        linha = this.posicao.getLinha()-1;
        coluna = this.posicao.getColuna();

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
            posDestino.setLinha(linha);
        }


        //Sul
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna();

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
            posDestino.setLinha(linha);
        }


        //Oeste
        linha = this.posicao.getLinha();
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


            coluna--;
            posDestino.setColuna(coluna);
        }


        //Leste
        linha = this.posicao.getLinha();
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


            coluna++;
            posDestino.setColuna(coluna);
        }


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