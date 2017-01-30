/*
 * file:	FinanceDataProviderTest.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	50
 */

//package declaration
package ch.nolix.commonTest.financeTest;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.container.Pair;
import ch.nolix.common.finance.CandleStick;
import ch.nolix.common.finance.DataProvider;
import ch.nolix.common.finance.NASDAQProductSymbolManager;
import ch.nolix.common.finance.VolumeCandleStick;
import ch.nolix.common.util.Time;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the finance data provider class.
 */
public final class DataProviderTest extends ZetaTest {

	//test method
	public final void testGetDailyClosingPrices() {
		
		final List<Pair<Time, Double>> dailyClosingPrices = DataProvider.getDailyClosingPrices(
			NASDAQProductSymbolManager.FACEBOOK,
			new Time(2015, 1, 1),
			new Time(2016, 1, 1)
		);
		
		for (Pair<Time, Double> dcp: dailyClosingPrices) {
			expectThat(dcp.getRefElement2().doubleValue()).isNotNegative();
		}
	}
	
	//test method
	public void testGetDailyClosingPrices2() {
		
		//execution part 1
		final List<Pair<Time, Double>> dailyClosingPrices1 = DataProvider.getDailyClosingPrices(
			NASDAQProductSymbolManager.FACEBOOK,
			new Time(2015, 1, 1),
			new Time(2016, 1, 1)
		);
		
		//execution part 2
		final List<Pair<Time, Double>> dailyClosingPrices2
		= DataProvider.getDailyClosingPrices(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2015, 1, 1),
			new Time(2016, 1, 1)
		);

		//verification
		expectThat(dailyClosingPrices1.getSize()).equals(dailyClosingPrices2.getSize());
	}
	
	//test method
	public void testGetDailyCandleSticks() {
		
		//execution
		final List<VolumeCandleStick> dailyCandleSticks 
		= DataProvider.getDailyCandleSticks(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2015, 1, 1),
			new Time(2016, 1, 1)
		);
		
		//verification
		for (CandleStick dcs: dailyCandleSticks) {

			expectThat(dcs.getLowestPrice()).isSmallerThanOrEqualTo(dcs.getOpeningPrice());
			expectThat(dcs.getLowestPrice()).isSmallerThanOrEqualTo(dcs.getClosingPrice());
			expectThat(dcs.getLowestPrice()).isSmallerThanOrEqualTo(dcs.getHighestPrice());
			
			expectThat(dcs.getHighestPrice()).isBiggerThanOrEqual(dcs.getOpeningPrice());
			expectThat(dcs.getHighestPrice()).isBiggerThanOrEqual(dcs.getClosingPrice());
			expectThat(dcs.getHighestPrice()).isBiggerThanOrEqual(dcs.getLowestPrice());
		}
	}
}
