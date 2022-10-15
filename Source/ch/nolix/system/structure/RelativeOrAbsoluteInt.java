//package declaration
package ch.nolix.system.structure;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.structureapi.IRelativeOrAbsoluteInt;

//class
/**
 * A {@link RelativeOrAbsoluteInt} stores either an integer or a percentage.
 * A {@link RelativeOrAbsoluteInt} is not mutable.
 *  
 * @author Silvan Wyss
 * @date 2022-10-15
 */
public final class RelativeOrAbsoluteInt extends Element implements IRelativeOrAbsoluteInt {
	
	//attribute
	private final boolean isAbsolute;
	
	//attribute
	private final int absoluteValue;
	
	//attribute
	private final double percentage;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link RelativeOrAbsoluteInt} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static RelativeOrAbsoluteInt fromSpecification(final INode<?> specification) {
		
		final var attribute = specification.getSingleChildNodeHeader();
		
		if (attribute.endsWith("%")) {
			return withPercentage(0.01 * Double.valueOf(attribute.substring(0, attribute.length() - 1)));
		}
		
		return withIntValue(GlobalStringHelper.toInt(attribute));
	}
	
	//static method
	/**
	 * @param intValue
	 * @return a new {@link RelativeOrAbsoluteInt} with the given intValue.
	 */
	public static RelativeOrAbsoluteInt withIntValue(final int intValue) {
		return new RelativeOrAbsoluteInt(intValue);
	}
	
	//static method
	/**
	 * @param percentage
	 * @return a new {@link RelativeOrAbsoluteInt} with the given percentage.
	 * @throws NegativeArgumentException if the given percentage is negative.
	 */
	public static RelativeOrAbsoluteInt withPercentage(final double percentage) {
		return new RelativeOrAbsoluteInt(percentage);
	}
	
	//constructor
	/**
	 * Creates a new {@link RelativeOrAbsoluteInt} with the given intValue.
	 * 
	 * @param intValue
	 */
	private RelativeOrAbsoluteInt(final int intValue) {
		isAbsolute = true;
		this.absoluteValue = intValue;
		percentage = 0.0;
	}
	
	//constructor
	/**
	 * Creates a new {@link RelativeOrAbsoluteInt} with the given percentage.
	 * 
	 * @param percentage
	 * @throws NegativeArgumentException if the given percentage is negative.
	 */
	private RelativeOrAbsoluteInt(final double percentage) {
		
		GlobalValidator.assertThat(percentage).thatIsNamed(LowerCaseCatalogue.PERCENTAGE).isNotNegative();
		
		isAbsolute = false;
		absoluteValue = 0;
		this.percentage = percentage;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<INode<?>> getAttributes() {
		
		final var attributes = new LinkedList<INode<?>>();
		
		if (isAbsolute()) {
			attributes.addAtEnd(Node.withHeaderAndChildNode(PascalCaseCatalogue.VALUE, getAbsoluteValue()));
		} else if (isRelative()) {
			attributes.addAtEnd(Node.withHeaderAndChildNode(PascalCaseCatalogue.PERCENTAGE, getPercentage()));
		}
		
		return attributes;
	}
	
	//method

	public int getAbsoluteValue() {
		
		assertIsAbsolute();
		
		return absoluteValue;
	}
	
	//method

	public double getPercentage() {
		
		assertIsRelative();
		
		return percentage;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	public int getValueRelativeToHundredPercentValue(final int hundredPercentValue) {
		
		if (isAbsolute) {
			return absoluteValue;
		}
		
		return (int)(percentage * hundredPercentValue);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean isAbsolute() {
		return isAbsolute;
	}

	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	public boolean isPositive() {
		
		if (isAbsolute) {
			return (absoluteValue > 0);
		}
		
		return (percentage > 0.0);
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	public boolean isRelative() {
		return !isAbsolute();
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link RelativeOrAbsoluteInt} does not have an integer value.
	 */
	private void assertIsAbsolute() {
		if (!isAbsolute()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "integer value");
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link RelativeOrAbsoluteInt} does not have a percentage.
	 */
	private void assertIsRelative() {
		if (!isRelative()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.PERCENTAGE);
		}
	}
}
