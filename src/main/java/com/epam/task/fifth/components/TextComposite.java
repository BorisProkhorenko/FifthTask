package com.epam.task.fifth.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements Component, Comparable<TextComposite> {

    private List<Component> components = new ArrayList<>();

    public TextComposite() {
    }

    public TextComposite(List<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public Component getChild(int index) {
        return components.get(index);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    @Override
    public int compareTo(TextComposite o) {
        return components.size() - o.components.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextComposite)) return false;
        TextComposite that = (TextComposite) o;
        return Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    @Override
    public String toString() {
        return "TextComposite{" +
                "components=" + components +
                '}';
    }

}
