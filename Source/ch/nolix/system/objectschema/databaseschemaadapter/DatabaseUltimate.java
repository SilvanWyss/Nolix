//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.license.Feature;
import ch.nolix.nolixlicense.Nolix2020Ultimate;

//class
public final class DatabaseUltimate extends Feature {
	
	//method
	@Override
	public IContainer<Class<?>> getAuthorizedLicenseTypes() {
		return LinkedList.withElements(Nolix2020Ultimate.class);
	}
}
