//package declaration
package ch.nolix.element.finance;

//own imports
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.basic.Time;

//class
/**
 * A volume candle stick is a candle stick that also stores the traded volume.
 * A volume candle stick is immutable.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public class VolumeCandleStick extends CandleStick {
	
	//attribute
	private final int volume;

	//constructor
	/**
	 * Creates new volume candle stick with the given time, volume, opening price, closing price, lowest price and highest price.
	 * 
	 * @param time			The time of this candle stick.
	 * @param volume		The volume of this candle stick.
	 * @param openingPrice	The opening price of this candle stick.
	 * @param closingPrice	The closing price of this candle stick.
	 * @param lowestPrice	The lowest price of this candle stick.
	 * @param highestPrice	The highest price of this candle stick.
	 */
	public VolumeCandleStick(
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
		ZetaValidator.supposeThat(volume).thatIsNamed("volume").isNotNegative();
		
		//Sets the volume of this candle stick.
		this.volume = volume;
	}
	
	//method
	/**
	 * @return a copy of this volume candle stick.
	 */
	public VolumeCandleStick getCopy() {
		return (
			new VolumeCandleStick(getRefTime(),
				getVolume(),
				getOpeningPrice(),
				getClosingPrice(), getLowestPrice(),
				getHighestPrice())
		);
	}
	
	//method
	/**
	 * @return the volume of this volume candle stick
	 */
	public final int getVolume() {
		return volume;
	}
	
	//method
	/**
	 * @param volumeCandleStick		The volume candle stick that is compared to this candle stick.
	 * @return true if this volume candle stick has a bigger volume than the given candle stick
	 */
	public final boolean hasBiggerVolumeThan(final VolumeCandleStick volumeCandleStick) {
		return (getVolume() > volumeCandleStick.getVolume());
	}
}
