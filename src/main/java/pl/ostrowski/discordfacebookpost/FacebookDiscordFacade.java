package pl.ostrowski.discordfacebookpost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ostrowski.discordfacebookpost.discord.DiscordSender;
import pl.ostrowski.discordfacebookpost.discord.HookGenerator;
import pl.ostrowski.discordfacebookpost.facebook.Feed;

@Service
@RequiredArgsConstructor
public class FacebookDiscordFacade {

  private final DiscordSender discordSender;

  public void proceedPostToDiscord(Feed feed) {
    discordSender.send(HookGenerator.generate(feed));
  }
}
