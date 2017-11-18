//package declaration
package ch.nolix.core.specification;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.fileSystem.FileSystemAccessor;

//class
/**
 * A simple persistent specification is a specification that is stored in a file.
 * 
 * @author Silvan Wyss
 * @month 2017-07
 * @lines 130
 */
public final class SimplePersistentSpecification extends Specification {

	//attributes
	private final FileAccessor fileAccessor;
	private final StandardSpecification internalSpecification;
	
	//constructor
	/**
	 * Creates new simple persistent specification with the given file path.
	 * Creates new file if there exists no file with the given file path.
	 * Access and changes the file if there exists a file with the given file path.
	 * 
	 * @param filePath
	 */
	public SimplePersistentSpecification(final String filePath) {
		
		final FileSystemAccessor fileSystemAccessor = new FileSystemAccessor();
		
		//Handles the case that there exists no file with the given file path.
		if (!fileSystemAccessor.fileSystemItemIsFile(filePath)) {
			fileAccessor = fileSystemAccessor.createFile(filePath);
		}
		
		//Handles the case that there exists a file with the given file path.
		else {
			fileAccessor = new FileAccessor(filePath);
		}
		
		internalSpecification
		= StandardSpecification.createSpecificationFromFile(filePath);
	}
	
	//method
	/**
	 * Adds the given attribute to this simple persistent specification.
	 * 
	 * @param attribute
	 */
	public void addAttribute(final Specification attribute) {
		internalSpecification.addAttribute(attribute);
		save();
	}

	//method
	/**
	 * @return true if this simple persistent specification contains attributes.
	 */
	public boolean containsAttributes() {
		return internalSpecification.containsAttributes();
	}

	//method
	/**
	 * @return true if this simple persistent specification has a header.
	 * @throws UnexistingAttributeException if this simple persistent specification
	 * has no header.
	 */
	public String getHeader() {
		return internalSpecification.getHeader();
	}

	//method
	/**
	 * @return the attributes of this simple persistent specification.
	 */
	@SuppressWarnings("unchecked")
	public AccessorContainer<SubSpecification> getRefAttributes() {
		return new AccessorContainer<SubSpecification>(
			internalSpecification.getRefAttributes().to(
				a -> new SubSpecification(this, a)
			)
		);
	}

	//method
	/**
	 * @return the one attribute of this simple persistent specification.
	 * @throws RuntimeException if this simple persistent specification
	 * has no or several attributes.
	 */
	@SuppressWarnings("unchecked")
	public SubSpecification getRefOneAttribute() {
		return new SubSpecification(
			this,
			internalSpecification.getRefOneAttribute()
		);
	}

	//method
	/**
	 * @return true if this simple persistent specification has a header.
	 */
	public boolean hasHeader() {
		return internalSpecification.hasHeader();
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
		fileAccessor.overwriteFile(
			internalSpecification.toFormatedReproducingString()
		);
	}
}
