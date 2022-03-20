package pl.ostrowski.discordfacebookpost.sender.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import pl.ostrowski.discordfacebookpost.facebook.Value;
import pl.ostrowski.discordfacebookpost.facebook.Value.Item;

public class HookGenerator {

  private static final List<Item> IGNORED_FEEDS = Arrays.asList(Item.COMMENT, Item.REACTION);

  public static Optional<Hook> generate(Value value) {
    if ((value.getMessage() != null && value.getLink() != null && value.getPostId() != null)
        && !isIgnoredFeed(value.getItem())) {

      String link = value.getUrl();
      String message = value.getMessage();
      String photo = value.getLink();

      return Optional.of(new Hook(message, new Embed[] {new Embed(link, link, new Image(photo))}));
    } else {
      return Optional.empty();
    }
  }

  private static boolean isIgnoredFeed(Item item) {
    return IGNORED_FEEDS.contains(item);
  }
}
