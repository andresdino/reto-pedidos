package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.application.mapper.request.IRestaurantEmployeeRequestMapper;
import com.pragma.powerup.application.mapper.response.IRestaurantResponseMapper;
import com.pragma.powerup.domain.api.IObjectServicePort;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.spi.IObjectPersistencePort;
import com.pragma.powerup.domain.spi.feing.IClientFeignPort;
import com.pragma.powerup.domain.spi.feing.IEmployeeFeignClientPort;
import com.pragma.powerup.domain.spi.password.IUserPassEncryptPort;
import com.pragma.powerup.domain.spi.persistence.IRolPersistencePort;
import com.pragma.powerup.domain.spi.persistence.IUserPersistencePort;
import com.pragma.powerup.domain.spi.token.IToken;
import com.pragma.powerup.domain.usecase.ObjectUseCase;
import com.pragma.powerup.domain.usecase.RolUseCase;
import com.pragma.powerup.domain.usecase.UserUseCase;
import com.pragma.powerup.infrastructure.out.jpa.Token.TokenAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RolJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.feign.Adapter.RestaurantEmployeeFeignAdapter;
import com.pragma.powerup.infrastructure.out.jpa.feign.Adapter.RestaurantFeignAdapter;
import com.pragma.powerup.infrastructure.out.jpa.feign.ClientFeign;
import com.pragma.powerup.infrastructure.out.jpa.feign.EmployeeFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRolEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.passwordEncoder.BCrypPasswordEncoderAdapter;
import com.pragma.powerup.infrastructure.out.jpa.repository.IObjectRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRolRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUSerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor

public class BeanConfiguration {

    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    private final IUSerRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;
    private final EmployeeFeignClient employeeFeignClient;

    private final ClientFeign clientFeign;

    private final IRestaurantResponseMapper restaurantResponseMapper;


    @Bean
    public IRolPersistencePort rolPersistencePort(){
        return new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort(){
        return new RolUseCase(rolPersistencePort());
    }

    @Bean
    public IUserPassEncryptPort userPassEncryptPort(){return new BCrypPasswordEncoderAdapter();
    }


    @Bean
    public IUserPersistencePort userPersistencePort(){return new UserJpaAdapter(userRepository, userEntityMapper);
    }


    @Bean
    public IToken token(){
        return new TokenAdapter();
    }

    @Bean
    public IEmployeeFeignClientPort employeeFeignClientPort(){
        return new RestaurantEmployeeFeignAdapter(employeeFeignClient, restaurantEmployeeRequestMapper);
    }


    @Bean
    public IClientFeignPort clientFeingPort(){
        return new RestaurantFeignAdapter(clientFeign, restaurantResponseMapper);
    }

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort() {
        return new ObjectUseCase(objectPersistencePort());
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), userPassEncryptPort(),token(),clientFeingPort(),employeeFeignClientPort());
    }
}
