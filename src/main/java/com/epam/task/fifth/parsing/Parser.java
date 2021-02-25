package com.epam.task.fifth.parsing;

import com.epam.task.fifth.entity.components.Component;
import com.epam.task.fifth.entity.components.TextComposite;

public interface Parser {

    Component parseComponents(String input);
    String parseString(TextComposite composite);
}
