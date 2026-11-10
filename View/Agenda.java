package View;

import Controller.AgendaController;
import Model.DAO.Banco;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Agenda extends JFrame {

    private final AgendaController controller;

    private JTextField textId;
    private JComboBox comboCliente;
    private JComboBox comboServico;
    private JTextField textValor;
    private JTextField textData;
    private JTextField textHora;
    private JTextArea textObservacao;
    private JTable tabela;
    private JButton botaoAgendar;

    public Agenda() {
        super("Barbearia");
        Aparencia.aplicar();
        prepararBanco();
        montarTela();
        controller = new AgendaController(this);
        iniciar();
    }

    private void prepararBanco() {
        if (Banco.agendamento == null) {
            try {
                Banco.inicia();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void montarTela() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1040, 720);
        setMinimumSize(new Dimension(860, 600));
        setLocationRelativeTo(null);

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(Aparencia.FUNDO);
        setContentPane(raiz);

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(Aparencia.CARTAO);
        cabecalho.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Aparencia.DOURADO),
                new EmptyBorder(20, 24, 16, 24)));

        JLabel titulo = new JLabel("Agenda");
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(Aparencia.CREME);
        JLabel subtitulo = new JLabel("Agendamentos, clientes e serviços do banco");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitulo.setForeground(Aparencia.MUTED);

        JPanel textos = new JPanel(new BorderLayout(0, 4));
        textos.setOpaque(false);
        textos.add(titulo, BorderLayout.NORTH);
        textos.add(subtitulo, BorderLayout.SOUTH);
        cabecalho.add(textos, BorderLayout.WEST);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Aparencia.FUNDO);
        formulario.setBorder(new EmptyBorder(16, 24, 8, 24));

        textId = new JTextField("0");
        comboCliente = new JComboBox();
        comboServico = new JComboBox();
        textValor = new JTextField();
        textData = new JTextField();
        textHora = new JTextField();
        textObservacao = new JTextArea(4, 20);
        botaoAgendar = Aparencia.botaoPrincipal("Agendar");

        Aparencia.estilizarCampo(textId);
        Aparencia.estilizarCampo(textValor);
        Aparencia.estilizarCampo(textData);
        Aparencia.estilizarCampo(textHora);
        estilizarCombo(comboCliente);
        estilizarCombo(comboServico);
        textObservacao.setBackground(Aparencia.CAMPO);
        textObservacao.setForeground(Aparencia.CREME);
        textObservacao.setCaretColor(Aparencia.DOURADO);
        textObservacao.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textObservacao.setLineWrap(true);
        textObservacao.setWrapStyleWord(true);
        textObservacao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Aparencia.LINHA),
                new EmptyBorder(8, 10, 8, 10)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        adicionarCampo(formulario, gbc, 0, "Id", textId);
        adicionarCampo(formulario, gbc, 1, "Cliente", comboCliente);
        adicionarCampo(formulario, gbc, 2, "Serviço", comboServico);
        adicionarCampo(formulario, gbc, 3, "Valor R$", textValor);
        adicionarCampo(formulario, gbc, 4, "Data", textData);
        adicionarCampo(formulario, gbc, 5, "Hora", textHora);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;
        JLabel labelObs = criarRotulo("Observação");
        formulario.add(labelObs, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane scrollObs = new JScrollPane(textObservacao);
        scrollObs.setBorder(BorderFactory.createEmptyBorder());
        formulario.add(scrollObs, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        formulario.add(botaoAgendar, gbc);

        String[] colunas = {"Id", "Cliente", "Serviço", "Valor", "Data", "Hora", "Observação"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modelo);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.setRowHeight(32);
        tabela.setBackground(Aparencia.FUNDO);
        tabela.setForeground(Aparencia.CREME);
        tabela.setGridColor(Aparencia.LINHA);
        tabela.setSelectionBackground(Aparencia.SELECAO);
        tabela.setSelectionForeground(Aparencia.CREME);
        tabela.setFillsViewportHeight(true);
        tabela.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel celula = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                celula.setBackground(Aparencia.CARTAO);
                celula.setForeground(Aparencia.DOURADO);
                celula.setFont(new Font("SansSerif", Font.BOLD, 13));
                celula.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 1, Aparencia.LINHA),
                        new EmptyBorder(8, 8, 8, 8)));
                celula.setOpaque(true);
                return celula;
            }
        });

        JScrollPane rolagem = new JScrollPane(tabela);
        rolagem.setBorder(BorderFactory.createEmptyBorder());
        rolagem.getViewport().setBackground(Aparencia.FUNDO);

        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBackground(Aparencia.FUNDO);
        rodape.setBorder(new EmptyBorder(12, 24, 16, 24));
        JButton voltar = Aparencia.botaoSecundario("Voltar ao menu");
        voltar.addActionListener(e -> dispose());
        rodape.add(voltar, BorderLayout.EAST);

        comboServico.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                controller.atualizaValor();
            }
        });

        botaoAgendar.addActionListener(e -> controller.agendar());

        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(rolagem, BorderLayout.CENTER);

        raiz.add(cabecalho, BorderLayout.NORTH);
        raiz.add(centro, BorderLayout.CENTER);
        raiz.add(rodape, BorderLayout.SOUTH);
    }

    private void adicionarCampo(JPanel painel, GridBagConstraints gbc, int linha, String titulo, Component campo) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        painel.add(criarRotulo(titulo), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(campo, gbc);
    }

    private JLabel criarRotulo(String texto) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setForeground(Aparencia.DOURADO);
        rotulo.setFont(new Font("SansSerif", Font.BOLD, 13));
        return rotulo;
    }

    private void estilizarCombo(JComboBox combo) {
        combo.setBackground(Aparencia.CAMPO);
        combo.setForeground(Aparencia.CREME);
        combo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        combo.setPreferredSize(new Dimension(300, 42));
    }

    private void iniciar() {
        this.controller.atualizaTabela();
        this.controller.atualizaCliente();
        this.controller.atualizaServico();
        this.controller.atualizaValor();
    }

    public JTable getTabela() {
        return tabela;
    }

    public JComboBox getComboCliente() {
        return comboCliente;
    }

    public JComboBox getComboServico() {
        return comboServico;
    }

    public JTextField getTextValor() {
        return textValor;
    }

    public JTextField getTextId() {
        return textId;
    }

    public JTextField getTextData() {
        return textData;
    }

    public JTextField getTextHora() {
        return textHora;
    }

    public JTextArea getTextObservacao() {
        return textObservacao;
    }
}
