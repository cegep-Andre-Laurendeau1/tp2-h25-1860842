package ca.cal.tp2.repository;

import java.sql.*;

public abstract class RepositoryParentJDBC {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql:tp2";
    static final String USER = "1860842";
    static final String PASS = "secret";
    static Connection conn = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE CLIENT " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);

            // Création de la table EmpruntDetail
            sql = "CREATE TABLE EmpruntDetail " +
                    "(id INTEGER not NULL, " +
                    " dateRetourPrevue DATE, " +
                    " dateRetourActuelle DATE, " +
                    " status VARCHAR(255), " +
                    " PRIMARY KEY ( lineItemID ))";
            statement.executeUpdate(sql);

            // Création de la table Document
            sql = "CREATE TABLE Document " +
                    "(documentID INTEGER not NULL, " +
                    " titre VARCHAR(255) not NULL, " +
                    " nbExemplaires INTEGER, " +
                    " PRIMARY KEY ( documentID ))";
            statement.executeUpdate(sql);

            // Création de la table Livre
            sql = "CREATE TABLE Livre " +
                    "(id INTEGER not NULL, " +
                    " documentId INTEGER not NULL, " +
                    " nbExemplaires INTEGER, " +
                    " titre VARCHAR(255), " +
                    " auteur VARCHAR(255), " +
                    " PRIMARY KEY ( id ), + " +
                    " FOREIGN KEY (documentID) REFERENCES Document(documentID))";
            statement.executeUpdate(sql);

            // Création de la table CD
            sql = "CREATE TABLE CD " +
                    "(id INTEGER not NULL, " +
                    " titre VARCHAR(255), " +
                    " artiste VARCHAR(255), "+
                    " duration INTEGER, " +
                    " genre VARCHAR(255),  " +
                    " nbExemplaires INTEGER) +" +
                    " PRIMARY KEY ( id ), " +
                    " FOREIGN KEY (documentID) REFERENCES Document(documentID))";
            statement.executeUpdate(sql);

            // Création de la table DVD
            sql = "CREATE TABLE DVD " +
                    "(id INTEGER not NULL, " +
                    " titre VARCHAR(255), " +
                    " director VARCHAR(255), "+
                    " duree INTEGER, " +
                    " rating VARCHAR(255),  " +
                    " nbExemplaires INTEGER), " +
                    " PRIMARY KEY ( id ), " +
                    " FOREIGN KEY (documentID) REFERENCES Document(documentID))";
            statement.executeUpdate(sql);

            // Création de la table Prepose
            sql = "CREATE TABLE Prepose " +
                    "(preposeID INTEGER not NULL, " +
                    " nom VARCHAR(255), " +
                    " prenom VARCHAR(255), " +
                    " PRIMARY KEY ( preposeID ))";
            statement.executeUpdate(sql);

            // Création de la table Emprunteur
            sql = "CREATE TABLE Emprunteur " +
                    "(preposeID INTEGER not NULL, " +
                    " nom VARCHAR(255), " +
                    " prenom VARCHAR(255), " +
                    " PRIMARY KEY ( preposeID ))";
            statement.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected <T> void executePreparedStatement(T t) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(getSqlRequest());) {
            getPreparedStatementParameters(t, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String getSqlRequest();
    protected abstract <T> void getPreparedStatementParameters(T t,
                                                               PreparedStatement preparedStatement)
                                                                throws SQLException;
}
