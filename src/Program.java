import tabuleiro.Posicao;
import ui.Tela;
import xadrez.PartidaDeXadrez;

import java.util.Scanner;

public class Program {
    static void main() {
        Scanner sc = new Scanner(System.in);
        Tela tela = new Tela();
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        while (!partidaDeXadrez.jogadorEmXequeMate()){
            try {
                tela.mostrarTabuleiro(partidaDeXadrez.tabuleiro);

                Posicao posOrigem = tela.lerPosicao(sc, "origem");
                Posicao posDestino = tela.lerPosicao(sc, "destino");

                partidaDeXadrez.fazerJogada(posOrigem, posDestino);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
