package trader.client.clientservice;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import trader.client.clientservice.trader.api.ApiClient;
import trader.client.clientservice.trader.client.TradeResourceApi;

@Configuration
public class ClientConfig {
	@Bean
	public TradeResourceApi tradeResourceApi() {
		final ApiClient client = new ApiClient();
		return new TradeResourceApi(client);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 
	    return builder
	            .setConnectTimeout(Duration.ofMillis(20000))
	            .setReadTimeout(Duration.ofMillis(20000))
	            .build();
	}
}
