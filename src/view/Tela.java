package view;

import pecas.Bispo;
import pecas.Peao;
import pecas.Torre;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import tabuleiro.Cor;
import xadrez.PartidaDeXadrez;
import pecas.Peca;

import java.util.Scanner;



//classe em desenvolvimento TUDO AQUI É PROVISÓRIO ent mudar dps





public class Tela {

    public static void main() {
        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

        while (true){

            mostrarTabuleiro(partidaDeXadrez.tabuleiro);

            Posicao posOrigem = lerPosicao(sc, "origem");
            Posicao posDestino = lerPosicao(sc, "destino");

            partidaDeXadrez.fazerJogada(posOrigem, posDestino);
        }


    }

    private static Posicao lerPosicao(Scanner sc, String tipo) {

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

        Posicao pos = new Posicao();
        pos.setLinha(linha);
        pos.setColuna(coluna);



        return pos;
    }


    public static void mostrarTabuleiro(Tabuleiro tabu){

        char[] alfabeto = "abcdefgh".toCharArray();

        for (int i = 0; i < 8; i++) {
            int esq = 8-i;
            System.out.print(esq+" ");
            for (int j = 0; j < 8; j++) {
                Peca peca = tabu.tabuleiro[i][j];
                if (tabu.tabuleiro[i][j] instanceof Peao){
                    if (tabu.tabuleiro[i][j].getCor() == Cor.BRANCO){
                        System.out.print(" P ");
                    } else {
                        System.out.print(" p ");
                    }
                } else if(tabu.tabuleiro[i][j] instanceof Torre){
                    if (tabu.tabuleiro[i][j].getCor() == Cor.BRANCO){
                        System.out.print(" T ");
                    } else {
                        System.out.print(" t ");
                    }
                } else if(tabu.tabuleiro[i][j] instanceof Bispo) {
                    if (tabu.tabuleiro[i][j].getCor() == Cor.BRANCO) {
                        System.out.print(" B ");
                    } else {
                        System.out.print(" b ");
                    }
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
