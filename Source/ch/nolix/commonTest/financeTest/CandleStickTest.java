/*
 * file:	CandleStickTest.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	100
 */

//package declaration
package ch.nolix.commonTest.financeTest;

//own imports
import ch.nolix.common.finance.CandleStick;
import ch.nolix.common.util.Time;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the candle stick class.
 */
public final class CandleStickTest extends ZetaTest {
	
	//test method
	public void testIsBearish1() {
		
		//setup
		final CandleStick candleStick
		= new CandleStick(
		   new Time(2020, 1, 1),
			100.0,
			200.0,
			50.0,
			250.0
		);
		
		//execution and verification
		expectThatNot(candleStick.isBearish());
	}
	
	//test method
	public void testIsBearish2() {
		
		//setup
		final CandleStick candleStick
		= new CandleStick(
		   new Time(2020, 1, 1),
			200.0,
			100.0,
			50.0,
			250.0
		);
		
		//execution and verification
		expectThat(candleStick.isBearish());
	}

	//test method
	public void testIsBullish1() {
		
		//setup
		final CandleStick candleStick
		= new CandleStick(
		   new Time(2020, 1, 1),
			100.0,
			200.0,
			50.0,
			250.0
		);
		
		//execution and verification
		expectThat(candleStick.isBullish());
	}
	
	//test method
	public void testIsBullish2() {
		
		//setup
		final CandleStick candleStick
		= new CandleStick(
		   new Time(2020, 1, 1),
			200.0,
			100.0,
			50.0,
			250.0
		);
		
		//execution and verification
		expectThatNot(candleStick.isBullish());
	}
	
	//test method
	public void testIsHammer() {
		
		//setup
		final CandleStick candleStick
		= new CandleStick(
		   new Time(2020, 1, 1),
			200.0,
			210.0,
			100.0,
			210.0
		);
		
		//execution and verification
		expectThat(candleStick.isHammer());
	}
}
