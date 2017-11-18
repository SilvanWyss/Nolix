//package declaration
package ch.nolix.elementTest.financeTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.test2.Test;
import ch.nolix.element.core.Time;
import ch.nolix.element.finance.DataProvider;
import ch.nolix.element.finance.NASDAQProductSymbolManager;
import ch.nolix.element.finance.VolumeCandlestick;

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
		final List<VolumeCandlestick> candleSticksPerDay 
		= new DataProvider().getCandleSticksPerDay2(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2015, 1, 1, 0, 0),
			new Time(2015, 12, 31, 23, 59)
		);
		
		//verification
		for (final VolumeCandlestick cspd: candleSticksPerDay) {

			expect(cspd.getLowestPrice()).isSmallerThanOrEqualTo(cspd.getOpeningPrice());
			expect(cspd.getLowestPrice()).isSmallerThanOrEqualTo(cspd.getClosingPrice());
			expect(cspd.getLowestPrice()).isSmallerThanOrEqualTo(cspd.getHighestPrice());
			
			expect(cspd.getHighestPrice()).isBiggerThanOrEqual(cspd.getOpeningPrice());
			expect(cspd.getHighestPrice()).isBiggerThanOrEqual(cspd.getClosingPrice());
			expect(cspd.getHighestPrice()).isBiggerThanOrEqual(cspd.getLowestPrice());
		}
	}
	
	//test method
	public void test_getCandleSticksPerHour() {
		
		//execution
		final List<VolumeCandlestick> candleStricksPerHour 
		= new DataProvider().getCandleSticksPerHour(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2017, 1, 1, 0, 0),
			new Time(2017, 1, 31, 23, 59)
		);
				
		//verification
		for (final VolumeCandlestick csph: candleStricksPerHour) {

			expect(csph.getLowestPrice()).isSmallerThanOrEqualTo(csph.getOpeningPrice());
			expect(csph.getLowestPrice()).isSmallerThanOrEqualTo(csph.getClosingPrice());
			expect(csph.getLowestPrice()).isSmallerThanOrEqualTo(csph.getHighestPrice());
			
			expect(csph.getHighestPrice()).isBiggerThanOrEqual(csph.getOpeningPrice());
			expect(csph.getHighestPrice()).isBiggerThanOrEqual(csph.getClosingPrice());
			expect(csph.getHighestPrice()).isBiggerThanOrEqual(csph.getLowestPrice());
		}
	}
	
	//test method
	public void test_getCandleSticksPerMinute() {
		
		//execution
		final List<VolumeCandlestick> candleStricksPerMinute 
		= new DataProvider().getCandleSticksPerHour(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2017, 1, 1, 0, 0),
			new Time(2017, 1, 31, 23, 59)
		);
				
		//verification
		for (final VolumeCandlestick cspm: candleStricksPerMinute) {

			expect(cspm.getLowestPrice()).isSmallerThanOrEqualTo(cspm.getOpeningPrice());
			expect(cspm.getLowestPrice()).isSmallerThanOrEqualTo(cspm.getClosingPrice());
			expect(cspm.getLowestPrice()).isSmallerThanOrEqualTo(cspm.getHighestPrice());
			
			expect(cspm.getHighestPrice()).isBiggerThanOrEqual(cspm.getOpeningPrice());
			expect(cspm.getHighestPrice()).isBiggerThanOrEqual(cspm.getClosingPrice());
			expect(cspm.getHighestPrice()).isBiggerThanOrEqual(cspm.getLowestPrice());
		}
	}
}
