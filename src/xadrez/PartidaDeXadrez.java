package xadrez;

import pecas.*;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

import java.util.Scanner;

public class PartidaDeXadrez {

    private int lances = 0;

    Cor jogadorAtual;
    public Tabuleiro tabuleiro;

    public PartidaDeXadrez() {
        this.jogadorAtual = Cor.PRETO;
        this.tabuleiro = new Tabuleiro();
        colocarPecasIniciais();
    }

    public void colocarPecasIniciais() {

        //PEÇAS BRANCA
        this.tabuleiro.colocarPeca(new Rei(Cor.BRANCO, this.tabuleiro), new Posicao(7, 4));
        this.tabuleiro.colocarPeca(new Dama(Cor.BRANCO, this.tabuleiro), new Posicao(7, 3));
        this.tabuleiro.colocarPeca(new Bispo(Cor.BRANCO, this.tabuleiro), new Posicao(7, 2));
        this.tabuleiro.colocarPeca(new Bispo(Cor.BRANCO, this.tabuleiro), new Posicao(7, 5));
        this.tabuleiro.colocarPeca(new Cavalo(Cor.BRANCO, this.tabuleiro), new Posicao(7, 1));
        this.tabuleiro.colocarPeca(new Cavalo(Cor.BRANCO, this.tabuleiro), new Posicao(7, 6));
        this.tabuleiro.colocarPeca(new Torre(Cor.BRANCO, this.tabuleiro), new Posicao(7, 0));
        this.tabuleiro.colocarPeca(new Torre(Cor.BRANCO, this.tabuleiro), new Posicao(7, 7));

        for (int i = 0; i < 8; i++) {
            this.tabuleiro.colocarPeca(new Peao(Cor.BRANCO, tabuleiro), new Posicao(6, i));
        }


        //PEÇAS PRETAS
        this.tabuleiro.colocarPeca(new Rei(Cor.PRETO, this.tabuleiro), new Posicao(0, 4));
        this.tabuleiro.colocarPeca(new Dama(Cor.PRETO, this.tabuleiro), new Posicao(0, 3));
        this.tabuleiro.colocarPeca(new Bispo(Cor.PRETO, this.tabuleiro), new Posicao(0, 2));
        this.tabuleiro.colocarPeca(new Bispo(Cor.PRETO, this.tabuleiro), new Posicao(0, 5));
        this.tabuleiro.colocarPeca(new Cavalo(Cor.PRETO, this.tabuleiro), new Posicao(0, 1));
        this.tabuleiro.colocarPeca(new Cavalo(Cor.PRETO, this.tabuleiro), new Posicao(0, 6));
        this.tabuleiro.colocarPeca(new Torre(Cor.PRETO, this.tabuleiro), new Posicao(0, 0));
        this.tabuleiro.colocarPeca(new Torre(Cor.PRETO, this.tabuleiro), new Posicao(0, 7));

        for (int i = 0; i < 8; i++) {
            this.tabuleiro.colocarPeca(new Peao(Cor.PRETO, tabuleiro), new Posicao(1, i));
        }

//        ambienteDeTeste();

    }

    public void fazerJogada(Posicao origem, Posicao destino) {

        if (!tabuleiro.existePeca(origem)) {
            throw new RuntimeException("Não existe peça na posição de origem");
        }

        Peca peca = tabuleiro.getPeca(origem);

        if (peca.getCor() != jogadorAtual) {
            throw new RuntimeException("Está peça pertence ao adversário");
        }

        peca.movimentosPossiveis();

        if (!peca.getMatrizMovimentos()[destino.getLinha()][destino.getColuna()]) {
            throw new RuntimeException("Posição destino inválida! Escolha outra posição.");
        }

        tabuleiro.posicionarPeca(peca, destino);

        if (peca instanceof Rei rei){

            if (destino.getColuna() == origem.getColuna() - 2){

                fazerRoqueGrande(origem);

            } else if (destino.getColuna() == origem.getColuna() + 2){

                fazerRoquePequeno(origem);

            }
            rei.setPrimeiroMovimento(false);
        }

        if(jogadorEmXeque()){
            desfazerJogada(origem,peca);
            throw new RuntimeException("**********************\n SEU REI ESTÁ EM XEQUE \n      PROTEJA-O\n*********************");
        }

        if (peca instanceof Peao) {
            Peao peao = (Peao) peca;

            if (peao.podePromover(destino)) {
                promoverPeao(peao, destino);
            }
            peao.setPrimeiroMovimento(false);
        }

        trocarJogador();

        //lances++; (OBJETIVO FUTURO DE CONTAR LANCES)
    }

