package pl.ostrowski.discordfacebookpost;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ostrowski.discordfacebookpost.facebook.Feed;
import pl.ostrowski.discordfacebookpost.facebook.Page;
import pl.ostrowski.discordfacebookpost.facebook.Value;
import pl.ostrowski.discordfacebookpost.facebook.Value.Verb;
import pl.ostrowski.discordfacebookpost.sender.DiscordSender;
import pl.ostrowski.discordfacebookpost.sender.PageSender;
import pl.ostrowski.discordfacebookpost.sender.Sender;
import pl.ostrowski.discordfacebookpost.sender.dto.HookGenerator;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacebookDiscordFacade {

  private final DiscordSender discordSender;
  private final PageSender pageSender;

  public void proceedPostToDiscord(Page page) {
    List<Sender> senders = Arrays.asList(pageSender, discordSender);

    log.info("Received " + page);
    if (hasOneEntryAndOneChange(page)) {
      Feed feed = page.getEntry()[0].getChanges()[0];
      Value value =
          Optional.ofNullable(feed).map(Feed::getValue).orElseThrow(IllegalStateException::new);
      Optional.ofNullable(value)
          .filter(this::isAddedPage)
          .flatMap(HookGenerator::generate)
          .ifPresent(hook -> senders.forEach(sender -> sender.send(hook)));
    }
  }

  private boolean isAddedPage(Value value) {
    return Verb.ADD.equals(value.getVerb());
  }

  private boolean hasOneEntryAndOneChange(Page page) {
    return page.getEntry().length == 1 && page.getEntry()[0].getChanges().length == 1;
  }
}
