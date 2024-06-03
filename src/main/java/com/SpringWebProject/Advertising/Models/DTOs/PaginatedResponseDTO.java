package com.SpringWebProject.Advertising.Models.DTOs;

import java.util.List;

public record PaginatedResponseDTO<T>(
        List<T> content,
        int pageNo,
        int pageSize,
        int totalPages,
        Long totalElements
) {
}
