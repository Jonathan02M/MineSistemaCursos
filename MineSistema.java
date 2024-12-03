import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MineSistema {
    public static void main(String[] args) {
        final String url = "jdbc:mysql://localhost:3306/sistemaeventos";
        final String user = "root";
        final String password = "";
        Scanner scanner = new Scanner(System.in);

        int option;

        do {
  
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Organizador");
            System.out.println("2. Cadastrar Participante");
            System.out.println("3. Cadastrar Local");
            System.out.println("4. Cadastrar Evento");
            System.out.println("5. Listar Organizadores");
            System.out.println("6. Listar Participantes");
            System.out.println("7. Listar Locais");
            System.out.println("8. Listar Eventos");
            System.out.println("9. Alterar Participante");
            System.out.println("10. Excluir Participante");
            System.out.println("11. Encerrar Programa");
            System.out.print("Digite uma opção: ");
            option = scanner.nextInt();

            try (Connection con = DriverManager.getConnection(url, user, password)) {
                switch (option) {
                    case 1:
                        cadastrarOrganizador(con, scanner);
                        break;
                    case 2:
                        cadastrarParticipante(con, scanner);
                        break;
                    case 3:
                        cadastrarLocal(con, scanner);
                        break;
                    case 4:
                        cadastrarEvento(con, scanner);
                        break;
                    case 5:
                        listar(con, "Organizador");
                        break;
                    case 6:
                        listar(con, "Participantes");
                        break;
                    case 7:
                        listar(con, "Local");
                        break;
                    case 8:
                        listar(con, "Evento");
                        break;
                    case 9:
                        alterarParticipante(con, scanner);
                        break;
                    case 10:
                        excluirParticipante(con, scanner);
                        break;
                    case 11:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (SQLException e) {
                System.out.println("Erro de conexão: " + e.getMessage());
            }
        } while (option != 11);

        scanner.close();
    }

    // Cadastrar Organizador
    private static void cadastrarOrganizador(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Informe o Email do Organizador: ");
        String email = scanner.next();
        System.out.print("Informe a Notificação do Organizador: ");
        int notificacaoId = scanner.nextInt();

        String queryOrganizador = "INSERT INTO Organizador (email, notificacao_id) VALUES (?, ?)";
        try (PreparedStatement stm = con.prepareStatement(queryOrganizador, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, email);
            stm.setInt(2, notificacaoId);
            if (stm.executeUpdate() > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Organizador cadastrado com ID: " + rs.getInt(1));
                }
            }
        }
    }

    // Cadastrar Participante
    private static void cadastrarParticipante(Connection con, Scanner scanner) throws SQLException {
        System.out.println("Informe o Nome do Participante:");
        String nomeParticipante = scanner.next();
        System.out.print("Informe o Telefone do Participante: ");
        String telefone = scanner.next();
        System.out.print("Informe a Notificação do Participante: ");
        int notificacaoId = scanner.nextInt();

        String queryParticipante = "INSERT INTO Participantes (nomeParticipante, telefone, notificacao_id) VALUES (?, ?, ?)";
        try (PreparedStatement stm = con.prepareStatement(queryParticipante, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, nomeParticipante);
            stm.setString(2, telefone);
            stm.setInt(3, notificacaoId);
            if (stm.executeUpdate() > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Participante cadastrado com ID: " + rs.getInt(1));
                }
            }
        }
    }

    // Cadastrar Local
    private static void cadastrarLocal(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Informe a Descrição do Local: ");
        String descricaoLocal = scanner.next();
        System.out.print("Informe a Quantidade de Vagas: ");
        int vagasLocal = scanner.nextInt();

        String queryLocal = "INSERT INTO Local (descricao, vagas) VALUES (?, ?)";
        try (PreparedStatement stm = con.prepareStatement(queryLocal, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, descricaoLocal);
            stm.setInt(2, vagasLocal);
            if (stm.executeUpdate() > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Local cadastrado com ID: " + rs.getInt(1));
                }
            }
        }
    }

    // Cadastrar Evento
    private static void cadastrarEvento(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Informe a Data do Evento (yyyy-mm-dd): ");
        Date dataEvento = Date.valueOf(scanner.next());
        System.out.print("Informe a Descrição do Evento: ");
        String descricaoEvento = scanner.next();
        System.out.print("Informe a Quantidade de Vagas: ");
        int vagasEvento = scanner.nextInt();

        String queryEvento = "INSERT INTO Evento (data, descricao, vagas) VALUES (?, ?, ?)";
        try (PreparedStatement stm = con.prepareStatement(queryEvento, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stm.setDate(1, dataEvento);
            stm.setString(2, descricaoEvento);
            stm.setInt(3, vagasEvento);
            if (stm.executeUpdate() > 0) {
                ResultSet rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    System.out.println("Evento cadastrado com ID: " + rs.getInt(1));
                }
            }
        }
    }

    // Listar Dados
    private static void listar(Connection con, String tabela) throws SQLException {
        String query = "SELECT * FROM " + tabela;
        try (PreparedStatement stm = con.prepareStatement(query);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getMetaData().getColumnName(i) + ": " + rs.getString(i) + " ");
                }
                System.out.println();
            }
        }
    }

    // Alterar Participante
    private static void alterarParticipante(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Informe o ID do Participante que deseja alterar: ");
        int idParticipante = scanner.nextInt();
        System.out.print("Informe o novo Nome do Participante: ");
        String novoNome = scanner.next();
        System.out.print("Informe o novo Telefone do Participante: ");
        String novoTelefone = scanner.next();

        String query = "UPDATE Participantes SET nomeParticipante = ?, telefone = ? WHERE idParticipantes = ?";
        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setString(1, novoNome);
            stm.setString(2, novoTelefone);
            stm.setInt(3, idParticipante);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Participante atualizado com sucesso.");
            } else {
                System.out.println("Participante não encontrado.");
            }
        }
    }

    // Excluir Participante
    private static void excluirParticipante(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Informe o ID do Participante que deseja excluir: ");
        int idParticipante = scanner.nextInt();

        String query = "DELETE FROM Participantes WHERE idParticipantes = ?";
        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setInt(1, idParticipante);

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Participante excluído com sucesso.");
            } else {
                System.out.println("Participante não encontrado.");
            }
        }
    }
}
