package ca.cal.tp2.repository;

import ca.cal.tp2.modele.Emprunt;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmpruntDetailRepositoryJPA extends RepositoryParentJDBC implements EmpruntDetailRepository{

    @Override
    protected String getSqlRequest() {
        return "";
    }

    @Override
    protected <T> void getPreparedStatementParameters(T t, PreparedStatement preparedStatement) throws SQLException {
    }

    @Override
    public Emprunt getEmprunt() {
        return null;
    }
}
