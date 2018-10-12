package pfr.center.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper implements RowMapper<Department> {


    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department district = new Department();
        district.setId_depart(resultSet.getInt("ID_DEPART"));
        district.setName_dep(resultSet.getString("NAME_DEP"));
        district.setName_reg(resultSet.getString("NAME_REG"));
        district.setInfo(resultSet.getString("INFO"));
        return district;
    }
}
