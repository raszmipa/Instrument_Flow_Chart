package com.sample;

import java.util.Objects;

public class InstrumentCandidate {

    private String name;

    public InstrumentCandidate() {
    }

    public InstrumentCandidate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InstrumentCandidate{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstrumentCandidate)) return false;
        InstrumentCandidate that = (InstrumentCandidate) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
