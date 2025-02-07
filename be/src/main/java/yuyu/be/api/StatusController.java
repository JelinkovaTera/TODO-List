package yuyu.be.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuyu.be.models.Status;
import yuyu.be.service.StatusService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/statuses")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<Status> getStatuses() {
        return statusService.getStatusList();
    }
}
