//package declaration
package ch.nolix.core.document.filenode;

import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerBooleanGetter;

//class
/**
 * A {@link FileNode} is a specification that is stored in a file.
 * 
 * @author Silvan Wyss
 * @date 2017-07-14
 */
public final class FileNode extends BaseNode {

	//attributes
	private final FileAccessor fileAccessor;
	private final Node internalSpecification;
	
	//constructor
	/**
	 * Creates a new {@link FileNode} with the given file path.
	 * Creates a new file if there does not exist a file with the given file path.
	 * Access and changes the file if there exists a file with the given file path.
	 * 
	 * @param filePath
	 */
	public FileNode(final String filePath) {
			
		//Handles the case that there does not exist a file system item with the given filePath.
		if (!FileSystemAccessor.exists(filePath)) {
			fileAccessor = FileSystemAccessor.createFile(filePath);
			
		//Handles the case that there exists a file with the given filePath.
		} else if (FileSystemAccessor.isFile(filePath)) {
			fileAccessor = new FileAccessor(filePath);
			
		//Handles the case that there exists file system item with the given filePath that is not a file.
		} else {
			throw InvalidArgumentException.forArgumentAndErrorPredicate(filePath, "is not a file");
		}
		
		internalSpecification = Node.fromFile(filePath);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileNode addAttribute(final BaseNode attribute) {
		
		internalSpecification.addAttribute(attribute);
		save();
		
		return this;
	}

	//method
	/**
	 * {@inheritDoc}
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
	 * @return true if the current {@link FileNode} has a header.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link FileNode} does not have a header.
	 */
	@Override
	public String getHeader() {
		return internalSpecification.getHeader();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<BaseNode> getRefAttributes() {
		return
		ReadContainer.forIterable(
			internalSpecification.getRefAttributes().to(
				a -> new SubNode(this, (Node)a)
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BaseNode getRefOneAttribute() {
		return new SubNode(this, (Node)internalSpecification.getRefOneAttribute());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasHeader() {
		return internalSpecification.hasHeader();
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from the current {@link FileNode}.
	 * 
	 * @param selector
	 * @return the first attribute the given selector selects.
	 * @throws InvalidArgumentException if
	 * the current {@link Node} does not contain an attribute the given selector selects.
	 */
	@Override
	public BaseNode removeAndGetRefFirstAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		
		final var attribute = internalSpecification.removeAndGetRefFirstAttribute(selector::getOutput);
		save();
		
		return attribute;
	}
	
	//method
	/**
	 * Removes the first attribute the given selector selects from the current {@link FileNode}.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if the current {@link FileNode} does not contain an attribute the given selector selects.
	 */
	@Override
	public void removeFirstAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		internalSpecification.removeFirstAttribute(selector::getOutput);
		save();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAttributes() {
		internalSpecification.removeAttributes();
		save();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileNode removeHeader() {
		
		internalSpecification.removeHeader();
		save();
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replaceFirstAttributeHavingGivenHeaderWithGivenAttribute(
		final String header,
		final BaseNode attribute
	) {
		internalSpecification.replaceFirstAttributeHavingGivenHeaderWithGivenAttribute(header, attribute);
		save();
	}
	
	//method
	/**
	 * Sets the header of the current {@link FileNode}.
	 * 
	 * @param header
	 * @return the current {@link FileNode}.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws RuntimeException if an error occurs.
	 */
	@Override
	public FileNode setHeader(final String header) {
		
		internalSpecification.setHeader(header);
		save();
		
		return this;
	}
	
	//method
	/**
	 * Saves this {@link FileNode}.
	 * 
	 * @throws RuntimeException if an error occurs.
	 */
	void save() {
		fileAccessor.overwriteFile(internalSpecification.toFormatedString());
	}
}
