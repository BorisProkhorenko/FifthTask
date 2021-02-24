package com.epam.task.fifth.parsing;

import com.epam.task.fifth.components.Component;
import com.epam.task.fifth.components.TextComposite;
import com.epam.task.fifth.data.DataException;

public interface Parser {

    Component parseComponents(String input);
    String parseString(TextComposite composite);
}
