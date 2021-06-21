package pl.ostrowski.discordfacebookpost.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ostrowski.discordfacebookpost.sender.dto.Hook;

@RequiredArgsConstructor
@Service
@Slf4j
public class DiscordSender implements Sender {

  private final RestTemplate restTemplate;

  @Value("${discord_webhook_id}")
  private String discordWebhookId;

  @Value("${discord_webhook_token}")
  private String discordWebhookToken;

  @Override
  public void send(Hook hook) {
    log.info("Sending " + hook);
    String discordUrl = queryUrl();
    restTemplate.postForEntity(discordUrl, hook, Object.class);
  }

  private String queryUrl() {
    if (discordWebhookId == null || discordWebhookToken == null)
      throw new IllegalStateException("Discord parameters not set");
    return "https://discord.com/api/webhooks/" + discordWebhookId + "/" + discordWebhookToken;
  }
}
