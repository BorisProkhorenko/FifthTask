package com.epam.task.fifth.entity.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite implements Component, Comparable<Composite> {

    private List<Component> components = new ArrayList<>();

    public Composite() {
    }

    public Composite(List<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void setComponents(List<Component> components) {
        this.components = components;
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
    public int compareTo(Composite o) {
        return components.size() - o.components.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Composite)){
            return false;
        }
        Composite that = (Composite) o;
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
