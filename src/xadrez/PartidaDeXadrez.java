package xadrez;

import pecas.Peao;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

import java.util.Scanner;

public class PartidaDeXadrez {

    private int lances = 0;

    Cor jogadorAtual;
    public Tabuleiro tabuleiro;

    public PartidaDeXadrez() {
        this.jogadorAtual = Cor.BRANCO;
        this.tabuleiro = new Tabuleiro();
        colocarPecasIniciais();
    }


    public void colocarPecasIniciais() {

        //Peões brancos
        for (int i = 0; i < 8; i++) {
            Posicao pos = new Posicao();
            pos.setLinha(6);
            pos.setColuna(i);

            Peao peao = new Peao(Cor.BRANCO, pos, tabuleiro);

            this.tabuleiro.colocarPeca(peao, pos);
        }

        //Peões pretos
        for (int i = 0; i < 8; i++) {
            Posicao pos = new Posicao();
            pos.setLinha(1);
            pos.setColuna(i);

            Peao peao = new Peao(Cor.PRETO, pos, tabuleiro);

            this.tabuleiro.colocarPeca(peao, pos);
        }
    }

    public void fazerJogada(Posicao origem, Posicao destino) {

        if (!tabuleiro.existePeca(origem)) {
            throw new RuntimeException("Não existe peça na posição de origem");
        }

        Peca peca = tabuleiro.getPeca(origem);

        if (peca.cor != jogadorAtual) {
            throw new RuntimeException("Está peça pertence ao adversário");
        }

        peca.movimentosPossiveis();

        if (!peca.matrizMovimentos[destino.getLinha()][destino.getColuna()]) {
            throw new RuntimeException("Posição destino inválida! Escolha outra posição.");
        }

        tabuleiro.posicionarPeca(peca, destino);

        if (peca instanceof Peao) {

            Peao peao = (Peao) peca;

            peao.setPrimeiroMovimento(false);

            if (peao.podePromover(destino)) {
                promoverPeao(peao, destino);
            }
        }

        trocarJogador();

        lances++; //(OBJETIVO FUTURO DE CONTAR LANCES)
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

                tabuleiro.posicionarPeca(peao, destino); //primeiro posiciono ele(principalmente caso ele "coma")

                tabuleiro.removerPeca(peao); // remove o peao

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

    public void trocarJogador() {
        if (jogadorAtual == Cor.BRANCO) {
            this.jogadorAtual = Cor.PRETO;
        } else {
            this.jogadorAtual = Cor.BRANCO;
        }
    }

    public int getLances() {
        return lances;
    }
}
