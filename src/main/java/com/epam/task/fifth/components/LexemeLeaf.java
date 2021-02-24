package com.epam.task.fifth.components;

import com.epam.task.fifth.enums.LexemeType;

import java.util.Objects;

public class LexemeLeaf implements Component, Comparable<LexemeLeaf> {

    private String value;
    private LexemeType type;

    public LexemeLeaf(String value) {
        this.value = value;
    }

    public LexemeLeaf(String value, LexemeType type) {
        this.type = type;
        this.value = value;
    }

    public LexemeType getType() {
        return type;
    }

    public void setType(LexemeType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(LexemeLeaf o) {
        return value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LexemeLeaf)) {
            return false;
        }
        LexemeLeaf that = (LexemeLeaf) o;
        return type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "LexemeLeaf{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }


}
