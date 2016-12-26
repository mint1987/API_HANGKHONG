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
	private WaitFor waitFor;
	private PageUtil pageUtil;

	public FlightSearchEvent(WebDriver driver, FlightSearchDetail flightSearchDetail) {
		this.driver = driver;
		this.flightSearchDetail = flightSearchDetail;
		this.flightSearchPage = new FlightSearchPage(driver);
		this.waitFor = new WaitFor(driver);
		this.pageUtil = new PageUtil(driver);
	}

	public void openFlightSearchPage() throws InterruptedException {
		pageUtil.clickMenu(flightSearchPage.getFlightSearchPageParMenuId(),
				flightSearchPage.getFlightSearchPageSubMenuId());
		
		waitFor.presenceOfTheElement(By.cssSelector(flightSearchPage.getSearchBoxTitleElementCss()));
		
		pageUtil.isClickMenuSuccess(flightSearchPage.getSearchBoxTitle(),
				flightSearchPage.getSearchBoxTitleElementCss());
	}
	
	public void invalidFlightDate_noDataFound() throws InterruptedException{
		flightSearchPage.enterFlightDate(flightSearchDetail.getFlightDate());
		flightSearchPage.clickSearchButton();
		Thread.sleep(2000);
		String elementId = "#ctl00_PlaceHolderMain_ucHCFCImport_gvListFlight > tbody > .GridPager:nth-child(1)";
		String compareText = "Không có bản ghi nào";
		
		pageUtil.isResultValid(compareText, elementId);
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
