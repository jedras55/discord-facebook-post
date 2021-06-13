package pl.ostrowski.discordfacebookpost;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

  @GetMapping()
  public void validate(
      @RequestParam(name = "hub.challenge") String challenge,
      @RequestParam(name = "hub.verify_token") String verifyToken) {
    System.out.println(challenge);
    System.out.println(verifyToken);
  }
}
