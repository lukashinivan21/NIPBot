package tgbots.nipbot.service.cache;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CacheIdsUsersOnTestPeriod {

    private final Set<Long> idsUsersOnTestPeriod = new HashSet<>();

    public void addId(Long id) {
        idsUsersOnTestPeriod.add(id);
    }

    public void removeId(Long id) {
        idsUsersOnTestPeriod.remove(id);
    }

    public boolean containsId(Long id) {
        return idsUsersOnTestPeriod.contains(id);
    }

}