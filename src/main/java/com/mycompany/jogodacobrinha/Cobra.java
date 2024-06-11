import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Cobra extends ObjetoJogo implements Desenhavel {
    private LinkedList<Segmento> segmentos;
    private int direcao; // 0=Cima, 1=Direita, 2=Baixo, 3=Esquerda
    private boolean viva;
    private int larguraJanela;
    private int alturaJanela;

    public Cobra(int inicioX, int inicioY, int larguraJanela, int alturaJanela) {
        super(inicioX, inicioY);
        this.larguraJanela = larguraJanela;
        this.alturaJanela = alturaJanela;
        segmentos = new LinkedList<>();
        segmentos.add(new Segmento(inicioX, inicioY));
        direcao = 1; // Direção inicial é direita
        viva = true;
    }

    public void mudarDirecao(int novaDirecao) {
        // Garantir que a cobra não possa inverter
        if (Math.abs(direcao - novaDirecao) != 2) {
            direcao = novaDirecao;
        }
    }

    public void mover() {
        if (!viva) {
            return;
        }

        Segmento cabeca = segmentos.getFirst();
        int novoX = cabeca.x;
        int novoY = cabeca.y;

        switch (direcao) {
            case 0: novoY -= 10; break; // Cima
            case 1: novoX += 10; break; // Direita
            case 2: novoY += 10; break; // Baixo
            case 3: novoX -= 10; break; // Esquerda
        }

        // Verificar se a nova posição está fora dos limites da janela
        if (novoX < 0 || novoY < 0 || novoX >= larguraJanela || novoY >= alturaJanela) {
            viva = false;
            return;
        }

        segmentos.addFirst(new Segmento(novoX, novoY));
        segmentos.removeLast();

        // Verificar se a cobra colidiu consigo mesma
        if (colidiuComCorpo()) {
            viva = false;
        }
    }

    public void crescer() {
        Segmento ultimo = segmentos.getLast();
        segmentos.add(new Segmento(ultimo.x, ultimo.y)); 
    // Adicionar segmento na mesma posição do último segmento
    }

    public Segmento getCabeca() {
        return segmentos.getFirst();
    }

    private boolean colidiuComCorpo() {
        Segmento cabeca = getCabeca();
        for (int i = 1; i < segmentos.size(); i++) {
            Segmento segmento = segmentos.get(i);
            if (cabeca.x == segmento.x && cabeca.y == segmento.y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void atualizar() {
        mover();
    }

    @Override
    public void desenhar(Graphics g) {
        if (!viva) {
            g.setColor(Color.RED); // Se a cobra estiver morta, desenhar em vermelho
        } else {
            g.setColor(Color.GREEN);
        }
        for (Segmento segmento : segmentos) {
            g.fillRect(segmento.x, segmento.y, 10, 10);
        }
    }

    public boolean estaViva() {
        return viva;
    }

    public class Segmento {
        int x, y;

        Segmento(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}