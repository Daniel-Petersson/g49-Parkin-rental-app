package se.lexicon.Data.sequencer;

import java.util.UUID;

public class ReservationSequencer {
    public static String nextId() {
        return UUID.randomUUID().toString();
    }
}
