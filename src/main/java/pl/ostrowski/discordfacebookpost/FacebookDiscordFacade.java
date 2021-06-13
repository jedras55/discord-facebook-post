package pl.ostrowski.discordfacebookpost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ostrowski.discordfacebookpost.discord.DiscordSender;
import pl.ostrowski.discordfacebookpost.discord.HookGenerator;
import pl.ostrowski.discordfacebookpost.facebook.Feed;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacebookDiscordFacade {

  private final DiscordSender discordSender;

  public void proceedPostToDiscord(Feed feed) {
    log.info("Received " + feed);
    HookGenerator.generate(feed).ifPresent(discordSender::send);
  }
}
