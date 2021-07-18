package com.osm.wallet.api.interfaces;

import com.osm.wallet.api.domain.util.Response;

public interface TransferInterface {

    public Response walletToWallet(String sourceAccount, String beneficiaryAccount, Double amount, String sourceEmail) throws IllegalAccessException, InstantiationException;

    public Response transferViaEmail(String sourceEmail, String beneficiaryEmail, Double amount) throws IllegalAccessException, InstantiationException;

    public Response topUpWallet(String email, Double Amount) throws InstantiationException, IllegalAccessException;

}
