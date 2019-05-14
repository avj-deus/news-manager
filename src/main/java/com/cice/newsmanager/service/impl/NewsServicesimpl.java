package com.cice.newsmanager.service.impl;

import com.cice.newsmanager.controller.dto.NewsDTO;
import com.cice.newsmanager.repository.NewsRepository;
import com.cice.newsmanager.repository.entity.NewsEntity;
import com.cice.newsmanager.service.NewsService;
import com.cice.newsmanager.service.coverter.DTOEntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NewsServicesimpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    DTOEntityConverter mapper = new DTOEntityConverter();



    @Override
    public NewsDTO createNews(NewsDTO news) {
        NewsDTO responseDTO = null;
        NewsEntity newsEntity = newsRepository.save(mapper.mapDTOToEntity(news));
        responseDTO = mapper.mapEntityToDTO(newsEntity);
        return null;
    }

    @Override
    public NewsDTO searchNewsById(Long id) {
        NewsDTO responseDTO = null;

        Optional<NewsEntity> optionalnewsEntity = newsRepository.findById(id);
        if(optionalnewsEntity.isPresent()){
            responseDTO = mapper.mapEntityToDTO(optionalnewsEntity.get());
        }

        return responseDTO;
    }

    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsDTO> listNews = Collections.EMPTY_LIST;

        listNews = newsRepository
                .findAll()
                .stream()
                .map(entity -> mapper.mapEntityToDTO(entity))
                .collect(Collectors.toList());

        return listNews;
    }

    @Override
    public void deleteNewsById(long id) {
        NewsDTO responseDTO = null;
        newsRepository.deleteById(id);

    }

    @Override
    public NewsDTO updateNewsById(long id, NewsDTO newsDTO) {
        NewsDTO responseDTO = null;
        Optional<NewsEntity> entityOptional = newsRepository.findById(id);
        if(entityOptional.isPresent()){
            NewsEntity entityUnwrapped = entityOptional.get();
            if(newsDTO.getBody() != null){
                entityOptional.get().setBody(newsDTO.getBody());
            }
            if(newsDTO.getTitle() != null){
                entityOptional.get().setTitle(newsDTO.getTitle());
            }
            if(newsDTO.getAuthor() != null){
                entityOptional.get().setAuthor(newsDTO.getAuthor());
            }
            if(newsDTO.getDate() != null){
                entityOptional.get().setDate(newsDTO.getDate());
            }
        }
        NewsEntity entitysaved = newsRepository.save(entityOptional.get());
        responseDTO = mapper.mapEntityToDTO(entitysaved);
        return responseDTO;
    }
}
