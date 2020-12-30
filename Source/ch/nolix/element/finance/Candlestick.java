//package declaration
package ch.nolix.element.finance;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.time.Time;

//class
/**
 * A {@link Candlestick} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 * @lines 430
 */
public class Candlestick extends Element<Candlestick> {
	
	//constants
	public static final double DEFAULT_HAMMER_MIN_LOWER_WICK_LENGTH_RATIO = 0.5;
	public static final double DEFAULT_INVERTED_HAMMER_MIN_UPPER_WICK_LENGT_RATIO = 0.5;

	//constants
	private static final String OPENING_PRICE_HEADER = "OpeningPrice";
	private static final String CLOSING_PRICE_HEADER = "ClosingPrice";
	private static final String LOWEST_PRICE_HEADER = "LowestPrice";
	private static final String HIGHEST_PRICE_HEADER = "HighestPrice";
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link Candlestick} with the given attributes
	 */
	public static Candlestick withAttributes(final Iterable<Node> attributes) {
		
		Time time = null;
		double openingPrice = 0.0;
		double closingPrice = 0.0;
		double lowestPrice = 0.0;
		double highestPrice = 0.0;
		
		for (Node a : attributes) {
			switch (a.getHeader()) {
				case Time.TYPE_NAME:
					time = Time.fromSpecification(a);
					break;
				case OPENING_PRICE_HEADER:
					openingPrice = a.getOneAttributeAsDouble();
					break;
				case CLOSING_PRICE_HEADER:
					closingPrice = a.getOneAttributeAsDouble();
					break;
				case LOWEST_PRICE_HEADER:
					lowestPrice = a.getOneAttributeAsDouble();
					break;
				case HIGHEST_PRICE_HEADER:
					highestPrice = a.getOneAttributeAsDouble();
					break;
				default:
					throw new InvalidArgumentException(a);
			}
		}
		
		return new Candlestick(time, openingPrice, closingPrice, lowestPrice, highestPrice);
	}
	
	//attributes
	private final Time time;
	private final double openingPrice;
	private final double closingPrice;
	private final double lowestPrice;
	private final double highestPrice;
	
	//constructor
	/**
	 * Creates a new {@link Candlestick}
	 * with the given time, opening price, closing price, lowest price and highest price.
	 * 
	 * @param time
	 * @param openingPrice
	 * @param closingPrice
	 * @param lowestPrice
	 * @param highestPrice
	 * @throws ArgumentIsNullException if the given time is null.
	 * @throws NegativeArgumentException if the given opening price is negative.
	 * @throws NegativeArgumentException if the given closing price is negative.
	 * @throws NegativeArgumentException if the given lowest price is negative.
	 * @throws NegativeArgumentException if the given highest price is negative.
	 */
	public Candlestick(
		final Time time,
		final double openingPrice,
		final double closingPrice,
		final double lowestPrice,
		final double highestPrice
	) {
		//Checks the given arguments.
		Validator.assertThat(time).isOfType(Time.class);
		Validator.assertThat(openingPrice).thatIsNamed("opening price").isNotNegative();
		Validator.assertThat(closingPrice).thatIsNamed("closing price").isNotNegative();
		Validator.assertThat(lowestPrice).thatIsNamed("lowest price").isNotNegative();
		Validator.assertThat(highestPrice).thatIsNamed("highest price").isNotNegative();
		
		//Sets the values of the current {@link Candlestick}.
		this.time = time;
		this.openingPrice = openingPrice;
		this.closingPrice = closingPrice;
		this.lowestPrice = lowestPrice;
		this.highestPrice = highestPrice;
	}
	
	//method
	/**
	 * The body of a candlestick is the area between its opening price and its closing price.
	 * 
	 * @param candleStick
	 * @return true if the body of the current {@link Candlestick} is above the body of the given candlestick.
	 */
	public final boolean bodyIsAboveBodyOf(final Candlestick candleStick) {
		return (
			Calculator.getMin(getOpeningPrice(), getClosingPrice())
			> Calculator.getMax(candleStick.getOpeningPrice(), candleStick.getClosingPrice())
		);
	}
	
	//method
	/**
	 * The body of a candlestick is the area between its opening price and its closing price.
	 * 
	 * @param candleStick
	 * @return true if the body of the current {@link Candlestick} is below the body of the given candlestick.
	 */
	public final boolean bodyIsBelowBodyOf(final Candlestick candleStick) {
		return (
			Calculator.getMax(getOpeningPrice(), getClosingPrice())
			< Calculator.getMin(candleStick.getOpeningPrice(), candleStick.getClosingPrice())
		);
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Candlestick}.
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return
		LinkedList.withElements(
			time.getSpecification(),
			Node.withHeaderAndAttribute(OPENING_PRICE_HEADER, getOpeningPrice()),
			Node.withHeaderAndAttribute(CLOSING_PRICE_HEADER, getClosingPrice()),
			Node.withHeaderAndAttribute(LOWEST_PRICE_HEADER, getLowestPrice()),
			Node.withHeaderAndAttribute(HIGHEST_PRICE_HEADER, getHighestPrice())
		);
	}
	
	//method
	/**
	 * The body of a candlestick is the area between its opening price and its closing price.
	 * 
	 * @return the length of the body of the current {@link Candlestick}.
	 */
	public final double getBodyLength() {
		return (Math.abs(getClosingPrice() - getOpeningPrice()));
	}
	
	//method
	/**
	 * @return the difference of the closing price and the opening price of the current {@link Candlestick}.
	 */
	public final double getChange() {
		return (getClosingPrice() - getOpeningPrice());
	}
	
	//method
	/**
	 * @return the ratio of the change to the opening price of the current {@link Candlestick}.
	 */
	public final double getChangeRatio() {
		return (getChange() / getOpeningPrice());
	}
	
	//method
	/**
	 * @return the closing price of the current {@link Candlestick}.
	 */
	public final double getClosingPrice() {
		return closingPrice;
	}
	
	//method
	/**
	 * @return a copy of the current {@link Candlestick}.
	 */
	public Candlestick getCopy() {
		return new Candlestick(
			getRefTime(),
			getOpeningPrice(),
			getClosingPrice(),
			getLowestPrice(),
			getHighestPrice()
		);
	}
	
	//method
	/**
	 * @return the highest price of the current {@link Candlestick}.
	 */	
	public final double getHighestPrice() {
		return highestPrice;
	}
	
	//method
	/**
	 * @return the length of the current {@link Candlestick}.
	 */
	public final double getLength() {
		return (getHighestPrice() - getLowestPrice());
	}
	
	//method
	/**
	 * @return the lowest price of the current {@link Candlestick}.
	 */	
	public final double getLowestPrice() {
		return lowestPrice;
	}
	
	//method
	/**
	 * @return the length of the lower wick of the current {@link Candlestick}.
	 */
	public final double getLowerWick() {
		
		//Handles the case that the current Candlestick is bullish.
		if (isBullish()) {
			return (getOpeningPrice() - getLowestPrice());
		}
		
		//Handles the case that the current Candlestick is not bullish.
		return (getClosingPrice() - getLowestPrice());
	}
	
	//method
	/**
	 * @return the opening price of the current {@link Candlestick}.
	 */	
	public final double getOpeningPrice() {
		return openingPrice;
	}

	//method
	/**
	 * @return the time of the current {@link Candlestick}.
	 */	
	public final Time getRefTime() {
		return time;
	}
	
	//method
	/**
	 * This method returns a norm n that indicates the trend of the current {@link Candlestick}.
	 * nϵ[-1,-1]
	 * n=-1 ↔ perfect fall
	 * n=0 ↔ perfect immobility
	 * n=1 ↔ perfect raise
	 * 
	 * @return a norm that indicates the trend of the current {@link Candlestick}.
	 */
	public final double getTrend() {
		return (getChange() / getLength());
	}
	
	//method
	/**
	 * @return the length of the upper wick of the current {@link Candlestick}.
	 */
	public final double getUpperWick() {
		
		//Handles the case that the current Candlestick is bullish.
		if (isBullish()) {
			return (getHighestPrice() - getClosingPrice());
		}
		
		//Handles the case that the current Candlestick is not bullish.
		return (getHighestPrice() - getOpeningPrice());
	}
	
	//method
	/**
	 * A bearish candlestick is bearish if its closing price is smaller than its opening price.
	 * 
	 * @return true if the current {@link Candlestick} is bearish.
	 */
	public final boolean isBearish() {
		return (getClosingPrice() - getOpeningPrice() < 0);
	}
	
	//method
	/**
	 * A candlestick is bullish if its closing price is higher than its opening price.
	 * 
	 * @return true if the current {@link Candlestick} is bullish.
	 */
	public final boolean isBullish() {
		return (getClosingPrice() - getOpeningPrice() > 0);
	}
	
	//method
	/**
	 * A candlestick is a doji when its opening price and closing price are the same.
	 * 
	 * @return true if the current {@link Candlestick} is a doji.
	 */
	public final boolean isDoji() {
		return Calculator.equalsApproximatively(getOpeningPrice(), getClosingPrice());
	}
	
	//method
	/**
	 * A candlestick is a hammer if:
	 * -The length of the candlestick is positive. That means the highest price is bigger than the lowest price.
	 * -The lower wick of the candlestick is bigger than its upper wick.
	 * -The lower wick to length ratio of the candlestick is bigger than or equal to a default value.
	 * 
	 * @return true if the current {@link Candlestick} is a hammer.
	 */
	public final boolean isHammer() {
		return isHammer(DEFAULT_HAMMER_MIN_LOWER_WICK_LENGTH_RATIO);
	}
	
	//method
	/**
	 * A candlestick is a hammer if:
	 * -The length of the candlestick is positive. (That means the highest price is bigger than the lowest price.)
	 * -The lower wick of the candlestick is bigger than its upper wick.
	 * -The lower wick to length ratio of the candlestick is bigger than a given value.
	 * 
	 * @param minLowerWickLengthRatio
	 * @return true if the current {@link Candlestick}
	 * is a hammer whose lower wick to length ratio is bigger than or equal to the given min lower wick length ratio.
	 * @throws NegativeArgumentException if the given min lower wick length ratio is negative.
	 */
	public final boolean isHammer(final double minLowerWickLengthRatio) {
		
		//Asserts that the given min lower wick length ratio is not negative.
		Validator.assertThat(minLowerWickLengthRatio).thatIsNamed("min lower wick length ration").isNotNegative();

		//Calculates the needed length.
		final var length = getLength();
				
		//Handles the case that the length of the current Candlestick is 0.0.
		if (length == 0.0) {
			return false;
		}
		
		//Calculates the needed lowerWock and upperWick.
		final var lowerWick = getLowerWick();
		final var upperWick = getUpperWick();
		
		//Handles the case that the length of the current Candlestick is positive.
		return (
			lowerWick > upperWick
			&& lowerWick / length >= minLowerWickLengthRatio
		);
	}
	
	//method
	/**
	 * A candlestick is an inverted hammer if:
	 * -The length of the candlestick is positive. That means the highest price is bigger than the lowest price.
	 * -The upper wick of the candlestick is bigger than its lower wick.
	 * -The upper wick to length ratio of the candlestick is bigger than or equal to a default value.
	 * 
	 * @return true if the current {@link Candlestick} is an inverted a hammer.
	 */
	public final boolean isInvertedHammer() {
		return isInvertedHammer(DEFAULT_INVERTED_HAMMER_MIN_UPPER_WICK_LENGT_RATIO);
	}
	
	//method
	/**
	 * A candlestick is an inverted hammer if:
	 * -The length of the candlestick is positive. That means the highest price is bigger than the lowest price.
	 * -The upper wick of the candlestick is bigger than its lower wick.
	 * -The upper wick to length ratio of the candlestick is bigger than a given value.
	 * 
	 * An inverted hammer is also called a shooting star.
	 * 
	 * @param minUpperWickLengthRatio
	 * @return trueif the current {@link Candlestick}
	 * is a hammer whose upper wick to length ratio is bigger than or equal to the given min upper wick length ratio.
	 * @throws NegativeArgumentException if the given min upper wick length ratio is negative.
	 */
	public final boolean isInvertedHammer(final double minUpperWickLengthRatio) {
		
		//Asserts that the given min upper wick length ratio is not negative.
		Validator.assertThat(minUpperWickLengthRatio).thatIsNamed("min upper wick length ratio").isNotNegative();

		//Calculates the needed length.
		final var length = getLength();
				
		//Handles the case that the length of the current Candlestick is 0.
		if (length == 0.0) {
			return false;
		}
		
		//Calculates the needed lowerWock and upperWick.
		final var lowerWick = getLowerWick();
		final var upperWick = getUpperWick();
		
		//Handles the case that the length of the current Candlestick is positive.
		return (
			upperWick > lowerWick
			&& upperWick / length >= minUpperWickLengthRatio
		);
	}
	
	//method
	/**
	 * A candlestick is a marubozu if its body is as long as its whole length.
	 * 
	 * @return true if the current {@link Candlestick} is a marubozu.
	 */
	public final boolean isMarubozu() {
		return Calculator.equalsApproximatively(getBodyLength(), getLength());
	}
}
