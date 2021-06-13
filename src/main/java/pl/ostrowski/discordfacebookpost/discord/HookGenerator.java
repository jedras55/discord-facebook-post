package pl.ostrowski.discordfacebookpost.discord;

import java.util.Optional;
import pl.ostrowski.discordfacebookpost.facebook.Entry;
import pl.ostrowski.discordfacebookpost.facebook.Value;

public class HookGenerator {

  public static Optional<Hook> generate(Entry entry) {
    String pageId = entry.getId();
    Value value = entry.getChanges()[0].getValue();
    if (value != null
        && value.getMessage() != null
        && value.getLink() != null
        && value.getPostId() != null) {

      String link = queryPostLink(pageId, value.getPostId());
      String message = value.getMessage();
      String photo = value.getLink();

      return Optional.of(new Hook(message, new Embed[] {new Embed(link, link, new Image(photo))}));
    } else {
      return Optional.empty();
    }
  }

  private static String queryPostLink(String pageId, String postId) {
    return "https://www.facebook.com/permalink.php?story_fbid=" + postId + "&id=" + pageId;
  }
}
