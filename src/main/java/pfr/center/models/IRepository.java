package pfr.center.models;

import java.sql.Date;
import java.util.List;

public interface IRepository {
    List<Staff> getAllStaff();
    Staff getStaffbyId(Long id);
    List<Department> getAllDepartment();

    ProcessIn getOstatok(int id_depart, Date date);

    ProcessIn getProcessCompl(int id_depart, Date date);

    ProcessIn getProcessNew(int id_depart, Date date);
}
