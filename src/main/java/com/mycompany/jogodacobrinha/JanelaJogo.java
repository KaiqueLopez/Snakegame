import javax.swing.JFrame;

public class JanelaJogo extends JFrame {
    
    public JanelaJogo() {
        setTitle("Jogo da Cobrinha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720,720);
        setLocationRelativeTo(null);
        
        add(new PainelJogo());
    }
     public static void main(String[] args) {
        JanelaJogo frame = new JanelaJogo();
        frame.setVisible(true);
    }
}