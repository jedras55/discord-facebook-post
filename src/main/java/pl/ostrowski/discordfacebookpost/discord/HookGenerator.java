package pl.ostrowski.discordfacebookpost.discord;

import java.util.Optional;
import pl.ostrowski.discordfacebookpost.facebook.Feed;
import pl.ostrowski.discordfacebookpost.facebook.Value;

public class HookGenerator {

  public static Optional<Hook> generate(Feed feed) {
    Value value = feed.getValue();
    if (value != null
        && value.getPhoto() != null
        && value.getLink() != null
        && value.getPhoto() != null) {

      String link = value.getLink();
      String message = value.getMessage();
      String photo = value.getPhoto();

      return Optional.of(new Hook(message, new Embed[] {new Embed(link, link, new Image(photo))}));
    } else {
      return Optional.empty();
    }
  }
}
