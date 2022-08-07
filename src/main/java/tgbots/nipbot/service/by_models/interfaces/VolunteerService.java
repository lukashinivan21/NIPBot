package tgbots.nipbot.service.by_models.interfaces;

import tgbots.nipbot.models.Volunteer;

import java.util.List;

public interface VolunteerService {

    public Volunteer saveVolunteer(Volunteer volunteer);

    public Volunteer updateVolunteer(Volunteer volunteer);

    public Volunteer findVolunteerById(Long id);

    public void removeVolunteer(Long id);

    public List<Volunteer> findAll();
}
