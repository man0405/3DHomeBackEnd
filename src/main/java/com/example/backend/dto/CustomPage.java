package com.example.backend.dto;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {
    List<T> content;
    CustomPageable pageable;
    public CustomPage(Page<T> page){
        this.content = page.getContent();
        this.pageable = new CustomPageable(page.getPageable().getPageNumber(),page.getPageable().getPageSize(),page.getTotalPages());
    }


    @Data
    class CustomPageable {
        int pageNumber;
        int pageSize;
        int totalPages;

        public CustomPageable(int pageNumber, int pageSize, int totalPage){
            this.pageNumber= pageNumber;
            this.pageSize= pageSize;
            this.totalPages = totalPage;
        }

    }
}
