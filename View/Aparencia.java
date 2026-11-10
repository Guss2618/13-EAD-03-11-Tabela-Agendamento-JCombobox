package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public final class Aparencia {

    public static final Color FUNDO = new Color(28, 25, 23);
    public static final Color CARTAO = new Color(41, 37, 36);
    public static final Color DOURADO = new Color(214, 163, 92);
    public static final Color DOURADO_ESCURO = new Color(184, 132, 62);
    public static final Color CREME = new Color(250, 247, 242);
    public static final Color MUTED = new Color(168, 162, 158);
    public static final Color CAMPO = new Color(23, 20, 18);
    public static final Color LINHA = new Color(68, 64, 60);
    public static final Color SELECAO = new Color(92, 74, 46);

    private Aparencia() {
    }

    public static void aplicar() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println("Look and feel padrão indisponível.");
        }
    }

    public static JButton botaoPrincipal(String texto) {
        final JButton botao = new JButton(texto);
        botao.setFont(new Font("SansSerif", Font.BOLD, 15));
        botao.setBackground(DOURADO);
        botao.setForeground(new Color(28, 25, 23));
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setBorder(new EmptyBorder(12, 18, 12, 18));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(DOURADO_ESCURO);
            }

            public void mouseExited(MouseEvent e) {
                botao.setBackground(DOURADO);
            }
        });
        return botao;
    }

    public static JButton botaoSecundario(String texto) {
        final JButton botao = new JButton(texto);
        botao.setFont(new Font("SansSerif", Font.PLAIN, 14));
        botao.setBackground(CARTAO);
        botao.setForeground(CREME);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINHA),
                new EmptyBorder(10, 16, 10, 16)));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    public static void estilizarCampo(JTextField campo) {
        campo.setBackground(CAMPO);
        campo.setForeground(CREME);
        campo.setCaretColor(DOURADO);
        campo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINHA),
                new EmptyBorder(10, 12, 10, 12)));
        campo.setPreferredSize(new Dimension(300, 42));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
    }
}