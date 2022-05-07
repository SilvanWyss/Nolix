//package declaration
package ch.nolix.system.valueholder;

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
 * A {@link IntOrPercentageHolder} stores either an integer or a percentage.
 * A {@link IntOrPercentageHolder} is not mutable.
 *  
 * @author Silvan Wyss
 * @date 2018-03-25
 */
public final class IntOrPercentageHolder implements IElement<IntOrPercentageHolder> {
	
	//attribute
	private final boolean hasIntValue;
	
	//attribute
	private final int intValue;
	
	//attribute
	private final double percentage;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link IntOrPercentageHolder} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static IntOrPercentageHolder fromSpecification(final BaseNode specification) {
		
		final var attribute = specification.getOneAttributeHeader();
		
		if (attribute.endsWith("%")) {
			return
			new IntOrPercentageHolder(GlobalStringHelper.toDouble(attribute.substring(0, attribute.length() - 2)));
		}
		
		return new IntOrPercentageHolder(GlobalStringHelper.toInt(attribute));
	}
	
	//static method
	/**
	 * @param intValue
	 * @return a new {@link IntOrPercentageHolder} with the given intValue.
	 */
	public static IntOrPercentageHolder withIntValue(final int intValue) {
		return new IntOrPercentageHolder(intValue);
	}
	
	//static method
	/**
	 * @param percentage
	 * @return a new {@link IntOrPercentageHolder} with the given percentage.
	 */
	public static IntOrPercentageHolder withPercentage(final double percentage) {
		return new IntOrPercentageHolder(percentage);
	}
	
	//constructor
	/**
	 * Creates a new {@link IntOrPercentageHolder} with the given intValue.
	 * 
	 * @param intValue
	 */
	private IntOrPercentageHolder(final int intValue) {
		hasIntValue = true;
		this.intValue = intValue;
		percentage = 0.0;
	}
	
	//constructor
	/**
	 * Creates a new {@link IntOrPercentageHolder} with the given percentage.
	 * 
	 * @param percentage
	 */
	private IntOrPercentageHolder(final double percentage) {
		hasIntValue = false;
		intValue = 0;
		this.percentage = percentage;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		if (hasIntValue()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.VALUE, getIntValue()));
		} else if (hasPercentage()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.PERCENTAGE, getPercentage()));
		}
	}
	
	//method
	/**
	 * @return the integer value of the current {@link IntOrPercentageHolder}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link IntOrPercentageHolder} does not have an integer value.
	 */
	public int getIntValue() {
		
		assertIntHasValue();
		
		return intValue;
	}
	
	//method
	/**
	 * @return the percentage of this percentage holder.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link IntOrPercentageHolder} does not have a percentage.
	 */
	public double getPercentage() {
		
		assertHasPercentage();
		
		return percentage;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public int getValueInRelationToHundrerPercentValue(final int hundredPercentValue) {
		
		if (hasIntValue) {
			return intValue;
		}
		
		return (int)(percentage * hundredPercentValue);
	}
	
	//method
	/**
	 * @return true if the current {@link IntOrPercentageHolder} has a percentage.
	 */
	public boolean hasPercentage() {
		return !hasIntValue();
	}
	
	//method
	/**
	 * @return true if the current {@link IntOrPercentageHolder} has an integer value.
	 */
	public boolean hasIntValue() {
		return hasIntValue;
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link IntOrPercentageHolder} does not have an integer value.
	 */
	private void assertIntHasValue() {
		if (!hasIntValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "integer value");
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link IntOrPercentageHolder} does not have a percentage.
	 */
	private void assertHasPercentage() {
		if (!hasPercentage()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.PERCENTAGE);
		}
	}
}
