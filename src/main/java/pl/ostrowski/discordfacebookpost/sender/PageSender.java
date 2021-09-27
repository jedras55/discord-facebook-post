package pl.ostrowski.discordfacebookpost.sender;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Optional;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ostrowski.discordfacebookpost.sender.dto.Hook;

@RequiredArgsConstructor
@Service
@Slf4j
public class PageSender implements Sender {

  private final RestTemplate restTemplate = restTemplate();

  @Value("${page_url}")
  private String pageUrl;

  @Value("${page_token}")
  private String pageToken;

  public RestTemplate restTemplate() {

    RestTemplateBuilder builder = new RestTemplateBuilder();

    TrustManager[] trustAllCerts =
        new TrustManager[] {
          new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new X509Certificate[0];
            }

            public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {}

            public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {}
          }
        };
    SSLContext sslContext = null;
    try {
      sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
    } catch (NoSuchAlgorithmException | KeyManagementException e) {
      log.error(e.getMessage());
    }

    CloseableHttpClient httpClient =
        HttpClients.custom()
            .setSSLContext(sslContext)
            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
            .build();
    HttpComponentsClientHttpRequestFactory customRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    customRequestFactory.setHttpClient(httpClient);
    return builder.requestFactory(() -> customRequestFactory).build();
  }

  @Override
  public void send(Hook hook) {
    log.info("Sending to page " + hook);
    String discordUrl = queryUrl();
    try {
      HttpStatus status = restTemplate.postForEntity(discordUrl, hook, HttpStatus.class).getBody();
      if (Optional.ofNullable(status).map(HttpStatus::is4xxClientError).orElse(true)) {
        log.error("Forbidden");
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  private String queryUrl() {
    if (pageUrl == null || pageToken == null)
      throw new IllegalStateException("Page parameters not set");
    return pageUrl + "/api/webhooks/facebook/" + pageToken;
  }
}
