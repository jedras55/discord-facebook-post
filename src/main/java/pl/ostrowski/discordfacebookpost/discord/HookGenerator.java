package pl.ostrowski.discordfacebookpost.discord;

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

      String link = queryPostLink(value.getPostId());
      String message = value.getMessage();
      String photo = value.getLink();

      return Optional.of(new Hook(message, new Embed[] {new Embed(link, link, new Image(photo))}));
    } else {
      return Optional.empty();
    }
  }

  private static String queryPostLink(String connectedId) {
    String pageId = connectedId.substring(0, connectedId.indexOf("_"));
    String postId = connectedId.substring(connectedId.indexOf("_") + 1);

    return "https://www.facebook.com/permalink.php?story_fbid=" + postId + "&id=" + pageId;
  }

  private static boolean isIgnoredFeed(Item item) {
    return IGNORED_FEEDS.contains(item);
  }
}
