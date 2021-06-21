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
public class PageSender implements Sender {

  private final RestTemplate restTemplate;

  @Value("${page_url}")
  private String pageUrl;

  @Value("${page_token}")
  private String pageToken;

  @Override
  public void send(Hook hook) {
    log.info("Sending " + hook);
    String discordUrl = queryUrl();
    restTemplate.postForEntity(discordUrl, hook, Object.class);
  }

  private String queryUrl() {
    if (pageUrl == null || pageToken == null)
      throw new IllegalStateException("Page parameters not set");
    return pageUrl + "/api/webhooks/facebook" + pageToken;
  }
}
