//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.license.Feature;
import ch.nolix.nolixLicenses.ApplicationUltimate;

//class
public final class FullDatabase extends Feature {
	
	//method
	@Override
	public IContainer<Class<?>> getAuthorizedLicenseTypes() {
		return new List<>(ApplicationUltimate.class);
	}
}
