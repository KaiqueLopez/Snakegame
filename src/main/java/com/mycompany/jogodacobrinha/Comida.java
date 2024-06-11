import java.awt.Color;
import java.awt.Graphics;

public class Comida extends ObjetoJogo implements Desenhavel {

    public Comida(int x, int y) {
        super(x, y);
    }

    @Override
    public void atualizar() {
        // Sem lógica de atualização contínua para Comida nesta versão simples
    }

    public void mudarPosicao(int largura, int altura) {
        this.x = (int) (Math.random() * (largura / 10)) * 10;
        this.y = (int) (Math.random() * (altura / 10)) * 10;
    }

    @Override
    public void desenhar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 10, 10);
    }
}