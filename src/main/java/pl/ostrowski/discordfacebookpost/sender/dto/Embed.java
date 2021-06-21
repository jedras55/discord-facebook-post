package pl.ostrowski.discordfacebookpost.sender.dto;

import lombok.Value;

@Value
public class Embed {
  String title;
  String url;
  Image image;
}
