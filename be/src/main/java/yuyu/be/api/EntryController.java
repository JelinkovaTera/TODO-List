package yuyu.be.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yuyu.be.models.TodoEntry;
import yuyu.be.models.TodoEntryGetModel;
import yuyu.be.service.EntryService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/entries")
public class EntryController {

    public static final Logger log = LoggerFactory.getLogger(EntryController.class);
    private final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping
    public List<TodoEntryGetModel> getEntryList() {
        log.debug("Get Request received.");
        List<TodoEntryGetModel> resultList = entryService.getTODOEntryList();
        log.debug("Response contains " + resultList.size() + " entries.");
        return resultList;
    }

    //New Entry
    @PostMapping
    public TodoEntry saveEntry(@RequestBody TodoEntry todoEntry) {
        log.debug("Post Request received: " + todoEntry.toString());
        TodoEntry result = entryService.saveEntry(todoEntry);
        log.debug("Post finished, result: " + result.toString());
        return result;
    }

    //Modify Entry
    @PutMapping("/{id}")
    public TodoEntry putEntry(@PathVariable("id") int id, @RequestBody TodoEntry todoEntry) {
        todoEntry.setEntry_id(id);
        log.debug("Put Request received: " + todoEntry);
        TodoEntry result = entryService.saveEntry(todoEntry);
        log.debug("Put finished, result: " + result.toString());
        return result;
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable("id") int id) {
        log.debug("Delete Request received. Id: " + id);
        entryService.deleteEntry(id);
    }
}
