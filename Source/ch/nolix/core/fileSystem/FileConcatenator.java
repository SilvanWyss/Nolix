//package declaration
package ch.nolix.core.fileSystem;

import ch.nolix.core.invalidArgumentExceptions.FrozenArgumentException;
import ch.nolix.core.skillAPI.Freezable;

//class
public final class FileConcatenator implements Freezable<FileConcatenator> {
	
	//attributes
	private boolean frozen = false;
	private final FolderAccessor rootFolderAccessor;
	private final StringBuilder stringBuilder = new StringBuilder();
	
	//constructor
	public FileConcatenator(final String rootFolderPath) {
		rootFolderAccessor = new FolderAccessor(rootFolderPath);
	}
	
	//method
	public FileConcatenator addFile(final String relativeFilePath) {
		return addFileContent(rootFolderAccessor.readFile(relativeFilePath));
	}
	
	//method
	public FileConcatenator addFileContent(final String fileContent) {
		
		if (frozen) {
			throw new FrozenArgumentException(this);
		}
		
		stringBuilder.append(fileContent);
		
		return this;
	}
	
	//method
	@Override
	public FileConcatenator freeze() {
		
		frozen = true;
		
		return this;
	}
	
	//method
	public String getConcatenatatedContent() {
		return stringBuilder.toString();
	}
	
	@Override
	public boolean isFrozen() {
		return frozen;
	}
}
