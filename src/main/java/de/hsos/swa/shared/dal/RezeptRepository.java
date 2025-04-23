package de.hsos.swa.shared.dal;

import de.hsos.swa.shared.Rezept;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.*;

@ApplicationScoped
public class RezeptRepository {

    private Connection conn;

    public RezeptRepository() {
        try {
            this.conn = DriverManager.getConnection("jdbc:derby:mocktaildb;");
            this.conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rezept> findAll() {
        List<Rezept> rezepte = new ArrayList<>();
        String sql = "SELECT * FROM mocktail";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rezept r = new Rezept(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("zutaten"),
                        rs.getString("zubereitung"),
                        rs.getDouble("preis")
                );
                rezepte.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezepte;
    }

    public Optional<Rezept> findById(Long id) {
        String sql = "SELECT * FROM mocktail WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Rezept r = new Rezept(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("zutaten"),
                        rs.getString("zubereitung"),
                        rs.getDouble("preis")
                );
                return Optional.of(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Rezept> findByName(String name) {
        List<Rezept> matching = new ArrayList<>();
        String sql = "SELECT * FROM mocktail WHERE LOWER(name) LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                matching.add(new Rezept(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("zutaten"),
                        rs.getString("zubereitung"),
                        rs.getDouble("preis")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matching;
    }


    public Long create(String name, String zutaten, String zubereitung) {
        String sql = "INSERT INTO mocktail (id, name, zutaten, zubereitung, preis) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            long id = getNextId();
            stmt.setLong(1, id);
            stmt.setString(2, name);
            stmt.setString(3, zutaten);
            stmt.setString(4, zubereitung);
            stmt.setDouble(5, 0.0); // default-Preis, da nicht übergeben

            stmt.executeUpdate();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean delete(Long id) {
        String sql = "DELETE FROM mocktail WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Rezept> update(Long id, String name, String zutaten, String zubereitung) {
        String sql = "UPDATE mocktail SET name = ?, zutaten = ?, zubereitung = ?, preis = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, zutaten);
            stmt.setString(3, zubereitung);
            stmt.setDouble(4, 0.0); // Optional: Standardwert für Preis
            stmt.setLong(5, id);

            int affected = stmt.executeUpdate();
            if (affected > 0) {
                Rezept updated = new Rezept(id, name, zutaten, zubereitung, 0.0);
                return Optional.of(updated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    private long getNextId() throws SQLException {
        // Simple Auto-Increment Replacement
        String sql = "SELECT MAX(id) FROM mocktail";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getLong(1) + 1;
            }
        }
        return 1;
    }
}

