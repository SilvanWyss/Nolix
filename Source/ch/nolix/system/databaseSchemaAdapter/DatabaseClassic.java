//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.license.Feature;
import ch.nolix.nolixLicenses.Nolix2020Classic;
import ch.nolix.nolixLicenses.Nolix2020Ultimate;

//class
public final class DatabaseClassic extends Feature {
	
	//method
	@Override
	public IContainer<Class<?>> getAuthorizedLicenseTypes() {
		return new LinkedList<>(Nolix2020Classic.class, Nolix2020Ultimate.class);
	}
}
