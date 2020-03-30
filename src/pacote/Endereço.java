package pacote;

public class Endereço {
    private String rua;
    private String bairro;
    private String cidade;
    private int numero;

    public Endereço(String rua, String bairro, String cidade, int numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    @Override
    public String toString(){
        return rua + ", " + numero + "°, " + bairro;
    }
}
