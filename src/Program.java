import tabuleiro.Posicao;
import ui.Tela;
import xadrez.PartidaDeXadrez;
import xadrez.Peca;

import java.util.Scanner;

public class Program {
    static void main() {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();

        while (!partidaDeXadrez.jogadorEmXequeMate()){
            try {
                Tela.mostrarTabuleiro(partidaDeXadrez.tabuleiro);

                Posicao posOrigem = Tela.lerPosicao(sc, "origem");

                Peca pecaSelecionada = partidaDeXadrez.tabuleiro.getPeca(posOrigem);
                Tela.mostrarTabuleiro(partidaDeXadrez.tabuleiro, pecaSelecionada);

                Posicao posDestino = Tela.lerPosicao(sc, "destino");

                partidaDeXadrez.fazerJogada(posOrigem, posDestino);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
