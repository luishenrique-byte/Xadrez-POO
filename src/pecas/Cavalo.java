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

        limparMatrizMovimentos();

        //Nordeste 1
        calcularSalto(-2,+1);

        //Nordeste 2
        calcularSalto(-1,+2);

        //Noroeste 1
        calcularSalto(-1,-2);

        //Noroeste 2
        calcularSalto(-2,-1);

        //Sudeste 1
        calcularSalto(+1,+2);

        //Sudeste 2
        calcularSalto(+2,+1);

        //Sudoeste 1
        calcularSalto(+2,-1);

        //Sudoeste 2
        calcularSalto(+1,-2);
    }

    public void calcularSalto(int deltaLinha, int deltaColuna) { //deltaLinha e deltaColuna são índices de direção

        int linha = this.posicao.getLinha() + deltaLinha;
        int coluna = this.posicao.getColuna() + deltaColuna;

        Posicao posDestino = new Posicao();
        posDestino.setLinha(linha);
        posDestino.setColuna(coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino) || tabuleiro.getPeca(posDestino).cor != this.cor){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }
    }
}
