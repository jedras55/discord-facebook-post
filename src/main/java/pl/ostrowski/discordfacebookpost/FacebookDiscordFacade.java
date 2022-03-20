package pl.ostrowski.discordfacebookpost;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.ostrowski.discordfacebookpost.facebook.Value;
import pl.ostrowski.discordfacebookpost.facebook.Value.Verb;
import pl.ostrowski.discordfacebookpost.sender.DiscordSender;
import pl.ostrowski.discordfacebookpost.sender.Sender;
import pl.ostrowski.discordfacebookpost.sender.dto.HookGenerator;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacebookDiscordFacade {

  private final DiscordSender discordSender;

  public void proceedPostToDiscord(Value value) {
    List<Sender> senders = List.of(discordSender);

    log.info("Received " + value);

    Optional.ofNullable(value)
        .filter(this::isAddedPage)
        .flatMap(HookGenerator::generate)
        .ifPresent(hook -> senders.forEach(sender -> sender.send(hook)));
  }

  private boolean isAddedPage(Value value) {
    return Verb.ADD.equals(value.getVerb());
  }
}
