package pl.ostrowski.discordfacebookpost.discord;

import lombok.Value;

@Value
public class Embed {
  String title;
  String url;
  Image image;
}
