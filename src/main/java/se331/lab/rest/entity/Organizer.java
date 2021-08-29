package se331.lab.rest.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Organizer {
    Long idorgan;
    String name;
    String address;
}
