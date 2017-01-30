/*
 * file:	CandleStickTutorial.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	60
 */

//package declaration
package ch.nolix.commonTutorial.financeTutorial;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.container.SequencePattern;
import ch.nolix.common.finance.DataProvider;
import ch.nolix.common.finance.NYSEProductSymbolManager;
import ch.nolix.common.finance.VolumeCandleStick;
import ch.nolix.common.util.Time;

//class
/**
 * This class provides a tutorial for the candle stick class.
 * 
 * @author Silvan Wyss
 */
public final class CandleStickTutorial {

	//main method
	/**
	 * Loads all daily candle sticks of the Boeing.inc share of the year 2015.
	 * The candle sticks contain the real data.
	 * It is supposed to be probable, that after a hammer a bullish follows.
	 * A hammer is a candle stick of a special pattern and a bullish is a canlde stick whose closing price is higher than its opening price.
	 * Lets check this thesis by calculating the percentage of the hammers followed by a bullish from all hammers.
	 */
	public static void main(String[] args) {
		
		//Loads the candle sticks.
		final List<VolumeCandleStick> candleSticks
		= DataProvider.getDailyCandleSticks(
			NYSEProductSymbolManager.BOEING,
			new Time(2015, 1, 1),
			new Time(2015, 12, 31)
		);
		
		//Calculates the percentage.	
		final double percentage
		= candleSticks.getSequences(
			new SequencePattern<VolumeCandleStick>()
			.addConditionForNext(cs -> cs.isHammer())
			.addBlankForNext()
		)
		.getPercentage(s -> s.getRefAt(2).isBullish());
	
		//Prints out the percentage to the console.
		System.out.format("hammers followed by a bullish from all hammers: %.2f %%", percentage);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private CandleStickTutorial() {}
}
