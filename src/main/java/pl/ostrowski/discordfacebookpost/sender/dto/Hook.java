package pl.ostrowski.discordfacebookpost.sender.dto;

import java.util.List;
import lombok.Value;

@Value
public class Hook {
  String content;
  Embed[] embeds;

  public List<Hook> clipHooks() {
    if (content.length() > 2000) {
      int indexOfLastTab = content.substring(0, 2000).lastIndexOf("\n");
      return List.of(
          new Hook(content.substring(0, indexOfLastTab), null),
          new Hook(content.substring(indexOfLastTab), embeds));
    }
    return List.of(this);
  }
}
