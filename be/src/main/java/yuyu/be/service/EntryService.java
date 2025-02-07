package yuyu.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuyu.be.models.TodoEntry;
import yuyu.be.models.TodoEntryGetModel;
import yuyu.be.repository.TODOEntryRepository;

import java.util.List;

@Service
public class EntryService {

    TODOEntryRepository todoEntryRepository;

    @Autowired
    public EntryService(TODOEntryRepository todoEntryRepository) {
        this.todoEntryRepository = todoEntryRepository;
    }

    public List<TodoEntryGetModel> getTODOEntryList() {
        return todoEntryRepository.findAllEntries();
    }

    public TodoEntry saveEntry(TodoEntry todoEntry) {
        return todoEntryRepository.save(todoEntry);
    }

    public void deleteEntry(int id) {
        todoEntryRepository.deleteById(id);
    }
}
