package org.acme.service;

import io.smallrye.reactive.messaging.annotations.Blocking;
import org.acme.entity.BetEntity;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ForgetUserService {

    @Incoming("deleteUser")
    @Blocking
    @Transactional
    public void deleteGambleHistory(Object username){
        BetEntity.delete("username", username);
    }
}
