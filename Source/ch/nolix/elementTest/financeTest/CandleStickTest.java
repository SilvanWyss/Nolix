//package declaration
package ch.nolix.elementTest.financeTest;

//own imports
import ch.nolix.core.zetaTest.ZetaTest;
import ch.nolix.element.basic.Time;
import ch.nolix.element.finance.CandleStick;

//test class
/**
 * This class is a test class for the candle stick class.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 100
 */
public final class CandleStickTest extends ZetaTest {
	
	//test method
	public void test_isBearish_1() {
		
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
	public void test_isBearish_2() {
		
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
	public void test_isBullish_1() {
		
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
	public void test_isBullish_2() {
		
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
	public void test_isHammer() {
		
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
