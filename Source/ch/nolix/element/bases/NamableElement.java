/*
 * file:	NamedElement.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	120
 */

//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IRequestableContainer;
import ch.nolix.core.interfaces.Namable;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.core.NonEmptyText;
import ch.nolix.element.data.Name;

//class
/**
 * A named element is an element that has a name.
 */
public abstract class NamableElement<NE extends NamableElement<NE>> extends MutableElement implements Namable<NE> {
	
	//attribute name
	private static final String NAME = "Name";
	
	//optional attribute
	private IRequestableContainer containerOfNameds;
	
	//attribute
	private NonEmptyText name = new NonEmptyText();
	
	//method
	/**
	 * @return true if this named object belongs to a container of nameds
	 */
	public final boolean belongsToContainerOfNameds() {
		return (containerOfNameds != null);
	}
		
	//method
	/**
	 * @return the attributes of this named element
	 */
	public List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>().addAtEnd(name.getSpecificationAs(NAME));
	}
	
	//method
	/**
	 * @return the name of this named element
	 */
	public final String getName() {
		return name.getValue();
	}
	
	//method
	/**
	 * @return true if this named element has the given name
	 */
	public final boolean hasName(String name) {
		return this.name.hasValue(name);
	}
	
	//method
	/**
	 * Resets this named element.
	 */
	public void reset() {
		name = new Name();
	}
	
	//method
	/**
	 * Sets the given attribute to this named element.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(StandardSpecification attribute) {
		switch (attribute.getHeader()) {
			case NAME:
				setName(attribute.getOneAttributeToString());
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);
				
		}
	}
	
	//method
	/**
	 * Sets the container of nameds of this named element.
	 * 
	 * @param containerOfNameds
	 * @throws Exception if:
	 *  -the given container of named is null
	 *  -this named element already belongs to an other container of nameds
	 */
	public final void setSearchContainer(IRequestableContainer containerOfNameds) {
		
		Validator.throwExceptionIfValueIsNull("container of nameds", containerOfNameds);
		
		if (belongsToContainerOfNameds() && this.containerOfNameds != containerOfNameds) {
			throw new RuntimeException("Namable element " + getNameInQuotes() + " already belongs to an other container of nameds.");
		}
		
		this.containerOfNameds = containerOfNameds;
	}
	
	//method
	/**
	 * Sets the name of this named element.
	 * 
	 * @param name
	 * @throws Exception if:
	 *  -the given name is null or an empty string
	 *  -this named element belongs to a container of nameds that already contains an other named element with the given name
	 */
	@SuppressWarnings("unchecked")
	public NE setName(String name) {
		if (!hasName(name)) {
			
			if (belongsToContainerOfNameds() && containerOfNameds.containsElement(name)) {
				throw new RuntimeException("Named element " + getNameInQuotes() + " belongs to a container that contains an other element with the name '" + name + "'.");
			}
			
			this.name = new Name(name);
		}
		
		return (NE)this;
	}
}
