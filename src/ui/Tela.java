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
                if (peca instanceof Peao) {

                    System.out.print(peca);

                } else if (peca instanceof Torre) {

                    System.out.print(peca);

                } else if (peca instanceof Bispo) {

                    System.out.print(peca);

                } else if (peca instanceof Cavalo) {

                    System.out.print(peca);

                } else if(peca instanceof Dama) {

                    System.out.print(peca);

                } else if(peca instanceof Rei) {

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
}
