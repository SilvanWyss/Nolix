//package declaration
package ch.nolix.element.trading;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.Time;

//class
/**
 * A {@link VolumeCandleStick} is a {@link CandleStick} that stores the traded volume.
 * A {@link VolumeCandleStick} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @lines 100
 */
public final class VolumeCandleStick extends CandleStick {
	
	//constant
	private static final String VOLUME_HEADER = "Volume";
	
	//attribute
	private final int volume;
	
	//constructor
	/**
	 * Creates a new {@link VolumeCandleStick}
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
		
		//Checks if the given volume is not negative.
		Validator.assertThat(volume).thatIsNamed("volume").isNotNegative();
		
		//Sets the volume of the current VolumeCandleStick.
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
		
		list.addAtEnd(Node.withHeaderAndAttribute(VOLUME_HEADER, getVolume()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public VolumeCandleStick getCopy() {
		return (
			new VolumeCandleStick(getRefTime(),
				getVolume(),
				getOpeningPrice(),
				getClosingPrice(), getLowestPrice(),
				getHighestPrice()
			)
		);
	}
	
	//method
	/**
	 * @return the volume of the current {@link VolumeCandleStick}
	 */
	public int getVolume() {
		return volume;
	}
	
	//method
	/**
	 * @param volumeCandleStick
	 * @return true if the current {@link VolumeCandleStick} has a bigger volume than the given volumeCandleStick.
	 */
	public boolean hasBiggerVolumeThan(final VolumeCandleStick volumeCandleStick) {
		return (getVolume() > volumeCandleStick.getVolume());
	}
}
