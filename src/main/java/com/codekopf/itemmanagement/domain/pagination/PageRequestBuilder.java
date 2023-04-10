package com.codekopf.itemmanagement.domain.pagination;

public interface PageRequestBuilder<O> {

    PageRequestBuilder<O> setPage(int newPage);

    PageRequestBuilder<O> setPageSize(int newPageSize);

    O build();
}
