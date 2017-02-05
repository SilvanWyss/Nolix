/*
 * file:	CandleStick.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	360
 */

//package declaration
package ch.nolix.common.finance;

//own imports
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.common.util.Time;
import ch.nolix.common.util.Validator;

//class
public class CandleStick {
	
	//constants
	public final static double DEFAULT_HAMMER_MIN_LOWER_WICK_LENGTH_RATIO = 0.5;
	public final static double DEFAULT_INVERTED_HAMMER_MIN_UPPER_WICK_LENGT_RATIO = 0.5;

	//attributes
	private final Time time;
	private final double openingPrice;
	private final double closingPrice;
	private final double lowestPrice;
	private final double highestPrice;
	
	//constructor
	/**
	 * Creates new candle stick with the given time, the given opening price, the given closing price, the given lowest price and the given highest price.
	 * 
	 * @param time
	 * @param openingPrice
	 * @param closingPrice
	 * @param lowestPrice
	 * @param highestPrice
	 * @throws Exception if:
	 * -The given opening price is negative.
	 * -The given closing price is negative.
	 * -The given lowest price is negative.
	 * -The given highest price is negative.
	 */
	public CandleStick(
		final Time time,
		final double openingPrice,
		final double closingPrice,
		final double lowestPrice,
		final double highestPrice
	) {
		//Checks the given parameters.
		Validator.throwExceptionIfValueIsNegative("opening price", openingPrice);
		Validator.throwExceptionIfValueIsNegative("closing price", closingPrice);
		Validator.throwExceptionIfValueIsNegative("lowest price", lowestPrice);
		Validator.throwExceptionIfValueIsNegative("highest price", highestPrice);
		
		//Sets the values of this candle stick.
		this.time = time.getCopy();
		this.time.freeze();
		this.openingPrice = openingPrice;
		this.closingPrice = closingPrice;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
	}
	
	//method
	/**
	 * The body of a candle stick is the area between its opening price and its closing price.
	 * 
	 * @param candleStick
	 * @return true if the body of this candle stick is above the body of the given candle stick
	 */
	public final boolean bodyIsAboveBodyOf(final CandleStick candleStick) {
		return (
			Calculator.getMin(getOpeningPrice(), getClosingPrice())
			> Calculator.getMax(candleStick.getOpeningPrice(), candleStick.getClosingPrice())
		);
	}
	
	//method
	/**
	 * The body of a candle stick is the area between its opening price and its closing price.
	 * 
	 * @param candleStick
	 * @return true if the body of this candle stick is below the body of the given candle stick
	 */
	public final boolean bodyIsBelowBodyOf(final CandleStick candleStick) {
		return (
			Calculator.getMax(getOpeningPrice(), getClosingPrice())
			< Calculator.getMin(candleStick.getOpeningPrice(), candleStick.getClosingPrice())
		);
	}
	
	//method
	/**
	 * The body of a candle stick is the area between its opening price and its closing price.
	 * 
	 * @return the length of the body of this candle stick
	 */
	public final double getBodyLength() {
		return (Math.abs(getClosingPrice() - getOpeningPrice()));
	}
	
	//method
	/**
	 * @return the difference of the closing price and the opening price of this candle stick
	 */
	public final double getChange() {
		return (getClosingPrice() - getOpeningPrice());
	}
	
	//method
	/**
	 * @return the ratio of the change to the opening price of this candle stick
	 */
	public final double getChangeRatio() {
		return (getChange() / getOpeningPrice());
	}
	
	//method
	/**
	 * @return the closing price of this candle stick
	 */
	public final double getClosingPrice() {
		return closingPrice;
	}
	
	//method
	/**
	 * @return the highest price of this candle stick
	 */	
	public final double getHighestPrice() {
		return highestPrice;
	}
	
	//method
	/**
	 * @return the length of this candle stick
	 */
	public final double getLength() {
		return (getHighestPrice() - getLowestPrice());
	}
	
	//method
	/**
	 * @return the lowest price of this candle stick
	 */	
	public final double getLowestPrice() {
		return lowestPrice;
	}
	
	//method
	/**
	 * @return the length of the lower wick of this candle stick
	 */
	public final double getLowerWick() {
		
		if (isBullish()) {
			return (getOpeningPrice() - getLowestPrice());
		}
		
		return (getClosingPrice() - getLowestPrice());
	}
	
	//method
	/**
	 * @return the opening price of this candle stick
	 */	
	public final double getOpeningPrice() {
		return openingPrice;
	}

	//method
	/**
	 * @return the time of this candle stick
	 */	
	public final Time getRefTime() {
		return time;
	}
	
	//method
	/**
	 * This method returns a norm n that indicates the trend of this candle stick.
	 * nϵ[-1,-1]
	 * n=-1 ↔ perfect fall
	 * n=0 ↔ perfect immobility
	 * n=1 ↔ perfect raise
	 * 
	 * @return a norm that indicates the trend of this candle stick
	 */
	public final double getTrend() {
		return ((getClosingPrice() - getOpeningPrice()) / (getHighestPrice() - getLowestPrice()));
	}
	
	//method
	/**
	 * @return the length of the upper wick of this candle stick
	 */
	public final double getUpperWick() {
		
		if (isBullish()) {
			return (getHighestPrice() - getClosingPrice());
		}
		
		return (getHighestPrice() - getOpeningPrice());
	}
	
