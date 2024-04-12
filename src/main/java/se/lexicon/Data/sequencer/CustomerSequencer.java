package se.lexicon.Data.sequencer;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerSequencer {
    private static int sequencer = 1000;

    public static int nextId() {
        return ++sequencer;
    }
}
