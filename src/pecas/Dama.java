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

        Posicao posNorte = new Posicao();
        Posicao posSul = new Posicao();
        Posicao posOeste = new Posicao();
        Posicao posLeste = new Posicao();

        Posicao posNE = new Posicao();
        Posicao posNO = new Posicao();
        Posicao posSE = new Posicao();
        Posicao posSO = new Posicao();

        int linha;
        int coluna;



        //Norte
        linha = this.posicao.getLinha()-1;
        coluna = this.posicao.getColuna();

        posNorte.setLinha(linha);
        posNorte.setColuna(coluna);

        while (tabuleiro.existePosicao(posNorte)){

            if (!tabuleiro.existePeca(posNorte)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posNorte).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            linha--;
            posNorte.setLinha(linha);
        }


        //Sul
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna();

        posSul.setLinha(linha);
        posSul.setColuna(coluna);

        while (tabuleiro.existePosicao(posSul)){

            if (!tabuleiro.existePeca(posSul)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posSul).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            linha++;
            posSul.setLinha(linha);
        }


        //Oeste
        linha = this.posicao.getLinha();
        coluna = this.posicao.getColuna()-1;

        posOeste.setLinha(linha);
        posOeste.setColuna(coluna);

        while (tabuleiro.existePosicao(posOeste)){

            if (!tabuleiro.existePeca(posOeste)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posOeste).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            coluna--;
            posOeste.setColuna(coluna);
        }


        //Leste
        linha = this.posicao.getLinha();
        coluna = this.posicao.getColuna()+1;

        posLeste.setLinha(linha);
        posLeste.setColuna(coluna);

        while (tabuleiro.existePosicao(posLeste)){

            if (!tabuleiro.existePeca(posLeste)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posLeste).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            coluna++;
            posLeste.setColuna(coluna);
        }


        //Nordeste
        linha = this.posicao.getLinha()-1;
        coluna = this.posicao.getColuna()+1;

        posNE.setLinha(linha);
        posNE.setColuna(coluna);

        while (tabuleiro.existePosicao(posNE)){

            if (!tabuleiro.existePeca(posNE)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posNE).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha--;
            coluna++;
            posNE.setLinha(linha);
            posNE.setColuna(coluna);
        }


        //Noroeste

        linha = this.posicao.getLinha() - 1;
        coluna = this.posicao.getColuna() - 1;

        posNO.setLinha(linha);
        posNO.setColuna(coluna);

        while (tabuleiro.existePosicao(posNO)){

            if (!tabuleiro.existePeca(posNO)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posNO).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha--;
            coluna--;
            posNO.setLinha(linha);
            posNO.setColuna(coluna);

        }


        //Sudeste
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna()+1;

        posSE.setLinha(linha);
        posSE.setColuna(coluna);

        while (tabuleiro.existePosicao(posSE)){

            if (!tabuleiro.existePeca(posSE)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posSE).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha++;
            coluna++;
            posSE.setLinha(linha);
            posSE.setColuna(coluna);

        }


        //Sudoeste
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna()-1;

        posSO.setLinha(linha);
        posSO.setColuna(coluna);

        while (tabuleiro.existePosicao(posSO)){
            if (!tabuleiro.existePeca(posSO)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posSO).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha++;
            coluna--;
            posSO.setLinha(linha);
            posSO.setColuna(coluna);

        }
    }
}