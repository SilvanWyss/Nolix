//package declaration
package ch.nolix.elementtest.tradingtest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.element.time.base.Time;
import ch.nolix.element.trading.VolumeCandleStick;

//class
/**
* A {@link VolumeCandleStickTest} is a test for {@link VolumeCandleStick}s
* 
* @author Silvan Wyss
* @date 2021-02-27
* @lines 80
*/
public final class VolumeCandleStickTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var testUnit =
		new VolumeCandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 50_000, 100.0, 110.0, 95.0, 115.0);
		
		//verification
		expect(testUnit.getVolume()).isEqualTo(50_000);
		expect(testUnit.getOpeningPrice()).isEqualTo(100.0);
		expect(testUnit.getClosingPrice()).isEqualTo(110.0);
		expect(testUnit.getLowestPrice()).isEqualTo(95.0);
		expect(testUnit.getHighestPrice()).isEqualTo(115.0);
	}
	
	//method
	@TestCase
	public void testCase_hasBiggerVolumeThan_whenHasBiggerVolume() {
		
		//setup
		final var testUnit =
		new VolumeCandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 50_000, 100.0, 105.5, 100.0, 105.5);
		final var volumneCandleStick =
		new VolumeCandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 40_000, 100.0, 105.5, 100.0, 105.5);
		
		//execution
		final var result = testUnit.hasBiggerVolumeThan(volumneCandleStick);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_hasBiggerVolumeThan_whenHasSameVolume() {
		
		//setup
		final var testUnit =
		new VolumeCandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 50_000, 100.0, 105.5, 100.0, 105.5);
		final var volumneCandleStick =
		new VolumeCandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 50_000, 100.0, 105.5, 100.0, 105.5);
		
		//execution
		final var result = testUnit.hasBiggerVolumeThan(volumneCandleStick);
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_hasBiggerVolumeThan_whenHasSmallerVolume() {
		
		//setup
		final var testUnit =
		new VolumeCandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 50_000, 100.0, 105.5, 100.0, 105.5);
		final var volumneCandleStick =
		new VolumeCandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 60_000, 100.0, 105.5, 100.0, 105.5);
		
		//execution
		final var result = testUnit.hasBiggerVolumeThan(volumneCandleStick);
		
		//verification
		expectNot(result);
	}
}
