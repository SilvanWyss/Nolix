//package declaration
package ch.nolix.core.documentNode;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;

//class
/**
 * A simple persistent specification is a specification that is stored in a file.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 130
 */
public final class SimplePersistentDocumentNode extends DocumentNodeoid {

	//attributes
	private final FileAccessor fileAccessor;
	private final DocumentNode internalSpecification;
	
	//constructor
	/**
	 * Creates a new simple persistent specification with the given file path.
	 * Creates a new file if there does not exist a file with the given file path.
	 * Access and changes the file if there exists a file with the given file path.
	 * 
	 * @param filePath
	 */
	public SimplePersistentDocumentNode(final String filePath) {
		
		final FileSystemAccessor fileSystemAccessor = new FileSystemAccessor();
		
		//Handles the case that there does not exist a file with the given file path.
		if (!fileSystemAccessor.fileSystemItemIsFile(filePath)) {
			fileAccessor = fileSystemAccessor.createFile(filePath);
		}
		
		//Handles the case that there exists a file with the given file path.
		else {
			fileAccessor = new FileAccessor(filePath);
		}
		
		internalSpecification
		= DocumentNode.createFromFile(filePath);
	}
	
	//method
	/**
	 * Adds the given attribute to this simple persistent specification.
	 * 
	 * @param attribute
	 */
	@Override
	public void addAttribute(final DocumentNodeoid attribute) {
		internalSpecification.addAttribute(attribute);
		save();
	}

	//method
	/**
	 * @return true if this simple persistent specification contains attributes.
	 */
	@Override
	public boolean containsAttributes() {
		return internalSpecification.containsAttributes();
	}

	//method
	/**
	 * @return true if this simple persistent specification has a header.
	 * @throws ArgumentMissesAttributeException if this simple persistent specification
	 * does not have a header.
	 */
	@Override
	public String getHeader() {
		return internalSpecification.getHeader();
	}

	//method
	/**
	 * @return the attributes of this simple persistent specification.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IContainer<SubDocumentNode> getRefAttributes() {
		return new ReadContainer<SubDocumentNode>(
			internalSpecification.getRefAttributes().to(
				a -> new SubDocumentNode(this, a)
			)
		);
	}
	
	//method
	/**
	 * @return the one attribute of this simple persistent specification.
	 * @throws RuntimeException if this simple persistent specification
	 * does not contain an attribute or contains several attributes.
	 */
	@Override
	public SubDocumentNode getRefOneAttribute() {
		return new SubDocumentNode(
			this,
			internalSpecification.getRefOneAttribute()
		);
	}

	//method
	/**
	 * @return true if this simple persistent specification has a header.
	 */
	@Override
	public boolean hasHeader() {
		return internalSpecification.hasHeader();
	}

	//method
	/**
	 * Removes the first attribute the given selector selects from this simple persistent specification.
	 * 
	 * @param selector
	 * @throws InvalidArgumentException
	 * if this simple persistent specification does not contain an attribute the given selector selects.
	 */
	@Override
	public void removeFirstAttribute(final IElementTakerBooleanGetter<DocumentNodeoid> selector) {
		internalSpecification.removeFirstAttribute(a -> selector.getOutput(a));
		save();
	}
	
	//method
	/**
	 * Removes the attributes of this simple persisten specification.
	 */
	@Override
	public void removeAttributes() {
		internalSpecification.removeAttributes();
		save();
	}
	
	@Override
	public void removeHeader() {
		internalSpecification.removeHeader();
		save();
	}
	
	//method
	/**
	 * Sets the header of this simple persistent specification.
	 * 
	 * @param header
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws RuntimeException if an error occurs.
	 */
	@Override
	public void setHeader(final String header) {
		internalSpecification.setHeader(header);
		save();
	}
	
	//package-visible method
	/**
	 * Saves this simple persistent specification.
	 * 
	 * @throws RuntimeException if an error occurs.
	 */
	void save() {
		fileAccessor.overwriteFile(internalSpecification.toFormatedString());
	}
}
