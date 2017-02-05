//package declaration
package ch.nolix.commonTest.financeTest;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.finance.DataProvider;
import ch.nolix.common.finance.NASDAQProductSymbolManager;
import ch.nolix.common.finance.VolumeCandleStick;
import ch.nolix.common.util.Time;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the finance data provider class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 70
 */
public class DataProviderTest extends ZetaTest {

	//test method
	public void testGetDailyCandleSticks() {
		
		//execution
		final List<VolumeCandleStick> candleSticksPerDay 
		= DataProvider.getCandleSticksPerDay2(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2015, 1, 1),
			new Time(2016, 1, 1)
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
	public void testGetCandleSticksPerHour() {
		
		//execution
		final List<VolumeCandleStick> candleStricksPerHour 
		= DataProvider.getCandleSticksPerHour(
			NASDAQProductSymbolManager.MICROSOFT_CORPORATION,
			new Time(2017, 1, 1),
			new Time(2017, 2, 1)
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
}
