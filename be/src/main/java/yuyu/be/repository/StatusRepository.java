package yuyu.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yuyu.be.models.Status;

import java.util.List;

@Repository
public interface StatusRepository extends CrudRepository<Status, Integer> {
    @Query("SELECT s FROM Status s")
    List<Status> findAllStatuses();
}
