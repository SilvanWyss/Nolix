//package declaration
package ch.nolix.element.finance;

import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.Time;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 130
 */
public final class Order {

	//attributes
	private final OrderType type;
	private final Time time;
	private final String productSymbol;
	private final int volume;
	
	//constructor
	/**
	 * Creates a new order of the given type that has the given time, the product with the given product symbol and the given volume.
	 * 
	 * @param type
	 * @param time
	 * @param productSymbol
	 * @param volume
	 * @throws NullArgumentException if the given type is null.
	 * @throws NullArgumentException if the given time is null.
	 * @throws NullArgumentException if the given product symbol is null.
	 * @throws EmptyArgumentException if the given product symbol is empty.
	 * @throws NullArgumentException if the given volume is not positive.
	 */
	public Order(
		final OrderType type,
		final Time time,
		final String productSymbol,
		final int volume
	) {
		Validator.suppose(type).thatIsNamed("type").isInstance();
		Validator.suppose(time).isInstanceOf(Time.class);
		Validator.suppose(productSymbol).thatIsNamed("prodcut symbol").isNotEmpty();
		Validator.suppose(volume).thatIsNamed("volume").isPositive();
		
		this.type = type;
		this.time = time;
		this.productSymbol = productSymbol;
		this.volume= volume;
	}
	
	//method
	/**
	 * @return true if this order equals the given object.
	 */
	@Override
	public boolean equals(final Object object) {
		
		if (!(object instanceof Order)) {
			return false;
		}
		
		final Order order = (Order)object;
		return (
			getType().equals(order.getType())
			&& getRefTime().equals(order.getRefTime())
			&& getProductSymbol().equals(order.getProductSymbol())
			&& getVolume() == order.getVolume()
		);
	}
	
	//method
	/**
	 * @return a copy of this order.
	 */
	public Order getCopy() {
		return new Order(
			getType(),
			getRefTime(),
			getProductSymbol(),
			getVolume()
		);
	}
	
	//method
	/**
	 * @return the product symbol of the product of this order.
	 */
	public String getProductSymbol() {
		return productSymbol;
	}
	
	//method
	/**
	 * @return the time of this order.
	 */
	public Time getRefTime() {
		return time;
	}
	
	//method
	/**
	 * @return the type of this order.
	 */
	public OrderType getType() {
		return type;
	}
	
	//method
	/**
	 * @return the volume of this order.
	 */
	public int getVolume() {
		return volume;
	}
	
	//method
	/**
	 * @return a string representation of this order.
	 */
	@Override
	public String toString() {
		return (
			getClass().getSimpleName()
			+ "("
			+ "Type(" + getType() + ")"
			+ ","
			+ getRefTime().toString()
			+ ","
			+ "ProductSymbol(" + getProductSymbol() + ")"
			+ ","
			+ "Volume(" + getVolume() + ")"
			+ ")"
		);
	}
}
