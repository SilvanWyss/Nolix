//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 180
 */
public final class SubSpecification extends Specification {

	//attribute
	private final SimplePersistentSpecification simplePersistentSpecification;
	private final StandardSpecification internalSpecification;
	
	//package-visible constructor
	/**
	 * Creates a new sub specification that:
	 * -Belongs to the given simple persistent specification.
	 * -Has the given internal specification.
	 * 
	 * @param simplePersistentSpecification
	 * @param internalSpecification
	 */
	SubSpecification(
		final SimplePersistentSpecification simplePersistentSpecification,
		final StandardSpecification internalSpecification
	) {
		//Checks if the given simple persistent specification is not null.
		Validator
		.suppose(simplePersistentSpecification)
		.isInstanceOf(SimplePersistentSpecification.class);
		
		//Checks if the given internal specification is not null.
		Validator.suppose(internalSpecification)
		.thatIsNamed("internal specification")
		.isNotNull();
		
		//Sets the simple persistent specification of this sub specification.
		this.simplePersistentSpecification = simplePersistentSpecification;
		
		//Sets the internal specification of this sub specification.
		this.internalSpecification = internalSpecification;
	}
	
	//method
	/**
	 * Adds the given attribute to this sub specification.
	 * 
	 * @throws RuntimeException if an error occurs.
	 */
	public void addAttribute(final Specification attribute) {
		internalSpecification.addAttribute(attribute);
		simplePersistentSpecification.save();
	}

	//method
	/**
	 * @return true if this sub specification contains attributes.
	 */
	public boolean containsAttributes() {
		return internalSpecification.containsAttributes();
	}
	
	//method
	/**
	 * @return the header of this sub specification.
	 * @throws UnexistingAttributeException if this sub specification
	 * has no header.
	 */
	public String getHeader() {
		return internalSpecification.getHeader();
	}
	
	//method
	/**
	 * @return the attributes of this sub specification
	 */
	@SuppressWarnings("unchecked")
	public ReadContainer<SubSpecification> getRefAttributes() {
		return new ReadContainer<SubSpecification>(
			internalSpecification.getRefAttributes().to(
				a -> new SubSpecification(simplePersistentSpecification, a)
			)
		);
	}

	//method
	/**
	 * @return the one attribute of this sub specification.
	 * @throws EmptyStateException if this sub specification contains no attributes.
	 * @throws InvalidStateException if this sub specification contains several attributes.
	 */
	public SubSpecification getRefOneAttribute() {
		return new SubSpecification(
			simplePersistentSpecification, internalSpecification.getRefOneAttribute()
		);
	}

	//method
	/**
	 * @return true if this sub specification has a header.
	 */
	public boolean hasHeader() {
		return internalSpecification.hasHeader();
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from this sub specification.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if this sub specification contains no attribute the given selector selects.
	 */
	public void removeFirstAttribute(final IElementTakerBooleanGetter<Specification> selector) {
		internalSpecification.removeFirstAttribute(a -> selector.getOutput(a));
		simplePersistentSpecification.save();
	}

	//method
	/**
	 * Sets the given header to this sub specification.
	 * 
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws RuntimeException if an error occurs.
	 */
	public void setHeader(final String header) {
		internalSpecification.setHeader(header);
		simplePersistentSpecification.save();
	}

	@Override
	public void removeAttributes() {
		internalSpecification.removeAttributes();
		simplePersistentSpecification.save();
	}

	//method
	/**
	 * Removes the header of the current {@link SubSpecification}
	 */
	public void removeHeader() {
		internalSpecification.removeHeader();
		simplePersistentSpecification.save();
	}
}
