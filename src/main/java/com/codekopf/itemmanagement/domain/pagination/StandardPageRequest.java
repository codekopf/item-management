package com.codekopf.itemmanagement.domain.pagination;

import lombok.Value;

@Value(staticConstructor = "of")
public final class StandardPageRequest implements PageRequest {
    private final int page;
    private final int pageSize;

    @Override
    public int offset() {
        return (page - 1) * pageSize;
    }

    @Override
    // Method is accesing builder for concrete implementation
    @SuppressWarnings("FeatureEnvy")
    public <O> O build(final PageRequestBuilder<O> pageRequestBuilder) {
        return pageRequestBuilder.setPage(this.page).setPageSize(this.pageSize).build();
    }

}
