package com.tool.password;

import jakarta.validation.Valid;

import java.util.List;

public interface PasswordService {
    List<PasswordDto> getPassByEnvironment(String environment);
    List<Password> getAllPassword();
    void insertPassword(Password password, String remoteAddr);
    void updatePassword(Password password, String remoteAddr);
    void deletePassword(Password password);
}
