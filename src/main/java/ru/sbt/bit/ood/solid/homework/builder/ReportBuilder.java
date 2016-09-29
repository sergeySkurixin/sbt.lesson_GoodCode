package ru.sbt.bit.ood.solid.homework.builder;

import java.util.List;

/**
 * Created by SBTJavastudent on 29.09.2016.
 */
public interface ReportBuilder<T> {
    public StringBuilder build(List<T> objects);
}
