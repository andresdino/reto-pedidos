package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Ordenes.OrdenRequest;
import com.pragma.powerup.domain.model.Ordenes.OrdenResponse;

import java.util.List;

public interface IOrdenServicePort {

    void saveOrden(OrdenRequest ordenRequest);

    List<OrdenResponse> getAllOrdersWithPagination(Integer page, Integer size, String estado);

    void takeOrderAndUpdateStatus(Long idOrder, String estado);

    void updateAndNotifyOrdenReady(Long idOrder);

    void deliverOrder(Long idOrder, String pin);

    //void cancelOrden(Long idOrder);

}
