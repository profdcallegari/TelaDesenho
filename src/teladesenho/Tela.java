////////////////////////////////////////////////////////////////////////////////
//
// TelaDesenho - Versão Avançada
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

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Tela para desenho em modo procedural.
 * @author Daniel Callegari
 * @version 2018-06
 */
public class Tela extends JFrame {
    private final int GRAPHIC_SYS_WAIT_TIME = 3000;
    private CanvasInterno canvas;
    private Color corAtual = Color.WHITE;
    private static int nTela = 0; 
    private java.util.Random rand = new java.util.Random();    
    public Color[] cores = {
                Color.WHITE,Color.LIGHT_GRAY,Color.GRAY,Color.DARK_GRAY,
                Color.BLACK,Color.RED,Color.PINK,Color.ORANGE,Color.YELLOW,
                Color.GREEN,Color.MAGENTA,Color.CYAN,Color.BLUE};

    // CONSTRUTORES ////////////////////////////////////////////////////////////
    /**
    * Cria uma nova tela de desenho com o tamanho padrão de 640x480.
    */   
    public Tela() {
        this("Tela de Desenho #" + ++nTela);
    }
    
    /**
    * Cria uma nova tela de desenho com o tamanho padrão de 640x480.
    * @param titulo O título da janela que conterá a tela
    */
    public Tela(String titulo) {
        this(640, 480, titulo);
    }
        
    /**
     * Cria uma nova tela de desenho.
     * @param largura A largura da tela em pixels
     * @param altura A altura da tela em pixels
     * @param titulo O título da janela que conterá a tela
     */
    public Tela(int largura, int altura, String titulo) {
        this(largura, altura, titulo, null);
    }
    
    /**
     * Cria uma nova tela de desenho que suporta eventos de mouse.
     * @param largura A largura da tela em pixels
     * @param altura A altura da tela em pixels
     * @param titulo O título da janela que conterá a tela
     * @param eventos Referência para o objeto que trata eventos do mouse
     */
    public Tela(int largura, int altura, String titulo, TelaEventos eventos) {
        Tela tela = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                canvas = new CanvasInterno(eventos, tela);
                canvas.setPreferredSize(new Dimension(largura, altura));
                setContentPane(canvas);
                setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha TODAS ao clicar!
                setResizable(false);
                pack();
                setLocationByPlatform(true);
                setTitle(titulo);
                setVisible(true);
            }
        });
        
        // Esperar o sistema gráfico antes de inicializar a imagem bufferizada
        try {
            Thread.sleep(GRAPHIC_SYS_WAIT_TIME);
        } catch (InterruptedException ex) {
            System.out.println("ERRO: Aumentar tempo de duração do GRAPHIC_SYS_WAIT_TIME em caso de Exceções.");
        }
        
        // Por fim, preparar a imagem bufferizada
        canvas.image = new BufferedImage(largura, altura, BufferedImage.TYPE_4BYTE_ABGR);

    }

    // METODOS /////////////////////////////////////////////////////////////////
    private Graphics2D buff () {
        
        try {
            Thread.sleep(3);
        } catch (InterruptedException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Graphics2D g2 = (Graphics2D)canvas.image.getGraphics();
        g2.setPaint(corAtual);
        return g2;
    }
    
    /**
     * Especifica a cor usada para as próximas formas desenhadas.
     * @param cor Utilize as constantes Color.RED, Color.BLUE etc. (java.awt.Color)
     */
    public void corAtual(Color cor) {
        corAtual = cor;
    }
    
    /**
     * Especifica aleatoriamente uma cor dentre as possíveis.
     */
    public void sorteiaCor () {
        corAtual(cores[rand.nextInt(cores.length)]);
    }

    /**
     * Desenha um círculo com a cor atual.
     * @param x_centro Posição X do centro do círculo
     * @param y_centro Posição Y do centro do círculo
     * @param diametro Diâmetro do círculo em pixels
     */
    public void circulo(int x_centro, int y_centro, int diametro) {
        buff().fillOval(x_centro-diametro/2, y_centro-diametro/2, diametro, diametro);
        canvas.repaint();
    }

    /**
     * Desenha uma linha de (x1,y1) até (x2,y2) com a cor atual.
     * @param x1 Posição X do primeiro ponto
     * @param y1 Posição Y do primeiro ponto
     * @param x2 Posição X do segundo ponto
     * @param y2 Posição Y do segundo ponto
     */
    public void linha(int x1, int y1, int x2, int y2) {
        buff().drawLine(x1, y1, x2, y2);
        canvas.repaint();
    }

    /**
     * Desenha um retângulo com início em (x,y) com a largura e a altura especificadas.
     * @param x Posição X do canto superior esquerdo
     * @param y Posição X do canto superior esquerdo
     * @param largura Largura do retângulo em pixels
     * @param altura Altura do retângulo em pixels
     */
    public void retangulo(int x, int y, int largura, int altura) {
        buff().fillRect(x, y, largura, altura);
        canvas.repaint();
    }

    /**
     * Desenha um texto no local especificado.
     * @param x Posição X do início do texto
     * @param y Posição Y do início do texto
     * @param texto O texto a ser exibido
     */
    public void texto(int x, int y, String texto) {
        buff().drawString(texto, x, y);
        canvas.repaint();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    private class CanvasInterno extends JPanel
    {
        Tela tela;      // Para notificar eventos
        Image image;    // Imagem bufferizada

        public CanvasInterno(TelaEventos eventos, Tela tela) {
            if (eventos != null) {
                this.tela = tela;
                
                this.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent me) {
                        super.mousePressed(me);
                        eventos.usuarioClicouMouse(tela, me.getPoint().x, me.getPoint().y);
                    }
                });
                
                this.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent me) {
                        super.mouseDragged(me);
                        eventos.usuarioArrastouMouse(tela, me.getPoint().x, me.getPoint().y);
                    }
                });
            } // eventos?
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            if (image == null) return; // se chamado antes da inicialização
            
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);

            // Desenha imagem bufferizada
            g.drawImage(image, 0, 0, null);
        }
        
    }
    
}
