package Schedule.Schedule.Generator.models.data;

import Schedule.Schedule.Generator.models.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ShiftDao extends CrudRepository<Shift, Integer> {
}
