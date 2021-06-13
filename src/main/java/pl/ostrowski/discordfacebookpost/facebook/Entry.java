package pl.ostrowski.discordfacebookpost.facebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Entry {
  private String id;
  private Feed[] changes;
}
