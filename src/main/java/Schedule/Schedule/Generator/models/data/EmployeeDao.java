package Schedule.Schedule.Generator.models.data;


import Schedule.Schedule.Generator.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeDao  extends CrudRepository<Employee, Integer> {
}
