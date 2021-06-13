package pl.ostrowski.discordfacebookpost.verification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class VerificationService {

  @Value("${facebook-verify-token}")
  private String facebookVerifyToken;

  String verifyIntegration(String challenge, String verifyToken) {
    if (!facebookVerifyToken.equals(verifyToken))
      throw new VerificationException("Verify tokens do not match");
    return challenge;
  }
}
