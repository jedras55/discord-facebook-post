package pl.ostrowski.discordfacebookpost.discord;

import lombok.Value;

@Value
public class Hook {
  String content;
  Embed[] embeds;
}
