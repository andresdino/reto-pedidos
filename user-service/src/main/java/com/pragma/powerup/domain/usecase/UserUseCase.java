package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.password.IUserPassEncryptPort;
import com.pragma.powerup.domain.spi.persistence.IUserPersistencePort;
import com.pragma.powerup.domain.spi.token.IToken;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IUserPassEncryptPort userPassEncryptPort;
    private final IToken token;



    public UserUseCase(IUserPersistencePort userPersistencePort, IUserPassEncryptPort userPassEncryptPort, IToken token) {
        this.userPersistencePort = userPersistencePort;
        this.userPassEncryptPort = userPassEncryptPort;
        this.token = token;
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
            //Puede crear empleados
            rol.setId(3L);
        }else if(rolS.equals("ADMIN")){
            //Puede crear propietarios
            rol.setId(2L);
        }else{
            if(user.getRol().getId()==null){
                rol.setId(4L);
            }else
            if(user.getRol().getId()==1){
                //Si entra aqui, se registra un ADMIN
                System.out.println("Se esta registrando un ADMIN");
            }

        }
        //Si el Rol no es nulo, puede setearse al usuario, (Se valida ya que al ADMIN solo se le pasa el rol en el body del JSON)
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
}
