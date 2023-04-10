package com.codekopf.itemmanagement.infrastructure.repository.common;

import com.codekopf.itemmanagement.domain.pagination.Page;

public interface FilteredAndPaginated<T> {

    Page<T> findAll(FetchSpecification fetchSpecification);

}
