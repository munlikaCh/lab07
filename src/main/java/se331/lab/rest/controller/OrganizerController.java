package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.service.OrganizerService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller

public class OrganizerController {
    //    List<Event> eventList;
//
//    @PostConstruct
//    public void init(){
//        eventList = new ArrayList<>();
//        eventList.add(Event.builder()
//                .id(123L)
//                .category("animal welfare")
//                .title("Cat Adoption Day")
//                .description("Find your new feline friend at this event.")
//                .location("Meow Town")
//                .date("January 28, 2022")
//                .time("12:00")
//                .petAllowed(true)
//                .organizer("Kat Laydee")
//                .build());
//        eventList.add(Event.builder()
//                .id(456L)
//                .category("food")
//                .title("Community Gardening")
//                .description("Join us as we tend to the community edible plants.")
//                .location("Flora City")
//                .date("March 14, 2022")
//                .time("10:00")
//                .petAllowed(true)
//                .organizer("Fern Pollin")
//                .build());
////        db.json
//        eventList.add(Event.builder()
//                .id(789L)
//                .category("sustainability")
//                .title("Beach Cleanup")
//                .description("Help pick up trash along the shore.")
//                .location("Playa Del Carmen")
//                .date("July 22, 2022")
//                .time("11:00")
//                .petAllowed(false)
//                .organizer("Carey Wales")
//                .build());
//        eventList.add(Event.builder()
//                .id(1001L)
//                .category("animal welfare")
//                .title("Dog Adoption Day")
//                .description("Find your new canine friend at this event.")
//                .location("Woof Town")
//                .date("August 28, 2022")
//                .time("12:00")
//                .petAllowed(true)
//                .organizer("Dawg Dahd")
//                .build());
//        eventList.add(Event.builder()
//                .id(1002L)
//                .category("food")
//                .title("Canned Food Drive")
//                .description("Bring your canned food to donate to those in need.")
//                .location("Tin City")
//                .date("September 14, 2022")
//                .time("3:00")
//                .petAllowed(true)
//                .organizer("Kahn Opiner")
//                .build());
//        eventList.add(Event.builder()
//                .id(1003L)
//                .category("sustainability")
//                .title("Highway Cleanup")
//                .description("Help pick up trash along the highway.")
//                .location("Highway 50")
//                .date("July 22, 2022")
//                .time("11:00")
//                .petAllowed(false)
//                .organizer("Brody Kill")
//                .build());
//    }
    @Autowired
    OrganizerService organizerService;

    @GetMapping("organizers")
    public ResponseEntity<?> getOrganizerLists(@RequestParam(value = "_limit",required = false) Integer perPage,
                                           @RequestParam(value = "_page",required = false) Integer page){
        List<Organizer> output = null;
        Integer organizerSize = organizerService.getOrganizerSize();

        HttpHeaders responseHeader = new HttpHeaders();
//        responseHeader.set("x-total-count",String.valueOf(eventList.size()));
        responseHeader.set("x-total-count",String.valueOf(organizerSize));

        try {
            output = organizerService.getOrganizers(perPage,page);
            return new
                    ResponseEntity<>(output,responseHeader,HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex){
            return new
                    ResponseEntity<>(output,responseHeader,HttpStatus.OK);
        }
    }

    @GetMapping("organizers/{idorgan}")
    public ResponseEntity<?> getOrganizer(@PathVariable("idorgan") Long idorgan){
        Organizer output = organizerService.getOrganizer(idorgan);
        if (output != null){
            return ResponseEntity.ok(output);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }
}
