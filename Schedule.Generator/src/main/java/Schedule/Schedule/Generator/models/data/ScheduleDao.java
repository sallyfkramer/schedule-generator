package Schedule.Schedule.Generator.models.data;

import Schedule.Schedule.Generator.models.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ScheduleDao extends CrudRepository<Schedule, Integer> {
}
