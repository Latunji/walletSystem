package com.osm.wallet.api.services;

import com.osm.wallet.api.abstractentities.predicate.CustomPredicate;
import com.osm.wallet.api.base.services.GenericService;
import com.osm.wallet.api.domain.util.Response;
import com.osm.wallet.api.domain.util.Transactions;
import com.osm.wallet.api.domain.util.User;
import com.osm.wallet.api.domain.util.Wallet;
import com.osm.wallet.api.interfaces.TransferInterface;
import com.osm.wallet.api.payroll.utils.IppmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Arrays;

@Service
@Slf4j
public class TransferService implements TransferInterface {

    @Autowired
    GenericService genericService;

    Response resp = new Response();

    @Override
    public Response walletToWallet(String sourceAccount, String beneficiaryAccount, Double amount, String sourceEmail) throws IllegalAccessException, InstantiationException {
        Wallet sourceWallet = new Wallet();
        Wallet beneficiaryWallet = new Wallet();

        User user = this.genericService.loadObjectUsingRestriction(User.class, Arrays.asList(
                new CustomPredicate("emailAddress", sourceEmail)));

        if(IppmsUtils.isNotNull(user)) {
            sourceWallet = this.genericService.loadObjectUsingRestriction(Wallet.class, Arrays.asList(
                    new CustomPredicate("walletNumber", sourceAccount)));
            beneficiaryWallet = this.genericService.loadObjectUsingRestriction(Wallet.class, Arrays.asList(
                    new CustomPredicate("walletNumber", beneficiaryAccount)));
        }
        else{
            resp.setResponseCode("07");
            resp.setResponseMessage("User Not Found");
            return resp;
        }



       if(IppmsUtils.isNotNull(beneficiaryWallet)) {
            if (sourceWallet.getMaximumWithdrawal() > amount) {
                if (sourceWallet.getWalletBalance() > amount) {
                    //debit and update source wallet
                    Double newBal = sourceWallet.getWalletBalance() - amount;
                    sourceWallet.setWalletBalance(newBal);
                    this.genericService.saveOrUpdate(sourceWallet);

                    //credit and update beneficiary wallet
                    Double newBal2 = beneficiaryWallet.getWalletBalance() + amount;
                    beneficiaryWallet.setWalletBalance(newBal2);
                    this.genericService.saveOrUpdate(beneficiaryWallet);

                    //save transaction
                    Transactions trans = new Transactions();
                    trans.setAmount(amount);
                    trans.setBeneficiaryAccount(beneficiaryAccount);
                    trans.setBeneficiaryWallet(beneficiaryWallet);
                    trans.setSourceWallet(sourceWallet);
                    trans.setSourceUser(user);
                    this.genericService.saveObject(trans);

                    //return response
                    resp.setResponseCode("00");
                    resp.setResponseMessage("Transfer Successful");
                } else {
                    resp.setResponseCode("05");
                    resp.setResponseMessage("Insufficient Funds");
                }
            } else {
                resp.setResponseCode("06");
                resp.setResponseMessage("Amount is greater than maximum transfer limit");
            }
        }
        else{
            resp.setResponseCode("08");
            resp.setResponseMessage("Invalid Beneficiary Account");
        }
        return resp;
    }

    @Override
    public Response transferViaEmail(String sourceEmail, String beneficiaryEmail, Double amount) throws IllegalAccessException, InstantiationException {
        Wallet sourceWallet = new Wallet();
        Wallet beneficiaryWallet = new Wallet();

        User sourceUser = this.genericService.loadObjectUsingRestriction(User.class, Arrays.asList(new CustomPredicate(
                "emailAddress", sourceEmail)));
        User beneficiaryUser = this.genericService.loadObjectUsingRestriction(User.class, Arrays.asList(new CustomPredicate(
                "emailAddress", beneficiaryEmail)));

        if(IppmsUtils.isNotNull(sourceUser) && IppmsUtils.isNotNull(beneficiaryUser)) {
            sourceWallet = this.genericService.loadObjectUsingRestriction(Wallet.class, Arrays.asList(
                    new CustomPredicate("user.id", sourceUser.getId())));
            beneficiaryWallet = this.genericService.loadObjectUsingRestriction(Wallet.class, Arrays.asList(
                    new CustomPredicate("user.id", beneficiaryUser.getId())));
        }
        else{
            resp.setResponseCode("07");
            resp.setResponseMessage("User Not Found");
            return resp;
        }


        if(sourceWallet.getMaximumWithdrawal() > amount){
            if(sourceWallet.getWalletBalance() > amount) {
                //debit and update source wallet
                Double newBal = sourceWallet.getWalletBalance() - amount;
                sourceWallet.setWalletBalance(newBal);
                this.genericService.saveOrUpdate(sourceWallet);

                //credit and update beneficiary wallet
                Double newBal2 = beneficiaryWallet.getWalletBalance() + amount;
                beneficiaryWallet.setWalletBalance(newBal2);
                this.genericService.saveOrUpdate(beneficiaryWallet);

                //save transaction
                Transactions trans = new Transactions();
                trans.setAmount(amount);
                trans.setSourceAccount(sourceWallet.getWalletNumber());
                trans.setBeneficiaryAccount(beneficiaryWallet.getWalletNumber());
                trans.setBeneficiaryWallet(beneficiaryWallet);
                trans.setSourceWallet(sourceWallet);
                trans.setSourceUser(sourceUser);
                trans.setBeneficiaryUser(beneficiaryUser);
                this.genericService.saveObject(trans);

                //return response
                resp.setResponseCode("00");
                resp.setResponseMessage("Transfer Successful");
            }
            else{
                resp.setResponseCode("05");
                resp.setResponseMessage("Insufficient Funds");
            }
        }
        else{
            resp.setResponseCode("06");
            resp.setResponseMessage("Amount is greater than maximum transfer limit");
        }

        return resp;
    }

    @Override
    public Response topUpWallet(String email, Double amount) throws InstantiationException, IllegalAccessException {

        User user = this.genericService.loadObjectWithSingleCondition(User.class,
                new CustomPredicate("emailAddress", email));

        if(IppmsUtils.isNotNull(user)){
            Wallet userWallet = this.genericService.loadObjectWithSingleCondition(Wallet.class, new CustomPredicate(
                    "user.id", user.getId()));

            Double topUp = userWallet.getWalletBalance() + amount;
            userWallet.setWalletBalance(topUp);

            this.genericService.saveOrUpdate(userWallet);

            resp.setResponseCode("oo");
            resp.setResponseMessage("Top Up Successful");
        }
        else{
            resp.setResponseCode("07");
            resp.setResponseMessage("User Not Found");
        }

        return resp;
    }
}
