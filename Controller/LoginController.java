package Controller;

import Controller.Helper.LoginHelper;
import Model.DAO.Banco;
import Model.DAO.UsuarioDAO;
import Model.Usuario;
import View.Login;
import View.MenuPrincipal;
import java.text.ParseException;
import javax.swing.JOptionPane;

public class LoginController {

    private final Login view;
    private final LoginHelper helper;

    public LoginController(Login view) {
        this.view = view;
        this.helper = new LoginHelper(view);
    }

    public void entrarNoSistema() {
        if (Banco.usuario == null) {
            try {
                Banco.inicia();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        Usuario usuario = helper.obterModelo();
        Usuario autenticado = new UsuarioDAO().selectPorNomeESenha(usuario);

        if (autenticado != null) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Usuário ou senha inválidos");
            this.helper.limparTela();
        }
    }
}