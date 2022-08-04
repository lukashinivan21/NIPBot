package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import tgbots.nipbot.models.Volunteer;
import tgbots.nipbot.repositories.VolunteerRepository;
import tgbots.nipbot.service.by_models.interfaces.VolunteerService;

import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public Volunteer saveVolunteer(Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    public Volunteer updateVolunteer(Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    public Volunteer findVolunteerById(Long id){
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(id);
        return volunteerOptional.orElse(null);
    }

    public void removeVolunteer(Long id){
        volunteerRepository.deleteById(id);
    }

}
