package Model.DAO;

import Model.Agendamento;
import java.util.ArrayList;

public class AgendamentoDAO {

    public void insert(Agendamento agendamento) {
        if (agendamento.getId() == 0) {
            agendamento.setId(proximoId());
            Banco.agendamento.add(agendamento);
        }
    }

    public boolean update(Agendamento agendamento) {
        for (int i = 0; i < Banco.agendamento.size(); i++) {
            if (idSaoIguais(Banco.agendamento.get(i), agendamento)) {
                Banco.agendamento.set(i, agendamento);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Agendamento agendamento) {
        for (Agendamento agendamentoLista : Banco.agendamento) {
            if (idSaoIguais(agendamentoLista, agendamento)) {
                Banco.agendamento.remove(agendamentoLista);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Agendamento> selectAll() {
        return Banco.agendamento;
    }

    private boolean idSaoIguais(Agendamento agendamento, Agendamento agendamentoAComparar) {
        return agendamento.getId() == agendamentoAComparar.getId();
    }

    private int proximoId() {
        int maiorId = 0;
        for (Agendamento agendamento : Banco.agendamento) {
            int id = agendamento.getId();
            if (maiorId < id) {
                maiorId = id;
            }
        }
        return maiorId + 1;
    }
}