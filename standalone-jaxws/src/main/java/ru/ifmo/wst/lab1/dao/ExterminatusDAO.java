package ru.ifmo.wst.lab1.dao;

import lombok.RequiredArgsConstructor;
import ru.ifmo.wst.lab1.model.ExterminatusEntity;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ExterminatusDAO {
    private final DataSource dataSource;

    public List<ExterminatusEntity> findAll() throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        statement.execute("SELECT id, initiator, reason, method, planet, date FROM exterminatus");
        ResultSet rs = statement.getResultSet();
        List<ExterminatusEntity> result = new ArrayList<>();
        while (rs.next()) {
            result.add(resultSetToEntity(rs));
        }
        return result;
    }

    private ExterminatusEntity resultSetToEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String initiator = rs.getString("initiator");
        String reason = rs.getString("reason");
        String method = rs.getString("method");
        String planet = rs.getString("planet");
        Date date = rs.getDate("date");
        return new ExterminatusEntity(id, initiator, reason, method, planet, date);
    }
}
