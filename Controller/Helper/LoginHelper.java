package Controller.Helper;

import Model.Usuario;
import View.Login;

public class LoginHelper implements IHelper {

    private final Login view;

    public LoginHelper(Login view) {
        this.view = view;
    }

    @Override
    public Usuario obterModelo() {
        String nome = view.getTextUsuario().getText();
        String senha = view.getTextSenha().getText();
        return new Usuario(0, nome, senha);
    }

    public void setarModelo(Usuario modelo) {
        view.getTextUsuario().setText(modelo.getNome());
        view.getTextSenha().setText(modelo.getSenha());
    }

    @Override
    public void limparTela() {
        view.getTextUsuario().setText("");
        view.getTextSenha().setText("");
    }
}