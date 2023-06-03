package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IPlatoServicePort;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Plato;
import com.pragma.powerup.domain.spi.persistence.ICategoriaPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IPlatoPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.PlatoNoExisteException;

import java.util.List;

public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;

    private final IRestaurantPersistencePort restaurantPersistencePort;

    private final ICategoriaPersistencePort categoriaPersistencePort;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, ICategoriaPersistencePort categoriaPersistencePort) {
        this.platoPersistencePort = platoPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.categoriaPersistencePort = categoriaPersistencePort;
    }


    @Override
    public void savePlato(Plato plato) {

        if(restaurantPersistencePort.getRestaurantById(plato.getRestaurantId().getId()) == null) {throw new DomainException("Restaurante no Existe");
        }
        if(categoriaPersistencePort.getCategoriaById(plato.getCategoriaId().getId()) == null){ throw new DomainException("Categoria no Exsite");}

        plato.setActivo(true);

        platoPersistencePort.savePlato(plato);

    }

    @Override
    public Plato getPlatoById(Long id) {
        return platoPersistencePort.getPlatoById(id);
    }

    @Override
    public List<Plato> getAllPlatos() {
        return platoPersistencePort.getAllPlato();
    }

    @Override
    public void deletePlatoById(Long id) {
        platoPersistencePort.deletePlatoById(id);
    }

    @Override
    public void putPlato( Long id,Plato plato) {
        Plato platoPut = platoPersistencePort.getPlatoById(id);
        if(platoPut==null) {
            throw new PlatoNoExisteException();
        }

        platoPut.setPrecio(plato.getPrecio());
        platoPut.setDescripcion(plato.getDescripcion());

        platoPersistencePort.savePlato(platoPut);
    }

    @Override
    public void putEnableDisablePlato(Long platoId, Long flag) {
        Plato platoPut = platoPersistencePort.getPlatoById(platoId);
        if(platoPut==null) {
            throw new PlatoNoExisteException();
        }

        boolean isEnableOrDisable = (flag==1)?true:false;
        platoPut.setActivo(isEnableOrDisable);

        platoPersistencePort.savePlato(platoPut);
    }
}
