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
  private User from;

  @JsonProperty("post_id")
  private String postId;

  public void setItem(String item) {
    this.item = Item.valueOf(item.toUpperCase());
  }

  public enum Item {
    POST,
    STATUS,
    PHOTO,
    COMMENT,
    REACTION
  }
}
