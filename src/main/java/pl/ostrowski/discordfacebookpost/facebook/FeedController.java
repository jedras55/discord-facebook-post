package pl.ostrowski.discordfacebookpost.facebook;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.ostrowski.discordfacebookpost.FacebookDiscordFacade;

@RestController
@RequiredArgsConstructor
public class FeedController {

  private final FacebookDiscordFacade facebookDiscordFacade;

  @PostMapping
  public void handleFeed(@RequestBody Value value) {
    facebookDiscordFacade.proceedPostToDiscord(value);
  }
}
