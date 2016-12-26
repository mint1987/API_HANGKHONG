import java.io.IOException;
import org.openqa.selenium.WebDriver;

import com.detail.FlightSearchDetail;
import com.detail.LoginDetail;
import com.event.FlightSearchEvent;
import com.event.LoginEvent;

import util.PageUtil;
import util.PropertiesStore;

public class Runner {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		driver = PageUtil.openBrowser();

		LoginDetail loginDetail = new LoginDetail();
		loginDetail.setUserName(PropertiesStore.getProperty("username"));
		loginDetail.setPassWord(PropertiesStore.getProperty("password"));
		LoginEvent loginEvent = new LoginEvent(driver, loginDetail);
		loginEvent.waitForUsernameTextboxAppear();
		loginEvent.login();
		loginEvent.waitForAccountNameAppear();
		loginEvent.isLoginSuccess(loginDetail.getUserName());

		FlightSearchDetail flightSearchDetail = new FlightSearchDetail();
		FlightSearchEvent flightSearchEvent = new FlightSearchEvent(driver, flightSearchDetail);
		flightSearchEvent.openFlightSearchPage();
//		flightSearchDetail.setFlightDate("07/12/2016");
//		flightSearchDetail.setAirlinesCode("VN");
//		flightSearchDetail.setFlightNumber("036");
//		flightSearchDetail.setDepartureCode("HAN");
//		flightSearchDetail.setDestinationCode("MOW");
//		flightSearchDetail.setGate("6");
//		flightSearchDetail.setFlightType("");
//
//		flightSearchEvent.Search();
//		flightSearchEvent.getFlightsListFromDB();
		
		flightSearchDetail.setFlightDate("30/02/2016");
		flightSearchEvent.invalidFlightDate_noDataFound();
		
	}
}
