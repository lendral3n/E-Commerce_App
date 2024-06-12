package com.l3n.ecommerceapp.ecommmerce_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponse {
    private Integer currentPage;
    private Integer totalPage;
    private Integer limit;
}