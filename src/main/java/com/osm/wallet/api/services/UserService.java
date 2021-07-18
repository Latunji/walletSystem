package com.osm.wallet.api.services;

import com.osm.wallet.api.abstractentities.predicate.CustomPredicate;
import com.osm.wallet.api.base.services.GenericService;
import com.osm.wallet.api.domain.util.Response;
import com.osm.wallet.api.domain.util.Token;
import com.osm.wallet.api.domain.util.User;
import com.osm.wallet.api.domain.util.Wallet;
import com.osm.wallet.api.interfaces.UserInterface;
import com.osm.wallet.api.payroll.utils.IppmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserService implements UserInterface {

    @Autowired
    GenericService genericService;


    @Override
    public Response createUser(String firstName, String lastName, String emailAddress, String phoneNumber,
                               String password, String username) {
        User user = new User();
        Token token = new Token();
        IppmsUtils utils = new IppmsUtils();
        Response resp = new Response();
        String code;
        int retVal = 0;

        List<User> allUsersList = this.genericService.loadControlEntity(User.class);
        User existingUser = allUsersList.stream()
                .filter(users -> emailAddress.equals(users.getEmailAddress()))
                .findAny()
                .orElse(null);

        if(IppmsUtils.isNull(existingUser)) {

            if(IppmsUtils.isNotNullOrEmpty(firstName) && IppmsUtils.isNotNullOrEmpty(lastName) && IppmsUtils.isNotNullOrEmpty(emailAddress)
                    && IppmsUtils.isNotNullOrEmpty(phoneNumber) && IppmsUtils.isNotNullOrEmpty(password)  && IppmsUtils.isNotNullOrEmpty(username)) {
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmailAddress(emailAddress);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);
                user.setUsername(username);
                this.genericService.saveObject(user);

                //this method creates User Wallet
		        createUserWallet(user);

		        //this method sends mail to user for email validation
                try {
                    code = utils.generateUniqueId();
                    utils.sendMail(emailAddress, code);
                    token.setEmailAddress(emailAddress);
                    token.setToken(code);
                    this.genericService.saveObject(token);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

                resp.setResponseCode("00");
                resp.setResponseMessage("User Created Successfully!");
            }
            else {
                resp.setResponseCode("03");
                resp.setResponseMessage("Null Value/s Cannot Be passed");
            }

        }
        else {
            resp.setResponseCode("02");
            resp.setResponseMessage("User Already Exist!");
        }

        return resp;
    }


    private void createUserWallet(User user) {
        String walletno;

        //generate wallet number
        walletno = IppmsUtils.GeneratingRandomNumericString();

        Wallet wallet = new Wallet();

        walletno = IppmsUtils.GeneratingRandomNumericString();
		wallet.setUser(user);
        wallet.setWalletNumber(walletno);
        this.genericService.saveObject(wallet);
    }


    @Override
    public Response validateEmail(String emailAddress, String token) throws IllegalAccessException, InstantiationException {
        Response resp = new Response();
        int retVal = 0;

        Token tokenExist = this.genericService.loadObjectUsingRestriction(Token.class, Arrays.asList(
                new CustomPredicate("emailAddress", emailAddress), new CustomPredicate("token", token)));

        if(IppmsUtils.isNotNull(tokenExist)){
            User user = new User();
            Wallet wallet = new Wallet();

            user.setEmailAddress(emailAddress);
            user.setEmailVerificationInd(1);
            this.genericService.saveOrUpdate(wallet);

            wallet.setMaximumWithdrawal(50000);
            this.genericService.saveOrUpdate(wallet);

            resp.setResponseCode("00");
            resp.setResponseMessage("Email Validation Successful");
        }
        else{
            resp.setResponseCode("04");
            resp.setResponseMessage("Invalid token/user");
        }

        return resp;
    }


}
