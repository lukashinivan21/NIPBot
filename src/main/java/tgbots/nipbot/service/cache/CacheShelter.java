package tgbots.nipbot.service.cache;

import org.springframework.stereotype.Service;
import tgbots.nipbot.constants.Shelter;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheShelter {

    private final Map<Long, Shelter> shelterMap;

    public CacheShelter() {
        this.shelterMap = new HashMap<>();
    }

    public void addUpdateShelter(Long id, Shelter shelter){
        shelterMap.put(id, shelter);
    }

    public Shelter getShelter(Long id){
        return shelterMap.get(id);
    }

    public void removeShelter(Long id){
        shelterMap.remove(id);
    }

    public Map<Long, Shelter> getShelterMap() {
        return shelterMap;
    }
}
