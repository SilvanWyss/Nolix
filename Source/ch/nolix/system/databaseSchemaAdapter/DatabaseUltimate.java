//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.license.Feature;
import ch.nolix.nolixLicenses.Nolix2020Ultimate;

//class
public final class DatabaseUltimate extends Feature {
	
	//method
	@Override
	public IContainer<Class<?>> getAuthorizedLicenseTypes() {
		return new LinkedList<>(Nolix2020Ultimate.class);
	}
}
