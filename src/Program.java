import tabuleiro.Posicao;
import ui.Tela;
import xadrez.PartidaDeXadrez;

import java.util.Scanner;

public class Program {
    static void main() {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

        while (!partidaDeXadrez.jogadorEmXequeMate()){
            try {
                Tela.mostrarTabuleiro(partidaDeXadrez.tabuleiro);

                Posicao posOrigem = Tela.lerPosicao(sc, "origem");

                Tela.mostrarTabuleiro(partidaDeXadrez.tabuleiro);

                Posicao posDestino = Tela.lerPosicao(sc, "destino");

                partidaDeXadrez.fazerJogada(posOrigem, posDestino);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
