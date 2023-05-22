package com.pragma.powerup.domain.spi.password;

public interface IUserPassEncryptPort {

    String encode(String password);
}
