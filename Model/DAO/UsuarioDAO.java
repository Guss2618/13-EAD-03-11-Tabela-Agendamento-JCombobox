package Model.DAO;

import Model.Usuario;
import java.util.ArrayList;

public class UsuarioDAO {

    public void insert(Usuario usuario) {
        Banco.usuario.add(usuario);
    }

    public boolean update(Usuario usuario) {
        for (int i = 0; i < Banco.usuario.size(); i++) {
            if (idSaoIguais(Banco.usuario.get(i), usuario)) {
                Banco.usuario.set(i, usuario);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Usuario usuario) {
        for (Usuario usuarioLista : Banco.usuario) {
            if (idSaoIguais(usuarioLista, usuario)) {
                Banco.usuario.remove(usuarioLista);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Usuario> selectAll() {
        return Banco.usuario;
    }

    public Usuario selectPorNomeESenha(Usuario usuario) {
        for (Usuario usuarioLista : Banco.usuario) {
            if (nomeESenhaSaoIguais(usuarioLista, usuario)) {
                return usuarioLista;
            }
        }
        return null;
    }

    private boolean nomeESenhaSaoIguais(Usuario usuario, Usuario usuarioAPesquisar) {
        return usuario.getNome().equals(usuarioAPesquisar.getNome()) && usuario.getSenha().equals(usuarioAPesquisar.getSenha());
    }

    private boolean idSaoIguais(Usuario usuario, Usuario usuarioAComparar) {
        return usuario.getId() == usuarioAComparar.getId();
    }
}