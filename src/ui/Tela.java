package ui;

import pecas.*;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaDeXadrez;
import xadrez.Peca;

import java.util.Scanner;

//classe em desenvolvimento TUDO AQUI É PROVISÓRIO ent mudar dps

public class Tela {

    public static final String ANSI_AMARELO = "\u001B[43m";
    public static final String ANSI_VERDE = "\u001B[42m";
    public static final String ANSI_PRETO = "";
    public static final String ANSI_BRANCO = "";
    public static final String ANSI_RESET = "\u001B[0m";

    public static Posicao lerPosicao(Scanner sc, String tipo) {

        System.out.println("linha " + tipo + " (1 a 8)");
        int linhaUsuario = sc.nextInt();

        System.out.println("coluna " + tipo + " (a a h)");
        char colunaChar = sc.next().toLowerCase().charAt(0);

        int linha = 8 - linhaUsuario;      // converte para matriz


        //todo *char* é um número de acordo com a tabela ACII
        // 'a' = 97 (em decimal)  'h' = 104 (em decimal)
        // Logo, 'a'-'h' = 7  . Assim é possivel capturar todas as 8 colunas
        //            ↑
        //         só vai trocando a letra
        //         e troca o valor
        int coluna = colunaChar - 'a';     // 'a'->0, 'b'->1 ...

        Posicao pos = new Posicao(linha,coluna);

        return pos;
    }

    public static void mostrarTabuleiro(Tabuleiro tabu){

        char[] alfabeto = "abcdefgh".toCharArray();

        for (int i = 0; i < 8; i++) {
            int esq = 8-i;
            System.out.print(esq+" ");
            for (int j = 0; j < 8; j++) {

                Peca peca = tabu.tabuleiro[i][j];

                if (peca != null) {

                    System.out.print(peca);

                } else {

                    System.out.print(" - ");

                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + alfabeto[i] + " ");
        }
        System.out.println();
    }
    public static void mostrarTabuleiro(Tabuleiro tabu, Peca pecaSeleciona){

        pecaSeleciona.movimentosPossiveis();
        boolean[][] movimentosPossiveis = pecaSeleciona.getMatrizMovimentos();

        char[] alfabeto = "abcdefgh".toCharArray();

        for (int i = 0; i < 8; i++) {

            int esq = 8-i;
            System.out.print(esq+" ");

            for (int j = 0; j < 8; j++) {

                Peca peca = tabu.tabuleiro[i][j];

                if (peca != null) {

                    if (peca == pecaSeleciona){
                        System.out.print(marcarPecaSelecionada(peca));
                    } else {
                        System.out.print(peca);
                    }

                    if (movimentosPossiveis[i][j]){
                        System.out.print(marcarMovimentoPossivel(peca));
                    }

                } else {

                    if (movimentosPossiveis[i][j]){
                        System.out.print(marcarMovimentoPossivel(null));
                    } else {
                        System.out.print(" - ");
                    }

                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + alfabeto[i] + " ");
        }
        System.out.println();
    }
    public static String marcarPecaSelecionada(Peca peca){
        return ANSI_AMARELO + peca + ANSI_RESET;
    }
    public static String marcarMovimentoPossivel(Peca peca){
        if (peca != null){
            return ANSI_VERDE + peca + ANSI_RESET;
        } else {
            return ANSI_VERDE + " - " + ANSI_RESET;
        }
    }
}
