package com.epam.task.fifth.entity.components;

import com.epam.task.fifth.enums.LexemeType;

import java.util.Objects;

public class Lexeme implements Component, Comparable<Lexeme> {

    private String value;
    private LexemeType type;

    private Lexeme(String value, LexemeType type) {
        this.type = type;
        this.value = value;
    }

    public static Lexeme buildLexeme(String value) {
        return new Lexeme(value, null);
    }

    public static Lexeme buildLexeme(String value, LexemeType type) {
        return new Lexeme(value, type);
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
    public int compareTo(Lexeme o) {
        return value.length()-o.value.length();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lexeme)) {
            return false;
        }
        Lexeme that = (Lexeme) o;
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
