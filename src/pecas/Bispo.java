package pecas;

import xadrez.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Peca;

public class Bispo extends Peca {

    public Bispo(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public void movimentosPossiveis() {

        limparMatrizMovimentos();

        //Nordeste
       calcularMovimentosEmDirecao(-1,+1);

        //Noroeste
        calcularMovimentosEmDirecao(-1,-1);

        //Sudeste
        calcularMovimentosEmDirecao(+1,+1);

        //Sudoeste
        calcularMovimentosEmDirecao(+1,-1);
    }

    public void calcularMovimentosEmDirecao(int deltaLinha, int deltaColuna){ //deltaLinha e deltaColuna são índices de direção

        int linha = this.posicao.getLinha() + deltaLinha;
        int coluna = this.posicao.getColuna() + deltaColuna;

        Posicao posDestino = new Posicao(linha,coluna);

        while (tabuleiro.existePosicao(posDestino)){

            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[linha][coluna]=true;
            } else {
                if (tabuleiro.getPeca(posDestino).getCor() != this.cor){
                    matrizMovimentos[linha][posDestino.getColuna()]=true;
                }
                break;
            }

            linha+=deltaLinha;
            coluna+=deltaColuna;

            posDestino.setLinha(linha);
            posDestino.setColuna(coluna);
        }
    }
}