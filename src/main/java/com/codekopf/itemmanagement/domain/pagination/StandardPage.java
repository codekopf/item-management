package com.codekopf.itemmanagement.domain.pagination;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;

@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public final class StandardPage<T> implements Page<T> {
    private static final String TO_STRING_FORMAT = "StandardPage(totalCount=%d, contentSize=%d)";
    private final long totalCount;
    private final List<T> content;

    @Override
    public long getTotal() {
        return this.totalCount;
    }

    @Override
    public List<T> getContent() {
        return unmodifiableList(this.content);
    }

    @Override
    public Stream<T> stream() {
        return this.content.stream();
    }

    @Override
    public int size() {
        return this.content.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.content.iterator();
    }

    @Override
    public void forEach(final Consumer<? super T> action) {
        this.content.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return this.content.spliterator();
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, this.totalCount, this.content.size());
    }
}

