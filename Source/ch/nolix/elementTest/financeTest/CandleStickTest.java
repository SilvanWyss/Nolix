//package declaration
package ch.nolix.elementTest.financeTest;

import ch.nolix.element.core.Time;
import ch.nolix.element.finance.Candlestick;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * This class is a test class for the candle stick class.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 100
 */
public final class CandleStickTest extends Test {
	
	//test case
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
	
	//test case
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

	//test case
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
	
	//test case
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
	
	//test case
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