    /**
     * Desfaz a última jogada realizada.
     *
     * Este método é acionado quando uma jogada deixa o rei em xeque,
     * realizando o rollback completo da ação.
     *
     * Fluxo do desfazer:
     * 1. Remove a peça que foi movida da posição atual.
     * 2. Recupera a última peça registrada na lista de peças capturadas.
     *    - Essa peça pode ser null, indicando que não houve captura.
     * 3. Caso exista uma peça capturada, ela é restaurada ao tabuleiro
     *    na posição que ainda está armazenada internamente no objeto.
     * 4. Remove o registro da captura da lista (comportamento de pilha - LIFO).
     * 5. Reposiciona a peça movida de volta à sua posição original.
     *
     * Observações importantes:
     * - A lista pecasCapturadas funciona como uma pilha de controle
     *   sincronizada com cada jogada realizada.
     * - O funcionamento correto do rollback depende do fato de que
     *   a peça capturada mantém sua posição original armazenada no objeto.
     */
    public void desfazerJogada(Posicao posVoltar, Peca pecaMovida){

        tabuleiro.removerPeca(pecaMovida);

        int ultimoIndice = tabuleiro.pecasCapturadas.size() - 1;
        Peca pecaCapturada = tabuleiro.pecasCapturadas.get(ultimoIndice);
        if (pecaCapturada != null){
            tabuleiro.colocarPeca(pecaCapturada, pecaCapturada.getPosicao());
        }
        tabuleiro.pecasCapturadas.remove(ultimoIndice);


        tabuleiro.colocarPeca(pecaMovida,posVoltar);

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

        this.tabuleiro.posicionarPeca(peao,destino);//primeiro posiciono ele(principalmente caso ele "coma")
        Posicao posicaoAtual = peao.getPosicao(); //TOTALMENTE OPCIONAL, pois o objeto peao fica vivo na memória(neste caso)
        Cor cor = peao.getCor(); //TOTALMENTE OPCIONAL, pois o objeto peao fica vivo na memória(neste caso)

        this.tabuleiro.removerPeca(peao); // remove o peao

        switch (opcao) {
            case 'd':
                this.tabuleiro.colocarPeca(new Dama(cor,this.tabuleiro),posicaoAtual);
                break;

            case 't':
                this.tabuleiro.colocarPeca(new Torre(cor, this.tabuleiro),posicaoAtual);
                break;

            case 'b':
                this.tabuleiro.colocarPeca(new Bispo(cor, this.tabuleiro),posicaoAtual);
                break;

            case 'c':
                this.tabuleiro.colocarPeca(new Cavalo(cor, this.tabuleiro),posicaoAtual);
                break;
        }
    }
    // #jogadaEspecial    QueenSide
    public void fazerRoqueGrande(Posicao posReiOrigem){

        Posicao posTorre = new Posicao(posReiOrigem.getLinha(), posReiOrigem.getColuna()-4);

        if (tabuleiro.getPeca(posTorre) instanceof Torre torre) {
            Posicao destinoTorre = new Posicao(posTorre.getLinha(),posTorre.getColuna()+3);
            tabuleiro.removerPeca(torre);
            tabuleiro.colocarPeca(torre,destinoTorre);
        }
    }
    // #jogadaEspecial    KingSide
    public void fazerRoquePequeno(Posicao posReiOrigem){

        Posicao posTorre = new Posicao(posReiOrigem.getLinha(), posReiOrigem.getColuna()+3);

        if (tabuleiro.getPeca(posTorre) instanceof Torre torre){
            Posicao destinoTorre = new Posicao(posTorre.getLinha(),posTorre.getColuna()-2);
            tabuleiro.removerPeca(torre);
            tabuleiro.colocarPeca(torre,destinoTorre);
        }
    }
    public boolean jogadorEmXeque(){
        return tabuleiro.getRei(this.jogadorAtual).estaEmCheck();
    }
    public boolean jogadorEmXequeMate(){
        return tabuleiro.getRei(this.jogadorAtual).estaCheckMate(this);
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
    public void ambienteDeTeste(){
        this.tabuleiro.colocarPeca(new Rei(Cor.PRETO, this.tabuleiro), new Posicao(5, 5));
        this.tabuleiro.colocarPeca(new Rei(Cor.BRANCO, this.tabuleiro), new Posicao(7, 0));

        this.tabuleiro.colocarPeca(new Torre(Cor.PRETO, this.tabuleiro), new Posicao(1, 1));

        this.tabuleiro.colocarPeca(new Torre(Cor.PRETO, this.tabuleiro), new Posicao(0, 4));

    }
}