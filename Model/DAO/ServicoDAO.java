package Model.DAO;

import Model.Servico;
import java.util.ArrayList;

public class ServicoDAO {

    public void insert(Servico servico) {
        Banco.servico.add(servico);
    }

    public boolean update(Servico servico) {
        for (int i = 0; i < Banco.servico.size(); i++) {
            if (idSaoIguais(Banco.servico.get(i), servico)) {
                Banco.servico.set(i, servico);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Servico servico) {
        for (Servico servicoLista : Banco.servico) {
            if (idSaoIguais(servicoLista, servico)) {
                Banco.servico.remove(servicoLista);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Servico> selectAll() {
        return Banco.servico;
    }

    private boolean idSaoIguais(Servico servico, Servico servicoAComparar) {
        return servico.getId() == servicoAComparar.getId();
    }
}