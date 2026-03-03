package tabuleiro;

import xadrez.Peca;
import pecas.Rei;
import xadrez.Cor;

import java.util.ArrayList;

public class Tabuleiro {
    public Peca[][] tabuleiro = new Peca[8][8];
    private ArrayList<Peca> pecasNoTabuleiro = new ArrayList<Peca>();
    public ArrayList<Peca> pecasCapturadas = new ArrayList<Peca>();

    public void colocarPeca(Peca peca, Posicao pos) {
        peca.setPosicao(pos);
        this.tabuleiro[pos.linha][pos.coluna] = peca;
        this.pecasNoTabuleiro.add(peca);
    }

    public void posicionarPeca(Peca peca, Posicao destino) {

        Peca alvo = getPeca(destino);

        if (existePosicao(destino) && peca.getMatrizMovimentos()[destino.linha][destino.coluna] == true) {

            removerPeca(peca); //remover a peca

            capturarPeca(alvo);

            tabuleiro[destino.linha][destino.coluna] = peca; //colocar a peca

            peca.setPosicao(destino); //Altera o atributo da peça, evita causar bugs

        }
    }

    public void capturarPeca(Peca pecaAlvo){
        removerPeca(pecaAlvo);
        pecasCapturadas.add(pecaAlvo);
    }

    /**
     * Remove uma peça do tabuleiro.
     *
     * IMPORTANTE:
     * A posição interna da peça NÃO é alterada.
     * Isso é necessário para permitir o correto funcionamento
     * do rollback em desfazerJogada().
     */
    public void removerPeca(Peca peca) {
        if (peca != null){
            Posicao posicaoAtual = peca.getPosicao();
            this.tabuleiro[posicaoAtual.linha][posicaoAtual.coluna] = null;
            this.pecasNoTabuleiro.remove(peca);
        }
    }

    public boolean existePeca(Posicao posicao) {

        if (!existePosicao(posicao)) return false;

        if (tabuleiro[posicao.linha][posicao.coluna] != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean existePosicao(Posicao posicao) {

        if (posicao.coluna >= tabuleiro.length ||
                posicao.coluna < 0 ||
                posicao.linha >= tabuleiro.length ||
                posicao.linha < 0) {
            return false;
        } else {
            return true;
        }

    }

    public Peca getPeca(Posicao posicao) {
        int linha = posicao.getLinha();
        int coluna = posicao.getColuna();
        return this.tabuleiro[linha][coluna];
    }
    public Rei getRei(Cor jogadorAtual){
        for(Peca peca : pecasNoTabuleiro){
            if (peca instanceof Rei && peca.getCor() == jogadorAtual){
                return (Rei) peca;
            }
        }
        throw new RuntimeException("Não existe rei da cor " + jogadorAtual + " no tabuleiro.");
    }

    public ArrayList<Peca> getPecasNoTabuleiro(){
        return this.pecasNoTabuleiro;
    }
}