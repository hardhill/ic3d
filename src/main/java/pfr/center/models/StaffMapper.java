package pfr.center.models;


import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffMapper implements RowMapper<Staff> {
    public Staff mapRow(ResultSet resultSet, int i) throws SQLException{
        Staff staff = new Staff();
        staff.setId_staff(resultSet.getLong("ID_STAFF"));
        staff.setId_depart(resultSet.getLong("ID_DEPART"));
        staff.setId_otdel(resultSet.getLong("ID_OTDEL"));
        staff.setId_post(resultSet.getLong("ID_POST"));
        staff.setTabel(resultSet.getString("TABEL"));
        staff.setFa(resultSet.getString("FA"));
        staff.setIm(resultSet.getString("IM"));
        staff.setOt(resultSet.getString("OT"));
        staff.setDate_begin(resultSet.getDate("DATE_BEGIN"));
        staff.setDate_end(resultSet.getDate("DATE_END"));
        staff.setPassword(resultSet.getString("PASSWORD"));
        staff.setActive(resultSet.getInt("ACTIVE"));
        return staff;
    }

}
