package pl.ostrowski.discordfacebookpost.discord;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordSender {
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${discord_webhook_id}")
  private String discordWebhookId;

  @Value("${discord_webhook_token}")
  private String discordWebhookToken;

  public void send(Hook hook) {
    String discordUrl = queryDiscordUrl();
    restTemplate.postForEntity(discordUrl, hook, Object.class);
  }

  private String queryDiscordUrl() {
    if (discordWebhookId == null && discordWebhookToken == null)
      throw new IllegalStateException("Discord parameters not set");
    return "https://discord.com/api/webhooks/" + discordWebhookId + "/" + discordWebhookToken;
  }
}
