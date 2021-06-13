package pl.ostrowski.discordfacebookpost.discord;

import pl.ostrowski.discordfacebookpost.facebook.Feed;

public class HookGenerator {

  public static Hook generate(Feed feed) {
    return new Hook(
        feed.getValue().getMessage(),
        new Embed[] {
          new Embed(
              feed.getValue().getLink(),
              feed.getValue().getLink(),
              new Image(feed.getValue().getPhoto()))
        });
  }
}
