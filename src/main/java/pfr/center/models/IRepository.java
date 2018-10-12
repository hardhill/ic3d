package pfr.center.models;

import java.sql.Date;
import java.util.List;

public interface IRepository {
    List<Staff> getAllStaff();
    Staff getStaffbyId(Long id);
    List<Department> getAllDepartment();

    ProcessOne getOstatok(int id_depart, Date date);

    ProcessOne getProcessCompl(int id_depart, Date date);

    ProcessOne getProcessNew(int id_depart, Date date);
}
