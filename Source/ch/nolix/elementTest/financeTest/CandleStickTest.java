//package declaration
package ch.nolix.elementTest.financeTest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.element.finance.Candlestick;
import ch.nolix.element.time.Time;

//class
/**
 * This class is a test class for the candle stick class.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 100
 */
public final class CandleStickTest extends Test {
	
	//method
	@TestCase
	public void testCase_isBearish_1() {
		
		//setup
		final Candlestick candleStick
		= new Candlestick(
		 new Time(2020, 1, 1),
			100.0,
			200.0,
			50.0,
			250.0
		);
		
		//execution & verification
		expectNot(candleStick.isBearish());
	}
	
	//method
	@TestCase
	public void testCase_isBearish_2() {
		
		//setup
		final Candlestick candleStick
		= new Candlestick(
		 new Time(2020, 1, 1),
			200.0,
			100.0,
			50.0,
			250.0
		);
		
		//execution & verification
		expect(candleStick.isBearish());
	}

	//method
	@TestCase
	public void testCase_isBullish_1() {
		
		//setup
		final Candlestick candleStick
		= new Candlestick(
		 new Time(2020, 1, 1),
			100.0,
			200.0,
			50.0,
			250.0
		);
		
		//execution & verification
		expect(candleStick.isBullish());
	}
	
	//method
	@TestCase
	public void testCase_isBullish_2() {
		
		//setup
		final Candlestick candleStick
		= new Candlestick(
		 new Time(2020, 1, 1),
			200.0,
			100.0,
			50.0,
			250.0
		);
		
		//execution & verification
		expectNot(candleStick.isBullish());
	}
	
	//method
	@TestCase
	public void testCase_isHammer() {
		
		//setup
		final Candlestick candleStick
		= new Candlestick(
		 new Time(2020, 1, 1),
			200.0,
			210.0,
			100.0,
			210.0
		);
		
		//execution & verification
		expect(candleStick.isHammer());
	}
}
