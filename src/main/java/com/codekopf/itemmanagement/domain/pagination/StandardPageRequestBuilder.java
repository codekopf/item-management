package com.codekopf.itemmanagement.domain.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class StandardPageRequestBuilder implements PageRequestBuilder<PageRequest> {
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 25;
    private int page = DEFAULT_PAGE;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private final Sort sort;

    private StandardPageRequestBuilder(final Sort sort) {
        this.sort = sort;
    }

    public static StandardPageRequestBuilder createInstance(final Sort sort) {
        return new StandardPageRequestBuilder(sort);
    }

    @Override
    public PageRequestBuilder<PageRequest> setPage(final int newPage) {
        this.page = newPage - 1;
        return this;
    }

    @Override
    public PageRequestBuilder<PageRequest> setPageSize(final int newPageSize) {
        this.pageSize = newPageSize;
        return this;
    }

    @Override
    public PageRequest build() {
        return PageRequest.of(this.page, this.pageSize, this.sort);
    }
}

