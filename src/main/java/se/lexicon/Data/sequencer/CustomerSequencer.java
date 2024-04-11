package se.lexicon.Data.sequencer;

public class CustomerSequencer {
    private static int sequencer = 1000;

    public static int nextId() {
        return ++sequencer;
    }
}
