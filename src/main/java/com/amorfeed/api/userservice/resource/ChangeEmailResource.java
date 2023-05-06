package com.amorfeed.api.userservice.resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeEmailResource {
    private String currentEmail;
    private String newEmail;
}
