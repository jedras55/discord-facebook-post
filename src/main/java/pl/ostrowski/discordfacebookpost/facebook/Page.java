package pl.ostrowski.discordfacebookpost.facebook;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Page {
  private Entry[] entry;
}
