package org.soco.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
