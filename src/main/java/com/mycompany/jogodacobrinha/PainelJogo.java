import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelJogo extends JPanel implements ActionListener {
    private Cobra cobra;
    private Comida comida;
    private Timer timer;

   public PainelJogo() {
        setPreferredSize(new Dimension(700, 690));
        setBackground(Color.BLACK);
        cobra = new Cobra(300, 300, 700, 690);
        iniciarJogo();
        timer = new Timer(100, this);
        timer.start();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (cobra.estaViva()) {
                    switch (key) {
                        case KeyEvent.VK_UP:
                            cobra.mudarDirecao(0);
                            break;
                        case KeyEvent.VK_RIGHT:
                            cobra.mudarDirecao(1);
                            break;
                        case KeyEvent.VK_DOWN:
                            cobra.mudarDirecao(2);
                            break;
                        case KeyEvent.VK_LEFT:
                            cobra.mudarDirecao(3);
                            break;
                    }
                } else if (key == KeyEvent.VK_R) {
                    iniciarJogo();
                }
            }
        });
    }

    private void iniciarJogo() {
        cobra = new Cobra(300, 300, 700, 690);
        comida = new Comida(100, 100);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        cobra.desenhar(g);
        comida.desenhar(g);

        if (!cobra.estaViva()) {
            mostrarMensagemGameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cobra.estaViva()) {
            cobra.atualizar();
            verificaSeCobraComeuComida();
        }
        repaint();
    }

    private void verificaSeCobraComeuComida() {
        Cobra.Segmento cabeca = cobra.getCabeca();
        if (cabeca.getX() == comida.getX() && cabeca.getY() == comida.getY()) {
            cobra.crescer();
            comida.mudarPosicao(getWidth(), getHeight());
        }
    }

    private void mostrarMensagemGameOver(Graphics g) {
        String mensagem = "Você Perdeu,que pena! Aperte (R) para reiniciar.";
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        int stringWidth = g.getFontMetrics().stringWidth(mensagem);
        g.drawString(mensagem, (getWidth() - stringWidth) / 2, getHeight() / 2);
    }
}