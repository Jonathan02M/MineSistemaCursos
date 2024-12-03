import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Notificacao {

    // Classe interna para notificações ao participante
    public static class NotificacaoParticipante {
        final String url = "jdbc:mysql://localhost:3306/sistemaeventos"; // Localização do banco de dados
        final String user = "root";
        final String password = "";
        Participantes participante = null;
        String nomeParticipante;

        public NotificacaoParticipante(String nomeParticipante) {
            this.nomeParticipante = nomeParticipante;
        }

        public void enviarNotificacao() {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Verificação de participantes...\n");

            try {
                Connection con = DriverManager.getConnection(url, user, password);
                Statement stm = con.createStatement();
                ResultSet sql = stm.executeQuery("SELECT * FROM participantes");
                while(sql.next()) {
                    Participantes participante = new Participantes(
                        sql.getInt("idParticipantes"),
                        sql.getString("telefone"),
                        sql.getInt("notificacao_id"),
                        sql.getString("nomeParticipante")
                    );
                    System.out.println(participante);
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            }

            try {
                Connection con2 = DriverManager.getConnection(url, user, password);
                Statement stm2 = con2.createStatement();

                // Solicitar o ID do participante ao usuário
                System.out.print("\nDigite o ID do participante: ");
                int idParticipantes = scanner.nextInt();

                // Buscar o participante no banco de dados
                String query = "SELECT nomeParticipante FROM participantes WHERE idParticipantes  = " + idParticipantes;
                ResultSet resultSet = stm2.executeQuery(query);

                if (resultSet.next()) {
                    String nomeParticipante = resultSet.getString("nomeParticipante");
                    System.out.println("Notificação enviada ao participante: " + nomeParticipante + " por SMS.");
                } else {
                    System.out.println("Participante com ID " + idParticipantes + " não encontrado.");
                }

                con2.close();
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            }
        }
    }
}