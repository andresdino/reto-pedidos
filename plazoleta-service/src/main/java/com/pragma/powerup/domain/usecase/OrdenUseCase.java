package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrdenServicePort;
import com.pragma.powerup.domain.exception.*;
import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.domain.model.Ordenes.OrdenPlatoRequest;
import com.pragma.powerup.domain.model.Ordenes.OrdenPlatoResponse;
import com.pragma.powerup.domain.model.Ordenes.OrdenRequest;
import com.pragma.powerup.domain.model.Ordenes.OrdenResponse;
import com.pragma.powerup.domain.spi.IToken;
import com.pragma.powerup.domain.spi.feignClients.IUserFeignClientPort;
import com.pragma.powerup.domain.spi.persistence.IOrdenPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantEmployeePersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.PlatoNoExisteException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdenUseCase implements IOrdenServicePort {

        private final IOrdenPersistencePort ordenPersistencePort;

        private final IToken token;

        private final IRestaurantPersistencePort restaurantPersistencePort;

        private final IPlatoPersistencePort platoPersistencePort;

        private final IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort;

        private final IUserFeignClientPort userFeignClientPort;

        public OrdenUseCase(IOrdenPersistencePort ordenPersistencePort, IToken token, IRestaurantPersistencePort restaurantPersistencePort, IPlatoPersistencePort platoPersistencePort, IRestaurantEmployeePersistencePort restaurantEmployeePersistencePort, IUserFeignClientPort userFeignClientPort) {
            this.ordenPersistencePort = ordenPersistencePort;
            this.token = token;
            this.restaurantPersistencePort = restaurantPersistencePort;
            this.platoPersistencePort = platoPersistencePort;
            this.restaurantEmployeePersistencePort = restaurantEmployeePersistencePort;
            this.userFeignClientPort = userFeignClientPort;
        }

        @Override
        public void saveOrden(OrdenRequest ordenRequest) {
            Date date = new Date();
            String bearerToken = token.getBearerToken();
            if(bearerToken==null) throw new OwnerNotAuthenticatedException();
            Long idClientAuth = token.getUsuarioAutenticadoId(bearerToken);

            List<String> estados = List.of("PENDIENTE", "EN_PREPARACION", "LISTO");

            if(ordenPersistencePort.existsByIdClienteAndEstado(idClientAuth, estados.get(0)) ||
                    ordenPersistencePort.existsByIdClienteAndEstado(idClientAuth, estados.get(1)) ||
                    ordenPersistencePort.existsByIdClienteAndEstado(idClientAuth, estados.get(2))) throw new ClientHasAnOrderException();

            Long idRestaurante = ordenRequest.getResturanteId();

            Restaurant restaurantModel= restaurantPersistencePort.getRestaurantById(idRestaurante);
            if(restaurantModel==null) throw new RestaurantNotExistException();
            Orden orderModel = new Orden(-1L, idClientAuth,date,"PENDIENTE",null,restaurantModel);

            List<OrdenPlatoRequest> orderDishes = ordenRequest.getPlatos();
            if(orderDishes.isEmpty()){
                throw new NoDataFoundException();
            }
            for (int i=0; i<orderDishes.size();i++) {
                Plato plato = platoPersistencePort.getPlatoById(orderDishes.get(i).getIdPlatos());
                if (plato == null) throw new PlatoNoExisteException();
                if (plato.getRestaurantId().getId() != orderModel.getRestaurante().getId()) throw new PlatoRestaurantIdNotIsEqualsOrderException();
                if(!plato.getActivo()) throw new PlatoIsInactiveException();
            }
            Orden order =ordenPersistencePort.saveOrden(orderModel);


            List<OrdenPlato> ordenPlatoEmpty = new ArrayList<>();
            for (int i=0; i<orderDishes.size();i++){
                Plato dishModel= platoPersistencePort.getPlatoById(orderDishes.get(i).getIdPlatos());
                OrdenPlato OrdenPlato = new OrdenPlato(-1L, order,dishModel, String.valueOf(orderDishes.get(i).getCantidad()));
                ordenPlatoEmpty.add(OrdenPlato);
            }

            ordenPersistencePort.saveOrdenPlato(ordenPlatoEmpty);
        }

        @Override
        public List<OrdenResponse> getAllOrdersWithPagination(Integer page, Integer size, String estado) {
            String bearerToken = token.getBearerToken();
            if(bearerToken==null) throw new OwnerNotAuthenticatedException();
            Long idEmployeeAuth = token.getUsuarioAutenticadoId(bearerToken);
            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(String.valueOf(idEmployeeAuth));

            List<OrdenResponse> listaPedidosResponse = new ArrayList<>();
            Long restauranteId = Long.parseLong(restaurantEmployeeModel.getRestaurantId());
            List<Orden> pedidos = ordenPersistencePort.getAllOrdenesWithPagination(page, size,restauranteId ,estado);

            for (int i=0; i<pedidos.size();i++){
                OrdenResponse orderResponseModel = new OrdenResponse();
                orderResponseModel.setId(pedidos.get(i).getId());
                orderResponseModel.setIdCliente(pedidos.get(i).getIdCliente());
                if(pedidos.get(i).getChef()==null) orderResponseModel.setIdChef(null);
                else orderResponseModel.setIdChef(pedidos.get(i).getChef().getId());
                orderResponseModel.setFecha(pedidos.get(i).getFecha());
                orderResponseModel.setPedidoPlatos(new ArrayList<>());

                List<OrdenPlato> pedidoPlatos = ordenPersistencePort.getAllOrdenessByPedido(pedidos.get(i).getId());
                for (int k=0; k<pedidoPlatos.size(); k++){
                    Plato plato= platoPersistencePort.getPlatoById(pedidoPlatos.get(k).getPlato().getId());
                    OrdenPlatoResponse OrdenPlatoResponse = new OrdenPlatoResponse();
                    OrdenPlatoResponse.setId(plato.getId());
                    OrdenPlatoResponse.setNombre(plato.getNombre());
                    OrdenPlatoResponse.setPrecio(plato.getPrecio());
                    OrdenPlatoResponse.setDescripcion(plato.getDescripcion());
                    OrdenPlatoResponse.setUrlImagen(plato.getUrlImagen());
                    OrdenPlatoResponse.setCategoriaId(plato.getCategoriaId());
                    OrdenPlatoResponse.setCantidad(pedidoPlatos.get(k).getCantidad());

                    orderResponseModel.getPedidoPlatos().add(OrdenPlatoResponse);
                }
                listaPedidosResponse.add(orderResponseModel);
            }
            return listaPedidosResponse;
        }

        @Override
        public void takeOrderAndUpdateStatus(Long idOrder, String estado) {
            if(!estado.equals("EN_PREPARACION")) throw new NoDataFoundException();
            if(Boolean.FALSE.equals(ordenPersistencePort.existsByIdAndEstado(idOrder, "PENDIENTE"))) throw new NoDataFoundException();

            String bearerToken = token.getBearerToken();
            if(bearerToken==null) throw new OwnerNotAuthenticatedException();
            Long idEmployeeAuth = token.getUsuarioAutenticadoId(bearerToken);
            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(String.valueOf(idEmployeeAuth));
            if(restaurantEmployeeModel==null) throw new RestaurantEmployeeNotExistException();
            Orden orderModel= ordenPersistencePort.getOrdenById(idOrder);
            if(orderModel==null) throw new OrderNotExistException();

            Long idRestaurantEmployeeAuth = Long.valueOf(restaurantEmployeeModel.getRestaurantId());
            Long idRestaurantOrder = orderModel.getRestaurante().getId();

            if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new OrderRestaurantMustBeEqualsEmployeeRestaurantException();

            orderModel.setChef(restaurantEmployeeModel);
            orderModel.setEstado(estado);

            ordenPersistencePort.saveOrden(orderModel);
        }

        @Override
        public void updateAndNotifyOrderReady(Long idOrder) {
            if(Boolean.FALSE.equals(ordenPersistencePort.existsByIdAndEstado(idOrder, "EN_PREPARACION"))) throw new NoDataFoundException();
            String bearerToken = token.getBearerToken();
            if(bearerToken==null) throw new OwnerNotAuthenticatedException();
            Long idEmployeeAuth = token.getUsuarioAutenticadoId(bearerToken);
            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(String.valueOf(idEmployeeAuth));
            if(restaurantEmployeeModel==null) throw new RestaurantEmployeeNotExistException();
            Orden orden= ordenPersistencePort.getOrdenById(idOrder);
            if(orden==null) throw new OrderNotExistException();

            Long idRestaurantEmployeeAuth = Long.valueOf(restaurantEmployeeModel.getRestaurantId());
            Long idRestaurantOrder = orden.getRestaurante().getId();

            if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new OrderRestaurantMustBeEqualsEmployeeRestaurantException();

            orden.setEstado("LISTO");
            ordenPersistencePort.saveOrden(orden);


        }

        @Override
        public void deliverOrder(Long idOrder, String pin) {
            if(Boolean.FALSE.equals(ordenPersistencePort.existsByIdAndEstado(idOrder, "LISTO"))) throw new NoDataFoundException();
            String bearerToken = token.getBearerToken();
            if(bearerToken==null) throw new OwnerNotAuthenticatedException();
            Long idEmployeeAuth = token.getUsuarioAutenticadoId(bearerToken);
            RestaurantEmployee restaurantEmployeeModel= restaurantEmployeePersistencePort.findByPersonId(String.valueOf(idEmployeeAuth));
            if(restaurantEmployeeModel==null) throw new RestaurantEmployeeNotExistException();
            Orden orden= ordenPersistencePort.getOrdenById(idOrder);
            if(orden==null) throw new OrderNotExistException();

            Long idRestaurantEmployeeAuth = Long.valueOf(restaurantEmployeeModel.getRestaurantId());
            Long idRestaurantOrder = orden.getRestaurante().getId();

            if(idRestaurantEmployeeAuth!=idRestaurantOrder) throw new OrderRestaurantMustBeEqualsEmployeeRestaurantException();

            orden.setEstado("ENTREGADO");
            ordenPersistencePort.saveOrden(orden);
        }
}
