//package declaration
package ch.nolix.core.util;

//own imports
import ch.nolix.core.constants.FileExtensionCatalogue;
import ch.nolix.core.fileSystem.FolderAccessor;
import ch.nolix.core.validator.Validator;

//class
public final class JavaScriptBundler {
	
	//attribute
	private final String sourceFolderPath;
	
	//constructor
	public JavaScriptBundler(final String sourceFolderPath) {
		
		Validator
		.suppose(sourceFolderPath)
		.isNotBlank();
		
		this.sourceFolderPath = sourceFolderPath;
	}
	
	//method
	public String getConcatenatedJavaScript() {
		
		final var stringBuilder = new StringBuilder();
		
		//TODO: Order JavaScript definitions correctly.
		for (
			final var fa :
			new FolderAccessor(getSourceFolderPath()).getFileAccessorsRecursively(FileExtensionCatalogue.JS)
		) {
			stringBuilder
			.append(fa.readFile())
			.append("/r/n");
		}
		
		return stringBuilder.toString();
	}
	
	//method
	public String getSourceFolderPath() {
		return sourceFolderPath;
	}
}
