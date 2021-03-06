package ru.ifmo.wst.lab1.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.ifmo.wst.lab1.model.ExterminatusEntity;
import ru.ifmo.wst.lab1.db.DefaultCondition;
import ru.ifmo.wst.lab1.db.IgnoreCaseContainsCondition;
import ru.ifmo.wst.lab1.db.Query;
import ru.ifmo.wst.lab1.db.QueryBuilder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class ExterminatusDAO {

    private final String TABLE_NAME = "exterminatus";
    private final String ID_COLUMN = "id";
    private final String INITIATOR_COLUMN = "initiator";
    private final String REASON_COLUMN = "reason";
    private final String METHOD_COLUMN = "method";
    private final String PLANET_COLUMN = "planet";
    private final String DATE_COLUMN = "date";

    private final DataSource dataSource;

    public List<ExterminatusEntity> findAll() throws SQLException {
        log.debug("Find all query");
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT id, initiator, reason, method, planet, date FROM exterminatus");
            List<ExterminatusEntity> result = rsToEntities(statement.getResultSet());
            return result;
        }

    }

    public List<ExterminatusEntity> filter(Long id, String initiator, String reason, String method, String planet, Date date) throws SQLException {
        log.debug("Filter with args: {} {} {} {} {} {}", id, initiator, reason, method, planet, date);
        if (Stream.of(id, initiator, reason, method, planet, date).allMatch(Objects::isNull)) {
            log.debug("Args are empty");
            return findAll();
        }
        Query query = new QueryBuilder()
                .tableName(TABLE_NAME)
                .selectColumns(ID_COLUMN, INITIATOR_COLUMN, REASON_COLUMN, METHOD_COLUMN, PLANET_COLUMN, DATE_COLUMN)
                .condition(DefaultCondition.defaultCondition(ID_COLUMN, id, Long.class))
                .condition(new IgnoreCaseContainsCondition(INITIATOR_COLUMN, initiator))
                .condition(new IgnoreCaseContainsCondition(REASON_COLUMN, reason))
                .condition(new IgnoreCaseContainsCondition(METHOD_COLUMN, method))
                .condition(new IgnoreCaseContainsCondition(PLANET_COLUMN, planet))
                .condition(DefaultCondition.defaultCondition(DATE_COLUMN, date, Date.class))
                .buildPreparedStatementQuery();
        log.debug("Built query {}", query.getQueryString());
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query.getQueryString());
            query.initPreparedStatement(ps);
            ResultSet rs = ps.executeQuery();
            return rsToEntities(rs);
        }

    }

    private List<ExterminatusEntity> rsToEntities(ResultSet rs) throws SQLException {
        List<ExterminatusEntity> result = new ArrayList<>();
        while (rs.next()) {
            result.add(resultSetToEntity(rs));
        }
        log.debug("Result set was converted to entity list {}", result);
        return result;
    }

    private ExterminatusEntity resultSetToEntity(ResultSet rs) throws SQLException {
        long id = rs.getLong(ID_COLUMN);
        String initiator = rs.getString(INITIATOR_COLUMN);
        String reason = rs.getString(REASON_COLUMN);
        String method = rs.getString(METHOD_COLUMN);
        String planet = rs.getString(PLANET_COLUMN);
        Date date = rs.getDate(DATE_COLUMN);
        return new ExterminatusEntity(id, initiator, reason, method, planet, date);
    }
}
