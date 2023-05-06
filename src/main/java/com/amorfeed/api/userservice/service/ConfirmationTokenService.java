package com.amorfeed.api.userservice.service;

import java.util.Optional;

import com.amorfeed.api.userservice.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    public void saveConfirmationToken(ConfirmationToken token);
    public Optional<ConfirmationToken> getByToken(String token);
    public void setConfirmedAt(String token);
}
