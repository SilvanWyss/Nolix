//package declaration
package ch.nolix.core.documentNode;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.ArgumentMissesAttributeException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 180
 */
public final class SubDocumentNode extends DocumentNodeoid {

	//attribute
	private final SimplePersistentDocumentNode simplePersistentSpecification;
	private final DocumentNode internalSpecification;
	
	//package-visible constructor
	/**
	 * Creates a new sub specification that:
	 * -Belongs to the given simple persistent specification.
	 * -Has the given internal specification.
	 * 
	 * @param simplePersistentSpecification
	 * @param internalSpecification
	 */
	SubDocumentNode(
		final SimplePersistentDocumentNode simplePersistentSpecification,
		final DocumentNode internalSpecification
	) {
		//Checks if the given simple persistent specification is not null.
		Validator
		.suppose(simplePersistentSpecification)
		.isOfType(SimplePersistentDocumentNode.class);
		
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
	@Override
	public void addAttribute(final DocumentNodeoid attribute) {
		internalSpecification.addAttribute(attribute);
		simplePersistentSpecification.save();
	}

	//method
	/**
	 * @return true if this sub specification contains attributes.
	 */
	@Override
	public boolean containsAttributes() {
		return internalSpecification.containsAttributes();
	}
	
	//method
	/**
	 * @return the header of this sub specification.
	 * @throws ArgumentMissesAttributeException if this sub specification
	 * does not have a header.
	 */
	@Override
	public String getHeader() {
		return internalSpecification.getHeader();
	}
	
	//method
	/**
	 * @return the attributes of this sub specification
	 */
	@Override
	@SuppressWarnings("unchecked")
	public ReadContainer<SubDocumentNode> getRefAttributes() {
		return new ReadContainer<SubDocumentNode>(
			internalSpecification.getRefAttributes().to(
				a -> new SubDocumentNode(simplePersistentSpecification, a)
			)
		);
	}

	//method
	/**
	 * @return the one attribute of this sub specification.
	 * @throws EmptyArgumentException if this sub specification does not contain an attribute.
	 * @throws InvalidArgumentException if this sub specification contains several attributes.
	 */
	@Override
	public SubDocumentNode getRefOneAttribute() {
		return new SubDocumentNode(
			simplePersistentSpecification, internalSpecification.getRefOneAttribute()
		);
	}

	//method
	/**
	 * @return true if this sub specification has a header.
	 */
	@Override
	public boolean hasHeader() {
		return internalSpecification.hasHeader();
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from this sub specification.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if this sub specification does not contain an attribute the given selector selects.
	 */
	@Override
	public void removeFirstAttribute(final IElementTakerBooleanGetter<DocumentNodeoid> selector) {
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
	@Override
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
	 * Removes the header of the current {@link SubDocumentNode}
	 */
	@Override
	public void removeHeader() {
		internalSpecification.removeHeader();
		simplePersistentSpecification.save();
	}
}
