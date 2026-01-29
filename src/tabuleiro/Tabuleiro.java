package tabuleiro;

import pecas.Peao;
import xadrez.Peca;

import java.util.Scanner;

public class Tabuleiro {
    public Peca[][] tabuleiro = new Peca[8][8];

    public void colocarPeca(Peca peca, Posicao posicao) {
        tabuleiro[posicao.linha][posicao.coluna] = peca;
    }

    public void posicionarPeca(Peca peca, Posicao destino) {

        Peca alvo = getPeca(destino);

        if (existePosicao(destino) && peca.podeMover(destino)) {

            removerPeca(peca); //remover a peca

            if (alvo != null) { //momento de captura de peça
                removerPeca(alvo);
            }

            tabuleiro[destino.linha][destino.coluna] = peca; //colocar a peca

            peca.setPosicao(destino); //Altera o atributo da peça, evita causar bugs

        }
    }

    // #jogadaEspecial
    public void promoverPeao(Peao peao, Posicao destino) {

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("#--- PROMOÇÃO! ---#");
        System.out.println("Opções de promoção:");
        System.out.println(" - Dama[D]\n - Torre[T]\n - Bispo[B]\n - Cavalo[C]");
        System.out.println("Digite o caractere da opção escolhida: ");

        char opcao = sc.next().toLowerCase().charAt(0);

        switch (opcao) {
            case 'd':

                posicionarPeca(peao, destino); //primeiro posiciono ele(principalmente caso ele "coma")

                removerPeca(peao); // remove o peao

                //e ent coloca a dama
                //Dama dama = new Dama(peao.cor,peao.getPosicao(),this.tabuleiro);     add linha dps de criar a classe

                break;

            case 't':

                break;

            case 'b':

                break;

            case 'c':

                break;
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