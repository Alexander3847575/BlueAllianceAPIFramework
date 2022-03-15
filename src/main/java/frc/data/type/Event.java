package frc.data.type;

import lombok.Data;

/**
 * Data class representing a frc event, using lombok @Data.
 */
@Data
public class Event {

    private final int eventNumber;
    private final String eventId;

}
