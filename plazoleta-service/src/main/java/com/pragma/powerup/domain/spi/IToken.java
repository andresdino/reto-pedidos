package com.pragma.powerup.domain.spi;

public interface IToken {


    String getBearerToken();

    String getCorreo(String token);

    Long getUsuarioAutenticadoId(String token);
}
