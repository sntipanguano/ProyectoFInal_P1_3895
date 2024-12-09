package com.soap.soap.endpoint;

import com.soap.soap.converter.LibrosConverter;
import com.soap.soap.gen.*;
import com.soap.soap.model.LibrosModel;
import com.soap.soap.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class LibrosEndpoint {
    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private LibrosConverter librosConverter;

    // Endpoint para obtener todos los libros
    @PayloadRoot(namespace = "http://www.soap.com/", localPart = "getLibrosRequest")
    @ResponsePayload
    public GetLibrosResponse getLibros(@RequestPayload GetLibrosRequest request) {
        GetLibrosResponse response = new GetLibrosResponse();
        List<LibrosModel> librosModels = librosRepository.findAll();
        List<Libros> libros = librosConverter.convertLibrosModelsToLibros(librosModels);
        response.getLibros().addAll(libros);
        return response;
    }
}
