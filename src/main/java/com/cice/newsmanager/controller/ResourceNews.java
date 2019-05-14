package com.cice.newsmanager.controller;


import com.cice.newsmanager.controller.dto.NewsDTO;
import com.cice.newsmanager.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * clase resource de news
 * vamos a definir todos los metodos de un CRUD(los 4 metodos de manipulacion de datos)
 */
@RestController
public class ResourceNews {


    @Autowired
    NewsService newsService;

    /**
     * este metodo sirve para crear un recurso nuevo
     * Dado un modelo news, persistiremos esta en la DB y devolveremos como respuesta el
     * mismo objeto creado junto a su identificador unico.
     *
     * @param news DTO con la información de la noticia para dar de alta en el sistema.
     * @return ResponseEntity<NewsDTO>
     */
    @RequestMapping(path = "/news", method = RequestMethod.POST)
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO news){
        ResponseEntity<NewsDTO> response = null;
        if(validateNews(news)){
            //la notice es valida
            NewsDTO newsDTO = newsService.createNews(news);
            response = ResponseEntity.ok(newsDTO);
        }else{
            response = ResponseEntity.badRequest().body(news);
        }

        return response;
    }

    /**
     * metodo para recuperar un objeto en base al id
     * @param id
     * @return
     */
    @RequestMapping(path = "/news/{id}", method = RequestMethod.GET)
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable(name = "id") Long id){
        ResponseEntity<NewsDTO> response = null;
        NewsDTO newsDTO = newsService.searchNewsById(id);
        if(newsDTO != null){
            response = ResponseEntity.ok(newsDTO);
        }else{
            response = ResponseEntity.notFound().build();
        }
        return response;
    }
    @RequestMapping(path = "/news", method = RequestMethod.GET)
    public ResponseEntity<List<NewsDTO>> getAllNews(){
        ResponseEntity<List<NewsDTO>> response = null;
        List<NewsDTO> allNews = newsService.getAllNews();
        response = ResponseEntity.ok(allNews);

        return response;

    }
    @RequestMapping(path = "/news/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<NewsDTO>  deleteNewsById(@PathVariable(name = "id")Long id){
        newsService.deleteNewsById(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * metodo que actualiza los datos de una noticia dado su id
     *
     * @param id
     * @param newsDTO
     * @return
     */
    @RequestMapping(path = "/news/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<NewsDTO> updateNewsById(@PathVariable(name = "id") long id,@RequestBody NewsDTO newsDTO){
        ResponseEntity<NewsDTO> responseDTO = null;
        NewsDTO newsDTO1 = newsService.updateNewsById(id, newsDTO);
        responseDTO = ResponseEntity.ok(newsDTO1);

        return responseDTO;
    }


    private boolean validateNews(NewsDTO news){
        return (news.getTitle().isEmpty() && news.getBody().isEmpty()) ? false : true;
    }



}
