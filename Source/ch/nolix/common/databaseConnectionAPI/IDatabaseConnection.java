//package declaration
package ch.nolix.common.databaseConnectionAPI;

//own import
import ch.nolix.common.closeableElement.ICloseableElement;

//interface
public interface IDatabaseConnection extends ICloseableElement {
	
	//method declaration
	public abstract String getDatabaseName();
}
