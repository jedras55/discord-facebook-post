package pl.ostrowski.discordfacebookpost.facebook;

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
  private String photo;

  public void setItem(String item) {
    this.item = Item.valueOf(item.toUpperCase());
  }

  enum Item {
    POST,
    STATUS,
    PHOTO
  }
}
