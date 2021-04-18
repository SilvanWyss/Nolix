package ch.nolix.elementtutorial.financetutorial;

import ch.nolix.common.container.SequencePattern;
import ch.nolix.element.time.Time;
import ch.nolix.element.trading.CandleStick;
import ch.nolix.element.trading.NYSEProductSymbolCatalogue;
import ch.nolix.element.trading.QuandlDataProvider;
import ch.nolix.element.trading.VolumeCandleStick;

/**
 * The {@link CandleStickTutorial} is a tutorial for {@link CandleStick}s.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 50
 */
public final class CandleStickTutorial {
	
	/**
	 * Loads all daily candle sticks of the Boeing.inc share of the year 2015.
	 * The candle sticks will contain the real data.
	 * It is supposed to be probable, that after a hammer a bullish follows.
	 * A hammer is a candle stick of a special pattern.
	 * A bullish is a candle whose closing price is higher than its opening price.
	 * Lets check this thesis
	 * by calculating the percentage of the hammers followed by a bullish in relation to all hammers.
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		
		//Loads the CandleSticks.
		final var candleSticks =
		new QuandlDataProvider()
		.getCandleSticksPerDay(
			NYSEProductSymbolCatalogue.BOEING,
			new Time(2015, 1, 1),
			new Time(2015, 12, 31)
		);
		
		//Calculates the percentage.
		final double percentage =
		candleSticks.getSequences(
			new SequencePattern<VolumeCandleStick>()
			.addConditionForNext(VolumeCandleStick::isHammer)
			.addBlankForNext()
		)
		.getPercentage(s -> s.getRefAt(2).isBullish());
		
		//Prints out the percentage to the console.
		System.out.format("hammers followed by a bullish from all hammers: %.2f %%", percentage);
	}
	
	/**
	 * Prevents that an instance of the {@link CandleStickTutorial} can be created.
	 */
	private CandleStickTutorial() {}
}
