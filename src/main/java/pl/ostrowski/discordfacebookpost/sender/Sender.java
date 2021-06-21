package pl.ostrowski.discordfacebookpost.sender;

import pl.ostrowski.discordfacebookpost.sender.dto.Hook;

public interface Sender {
  void send(Hook hook);
}
