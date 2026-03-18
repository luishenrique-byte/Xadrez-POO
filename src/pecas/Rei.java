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

        calcularMovimentosBasicos();

        if (this.primeiroMovimento){
            //Calcular Rook Grande
            if (caminhoLivreRoque(0) && podeRoque(possivelTorreEsquerda(), -1)){

                int coluna = this.posicao.getColuna();

                if (coluna >= 0){ // Validação extra de segurança
                    matrizMovimentos[this.posicao.getLinha()][this.posicao.getColuna()-2] = true;
                }

            }

            //Calcular Roque Pequeno
            if (caminhoLivreRoque(7) && podeRoque(possivelTorreDireita(), +1)){

                int coluna = this.posicao.getColuna();

                if (coluna <= 7){ // Validação extra de segurança
                    matrizMovimentos[this.posicao.getLinha()][this.posicao.getColuna()+2] = true;
                }

            }
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

    public void calcularMovimentosBasicos(){
        calcularMovimentos(-1,0); //Norte
        calcularMovimentos(+1,0); //Sul
        calcularMovimentos(0,-1); //Oeste
        calcularMovimentos(0,+1); //Leste
        calcularMovimentos(-1,+1); //Nordeste
        calcularMovimentos(-1,-1); //Noroeste
        calcularMovimentos(+1,+1); //Sudeste
        calcularMovimentos(+1,-1); //Sudoeste
    }

    public boolean[][] movimentosDeAtaque() {
        calcularMovimentosBasicos();
        return this.matrizMovimentos;
    }

    public boolean estaEmCheck(){

        ArrayList<Peca> listaPecas = this.tabuleiro.getPecasNoTabuleiro();

        for(Peca pecaInimiga : listaPecas){
            if (pecaInimiga.getCor() != this.cor){
                boolean[][] ataques = pecaInimiga.movimentosDeAtaque();
                if (ataques[this.posicao.getLinha()][this.posicao.getColuna()]){
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

    public boolean podeRoque(Torre torre, int direcao){
        if (torre == null){
            return false;
        }

        boolean torreAliada = torre.getCor() == this.cor;
        boolean ambosNaoDslocadas = torre.isPrimeiroMovimento() && this.isPrimeiroMovimento();
        boolean reiSeguro = !estaEmCheck();

        if (!torreAliada || !ambosNaoDslocadas || !reiSeguro){
            return false;
        }

        // ### Verifica se casas intermediárias estão atacadas ###
        int colunaRei = this.posicao.getColuna();
        int linhaRei = this.posicao.getLinha();

        // Verifica casa intermediária e casa final
        for (int i = 1; i <= 2; i++) {
            int colunaVerificar = colunaRei + (direcao * i);
            Posicao posVerificar = new Posicao(linhaRei, colunaVerificar);

            if (caminhoReiRoqueAmeacado(posVerificar)) {
                return false;
            }
        }

        return true;
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

    public boolean caminhoReiRoqueAmeacado(Posicao posVerificar){
        ArrayList<Peca> listaPecas = this.tabuleiro.getPecasNoTabuleiro();

        for(Peca pecaInimiga : listaPecas){
            if (pecaInimiga.getCor() != this.cor){
                boolean[][] ataques = pecaInimiga.movimentosDeAtaque();
                if (ataques[posVerificar.getLinha()][posVerificar.getColuna()]){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isPrimeiroMovimento() {
        return primeiroMovimento;
    }

    public void setPrimeiroMovimento(boolean primeiroMovimento) {
        this.primeiroMovimento = primeiroMovimento;
    }

    @Override
    public String toString() {
        if (this.cor == Cor.BRANCO){
            return " R ";
        } else {
            return " r ";
        }
    }
}
