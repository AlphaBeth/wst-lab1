package ru.ifmo.wst.lab1.ws;

import ru.ifmo.wst.lab1.dao.ExterminatusDAO;
import ru.ifmo.wst.lab1.model.ExterminatusEntity;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService
public class ExterminatusService {
    private ExterminatusDAO exterminatusDAO;

    @WebMethod
    public List<ExterminatusEntity> findAll() throws SQLException {
        return exterminatusDAO.findAll();
    }

    public ExterminatusService(ExterminatusDAO exterminatusDAO) {
        this.exterminatusDAO = exterminatusDAO;
    }

    public ExterminatusService() {
    }
}
