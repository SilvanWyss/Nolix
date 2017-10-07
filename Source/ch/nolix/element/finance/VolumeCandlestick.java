//package declaration
package ch.nolix.element.finance;

//own imports
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.Time;

//class
/**
 * A volume candlestick is a candle stick that also stores the traded volume.
 * A volume candlestick is immutable.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public class VolumeCandlestick extends Candlestick {
	
	//attribute
	private final int volume;

	//constructor
	/**
	 * Creates new volume candlestick with the given time, volume, opening price, closing price, lowest price and highest price.
	 * 
	 * @param time			The time of this candle stick.
	 * @param volume		The volume of this candle stick.
	 * @param openingPrice	The opening price of this candle stick.
	 * @param closingPrice	The closing price of this candle stick.
	 * @param lowestPrice	The lowest price of this candle stick.
	 * @param highestPrice	The highest price of this candle stick.
	 */
	public VolumeCandlestick(
		final Time time,
		final int volume,
		final double openingPrice,
		final double closingPrice,
		final double lowestPrice,
		final double highestPrice
	) {
		
		//Calls constructor of the base class.
		super(time, openingPrice, closingPrice, lowestPrice, highestPrice);
		
		//Checks the given volume.
		Validator.suppose(volume).thatIsNamed("volume").isNotNegative();
		
		//Sets the volume of this candle stick.
		this.volume = volume;
	}
	
	//method
	/**
	 * @return a copy of this volume candlestick.
	 */
	public VolumeCandlestick getCopy() {
		return (
			new VolumeCandlestick(getRefTime(),
				getVolume(),
				getOpeningPrice(),
				getClosingPrice(), getLowestPrice(),
				getHighestPrice())
		);
	}
	
	//method
	/**
	 * @return the volume of this volume candlestick
	 */
	public final int getVolume() {
		return volume;
	}
	
	//method
	/**
	 * @param volumeCandleStick		The volume candlestick that is compared to this candle stick.
	 * @return true if this volume candlestick has a bigger volume than the given candle stick
	 */
	public final boolean hasBiggerVolumeThan(final VolumeCandlestick volumeCandleStick) {
		return (getVolume() > volumeCandleStick.getVolume());
	}
}
