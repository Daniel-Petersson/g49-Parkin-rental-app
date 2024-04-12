package se.lexicon.daoImpl;

import org.junit.jupiter.api.Test;
import se.lexicon.Data.sequencer.CustomerSequencer;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomSequencerTest {
    @Test
    public void testNextId() {
        int id1 = CustomerSequencer.nextId();
        int id2 = CustomerSequencer.nextId();
        int id3 = CustomerSequencer.nextId();

        assertNotEquals(id1, id2, "IDs should be unique");
        assertNotEquals(id1, id3, "IDs should be unique");
        assertNotEquals(id2, id3, "IDs should be unique");
    }
}
