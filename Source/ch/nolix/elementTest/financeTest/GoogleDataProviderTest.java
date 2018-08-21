//package declaration
package ch.nolix.elementTest.financeTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.core.Time;
import ch.nolix.element.finance.GoogleDataProvider;
import ch.nolix.element.finance.NASDAQProductSymbolManager;
import ch.nolix.element.finance.VolumeCandlestick;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * This class is a test class for the {@link GoogleDataProvider} class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 90
 */
public final class GoogleDataProviderTest extends Test {

	//test case
	public void test_getCandleSticksPerDay() {
		
		//execution
		final List<VolumeCandlestick> candleSticksPerDay 
		= new GoogleDataProvider().getCandleSticksPerDay(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2015, 1, 1, 0, 0),
			new Time(2015, 12, 31, 23, 59)
		);
		
		//verification
		for (final VolumeCandlestick cspd: candleSticksPerDay) {

			expect(cspd.getLowestPrice()).isSmallerThanOrEquals(cspd.getOpeningPrice());
			expect(cspd.getLowestPrice()).isSmallerThanOrEquals(cspd.getClosingPrice());
			expect(cspd.getLowestPrice()).isSmallerThanOrEquals(cspd.getHighestPrice());
			
			expect(cspd.getHighestPrice()).isBiggerThanOrEquals(cspd.getOpeningPrice());
			expect(cspd.getHighestPrice()).isBiggerThanOrEquals(cspd.getClosingPrice());
			expect(cspd.getHighestPrice()).isBiggerThanOrEquals(cspd.getLowestPrice());
		}
	}
	
	//test case
	public void test_getCandleSticksPerHour() {
		
		//execution
		final List<VolumeCandlestick> candleStricksPerHour 
		= new GoogleDataProvider().getCandleSticksPerHour(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2017, 1, 1, 0, 0),
			new Time(2017, 1, 31, 23, 59)
		);
				
		//verification
		for (final VolumeCandlestick csph: candleStricksPerHour) {

			expect(csph.getLowestPrice()).isSmallerThanOrEquals(csph.getOpeningPrice());
			expect(csph.getLowestPrice()).isSmallerThanOrEquals(csph.getClosingPrice());
			expect(csph.getLowestPrice()).isSmallerThanOrEquals(csph.getHighestPrice());
			
			expect(csph.getHighestPrice()).isBiggerThanOrEquals(csph.getOpeningPrice());
			expect(csph.getHighestPrice()).isBiggerThanOrEquals(csph.getClosingPrice());
			expect(csph.getHighestPrice()).isBiggerThanOrEquals(csph.getLowestPrice());
		}
	}
	
	//test case
	public void test_getCandleSticksPerMinute() {
		
		//execution
		final List<VolumeCandlestick> candleStricksPerMinute 
		= new GoogleDataProvider().getCandleSticksPerHour(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2017, 1, 1, 0, 0),
			new Time(2017, 1, 31, 23, 59)
		);
				
		//verification
		for (final VolumeCandlestick cspm: candleStricksPerMinute) {

			expect(cspm.getLowestPrice()).isSmallerThanOrEquals(cspm.getOpeningPrice());
			expect(cspm.getLowestPrice()).isSmallerThanOrEquals(cspm.getClosingPrice());
			expect(cspm.getLowestPrice()).isSmallerThanOrEquals(cspm.getHighestPrice());
			
			expect(cspm.getHighestPrice()).isBiggerThanOrEquals(cspm.getOpeningPrice());
			expect(cspm.getHighestPrice()).isBiggerThanOrEquals(cspm.getClosingPrice());
			expect(cspm.getHighestPrice()).isBiggerThanOrEquals(cspm.getLowestPrice());
		}
	}
}
