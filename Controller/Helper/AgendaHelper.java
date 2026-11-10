package Controller.Helper;

import Model.Agendamento;
import Model.Cliente;
import Model.Servico;
import View.Agenda;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class AgendaHelper implements IHelper {

    private final Agenda view;

    public AgendaHelper(Agenda view) {
        this.view = view;
    }

    public void preencherTabela(ArrayList<Agendamento> agendamentos) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTabela().getModel();
        tableModel.setNumRows(0);

        for (Agendamento agendamento : agendamentos) {
            tableModel.addRow(new Object[]{
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getServico().getDescricao(),
                agendamento.getValor(),
                agendamento.getDataFormatada(),
                agendamento.getHoraFormatada(),
                agendamento.getObservacao()
            });
        }
    }

    public void preencherClientes(ArrayList<Cliente> clientes) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getComboCliente().getModel();
        comboBoxModel.removeAllElements();
        for (Cliente cliente : clientes) {
            comboBoxModel.addElement(cliente);
        }
    }

    public void preencherServicos(ArrayList<Servico> servicos) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getComboServico().getModel();
        comboBoxModel.removeAllElements();
        for (Servico servico : servicos) {
            comboBoxModel.addElement(servico);
        }
    }

    public Cliente obterCliente() {
        return (Cliente) view.getComboCliente().getSelectedItem();
    }

    public Servico obterServico() {
        return (Servico) view.getComboServico().getSelectedItem();
    }

    public void setarValor(float valor) {
        view.getTextValor().setText(Float.toString(valor));
    }

    @Override
    public Object obterModelo() {
        String idString = view.getTextId().getText();
        int id = idString == null || idString.trim().isEmpty() ? 0 : Integer.parseInt(idString.trim());

        Cliente cliente = obterCliente();
        Servico servico = obterServico();
        float valor = Float.parseFloat(view.getTextValor().getText());
        String data = view.getTextData().getText();
        String hora = view.getTextHora().getText();
        String observacao = view.getTextObservacao().getText();

        return new Agendamento(id, cliente, servico, valor, data, hora, observacao);
    }

    @Override
    public void limparTela() {
        view.getTextId().setText("0");
        view.getTextData().setText("");
        view.getTextHora().setText("");
        view.getTextObservacao().setText("");
    }
}
