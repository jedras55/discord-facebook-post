package pl.ostrowski.discordfacebookpost.verification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VerificationController {

  private final VerificationService verificationService;

  @GetMapping()
  public String verifyIntegration(
      @RequestParam(name = "hub.challenge") String challenge,
      @RequestParam(name = "hub.verify_token") String verifyToken) {
    return verificationService.verifyIntegration(challenge, verifyToken);
  }
}
