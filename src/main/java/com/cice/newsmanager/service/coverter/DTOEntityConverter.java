package com.cice.newsmanager.service.coverter;


import com.cice.newsmanager.controller.dto.NewsDTO;
import com.cice.newsmanager.repository.entity.NewsEntity;

public class DTOEntityConverter {

    public NewsEntity mapDTOToEntity(NewsDTO newsDTO){
        NewsEntity newsEntity = null;
        newsEntity = new NewsEntity()
                   .setId(newsDTO.getId())
                   .setTitle(newsDTO.getTitle())
                   .setBody(newsDTO.getBody())
                   .setAuthor(newsDTO.getAuthor())
                   .setDate(newsDTO.getDate());

        return newsEntity;
    }
    public NewsDTO mapEntityToDTO(NewsEntity newsEntity){
        NewsDTO newsDTO = null;
        newsDTO = new NewsDTO()
                .setId(newsEntity.getId())
                .setBody(newsEntity.getBody())
                .setAuthor(newsEntity.getAuthor())
                .setDate(newsEntity.getDate());

        return newsDTO;

    }
}
