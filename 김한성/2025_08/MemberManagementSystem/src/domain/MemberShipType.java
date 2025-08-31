package domain;

import java.time.LocalDate;
import java.util.Optional;

public enum MemberShipType {

    ONE_MONTH() {
        @Override public boolean isSessionBased() {return false;}
        @Override public Optional<LocalDate> expireAt(LocalDate start) {return Optional.of(start.plusMonths(1));}
        @Override public int initialSessions() {return 0;}
    },

    THREE_MONTH() {
        @Override public boolean isSessionBased() {return false;}
        @Override public Optional<LocalDate> expireAt(LocalDate start) {return Optional.of(start.plusMonths(3));}
        @Override public int initialSessions() {return 0;}
    },

    TEN_SESSION() {
        @Override public boolean isSessionBased() {return true;}
        @Override public Optional<LocalDate> expireAt(LocalDate start) {return Optional.of(start.plusYears(2));}
        @Override public int initialSessions() {return 10;}
    },

    THIRTY_SESSION() {
        @Override public boolean isSessionBased() {return true;}
        @Override public Optional<LocalDate> expireAt(LocalDate start) {return Optional.of(start.plusYears(2));}
        @Override public int initialSessions() {return 30;}
    },

    ONE_DAY() {
        @Override public Optional<LocalDate> expireAt(LocalDate start) {return Optional.empty();}
        @Override public int initialSessions() {return 0;}
        @Override public boolean isSessionBased() {return false;}
    };

    public abstract boolean isSessionBased();
    public abstract Optional<LocalDate> expireAt(LocalDate start);
    public abstract int initialSessions();
}
