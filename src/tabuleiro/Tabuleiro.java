package tabuleiro;

import pecas.Peca;

public class Tabuleiro {
    public Peca[][] tabuleiro = new Peca[8][8];

    public void colocarPeca(Peca peca, Posicao pos) {
        peca.setPosicao(pos);
        tabuleiro[pos.linha][pos.coluna] = peca;
    }

    public void posicionarPeca(Peca peca, Posicao destino) {

        Peca alvo = getPeca(destino);

        if (existePosicao(destino) && peca.getMatrizMovimentos()[destino.linha][destino.coluna] == true) {

            removerPeca(peca); //remover a peca

            if (alvo != null) { //momento de captura de peça
                removerPeca(alvo);
            }

            tabuleiro[destino.linha][destino.coluna] = peca; //colocar a peca

            peca.setPosicao(destino); //Altera o atributo da peça, evita causar bugs

        }
    }



    public void removerPeca(Peca peca) {
        Posicao posicaoAtual = peca.getPosicao();

        tabuleiro[posicaoAtual.linha][posicaoAtual.coluna] = null;
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
}