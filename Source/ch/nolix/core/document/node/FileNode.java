//package declaration
package ch.nolix.core.document.node;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.environment.filesystem.FileAccessor;
import ch.nolix.core.environment.filesystem.FileSystemAccessor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionuniversalapi.IElementTakerBooleanGetter;

//class
/**
 * A {@link FileNode} is a specification that is stored in a file.
 * 
 * @author Silvan Wyss
 * @date 2017-07-14
 */
public final class FileNode extends BaseMutableNode<FileNode> {

	//attribute
	private final IMutableNode<?> internalSpecification;
	
	//optional attribute
	private final FileAccessor fileAccessor;
	
	//optional attribute
	private final FileNode parentFileNode;
	
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
		
		internalSpecification = MutableNode.fromFile(filePath);
		
		parentFileNode = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link FileNode} that:
	 * -Belongs to the given parentFileNode.
	 * -Has the given internal specification.
	 * 
	 * @param parentFileNode
	 * @param internalSpecification
	 */
	private FileNode(final FileNode parentFileNode, final IMutableNode<?> internalSpecification) {
		
		//Asserts that the given simple persistent specification is not null.
		GlobalValidator
		.assertThat(parentFileNode)
		.isOfType(FileNode.class);
		
		//Asserts that the given internal specification is not null.
		GlobalValidator.assertThat(internalSpecification)
		.thatIsNamed("internal specification")
		.isNotNull();
		
		//Sets the simple persistent specification of the current SubNode.
		this.parentFileNode = parentFileNode;
		
		//Sets the internal specification of the current SubNode.
		this.internalSpecification = internalSpecification;
		
		fileAccessor = null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileNode addChildNode(final INode<?>... childNodes) {
		
		internalSpecification.addChildNode(childNodes);
		save();
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileNode addChildNodeFromString(final String... strings) {
		
		internalSpecification.addChildNodeFromString(strings);
		save();
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <N extends INode<?>> FileNode addChildNodes(final Iterable<N> childNodes) {
		
		internalSpecification.addChildNodes(childNodes);
		save();
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileNode addChildNodesFromStrings(final Iterable<String> strings) {
		
		internalSpecification.addChildNodesFromStrings(strings);
		save();
		
		return this;
	}
	
	//method
	/**
	 * @return true if this {@link FileNode} contains attributes.
	 */
	@Override
	public boolean containsChildNodes() {
		return internalSpecification.containsChildNodes();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object object) {
		
		if (!(object instanceof FileNode)) {
			return false;
		}
		
		return equalsNode((FileNode)object);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildNodeCount() {
		return internalSpecification.getChildNodeCount();
	}
	
	//method
	/**
	 * @return the header of the current {@link FileNode}.
	 * @throws ArgumentDoesNotHaveAttributeException if this {@link FileNode}
	 * does not have a header.
	 */
	@Override
	public String getHeader() {
		return internalSpecification.getHeader();
	}
	
	//method
	/**
	 * @return the attributes of the current {@link FileNode}
	 */
	@Override
	public ReadContainer<FileNode> getRefChildNodes() {
		return
		ReadContainer.forIterable(
			internalSpecification.getRefChildNodes().to(
				a -> new FileNode(getRefRootFileNode(), (IMutableNode<?>)a)
			)
		);
	}

	//method
	/**
	 * @return the one attribute of the current {@link FileNode}.
	 * @throws EmptyArgumentException if this {@link FileNode} does not contain an attribute.
	 * @throws InvalidArgumentException if this {@link FileNode} contains several attributes.
	 */
	@Override
	public FileNode getRefSingleChildNode() {
		return new FileNode(
			getRefRootFileNode(), (IMutableNode<?>)internalSpecification.getRefSingleChildNode()
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return toString().hashCode();
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
	 * Removes the first attribute the given selector selects from the current {@link FileNode}.
	 * 
	 * @param selector
	 * @return the first attribute the given selector selects.
	 * @throws InvalidArgumentException if
	 * the current {@link Node} does not contain an attribute the given selector selects.
	 */
	@Override
	public FileNode removeAndGetRefFirstChildNodeThat(final IElementTakerBooleanGetter<INode<?>> selector) {
		
		final var attribute = internalSpecification.removeAndGetRefFirstChildNodeThat(selector::getOutput);
		save();
		
		return new FileNode(this, attribute);
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
	public void removeFirstChildNodeThat(final IElementTakerBooleanGetter<? extends INode<?>> selector) {
		internalSpecification.removeFirstChildNodeThat(selector);
		save();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeFirstChildNodeWithHeader(String header) {
		internalSpecification.removeFirstChildNodeWithHeader(header);
	}
	
	@Override
	public void removeChildNodes() {
		internalSpecification.removeChildNodes();
		save();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeHeader() {
		
		internalSpecification.removeHeader();
		
		save();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void replaceFirstChildNodeWithGivenHeaderByGivenNode(final String header, final INode<?> node) {
		internalSpecification.replaceFirstChildNodeWithGivenHeaderByGivenNode(header, node);
		save();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		removeHeader();
		removeChildNodes();
	}
	
	//method
	/**
	 * Sets the given header to the current {@link FileNode}.
	 * 
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
	 * {@inheritDoc}
	 */
	@Override
	protected FileNode asConcrete() {
		return this;
	}
	
	//method
	/**
	 * @return the root {@link FileNode} of the current {@link FileNode}.
	 */
	private FileNode getRefRootFileNode() {
		
		if (!isRootFileNode()) {
			return parentFileNode.getRefRootFileNode();
		}
		
		return this;
	}
	
	//method
	/**
	 * @return true if the current {@link FileNode} is a root {@link FileNode}.
	 */
	private boolean isRootFileNode() {
		return (fileAccessor != null);
	}
	
	//method
	/**
	 * Saves this {@link FileNode}.
	 * 
	 * @throws RuntimeException if an error occurs.
	 */
	private void save() {
		if (!isRootFileNode()) {
			save();
		} else {
			fileAccessor.overwriteFile(internalSpecification.toFormattedString());
		}
	}
}
