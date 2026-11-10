package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agendamento {

    private int id;
    private Cliente cliente;
    private Servico servico;
    private float valor;
    private Date data;
    private String hora;
    private String observacao;

    public Agendamento(int id, Cliente cliente, Servico servico, float valor, String data) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
        this.data = interpretarData(data);
        if (data != null && data.contains(" ")) {
            this.hora = data.substring(data.indexOf(' ') + 1).trim();
        }
    }

    public Agendamento(int id, Cliente cliente, Servico servico, float valor, String data, String hora, String observacao) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
        this.data = interpretarData(data);
        this.hora = hora;
        this.observacao = observacao;
    }

    private Date interpretarData(String data) {
        if (data == null || data.trim().isEmpty()) {
            return null;
        }
        String formato = data.contains(":") ? "dd/MM/yyyy HH:mm" : "dd/MM/yyyy";
        SimpleDateFormat formatador = new SimpleDateFormat(formato);
        formatador.setLenient(false);
        try {
            return formatador.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Agendamento.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getDataFormatada() {
        if (this.data == null) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(this.data);
    }

    public String getHoraFormatada() {
        if (this.hora != null && !this.hora.trim().isEmpty()) {
            return this.hora;
        }
        if (this.data == null) {
            return "";
        }
        return new SimpleDateFormat("HH:mm").format(this.data);
    }
}