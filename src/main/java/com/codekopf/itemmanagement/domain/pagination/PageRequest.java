package com.codekopf.itemmanagement.domain.pagination;

public interface PageRequest {

    int getPage();

    int getPageSize();

    int offset();

    <O> O build(PageRequestBuilder<O> pageRequestBuilder);

}
