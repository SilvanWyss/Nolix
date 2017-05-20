//package declaration
package ch.nolix.elementTest.financeTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.test2.Test;
import ch.nolix.element.basic.Time;
import ch.nolix.element.finance.DataProvider;
import ch.nolix.element.finance.NASDAQProductSymbolManager;
import ch.nolix.element.finance.VolumeCandleStick;

//test class
/**
 * This class is a test class for the finance data provider class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 90
 */
public final class DataProviderTest extends Test {

	//test method
	public void test_getCandleSticksPerDay2() {
		
		//execution
		final List<VolumeCandleStick> candleSticksPerDay 
		= DataProvider.getCandleSticksPerDay2(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2015, 1, 1, 0, 0),
			new Time(2015, 12, 31, 23, 59)
		);
		
		//verification
		for (final VolumeCandleStick cspd: candleSticksPerDay) {

			expectThat(cspd.getLowestPrice()).isSmallerThanOrEqualTo(cspd.getOpeningPrice());
			expectThat(cspd.getLowestPrice()).isSmallerThanOrEqualTo(cspd.getClosingPrice());
			expectThat(cspd.getLowestPrice()).isSmallerThanOrEqualTo(cspd.getHighestPrice());
			
			expectThat(cspd.getHighestPrice()).isBiggerThanOrEqual(cspd.getOpeningPrice());
			expectThat(cspd.getHighestPrice()).isBiggerThanOrEqual(cspd.getClosingPrice());
			expectThat(cspd.getHighestPrice()).isBiggerThanOrEqual(cspd.getLowestPrice());
		}
	}
	
	//test method
	public void test_getCandleSticksPerHour() {
		
		//execution
		final List<VolumeCandleStick> candleStricksPerHour 
		= DataProvider.getCandleSticksPerHour(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2017, 1, 1, 0, 0),
			new Time(2017, 1, 31, 23, 59)
		);
				
		//verification
		for (final VolumeCandleStick csph: candleStricksPerHour) {

			expectThat(csph.getLowestPrice()).isSmallerThanOrEqualTo(csph.getOpeningPrice());
			expectThat(csph.getLowestPrice()).isSmallerThanOrEqualTo(csph.getClosingPrice());
			expectThat(csph.getLowestPrice()).isSmallerThanOrEqualTo(csph.getHighestPrice());
			
			expectThat(csph.getHighestPrice()).isBiggerThanOrEqual(csph.getOpeningPrice());
			expectThat(csph.getHighestPrice()).isBiggerThanOrEqual(csph.getClosingPrice());
			expectThat(csph.getHighestPrice()).isBiggerThanOrEqual(csph.getLowestPrice());
		}
	}
	
	//test method
	public void test_getCandleSticksPerMinute() {
		
		//execution
		final List<VolumeCandleStick> candleStricksPerMinute 
		= DataProvider.getCandleSticksPerHour(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2017, 1, 1, 0, 0),
			new Time(2017, 1, 31, 23, 59)
		);
				
		//verification
		for (final VolumeCandleStick cspm: candleStricksPerMinute) {

			expectThat(cspm.getLowestPrice()).isSmallerThanOrEqualTo(cspm.getOpeningPrice());
			expectThat(cspm.getLowestPrice()).isSmallerThanOrEqualTo(cspm.getClosingPrice());
			expectThat(cspm.getLowestPrice()).isSmallerThanOrEqualTo(cspm.getHighestPrice());
			
			expectThat(cspm.getHighestPrice()).isBiggerThanOrEqual(cspm.getOpeningPrice());
			expectThat(cspm.getHighestPrice()).isBiggerThanOrEqual(cspm.getClosingPrice());
			expectThat(cspm.getHighestPrice()).isBiggerThanOrEqual(cspm.getLowestPrice());
		}
	}
}
