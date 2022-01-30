//package declaration
package ch.nolix.core.document.filenode;

import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.IElementTakerBooleanGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-07-14
 * @lines 200
 */
public final class SubNode extends BaseNode {
	
	//attribute
	private final FileNode parentFileNode;
	private final Node internalSpecification;
	
	//constructor
	/**
	 * Creates a new {@link SubNode} that:
	 * -Belongs to the given parentFileNode.
	 * -Has the given internal specification.
	 * 
	 * @param parentFileNode
	 * @param internalSpecification
	 */
	SubNode(final FileNode parentFileNode, final Node internalSpecification) {
		
		//Asserts that the given simple persistent specification is not null.
		Validator
		.assertThat(parentFileNode)
		.isOfType(FileNode.class);
		
		//Asserts that the given internal specification is not null.
		Validator.assertThat(internalSpecification)
		.thatIsNamed("internal specification")
		.isNotNull();
		
		//Sets the simple persistent specification of the current SubNode.
		this.parentFileNode = parentFileNode;
		
		//Sets the internal specification of the current SubNode.
		this.internalSpecification = internalSpecification;
	}
	
	//method
	/**
	 * Adds the given attribute to the current {@link SubNode}.
	 * 
	 * @throws RuntimeException if an error occurs.
	 */
	@Override
	public SubNode addAttribute(final BaseNode attribute) {
		
		internalSpecification.addAttribute(attribute);
		parentFileNode.save();
		
		return this;
	}

	//method
	/**
	 * @return true if this {@link SubNode} contains attributes.
	 */
	@Override
	public boolean containsAttributes() {
		return internalSpecification.containsAttributes();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getAttributeCount() {
		return internalSpecification.getAttributeCount();
	}
	
	//method
	/**
	 * @return the header of the current {@link SubNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if this {@link SubNode}
	 * does not have a header.
	 */
	@Override
	public String getHeader() {
		return internalSpecification.getHeader();
	}
	
	//method
	/**
	 * @return the attributes of the current {@link SubNode}
	 */
	@Override
	public ReadContainer<BaseNode> getRefAttributes() {
		return
		ReadContainer.forIterable(
			internalSpecification.getRefAttributes().to(
				a -> new SubNode(parentFileNode, (Node)a)
			)
		);
	}

	//method
	/**
	 * @return the one attribute of the current {@link SubNode}.
	 * @throws EmptyArgumentException if this {@link SubNode} does not contain an attribute.
	 * @throws InvalidArgumentException if this {@link SubNode} contains several attributes.
	 */
	@Override
	public BaseNode getRefOneAttribute() {
		return new SubNode(
			parentFileNode, (Node)internalSpecification.getRefOneAttribute()
		);
	}

	//method
	/**
	 * @return true if this {@link SubNode} has a header.
	 */
	@Override
	public boolean hasHeader() {
		return internalSpecification.hasHeader();
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from the current {@link SubNode}.
	 * 
	 * @param selector
	 * @return the first attribute the given selector selects.
	 * @throws InvalidArgumentException if
	 * the current {@link Node} does not contain an attribute the given selector selects.
	 */
	@Override
	public BaseNode removeAndGetRefFirstAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		
		final var attribute = internalSpecification.removeAndGetRefFirstAttribute(selector::getOutput);
		parentFileNode.save();
		
		return attribute;
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from this {@link SubNode}.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if this {@link SubNode} does not contain an attribute the given selector selects.
	 */
	@Override
	public void removeFirstAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		internalSpecification.removeFirstAttribute(selector::getOutput);
		parentFileNode.save();
	}

	//method
	/**
	 * Sets the given header to the current {@link SubNode}.
	 * 
	 * @return the current {@link SubNode}.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws RuntimeException if an error occurs.
	 */
	@Override
	public SubNode setHeader(final String header) {
		
		internalSpecification.setHeader(header);
		parentFileNode.save();
		
		return this;
	}

	@Override
	public void removeAttributes() {
		internalSpecification.removeAttributes();
		parentFileNode.save();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubNode removeHeader() {
		
		internalSpecification.removeHeader();
		parentFileNode.save();
		
		return this;
	}
}
