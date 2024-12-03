public class Organizador {
    
    int idOrganizador;
    String email;
    int notificacao_id;


    public Organizador (int idOrganizador, String email, int notificacao_id){
        this.idOrganizador = idOrganizador;
        this.email = email;
        this.notificacao_id = notificacao_id;
    }

    public String toString() {
        return "Id: " + this.idOrganizador
            + "email:" + this.email
            + "notificacao: " + this.notificacao_id;

    }
}
