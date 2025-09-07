package com.astrapay.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private List<T> content;
}
