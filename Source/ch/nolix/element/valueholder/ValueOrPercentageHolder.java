//package declaration
package ch.nolix.element.valueholder;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.elementapi.IElement;

//class
/**
 * A {@link ValueOrPercentageHolder} stores either a value or a percentage.
 * A {@link ValueOrPercentageHolder} is not mutable.
 *  
 * @author Silvan Wyss
 * @date 2018-03-25
 */
public final class ValueOrPercentageHolder implements IElement<ValueOrPercentageHolder> {
	
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
