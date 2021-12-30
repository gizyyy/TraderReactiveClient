package trader.client.clientservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import trader.client.clientservice.trader.client.TradeResourceApi;
import trader.client.clientservice.trader.model.Company;
import trader.client.clientservice.trader.model.Matrix;

@RestController
@RequestMapping("/tradeclient")
public class TraderClientResource {

	private static final String OVERVIEW_V1 = "/overview/v1";
	private static final String OVERVIEW_V2 = "/overview/v2";

	private static final String TRADER_API_BASE_PATH = "http://localhost:8080/";

	@Autowired
	private TradeResourceApi traderApi;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(OVERVIEW_V1)
	public ResponseEntity<ClientDashboard> getDashboardRestTemplate() {

		List<Company> copanies = restTemplate.getForObject(TRADER_API_BASE_PATH + "trade/companies", ArrayList.class);
		List<Matrix> matrix = restTemplate.getForObject(TRADER_API_BASE_PATH + "trade/matrix", ArrayList.class);

		final ClientDashboard dashboard = new ClientDashboard();
		dashboard.setCompanies(copanies);
		dashboard.setMatrix(matrix);

		return ResponseEntity.ok().body(dashboard);
	}

	@GetMapping(OVERVIEW_V2)
	public ResponseEntity<Mono<ClientDashboard>> getDashboardReactiveWebClient() {

		Flux<Company> companies = traderApi.getCompanies();
		Flux<Matrix> matrix = traderApi.getMatrix();

		Flux<ClientDashboard> zip = Flux.zip(companies.collectList(), matrix.collectList(),
				(co, ma) -> new ClientDashboard(co, ma));

		return ResponseEntity.ok().body(zip.single());
	}

}
