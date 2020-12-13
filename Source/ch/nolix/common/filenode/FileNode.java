//package declaration
package ch.nolix.common.filenode;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.filesystem.FileAccessor;
import ch.nolix.common.filesystem.FileSystemAccessor;
import ch.nolix.common.functionapi.IElementTakerBooleanGetter;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;

//class
/**
 * A {@link FileNode} is a specification that is stored in a file.
 * 
 * @author Silvan Wyss
 * @date 2017-07-14
 * @lines 180
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
		
		//Handles the case that there does not exist a file with the given file path.
		if (!FileSystemAccessor.isFile(filePath)) {
			fileAccessor = FileSystemAccessor.createFile(filePath);
		}
		
		//Handles the case that there exists a file with the given file path.
		else {
			fileAccessor = new FileAccessor(filePath);
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
	 * @return true if this {@link FileNode} has a header.
	 * @throws ArgumentDoesNotHaveAttributeException if this {@link FileNode} does not have a header.
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
	 * @return true if this {@link FileNode} has a header.
	 */
	@Override
	public boolean hasHeader() {
		return internalSpecification.hasHeader();
	}

	//method
	/**
	 * Removes the first attribute the given selector selects from this {@link FileNode}.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if this {@link FileNode} does not contain an attribute the given selector selects.
	 */
	@Override
	public void removeFirstAttribute(final IElementTakerBooleanGetter<BaseNode> selector) {
		internalSpecification.removeFirstAttribute(selector::getOutput);
		save();
	}
	
	//method
	/**
	 * Removes the attributes of this {@link FileNode}.
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
	 * Sets the header of this {@link FileNode}.
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
