package trader.client.clientservice;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trader.client.clientservice.trader.model.Company;
import trader.client.clientservice.trader.model.Matrix;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDashboard {

	private List<Company> companies;
	private List<Matrix> matrix;
}
