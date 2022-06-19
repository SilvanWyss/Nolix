//package declaration
package ch.nolix.system.valueholder;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//class
/**
 * A {@link IntOrPercentageHolder} stores either an integer or a percentage.
 * A {@link IntOrPercentageHolder} is not mutable.
 *  
 * @author Silvan Wyss
 * @date 2018-03-25
 */
public final class IntOrPercentageHolder implements Specified {
	
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
			return withPercentage(0.01 * Double.valueOf(attribute.substring(0, attribute.length() - 1)));
		}
		
		return withIntValue(GlobalStringHelper.toInt(attribute));
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
	 * @throws NegativeArgumentException if the given percentage is negative.
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
	 * @throws NegativeArgumentException if the given percentage is negative.
	 */
	private IntOrPercentageHolder(final double percentage) {
		
		GlobalValidator.assertThat(percentage).thatIsNamed(LowerCaseCatalogue.PERCENTAGE).isNotNegative();
		
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
		
		assertHasIntValue();
		
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
	public int getValueInRelationToHundredPercentValue(final int hundredPercentValue) {
		
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
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return true if the current {@link IntOrPercentageHolder} has a positive integer value or a positive percentage.
	 */
	public boolean isPositive() {
		
		if (hasIntValue) {
			return (intValue > 0);
		}
		
		return (percentage > 0.0);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link IntOrPercentageHolder} does not have an integer value.
	 */
	private void assertHasIntValue() {
		if (!hasIntValue()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "integer value");
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link IntOrPercentageHolder} does not have a percentage.
	 */
	private void assertHasPercentage() {
		if (!hasPercentage()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.PERCENTAGE);
		}
	}
}
