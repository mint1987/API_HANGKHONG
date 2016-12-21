package com.event;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.detail.FlightSearchDetail;
import com.page.FlightSearchPage;

import util.OracleJDBC;
import util.PageUtil;
import util.WaitFor;

public class FlightSearchEvent {
	private WebDriver driver;
	private FlightSearchPage flightSearchPage;
	private FlightSearchDetail flightSearchDetail;

	public FlightSearchEvent(WebDriver driver, FlightSearchDetail flightSearchDetail) {
		this.driver = driver;
		this.flightSearchDetail = flightSearchDetail;
		this.flightSearchPage = new FlightSearchPage(driver);
	}

	public void openFlightSearchPage() throws InterruptedException {
		PageUtil pageUtil = new PageUtil(driver);
		pageUtil.clickMenu(flightSearchPage.getFlightSearchPageParMenuId(), flightSearchPage.getFlightSearchPageSubMenuId());
		
		WaitFor waitFor = new WaitFor(driver);
		waitFor.presenceOfTheElement(By.id(flightSearchPage.getSearchBoxTitleElementId()));
		
		pageUtil.isClickMenuSuccess(flightSearchPage.getSearchBoxTitle(), flightSearchPage.getSearchBoxTitleElementId());
	}

	public void Search() throws InterruptedException {
		flightSearchPage.enterFlightDate(flightSearchDetail.getFlightDate());
		flightSearchPage.enterAirlinesCode(flightSearchDetail.getAirlinesCode());
		// flightSearchPage.selectAirlinesName(flightSearchDetail.getAirlinesValue());
		flightSearchPage.enterFlightNumber(flightSearchDetail.getFlightNumber());
		flightSearchPage.enterDepartureCode(flightSearchDetail.getDepartureCode());
		// flightSearchPage.selectDepartureName(flightSearchDetail.getDepartureValue());
		flightSearchPage.enterDestinationCode(flightSearchDetail.getDestinationCode());
		// flightSearchPage.selectDestinationName(flightSearchDetail.getDestinationValue());
		flightSearchPage.enterGate(flightSearchDetail.getGate());
		flightSearchPage.selectFlightType(flightSearchDetail.getFlightType());

		flightSearchPage.clickSearchButton();
	}

	public void getFlightsListFromDB() {
		OracleJDBC connectDB = new OracleJDBC();

		try {
			connectDB.selectRecordsFromDbFlightTable(flightSearchDetail);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
