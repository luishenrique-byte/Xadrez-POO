package pecas;

import xadrez.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.PartidaDeXadrez;
import xadrez.Peca;

import java.util.ArrayList;

public class Rei extends Peca {

    private boolean primeiroMovimento;

    public Rei(Cor cor, Tabuleiro tabuleiro) {
        super(cor, tabuleiro);
        primeiroMovimento = true;
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

        //Calcular Rook Grande
        if (caminhoLivreRoque(0) && podeRoque(possivelTorreEsquerda())){
            matrizMovimentos[this.posicao.getLinha()][this.posicao.getColuna()-2] = true;
        }

        //Calcular Roque Pequeno
        if (caminhoLivreRoque(7) && podeRoque(possivelTorreDireita())){
            matrizMovimentos[this.posicao.getLinha()][this.posicao.getColuna()+2] = true;
        }
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

    public boolean podeRoque(Torre torre){
        if (torre == null){
            return false;
        }

        boolean torreAliada = torre.getCor() == this.cor;
        boolean ambosNaoDslocadas = torre.isPrimeiroMovimento() && this.isPrimeiroMovimento();
        boolean reiSeguro = !estaEmCheck();

        return torreAliada && ambosNaoDslocadas && reiSeguro;
    }
    public Torre possivelTorreEsquerda(){
        int linhaRei = this.posicao.getLinha();
        int colunaRei = this.posicao.getColuna();

        Posicao posTorre = new Posicao(linhaRei,colunaRei-4);

        if (tabuleiro.existePosicao(posTorre) && tabuleiro.getPeca(posTorre) instanceof Torre){
            return (Torre) tabuleiro.getPeca(posTorre);
        }
        return null;
    }
    public Torre possivelTorreDireita(){
        int linhaRei = this.posicao.getLinha();
        int colunaRei = this.posicao.getColuna();

        Posicao posTorre = new Posicao(linhaRei,colunaRei+3);

        if (tabuleiro.existePosicao(posTorre) && tabuleiro.getPeca(posTorre) instanceof Torre){
            return (Torre) tabuleiro.getPeca(posTorre);
        }
        return null;
    }
    public boolean caminhoLivreRoque(int colunaDirecao){
        int linhaRei = this.posicao.getLinha();
        int colunaRei = this.posicao.getColuna();

        int inicio = Math.min(colunaRei,colunaDirecao);
        int fim = Math.max(colunaRei,colunaDirecao);

        for (int col = inicio + 1; col < fim; col++) {

            Posicao pos = new Posicao(linhaRei,col);

            if (tabuleiro.existePeca(pos)){
                return false; //Caminho bloqueado
            }

        }
        return true; //Caminho Livre
    }

    public boolean isPrimeiroMovimento() {
        return primeiroMovimento;
    }

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }
}
