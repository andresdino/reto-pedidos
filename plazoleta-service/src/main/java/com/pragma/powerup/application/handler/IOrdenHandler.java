package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrdenRequestDTO;
import com.pragma.powerup.application.dto.response.OrdenResponseDTO;

import java.util.List;

public interface IOrdenHandler {

    void saveOrden(OrdenRequestDTO ordenRequest);
    List<OrdenResponseDTO> getAllOrdenesWithPagination(Integer page, Integer size, String estado);

    void takeOrdenAndUpdateStatus(Long idOrden, String estado);

    void updateAndNotifyOrdenReady(Long idOrden);


    void deliverOrden(Long idOrden, String pin);

   // void cancelOrden(Long idOrden);
}
