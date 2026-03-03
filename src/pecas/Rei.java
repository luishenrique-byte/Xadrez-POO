package pecas;

import xadrez.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Peca;

import java.util.ArrayList;

public class Rei extends Peca {

    public Rei(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
    }

    @Override
    public void movimentosPossiveis() {

        limparMatrizMovimentos();

        //Norte
        calcularMovimentos(-1,0);

        //Sul
        calcularMovimentos(+1,0);

        //Oeste
        calcularMovimentos(0,-1);

        //Leste
        calcularMovimentos(0,+1);

        //Nordeste
        calcularMovimentos(-1,+1);

        //Noroeste
        calcularMovimentos(-1,-1);

        //Sudeste
        calcularMovimentos(+1,+1);

        //Sudoeste
        calcularMovimentos(+1,-1);
    }

    public void calcularMovimentos(int deltaLinha, int deltaColuna){

        int linha = this.posicao.getLinha() + deltaLinha;
        int coluna = this.posicao.getColuna() + deltaColuna;

        Posicao posDestino = new Posicao(linha,coluna);

        if (tabuleiro.existePosicao(posDestino)){
            if (!tabuleiro.existePeca(posDestino) || tabuleiro.getPeca(posDestino).cor != this.cor){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

    }

    public boolean estaEmCheck(){

        ArrayList<Peca> listaPecas = tabuleiro.getPecasNoTabuleiro();

        for(Peca pecaInimiga : listaPecas){
            if (pecaInimiga.cor != this.cor){
                pecaInimiga.movimentosPossiveis();
                boolean matrizMovimentosInimiga[][] = pecaInimiga.getMatrizMovimentos();

                if (matrizMovimentosInimiga[this.posicao.getLinha()][this.posicao.getColuna()]){
                    return true;
                }
            }
        }
        return false;
    }
}
