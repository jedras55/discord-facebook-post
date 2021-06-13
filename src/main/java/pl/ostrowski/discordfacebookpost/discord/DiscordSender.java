package pl.ostrowski.discordfacebookpost.discord;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class DiscordSender {
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${discord_webhook_id}")
  private String discordWebhookId;

  @Value("${discord_webhook_token}")
  private String discordWebhookToken;

  public void send(Hook hook) {
    log.info("Sending " + hook);
    String discordUrl = queryDiscordUrl();
    restTemplate.postForEntity(discordUrl, hook, Object.class);
  }

  private String queryDiscordUrl() {
    if (discordWebhookId == null && discordWebhookToken == null)
      throw new IllegalStateException("Discord parameters not set");
    return "https://discord.com/api/webhooks/" + discordWebhookId + "/" + discordWebhookToken;
  }
}