	//method
	/**
	 * A bearish candle stick is a candle stick whose closing price is smaller than its opening price.
	 * 
	 * @return true if this candle stick is a bearish candle stick
	 */
	public final boolean isBearish() {
		return (getClosingPrice() - getOpeningPrice() < 0);
	}
	
	//method
	/**
	 * A bullish candle stick is a candle stick whose closing price is higher than its opening price.
	 * 
	 * @return true if this candle stick is a bullish candle stick
	 */
	public final boolean isBullish() {
		return (getClosingPrice() - getOpeningPrice() > 0);
	}
	
	//method
	/**
	 * A candle stick is a doji when its opening price and closing price are the same.
	 * 
	 * @return true if this candle stick is a doji.
	 */
	public final boolean isDoji() {
		return Calculator.equalsApproximatively(getOpeningPrice(), getClosingPrice());
	}
	
	//method
	/**
	 * A candle stick is a hammer if:
	 * -The length of the candle stick is positive. (That means the highest price is bigger than the lowest price.)
	 * -The lower wick of the candle stick is bigger than its upper wick.
	 * -The lower wick to length ratio of the candle stick is bigger than or equal to a default value.
	 * 
	 * @return true if this candle stick is a hammer
	 */
	public final boolean isHammer() {
		return isHammer(DEFAULT_HAMMER_MIN_LOWER_WICK_LENGTH_RATIO);
	}
	
	//method
	/**
	 * A candle stick is a hammer if:
	 * -The length of the candle stick is positive. (That means the highest price is bigger than the lowest price.)
	 * -The lower wick of the candle stick is bigger than its upper wick.
	 * -The lower wick to length ratio of the candle stick is bigger than a given value.
	 * 
	 * @param minLowerWickLengthRatio
	 * @return true if this candle stick is a hammer whose lower wick to length ratio is bigger than or equal to the given min lower wick length ratio
	 * @throws Exception if the given min lower wick length ratio is negative
	 */
	public final boolean isHammer(final double minLowerWickLengthRatio) {
		
		//Checks the given min lower wick length ratio.
		Validator.throwExceptionIfValueIsNegative("min lower wick length ratio", minLowerWickLengthRatio);

		//Calculates the needed characteristic values.
		final double length = getLength();
		final double lowerWick = getLowerWick();
		final double upperWick = getUpperWick();
		
		//Handles the case if the length of this candle stick is 0.
		if (length == 0) {
			return false;
		}
		
		//Handles the case if the length of this candle stick is positive.
		return (
			lowerWick > upperWick
			&& lowerWick / length >= minLowerWickLengthRatio
		);
	}
	
	//method
	/**
	 * A candle stick is an inverted hammer if:
	 * -The length of the candle stick is positive. (That means the highest price is bigger than the lowest price.)
	 * -The upper wick of the candle stick is bigger than its lower wick.
	 * -The upper wick to length ratio of the candle stick is bigger than or equal to a default value.
	 * 
	 * @return true if this candle stick is a hammer
	 */
	public final boolean isInvertedHammer() {
		return isInvertedHammer(DEFAULT_INVERTED_HAMMER_MIN_UPPER_WICK_LENGT_RATIO);
	}
	
	//method
	/**
	 * A candle stick is an inverted hammer if:
	 * -The length of the candle stick is positive. (That means the highest price is bigger than the lowest price.)
	 * -The upper wick of the candle stick is bigger than its lower wick.
	 * -The upper wick to length ratio of the candle stick is bigger than a given value.
	 * 
	 * @param minUpperWickLengthRatio
	 * @return true if this candle stick is a hammer whose upper wick to length ratio is bigger than or equal to the given min upper wick length ratio
	 * @throws Exception if the given min upper wick length ratio is negative
	 */
	public final boolean isInvertedHammer(final double minUpperWickLengthRatio) {
		
		//Checks the given min upper wick length ratio.
		Validator.throwExceptionIfValueIsNegative("min upper wick length ratio", minUpperWickLengthRatio);
		
		//Calculates the needed characteristic values.
		final double length = getLength();
		final double lowerWick = getLowerWick();
		final double upperWick = getUpperWick();
		
		//Handles the case if the length of this candle stick is 0.
		if (length == 0) {
			return false;
		}
		
		//Handles the case if the length of this candle stick is positive.
		return (
			upperWick > lowerWick
			&& upperWick / length >= minUpperWickLengthRatio
		);
	}
	
	//method
	/**
	 * A candle stick is a marubozu if its body is as long as itself.
	 * 
	 * @return true if this candle stick is a marubozu
	 */
	public final boolean isMarubozu() {
		return Calculator.equalsApproximatively(getBodyLength(), getLength());
	}
	
	//method
	/**
	 * @return a string representation of this candle stick.
	 */
	public String toString() {
		return (
			getClass().getSimpleName()
			+ "("
			+ "Time(" + getRefTime()+ ")"
			+ ","
			+ "OpeningPrice(" + getOpeningPrice() + ")"
			+ ","
			+ "ClosingPrice(" + getClosingPrice() + ")"
			+ ","
			+ "LowestPrice(" + getLowestPrice() + ")"
			+ ","
			+ "HighestPrice(" + getHighestPrice() + ")"
			+ ")"
		);
	}
}
