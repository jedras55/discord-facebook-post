package pl.ostrowski.discordfacebookpost.sender.dto;

import lombok.Value;

@Value
public class Hook {
  String content;
  Embed[] embeds;
}
