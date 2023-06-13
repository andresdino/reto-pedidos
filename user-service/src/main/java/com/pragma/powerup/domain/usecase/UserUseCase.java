package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.feing.IClientFeignPort;
import com.pragma.powerup.domain.spi.feing.IEmployeeFeignClientPort;
import com.pragma.powerup.domain.spi.password.IUserPassEncryptPort;
import com.pragma.powerup.domain.spi.persistence.IUserPersistencePort;
import com.pragma.powerup.domain.spi.token.IToken;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserPassEncryptPort userPassEncryptPort;

    private final IEmployeeFeignClientPort employeeFeignClientPort;

    private final IClientFeignPort clientFeignPort;

    private final IToken token;



    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPassEncryptPort userPassEncryptPort, IToken token,IClientFeignPort clientFeignPort, IEmployeeFeignClientPort employeeFeignClientPort ) {
        this.userPersistencePort = userPersistencePort;
        this.userPassEncryptPort = userPassEncryptPort;
        this.token = token;
        this.clientFeignPort = clientFeignPort;
        this.employeeFeignClientPort = employeeFeignClientPort;
    }


    @Override
    public void saveUser(User user) {
        validateRolesAuthAndNot(user);
        user.setClave(userPassEncryptPort.encode(user.getClave()));
        userPersistencePort.saveUser(user);
    }
    private void validateRolesAuthAndNot(User user){
        String bearerToken = token.getBearerToken();
        Rol rol = new Rol();
        String rolS = "";
        if(!(bearerToken==null)) {

            rolS = token.getUsuarioAutenticadoRol(bearerToken);
            System.out.println(rolS);
        }

        if(rolS.equals("PROPIETARIO")){
            rol.setId(3L);
        }else if(rolS.equals("ADMIN")){
            rol.setId(2L);
        }else{
            if(user.getRol().getId()==null){
                rol.setId(4L);
            }else
            if(user.getRol().getId()==1){
                System.out.println("Se esta registrando un ADMIN");
            }
        }
        if(!(rol.getId()==null)){
            user.setRol(rol);
        }
    }


    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }
    @Override
    public List<User> getAllUser() {
        return userPersistencePort.getAllUser();
    }
    @Override
    public Boolean existsUserById(Long id) {
        return userPersistencePort.existsUserById(id);
    }
    @Override
    public void deleteUserById(Long id) {
        userPersistencePort.deleteUserById(id);
    }

    @Override
    public User getUserByCorreo(String correo) {
        return null;
    }

    @Override
    public void saveRestaurantEmployee(User user) {
        RestaurantEmployeeModel restaurantEmployeeModel = new RestaurantEmployeeModel();
        String bearerToken = token.getBearerToken();
        Long idOwnerAuth = token.getUsuarioAutenticadoId(bearerToken);

        RestaurantModel restaurant = clientFeignPort.getRestaurantByIdPropietario(idOwnerAuth);

        String restaurantId = String.valueOf(restaurant.getId());

        System.out.println(restaurantId);

        String employee_id = String.valueOf(userPersistencePort.getUserByCorreo(user.getCorreo()).getId());

        System.out.println(employee_id);

        restaurantEmployeeModel.setRestaurantId(restaurantId);

        restaurantEmployeeModel.setPersonId(employee_id);

        employeeFeignClientPort.saveRestaurantEmployee(restaurantEmployeeModel);

    }
}
