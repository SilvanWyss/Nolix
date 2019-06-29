//package declaration
package ch.nolix.element.finance;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.core.Element;
import ch.nolix.element.core.FloatingPointNumber;
import ch.nolix.element.core.Time;

//class
/**
 * A candlestick is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 420
 */
public class Candlestick extends Element<Candlestick> {
	
	//constants
	public static final double DEFAULT_HAMMER_MIN_LOWER_WICK_LENGTH_RATIO = 0.5;
	public static final double DEFAULT_INVERTED_HAMMER_MIN_UPPER_WICK_LENGT_RATIO = 0.5;

	//attribute names
	private static final String OPENING_PRICE_NAME = "OpeningPrice";
	private static final String CLOSING_PRICE_NAME = "ClosingPrice";
	private static final String LOWEST_PRICE_NAME = "LowestPrice";
	private static final String HIGHEST_PRICE_NAME = "HighestPrice";
	
	public static Candlestick createCandleStick(final Iterable<DocumentNode> attributes) {
		
		Time time = null;
		double openingPrice = 0.0;
		double closingPrice = 0.0;
		double lowestPrice = 0.0;
		double highestPrice = 0.0;
		
		for (DocumentNode a : attributes) {
			switch (a.getHeader()) {
				case Time.TYPE_NAME:
					time = Time.createFromSpecification(a);
					break;
				case OPENING_PRICE_NAME:
					openingPrice = a.getOneAttributeAsDouble();
					break;
				case CLOSING_PRICE_NAME:
					closingPrice = a.getOneAttributeAsDouble();
					break;
				case LOWEST_PRICE_NAME:
					lowestPrice = a.getOneAttributeAsDouble();
					break;
				case HIGHEST_PRICE_NAME:
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
	 * Creates a new candlestick with the given time, opening price, closing price, lowest price and highest price.
	 * 
	 * @param time
	 * @param openingPrice
	 * @param closingPrice
	 * @param lowestPrice
	 * @param highestPrice
	 * @throws NullArgumentException if the given time is null.
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
		Validator.suppose(time).isOfType(Time.class);
		Validator.suppose(openingPrice).thatIsNamed("opening price").isNotNegative();
		Validator.suppose(closingPrice).thatIsNamed("closing price").isNotNegative();
		Validator.suppose(lowestPrice).thatIsNamed("lowest price").isNotNegative();
		Validator.suppose(highestPrice).thatIsNamed("highest price").isNotNegative();
		
		//Sets the values of this candlestick.
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
	 * @return true if the body of this candlestick is above the body of the given candlestick.
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
	 * @return true if the body of this candlestick is below the body of the given candlestick.
	 */
	public final boolean bodyIsBelowBodyOf(final Candlestick candleStick) {
		return (
			Calculator.getMax(getOpeningPrice(), getClosingPrice())
			< Calculator.getMin(candleStick.getOpeningPrice(), candleStick.getClosingPrice())
		);
	}
	
	//method
	/**
	 * @return the attributes of this candlestick.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		return
		new List<>(
			time.getSpecification(),
			new FloatingPointNumber(getOpeningPrice()).getSpecificationAs(OPENING_PRICE_NAME),
			new FloatingPointNumber(getClosingPrice()).getSpecificationAs(CLOSING_PRICE_NAME),
			new FloatingPointNumber(getLowestPrice()).getSpecificationAs(LOWEST_PRICE_NAME),
			new FloatingPointNumber(getHighestPrice()).getSpecificationAs(HIGHEST_PRICE_NAME)
		);
	}
	
	//method
	/**
	 * The body of a candlestick is the area between its opening price and its closing price.
	 * 
	 * @return the length of the body of this candlestick.
	 */
	public final double getBodyLength() {
		return (Math.abs(getClosingPrice() - getOpeningPrice()));
	}
	
	//method
	/**
	 * @return the difference of the closing price and the opening price of this candlestick.
	 */
	public final double getChange() {
		return (getClosingPrice() - getOpeningPrice());
	}
	
	//method
	/**
	 * @return the ratio of the change to the opening price of this candlestick.
	 */
	public final double getChangeRatio() {
		return (getChange() / getOpeningPrice());
	}
	
	//method
	/**
	 * @return the closing price of this candlestick.
	 */
	public final double getClosingPrice() {
		return closingPrice;
	}
	
	//method
	/**
	 * @return a copy of this candlestick.
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
	 * @return the highest price of this candlestick.
	 */	
	public final double getHighestPrice() {
		return highestPrice;
	}
	
	//method
	/**
	 * @return the length of this candlestick.
	 */
	public final double getLength() {
		return (getHighestPrice() - getLowestPrice());
	}
	
	//method
	/**
	 * @return the lowest price of this candlestick.
	 */	
	public final double getLowestPrice() {
		return lowestPrice;
	}
	
	//method
	/**
	 * @return the length of the lower wick of this candlestick.
	 */
	public final double getLowerWick() {
		
		//Handles the case that this candlestick is bullish.
		if (isBullish()) {
			return (getOpeningPrice() - getLowestPrice());
		}
		
		//Handles the case that this candlestick is not bullish.
		return (getClosingPrice() - getLowestPrice());
	}
	
	//method
	/**
	 * @return the opening price of this candlestick.
	 */	
	public final double getOpeningPrice() {
		return openingPrice;
	}

	//method
	/**
	 * @return the time of this candlestick.
	 */	
	public final Time getRefTime() {
		return time;
	}
	
	//method
	/**
	 * This method returns a norm n that indicates the trend of this candlestick.
	 * nϵ[-1,-1]
	 * n=-1 ↔ perfect fall
	 * n=0 ↔ perfect immobility
	 * n=1 ↔ perfect raise
	 * 
	 * @return a norm that indicates the trend of this candlestick.
	 */
	public final double getTrend() {
		return (getChange() / getLength());
	}
	
	//method
	/**
	 * @return the length of the upper wick of this candlestick.
	 */
	public final double getUpperWick() {
		
		//Handles the case that this candlestick is bullish.
		if (isBullish()) {
			return (getHighestPrice() - getClosingPrice());
		}
		
		//Handles the case that this candlestick is not bullish.
		return (getHighestPrice() - getOpeningPrice());
	}
	
	//method
	/**
	 * A bearish candlestick is bearish if its closing price is smaller than its opening price.
	 * 
	 * @return true if this candlestick is bearish.
	 */
	public final boolean isBearish() {
		return (getClosingPrice() - getOpeningPrice() < 0);
	}
	
	//method
	/**
	 * A candlestick is bullish if its closing price is higher than its opening price.
	 * 
	 * @return true if this candlestick is bullish.
	 */
	public final boolean isBullish() {
		return (getClosingPrice() - getOpeningPrice() > 0);
	}
	
	//method
	/**
	 * A candlestick is a doji when its opening price and closing price are the same.
	 * 
	 * @return true if this candlestick is a doji.
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
	 * @return true if this candlestick is a hammer.
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
	 * @return true if this candlestick is a hammer whose lower wick to length ratio is bigger than or equal to the given min lower wick length ratio.
	 * @throws NegativeArgumentException if the given min lower wick length ratio is negative.
	 */
	public final boolean isHammer(final double minLowerWickLengthRatio) {
		
		//Checks if the given min lower wick length ratio is not negative.
		Validator.suppose(minLowerWickLengthRatio).thatIsNamed("min lower wick length ration").isNotNegative();

		//Calculates the needed characteristic values.
		final double length = getLength();
		final double lowerWick = getLowerWick();
		final double upperWick = getUpperWick();
		
		//Handles the case that the length of this candlestick is 0.
		if (length == 0) {
			return false;
		}
		
		//Handles the case that the length of this candlestick is positive.
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
	 * @return true if this candlestick is an inverted a hammer.
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
	 * @return true if this candlestick is a hammer whose upper wick to length ratio is bigger than or equal to the given min upper wick length ratio. 
	 * @throws NegativeArgumentException if the given min upper wick length ratio is negative.
	 */
	public final boolean isInvertedHammer(final double minUpperWickLengthRatio) {
		
		//Checks if the given min upper wick length ratio is not negative.
		Validator.suppose(minUpperWickLengthRatio).thatIsNamed("min upper wick length ratio").isNotNegative();

		//Calculates the needed characteristic values.
		final double length = getLength();
		final double lowerWick = getLowerWick();
		final double upperWick = getUpperWick();
		
		//Handles the case that the length of this candlestick is 0.
		if (length == 0) {
			return false;
		}
		
		//Handles the case that the length of this candlestick is positive.
		return (
			upperWick > lowerWick
			&& upperWick / length >= minUpperWickLengthRatio
		);
	}
	
	//method
	/**
	 * A candlestick is a marubozu if its body is as long as its whole length.
	 * 
	 * @return true if this candlestick is a marubozu.
	 */
	public final boolean isMarubozu() {
		return Calculator.equalsApproximatively(getBodyLength(), getLength());
	}
}
