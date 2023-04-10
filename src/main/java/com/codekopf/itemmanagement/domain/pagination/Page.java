package com.codekopf.itemmanagement.domain.pagination;

import java.util.List;
import java.util.stream.Stream;

public interface Page<T> extends Iterable<T> {
    long getTotal();

    int size();

    List<T> getContent();

    Stream<T> stream();

}
