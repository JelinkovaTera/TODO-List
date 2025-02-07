package yuyu.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import yuyu.be.models.TodoEntry;
import yuyu.be.models.TodoEntryGetModel;

import java.util.List;

@Repository
public interface TODOEntryRepository extends CrudRepository<TodoEntry, Integer> {
    @Query("SELECT t FROM TodoEntryGetModel t")
    List<TodoEntryGetModel> findAllEntries();
}
