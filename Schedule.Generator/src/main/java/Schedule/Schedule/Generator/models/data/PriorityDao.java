package Schedule.Schedule.Generator.models.data;

import Schedule.Schedule.Generator.models.Priority;
import Schedule.Schedule.Generator.models.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PriorityDao extends CrudRepository<Priority, Integer> {
}

