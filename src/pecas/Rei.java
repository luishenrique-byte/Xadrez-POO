package pecas;

import xadrez.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PartidaDeXadrez;
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

        if (this.tabuleiro.existePosicao(posDestino)){
            if (!this.tabuleiro.existePeca(posDestino) || this.tabuleiro.getPeca(posDestino).getCor() != this.cor){
                matrizMovimentos[posDestino.getLinha()][posDestino.getColuna()] = true;
            }
        }

    }

    public boolean estaEmCheck(){

        ArrayList<Peca> listaPecas = this.tabuleiro.getPecasNoTabuleiro();

        for(Peca pecaInimiga : listaPecas){
            if (pecaInimiga.getCor() != this.cor){
                pecaInimiga.movimentosPossiveis();
                boolean matrizMovimentosInimiga[][] = pecaInimiga.getMatrizMovimentos();

                if (matrizMovimentosInimiga[this.posicao.getLinha()][this.posicao.getColuna()]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean estaCheckMate(PartidaDeXadrez partidaDeXadrez){

        if (!estaEmCheck()){
            return false;
        }

        //quando se atribui com um ArrayList '=' ela apenas cria um ponteiro para o ArrayList original
        //Quando eu eu faço ' new ArrayList<>(lista_original); ' ele aí sim cria uma cópia.
        ArrayList<Peca> listaPecas = new ArrayList<>(this.tabuleiro.getPecasNoTabuleiro());

        for(Peca peca : listaPecas){

            if (peca.getCor() != this.cor){
                continue;
            }

            peca.movimentosPossiveis();
            boolean matrizMovimentosPeca[][] = peca.getMatrizMovimentos();

            for (int i = 0; i < matrizMovimentosPeca.length; i++){
                for (int j = 0; j < matrizMovimentosPeca.length; j++) {
                    if (matrizMovimentosPeca[i][j]){

                        Posicao posOrigem = peca.getPosicao();
                        this.tabuleiro.posicionarPeca(peca,new Posicao(i,j));

                        boolean testeCheck = estaEmCheck();

                        partidaDeXadrez.desfazerJogada(posOrigem, peca);

                        if(!testeCheck){
                            return false;
                        }
                    }
                }
            }
        }

        return true;

    }
}
