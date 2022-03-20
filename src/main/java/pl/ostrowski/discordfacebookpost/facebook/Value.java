package pl.ostrowski.discordfacebookpost.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Value {

  private Item item;
  private String link;
  private String message;
  private String url;

  private Verb verb;

  @JsonProperty("post_id")
  private String postId;

  @JsonProperty("video_id")
  private String videoId;

  public void setItem(String item) {
    this.item = Item.valueOf(item.toUpperCase());
  }

  public void setVerb(String verb) {
    this.verb = Verb.valueOf(verb.toUpperCase());
  }

  public enum Item {
    POST,
    STATUS,
    PHOTO,
    COMMENT,
    REACTION,
    VIDEO,
    PICTURE
  }

  public enum Verb {
    ADD,
    BLOCK,
    EDIT,
    EDITED,
    DELETE,
    FOLLOW,
    HIDE,
    MUTE,
    REMOVE,
    UNBLOCK,
    UNHIDE,
    UPDATE
  }
}
