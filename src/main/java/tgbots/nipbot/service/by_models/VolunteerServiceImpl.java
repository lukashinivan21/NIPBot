package tgbots.nipbot.service.by_models;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import tgbots.nipbot.models.Volunteer;
import tgbots.nipbot.repositories.VolunteerRepository;
import tgbots.nipbot.service.by_models.interfaces.VolunteerService;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer saveVolunteer(Volunteer volunteer){
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(volunteer.getId());
        if(volunteerOptional.isEmpty()){
            return volunteerRepository.save(volunteer);
        }
        throw new EntityExistsException();
    }

    @Override
    public Volunteer updateVolunteer(Volunteer volunteer){
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(volunteer.getId());
        if(volunteerOptional.isPresent()){
            if(volunteer.getSecondName() == null){
                volunteer.setSecondName(volunteerOptional.get().getSecondName());
            }
            if(volunteer.getPassword() == null){
                volunteer.setPassword(volunteerOptional.get().getPassword());
            }
            volunteer.setUsername(volunteerOptional.get().getUsername());
            return volunteerRepository.save(volunteer);
        }
        throw new NotFoundException(volunteer + " Not found");
    }

    @Override
    public Volunteer findVolunteerById(Long id){
        Optional<Volunteer> volunteerOptional = volunteerRepository.findById(id);
        return volunteerOptional.orElse(null);
    }

    @Override
    public void removeVolunteer(Long id){
        volunteerRepository.deleteById(id);
    }

    @Override
    public List<Volunteer> findAll(){
        return volunteerRepository.findAll();
    }

}
