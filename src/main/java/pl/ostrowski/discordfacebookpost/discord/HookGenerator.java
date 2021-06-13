package pl.ostrowski.discordfacebookpost.discord;

import java.util.Optional;
import pl.ostrowski.discordfacebookpost.facebook.Feed;
import pl.ostrowski.discordfacebookpost.facebook.Value;

public class HookGenerator {

  public static Optional<Hook> generate(Feed feed) {
    Value value = feed.getValue();
    if (value != null
        && value.getMessage() != null
        && value.getLink() != null
        && value.getPostId() != null) {

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
}
