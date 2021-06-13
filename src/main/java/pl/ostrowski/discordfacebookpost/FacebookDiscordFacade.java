package pl.ostrowski.discordfacebookpost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ostrowski.discordfacebookpost.discord.DiscordSender;
import pl.ostrowski.discordfacebookpost.discord.HookGenerator;
import pl.ostrowski.discordfacebookpost.facebook.Page;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacebookDiscordFacade {

  private final DiscordSender discordSender;

  public void proceedPostToDiscord(Page page) {
    log.info("Received " + page);
    if (page.getEntry().length == 1 && page.getEntry()[0].getChanges().length == 1)
      HookGenerator.generate(page.getEntry()[0].getChanges()[0]).ifPresent(discordSender::send);
  }
}
