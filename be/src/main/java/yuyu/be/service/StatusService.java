package yuyu.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yuyu.be.models.Status;
import yuyu.be.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {

    StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getStatusList() {
    return statusRepository.findAllStatuses();
    }
}
