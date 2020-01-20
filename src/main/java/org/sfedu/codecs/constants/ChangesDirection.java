package org.sfedu.codecs.constants;

public enum ChangesDirection {
    POSITIVE(1), NEUTRAL(0), NEGATIVE(-1);

    private final int force;

    ChangesDirection(int force) {
        this.force = force;
    }

    public int getForce() {
        return force;
    }
}
