package com.cice.newsmanager.service;

import com.cice.newsmanager.controller.dto.NewsDTO;

import java.util.List;

/**
 * interfaz que contendra los metodos de la logica de negocio para el resource news
 */
public interface NewsService {

    /**
     * metodo que crea una nueva noticia en vase a los datos recibidos desde el resource
     * @param news para almacenar en base de datos
     * @return NewsDTO con el ID unico
     */
    NewsDTO createNews(NewsDTO news);

    /**
     * metodo que devuelve un objeto dto siempre que se encuentre en la datavase
     *
     * @param id
     * @return
     */
    NewsDTO searchNewsById(Long id);

    /**
     * metodo que devuelve todas las noticias
     *
     * @return List<NewsDTO>
     */

    List<NewsDTO> getAllNews();

    /**
     * metodo que elimina una noticia en base al id
     *
     * @param id
     * @return
     */
    void deleteNewsById(long id);

    /**
     * metodo que nos permite actualizar noticias de acuerdo al id;
     *
     * @param id
     * @return
     */
    NewsDTO updateNewsById(long id, NewsDTO newsDTO);
}
