/**
 * Classe utilizada no exemplo "desenhaExemplo".
 */

package teladesenho;

import java.awt.Color;

public class EventosUsuario implements TelaEventos{
    
    @Override
    public void usuarioClicouMouse(Tela tela, int x, int y) {
        //System.out.printf("Usuario clicou o mouse em (%d,%d)\n", x, y);
        tela.corAtual(Color.RED);
        tela.circulo(x, y, 30);
    }

    @Override
    public void usuarioArrastouMouse(Tela tela, int x, int y) {
        //System.out.printf("Usuario moveu o mouse para (%d,%d)\n", x, y);
        tela.corAtual(Color.LIGHT_GRAY);
        tela.circulo(x, y, 10);
    }
}
