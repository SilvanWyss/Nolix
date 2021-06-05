//package declaration
package ch.nolix.element.valueholder;

import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.base.MutableElement;

//class
/**
 * A {@link ValueOrPercentageHolder} stores either a value or a percentage.
 * A {@link ValueOrPercentageHolder} is not mutable.
 *  
 * @author Silvan Wyss
 * @date 2018-03-25
 * @lines 140
 */
public final class ValueOrPercentageHolder extends MutableElement<ValueOrPercentageHolder> {
	
	//attributes
	private final boolean hasValue;
	private final int value;
	private final double percentage;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ValueOrPercentageHolder}
	 * from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ValueOrPercentageHolder fromSpecification(final BaseNode specification) {
		
		final var attribute = specification.getOneAttributeHeader();
		
		//Handles the case that the given specification specifies a ValueOrPercentageHolder with a percentage.
		if (attribute.endsWith("%")) {
			return new ValueOrPercentageHolder(GlobalStringHelper.toDouble(attribute.substring(0, attribute.length() - 2)));
		}
		
		//Handles the case that the given specification specifies a ValueOrPercentageHolder with a value.
		return new ValueOrPercentageHolder(GlobalStringHelper.toInt(attribute));
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
	 * Creates a new {@link ValueOrPercentageHolder} with the given percentage.
	 * 
	 * @param percentage
	 */
	public ValueOrPercentageHolder(final double percentage) {
		
		hasValue = false;
		
		this.value = 0;
		this.percentage = percentage;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		super.fillUpAttributesInto(list);
		
		if (hasValue()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.VALUE, getValue()));
		}
		
		if (hasPercentage()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.PERCENTAGE, getPercentage()));
		}
	}
	
	//method
	/**
	 * @return the value of this percentage holder.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ValueOrPercentageHolder} does not have a value.
	 */
	public int getValue() {
		
		//Asserts that the current ValueOrPercentageHolder has a value.
		if (!hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.VALUE);
		}
		
		return value;
	}
	
	//method
	/**
	 * @return the percentage of this percentage holder.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link ValueOrPercentageHolder} does not have a percentage.
	 */
	public double getPercentage() {
		
		//Asserts that the current ValueOrPercentageHolder has a percentage.
		if (!hasPercentage()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.PERCENTAGE);
		}
		
		return percentage;
	}
	
	//method
	/**
	 * @return true if the current {@link ValueOrPercentageHolder} has a percentage.
	 */
	public boolean hasPercentage() {
		return !hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link ValueOrPercentageHolder} has a value.
	 */
	public boolean hasValue() {
		return hasValue;
	}
}
