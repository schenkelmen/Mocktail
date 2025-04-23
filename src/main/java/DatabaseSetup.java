import java.sql.*;

public class DatabaseSetup {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:mocktaildb;create=true")) {
            Statement stmt = conn.createStatement();

            // Tabelle erstellen (einmalig)
            try {
                stmt.executeUpdate("""
                    CREATE TABLE mocktail (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(100),
                        zutaten VARCHAR(500),
                        zubereitung VARCHAR(1000),
                        preis DECIMAL(5,2)
                    )
                """);
                System.out.println("Tabelle 'mocktail' wurde erstellt.");
            } catch (SQLException e) {
                System.out.println("Tabelle existiert bereits oder konnte nicht erstellt werden: " + e.getMessage());
            }

            // Einträge hinzufügen
            PreparedStatement insertStmt = conn.prepareStatement("""
                INSERT INTO mocktail (id, name, zutaten, zubereitung, preis)
                VALUES (?, ?, ?, ?, ?)
            """);

            Object[][] mocktails = {
                    {1L, "Virgin Mojito", "Minze, Limette, Zucker, Sodawasser", "Minzblätter andrücken, Limettensaft und Zucker zugeben, mit Soda auffüllen.", 4.50},
                    {2L, "Sunset Cooler", "Orangensaft, Grenadine, Zitronensaft", "Orangensaft mit Zitronensaft verrühren, Grenadine langsam einfließen lassen.", 4.00},
                    {3L, "Coconut Kiss", "Ananassaft, Kokosmilch, Sahne", "Alles im Shaker mit Eis mixen und in ein Glas abseihen.", 4.80}
            };

            for (Object[] mocktail : mocktails) {
                insertStmt.setLong(1, (Long) mocktail[0]);
                insertStmt.setString(2, (String) mocktail[1]);
                insertStmt.setString(3, (String) mocktail[2]);
                insertStmt.setString(4, (String) mocktail[3]);
                insertStmt.setBigDecimal(5, new java.math.BigDecimal(mocktail[4].toString()));
                try {
                    insertStmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Eintrag konnte nicht hinzugefügt werden (evtl. bereits vorhanden): " + e.getMessage());
                }
            }

            System.out.println("Mocktail-Daten wurden eingefügt.");
        } catch (SQLException e) {
            System.out.println("Fehler bei der Datenbankverbindung oder beim Einfügen: " + e.getMessage());
        }
    }
}
