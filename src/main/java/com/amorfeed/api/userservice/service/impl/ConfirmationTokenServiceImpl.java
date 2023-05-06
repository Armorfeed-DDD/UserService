package com.amorfeed.api.userservice.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.amorfeed.api.userservice.entity.ConfirmationToken;
import com.amorfeed.api.userservice.repository.ConfirmationTokenRepository;
import com.amorfeed.api.userservice.service.ConfirmationTokenService;
import com.amorfeed.api.userservice.service.UserService;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
       this.confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public void setConfirmedAt(String token) {
        Optional<ConfirmationToken> confirmationToken = this.confirmationTokenRepository.findByToken(token);
        if(confirmationToken.isEmpty()) {
            throw new NotFoundException("Confirmation token not found");
        }
        confirmationToken.get().setConfirmedAt(LocalDateTime.now());
        this.confirmationTokenRepository.save(confirmationToken.get());
    }
    
}
