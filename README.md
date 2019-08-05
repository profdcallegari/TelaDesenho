 **AppTelaDesenho - Versão Avançada**
 **(c) 2018, Daniel Callegari**

 Permite fazer desenhos simples em Java.
 Destinado para introdução à programação de forma divertida
 antes de apresentar a programação orientada a objetos.

 Para criar uma tela de desenho (qualquer uma é válida):
>      Tela tela = new Tela();
>      Tela tela = new Tela("Título da Janela");
>      Tela tela = new Tela(largura, altura, "Título da Janela");

 Escolhendo a cor atual (utilizada para desenhar as próximas formas):
>      tela.corAtual(Color.WHITE);
         Cores válidas:
>            WHITE,LIGHT_GRAY,GRAY,DARK_GRAY,BLACK,RED,
>            PINK,ORANGE,YELLOW,GREEN,MAGENTA,CYAN,BLUE
         Ou ainda:
>            tela.sorteiaCor();
      
 Desenhando as formas:
>      tela.linha(x1, y1, x2, y2);
>      tela.retangulo(x, y, largura, altura);
>      tela.circulo(x_centro, y_centro, diametro);

 Exibindo texto:
>      tela.texto(x, y, "Texto a exibir");
