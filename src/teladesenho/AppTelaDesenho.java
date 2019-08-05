////////////////////////////////////////////////////////////////////////////////
//
// AppTelaDesenho - Versão Avançada
// (c) 2018, Daniel Callegari
//
// Permite fazer desenhos simples em Java.
// Destinado para introdução à programação de forma divertida
// antes de apresentar a programação orientada a objetos.
//
// Para criar uma tela de desenho (qualquer uma é válida):
//      Tela tela = new Tela();
//      Tela tela = new Tela("Título da Janela");
//      Tela tela = new Tela(largura, altura, "Título da Janela");
//
// Escolhendo a cor atual (utilizada para desenhar as próximas formas):
//      tela.corAtual(Color.WHITE);
//          Cores válidas:
//            WHITE,LIGHT_GRAY,GRAY,DARK_GRAY,BLACK,RED,
//            PINK,ORANGE,YELLOW,GREEN,MAGENTA,CYAN,BLUE
//          Ou ainda:
//            tela.sorteiaCor();
//      
// Desenhando as formas:
//      tela.linha(x1, y1, x2, y2);
//      tela.retangulo(x, y, largura, altura);
//      tela.circulo(x_centro, y_centro, diametro);
//
// Exibindo texto:
//      tela.texto(x, y, "Texto a exibir");
//
////////////////////////////////////////////////////////////////////////////////

package teladesenho;

import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppTelaDesenho{

    public static void main(String[] args) {
//        desenhaLinhasMouse();
//        desenhaCirculos();
//        desenhaExemplo();
        desenhaTabuleiro();
    }
    
    //==========================================================================
    private static void desenhaCirculos() {
        Random rand = new Random();
        Tela tela = new Tela("Mil Circulos");
        
        for (int i = 0; i < 100000; i++) {
            int x1 = rand.nextInt(600) + 20;
            int y1 = rand.nextInt(440) + 20;
            tela.sorteiaCor();
            tela.circulo(x1, y1, 30);
        }
    }
       
    //==========================================================================
    public static void desenhaExemplo() {

        // Cria uma tela de 640 por 480 pixels
        EventosUsuario eventos = new EventosUsuario();
        Tela tela = new Tela(640, 480, "Exemplo de Desenho", eventos);
        
        // raios
        tela.corAtual(Color.ORANGE);
        for (int x = 40; x < 600; x+=10) {
            tela.sorteiaCor();
            tela.linha(40, 40, x, 400);
        }

        // sol
        tela.corAtual(Color.YELLOW);
        tela.circulo(40, 40, 50);

        // terra
        tela.corAtual(Color.GREEN);
        tela.retangulo(20, 400, 600, 4);
        
        // blocos de cor
        tela.corAtual(Color.RED);
        tela.retangulo(40, 420, 30, 30);
        tela.corAtual(Color.GREEN);
        tela.retangulo(80, 420, 30, 30);
        tela.corAtual(Color.BLUE);
        tela.retangulo(120, 420, 30, 30);
        
        // texto
        tela.corAtual(Color.WHITE);
        tela.texto(380, 440, "Exemplo de desenho (c) Daniel Callegari");
        
    } // desenhaExemplo

    //==========================================================================
    private static void desenhaLinhasMouse() {
        final int LARGURA=800, ALTURA=600;
        Tela tela = new Tela(LARGURA, ALTURA, "Linhas & Mouse", 
            new TelaEventos() {
                @Override
                public void usuarioClicouMouse(Tela tela, int x, int y) {
                    tela.corAtual(Color.BLACK);
                    tela.retangulo(x-2, y-10, 60, 12);
                    tela.corAtual(Color.WHITE);
                    tela.texto(x, y, "(" + x + "," + y + ")");
                }

                @Override
                public void usuarioArrastouMouse(Tela tela, int x, int y) {
                    tela.sorteiaCor();
                    tela.retangulo(0, y-3, LARGURA, 6);
                    tela.retangulo(x-3, 0, 6, ALTURA);
                }
            }
        );
        tela.texto(30, 30, "Clique / arraste o mouse!");
        
    } // desenhaLinhasMouse

    private static void desenhaTabuleiro() {
        final int LARGURA=800, ALTURA=600;
        
        final int LINHAS = 10;
        final int COLUNAS = 10;
        final int DRAW_OFFSET = 50;
        final int TAMANHO_CELULA = 30;
        
        boolean[][] matTabuleiro = new boolean[LINHAS][COLUNAS];
                
        Tela tela = new Tela(LARGURA, ALTURA, "Tabuleiro clicável", 
            new TelaEventos() {
                @Override
                public void usuarioClicouMouse(Tela tela, int x, int y) {
                    tela.corAtual(Color.BLACK);
                    tela.retangulo(14-2, 14-10, 60, 12);
                    tela.corAtual(Color.WHITE);
                    tela.texto(14, 14, "(" + x + "," + y + ")");
                    
                    // Em qual célula clicou?
                    int celulaLinha = (y-DRAW_OFFSET) / TAMANHO_CELULA;
                    int celulaColuna = (x-DRAW_OFFSET) / TAMANHO_CELULA;
                    //System.out.printf("L=%d, C=%d\n", celulaLinha, celulaColuna);
                    
                    if (celulaLinha >= 0 && celulaLinha < LINHAS
                            && celulaColuna >=0 && celulaColuna < COLUNAS)
                    {
                        // Toggle
                        
                        
                        // Desenha tabuleiro
                        for (int linha = 0; linha < LINHAS; linha++) {
                            for (int coluna = 0; coluna < COLUNAS; coluna++) {
                                tela.sorteiaCor();
                                tela.retangulo(
                                        DRAW_OFFSET + linha*TAMANHO_CELULA, 
                                        DRAW_OFFSET + coluna*TAMANHO_CELULA, 
                                        TAMANHO_CELULA, TAMANHO_CELULA
                                );
                            }
                        }
                    }
                    
                }

                @Override
                public void usuarioArrastouMouse(Tela tela, int x, int y) {
                    // ignorado
                }
            }
        );
    }
    
} // classe principal
