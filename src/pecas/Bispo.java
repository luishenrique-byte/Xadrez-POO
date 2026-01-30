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

        Posicao posicaoNE = new Posicao();
        Posicao posicaoNO = new Posicao();
        Posicao posicaoSE = new Posicao();
        Posicao posicaoSO = new Posicao();

        int linha;
        int coluna;

        //Nordeste
        linha = this.posicao.getLinha()-1;
        coluna = this.posicao.getColuna()+1;

        posicaoNE.setLinha(linha);
        posicaoNE.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoNE)){

            if (!tabuleiro.existePeca(posicaoNE)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoNE).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha--;
            coluna++;
            posicaoNE.setLinha(linha);
            posicaoNE.setColuna(coluna);
        }

        //Noroeste

        linha = this.posicao.getLinha() - 1;
        coluna = this.posicao.getColuna() - 1;

        posicaoNO.setLinha(linha);
        posicaoNO.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoNO)){

            if (!tabuleiro.existePeca(posicaoNO)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoNO).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha--;
            coluna--;
            posicaoNO.setLinha(linha);
            posicaoNO.setColuna(coluna);

        }

        //Sudeste
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna()+1;

        posicaoSE.setLinha(linha);
        posicaoSE.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoSE)){

            if (!tabuleiro.existePeca(posicaoSE)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoSE).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha++;
            coluna++;
            posicaoSE.setLinha(linha);
            posicaoSE.setColuna(coluna);

        }

        //Sudoeste
        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna()-1;

        posicaoSO.setLinha(linha);
        posicaoSO.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoSO)){
            if (!tabuleiro.existePeca(posicaoSO)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoSO).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha++;
            coluna--;
            posicaoSO.setLinha(linha);
            posicaoSO.setColuna(coluna);

        }
    }
}