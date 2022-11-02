package Spring.Camilly.dto;

public class ClienteDTO {
    private String endereco;

    public ClienteDTO(){

    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ClienteDTO(String endereco) {
        this.endereco = endereco;
    }
}