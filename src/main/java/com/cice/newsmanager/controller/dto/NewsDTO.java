package com.cice.newsmanager.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NewsDTO {

    private Long id;
    private String title;
    private String body;
    private String author;
    private String date;
}
