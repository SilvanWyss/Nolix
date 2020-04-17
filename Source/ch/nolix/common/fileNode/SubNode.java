//package declaration
package ch.nolix.common.fileNode;

//own imports
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 170
 */
public final class SubNode extends BaseNode {

	//attribute
	private final FileNode simplePersistentSpecification;
	private final Node internalSpecification;
	
	//constructor
	/**
	 * Creates a new sub specification that:
	 * -Belongs to the given simple persistent specification.
	 * -Has the given internal specification.
	 * 
	 * @param simplePersistentSpecification
	 * @param internalSpecification
	 */
	SubNode(
		final FileNode simplePersistentSpecification,
		final Node internalSpecification
	) {
		//Asserts that the given simple persistent specification is not null.
		Validator
		.assertThat(simplePersistentSpecification)
		.isOfType(FileNode.class);
		
		//Asserts that the given internal specification is not null.
		Validator.assertThat(internalSpecification)
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
	public void addAttribute(final BaseNode attribute) {
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
	 * @throws ArgumentDoesNotHaveAttributeException if this sub specification
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
	public ReadContainer<SubNode> getRefAttributes() {
		return new ReadContainer<>(
			internalSpecification.getRefAttributes().to(
				a -> new SubNode(simplePersistentSpecification, a)
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
	public SubNode getRefOneAttribute() {
		return new SubNode(
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
	public void removeFirstAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		internalSpecification.removeFirstAttribute(a -> selector.getOutput(a));
		simplePersistentSpecification.save();
	}

	//method
	/**
	 * Sets the given header to this sub specification.
	 * 
	 * @return the current {@link SubNode}.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws RuntimeException if an error occurs.
	 */
	@Override
	public SubNode setHeader(final String header) {
		
		internalSpecification.setHeader(header);
		simplePersistentSpecification.save();
		
		return this;
	}

	@Override
	public void removeAttributes() {
		internalSpecification.removeAttributes();
		simplePersistentSpecification.save();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubNode removeHeader() {
		
		internalSpecification.removeHeader();
		simplePersistentSpecification.save();
		
		return this;
	}
}
