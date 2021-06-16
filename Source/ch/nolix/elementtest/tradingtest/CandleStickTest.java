//package declaration
package ch.nolix.elementtest.tradingtest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.element.time.base.Time;
import ch.nolix.element.trading.CandleStick;

//class
/**
 * A {@link CandleStickTest} is a test for {@link CandleStick}s
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @lines 100
 */
public final class CandleStickTest extends Test {
	
	//method
	@TestCase
	public void testCase_isBearish_whenIsBearish() {
		
		//setup
		final var testUnit =
		new CandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 200.0, 100.0, 50.0, 250.0);
		
		//execution
		final var result = testUnit.isBearish();
		
		//verification
		expect(result);
	}

	//method
	@TestCase
	public void testCase_isBearish_whenIsNotBearish() {
		
		//setup
		final var testUnit =
		new CandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 100.0, 200.0, 50.0, 250.0);
		
		//execution
		final var result = testUnit.isBearish();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_isBullish_whenIsBullish() {
		
		//setup
		final var testUnit =
		new CandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 100.0, 200.0, 50.0, 250.0);
		
		//execution
		final var result = testUnit.isBullish();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isBullish_whenIsNotBullish() {
		
		//setup
		final var testUnit =
		new CandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 200.0, 100.0, 50.0, 250.0);
		
		//execution
		final var result = testUnit.isBullish();
		
		//verification
		expectNot(result);
	}
	
	//method
	@TestCase
	public void testCase_isHammer_whenIsHammer() {
		
		//setup
		final var testUnit =
		new CandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 200.0, 210.0, 100.0,	210.0);
		
		//execution
		final var result = testUnit.isHammer();
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_isHammer_whenIsNotHammer() {
		
		//setup
		final var testUnit =
		new CandleStick(Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1), 200.0, 210.0, 200.0,	210.0);
		
		//execution
		final var result = testUnit.isHammer();
		
		//verification
		expectNot(result);
	}
}
