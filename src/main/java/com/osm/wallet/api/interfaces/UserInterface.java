package com.osm.wallet.api.interfaces;

import com.osm.wallet.api.domain.util.Response;

public interface UserInterface {

    public Response createUser(String firstName, String lastName, String emailAddress, String phoneNumber,
                               String password, String username);

    public Response validateEmail(String emailAddress, String token) throws IllegalAccessException, InstantiationException;
}
