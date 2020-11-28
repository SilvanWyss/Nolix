//package declaration
package ch.nolix.element.valueHolder;

import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.base.Element;

//class
/**
 * A {@link ValueOrPercentageHolder} stores either a value or a percentage.
 * A {@link ValueOrPercentageHolder} is not mutable.
 * 
 * For higher performance,
 * a {@link ValueOrPercentageHolder} accesses its private attributes directly
 * and not through the available methods.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 140
 */
public final class ValueOrPercentageHolder extends Element<ValueOrPercentageHolder> {

	//attributes
	private final boolean hasValue;
	private final int value;
	private final double percentage;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ValueOrPercentageHolder}
	 * from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static ValueOrPercentageHolder fromSpecification(
		final BaseNode specification
	) {
		
		var attribute = specification.getOneAttributeHeader();
		
		//Handles the case that the given specification
		//specifies a value or percentage holder with a percentage.
		if (attribute.endsWith("%")) {
			return new ValueOrPercentageHolder(
				StringHelper.toDouble(
					attribute.substring(0, attribute.length() - 2)
				)
			);
		}
		
		//Handles the case that the given specification
		//specifies a value or percentage holder with a value.
		return new ValueOrPercentageHolder(StringHelper.toInt(attribute));
	}
	
	//constructor
	/**
	 * Creates a new {@link ValueOrPercentageHolder}
	 * with the given value.
	 * 
	 * @param value
	 */
	public ValueOrPercentageHolder(final int value) {
		hasValue = true;
		this.value = value;
		this.percentage = 0.0;
	}
	
	//constructor
	/**
	 * Creates a new {@link ValueOrPercentageHolder}
	 * with the given percentage.
	 * 
	 * @param value
	 */
	public ValueOrPercentageHolder(final double percentage) {
		hasValue = false;
		this.value = 0;
		this.percentage = percentage;
	}
	
	//method
	/**
	 * @return the value of this percentage holder.
	 * @throws UnexistingAttribtueException
	 * if the current {@link ValueOrPercentageHolder} does not have a value.
	 */
	public int getValue() {
		
		//Asserts that this value or percentage holder has a value.
		if (!hasValue) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.VALUE
			);
		}
		
		return value;
	}
	
	//method
	/**
	 * @return the percentage of this percentage holder.
	 * @throws UnexistingAttribtueException
	 * if the current {@link ValueOrPercentageHolder} does not have a percentage.
	 */
	public double getPercentage() {
		
		//Asserts that this value or percentage holder has a percentage.
		if (hasValue) {
			throw new ArgumentDoesNotHaveAttributeException(
				this,
				VariableNameCatalogue.PERCENTAGE
			);
		}
		
		return percentage;
	}
	
	//method
	/**
	 * @return true
	 * if the current {@link ValueOrPercentageHolder} has a percentage.
	 */
	public boolean hasPercentage() {
		return !hasValue;
	}
	
	//method
	/**
	 * 
	 * @return true
	 * if the current {@link ValueOrPercentageHolder} has a value.
	 */
	public boolean hasValue() {
		return hasValue;
	}
}
