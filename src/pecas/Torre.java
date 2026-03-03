package pecas;

import xadrez.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Peca;

public class Torre extends Peca {

    public Torre(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public void movimentosPossiveis() {

        limparMatrizMovimentos(); // Primeiro ele "limpa" a matriz (preenche tudo com false)

        //Acima
        calcularMovimentosEmDirecao(-1,0);

        //Abaixo
        calcularMovimentosEmDirecao(+1,0);

        //Esquerda
        calcularMovimentosEmDirecao(0,-1);

        //Direita
        calcularMovimentosEmDirecao(0,+1);
    }

    public void calcularMovimentosEmDirecao(int deltaLinha, int deltaColuna){ //deltaLinha e deltaColuna são índices de direção

        int linha = this.posicao.getLinha() + deltaLinha;
        int coluna = this.posicao.getColuna() + deltaColuna;

        Posicao posDestino = new Posicao(linha, coluna);

        while (tabuleiro.existePosicao(posDestino)){

            if (!tabuleiro.existePeca(posDestino)){
                matrizMovimentos[linha][coluna] = true;
            } else {
                if (tabuleiro.getPeca(posDestino).cor != this.cor){
                    matrizMovimentos[linha][coluna] = true;
                }
                break;
            }

            linha+=deltaLinha;
            coluna+=deltaColuna;

            posDestino.setLinha(linha);
            posDestino.setColuna(coluna);
        }
    }

    public boolean podeRook(){
        return false;
    }
}
