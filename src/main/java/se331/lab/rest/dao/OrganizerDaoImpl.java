package se331.lab.rest.dao;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Organizer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository

public class OrganizerDaoImpl implements OrganizerDao{
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder()
                .idorgan(123L)
                .name("Munlika Chaipradit")
                .address("San Pa Tong, Chiang mai")
                .build());
        organizerList.add(Organizer.builder()
                .idorgan(456L)
                .name("Don Luka")
                .address("ToronTo, Canada")
                .build());

        organizerList.add(Organizer.builder()
                .idorgan(789L)
                .name("Kaedehara Kazuha")
                .address("Inazuma")
                .build());
        organizerList.add(Organizer.builder()
                .idorgan(1001L)
                .name("Xiao")
                .address("Wangshu Inn, Liyue")
                .build());
        organizerList.add(Organizer.builder()
                .idorgan(1002L)
                .name("Diluc Ragnvindr")
                .address("Dawn Winerey, Mondstadt")
                .build());
        organizerList.add(Organizer.builder()
                .idorgan(1003L)
                .name("Tartaglia")
                .address("Snezhnaya")
                .build());
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizerList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page -1)*pageSize;
        return organizerList.subList(firstIndex, firstIndex+pageSize);
    }

    @Override
    public Organizer getOrganizer(Long idorgan) {
        return organizerList.stream().filter(event ->
                event.getIdorgan().equals(idorgan)).findFirst().orElse(null);
    }
}
