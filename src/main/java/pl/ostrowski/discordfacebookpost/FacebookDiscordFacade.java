package pl.ostrowski.discordfacebookpost;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ostrowski.discordfacebookpost.discord.DiscordSender;
import pl.ostrowski.discordfacebookpost.discord.HookGenerator;
import pl.ostrowski.discordfacebookpost.facebook.Feed;
import pl.ostrowski.discordfacebookpost.facebook.Page;
import pl.ostrowski.discordfacebookpost.facebook.Value;
import pl.ostrowski.discordfacebookpost.facebook.Value.Verb;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacebookDiscordFacade {

  private final DiscordSender discordSender;

  public void proceedPostToDiscord(Page page) {
    log.info("Received " + page);
    if (hasOneEntryAndOneChange(page)) {
      Feed feed = page.getEntry()[0].getChanges()[0];
      Value value =
          Optional.ofNullable(feed).map(Feed::getValue).orElseThrow(IllegalStateException::new);
      Optional.ofNullable(value)
          .filter(this::isAddedPage)
          .flatMap(HookGenerator::generate)
          .ifPresent(discordSender::send);
    }
  }

  private boolean isAddedPage(Value value) {
    return Verb.ADD.equals(value.getVerb());
  }

  private boolean hasOneEntryAndOneChange(Page page) {
    return page.getEntry().length == 1 && page.getEntry()[0].getChanges().length == 1;
  }
}
