package pecas;

import tabuleiro.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Torre extends Peca{

    public Torre(Cor cor, Posicao posicao, Tabuleiro tabuleiro) {
        super(cor, posicao, tabuleiro);
    }

    @Override
    public void movimentosPossiveis() {

        limparMatrizMovimentos(); // Primeiro ele "limpa" a matriz (preeenche tudo com false)

        Posicao posicaoAcima = new Posicao();
        Posicao posicaoAbaixo = new Posicao();
        Posicao posicaoEsq = new Posicao();
        Posicao posicaoDir = new Posicao();

        int linha;
        int coluna;


        //Acima

        linha = this.posicao.getLinha()-1;
        coluna = this.posicao.getColuna();

        posicaoAcima.setLinha(linha);
        posicaoAcima.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoAcima)){

            if (!tabuleiro.existePeca(posicaoAcima)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoAcima).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            linha--;
            posicaoAcima.setLinha(linha);
        }


        //Abaixo

        linha = this.posicao.getLinha()+1;
        coluna = this.posicao.getColuna();

        posicaoAbaixo.setLinha(linha);
        posicaoAbaixo.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoAbaixo)){

            if (!tabuleiro.existePeca(posicaoAbaixo)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoAbaixo).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            linha++;
            posicaoAbaixo.setLinha(linha);
        }

        //Esquerda

        linha = this.posicao.getLinha();
        coluna = this.posicao.getColuna()-1;

        posicaoEsq.setLinha(linha);
        posicaoEsq.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoEsq)){

            if (!tabuleiro.existePeca(posicaoEsq)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoEsq).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            coluna--;
            posicaoEsq.setColuna(coluna);
        }

        //Direita

        linha = this.posicao.getLinha();
        coluna = this.posicao.getColuna()+1;

        posicaoDir.setLinha(linha);
        posicaoDir.setColuna(coluna);

        while (tabuleiro.existePosicao(posicaoDir)){

            if (!tabuleiro.existePeca(posicaoDir)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posicaoDir).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }


            coluna++;
            posicaoDir.setColuna(coluna);
        }
    }


    public boolean podeRook(){
        return false;
    }
}
