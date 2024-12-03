public class Participantes {
        int idParticipantes;
        String telefone;
        int notificacao_id;
        String nomeParticipante;
        final String url = "jdbc:mysql://localhost:3306/sistemaeventos";
        final String user = "root";
        final String password = "";
    
        public Participantes (int idParticipantes, String telefone, int notificacao_id, String nomeParticipante){
            this.idParticipantes = idParticipantes;
            this.telefone = telefone;
            this.notificacao_id = notificacao_id;
            this.nomeParticipante = nomeParticipante;
        }

        public String toString() {
            return "Id: " + this.idParticipantes
                + "\nNome do Participante: " + this.nomeParticipante
                + "\ntelefone: " + this.telefone
                + "\nnotificacao:" + this.notificacao_id
                + "\n===============";
        }
}

