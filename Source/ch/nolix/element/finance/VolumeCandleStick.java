//package declaration
package ch.nolix.element.finance;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.time.Time;

//class
/**
 * A volume candlestick is a candle stick that also stores the traded volume.
 * A volume candlestick is immutable.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @lines 90
 */
public class VolumeCandleStick extends CandleStick {
	
	//attribute
	private final int volume;

	//constructor
	/**
	 * Creates a new volume candlestick
	 * with the given time, volume, opening price, closing price, lowest price and highest price.
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
		Validator.assertThat(volume).thatIsNamed("volume").isNotNegative();
		
		//Sets the volume of this candle stick.
		this.volume = volume;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//TODO: Implement.
	}
	
	//method
	/**
	 * @return a copy of this volume candlestick.
	 */
	@Override
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
	public final boolean hasBiggerVolumeThan(final VolumeCandleStick volumeCandleStick) {
		return (getVolume() > volumeCandleStick.getVolume());
	}
}
