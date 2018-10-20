//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.skillInterfaces.Castable;
import ch.nolix.core.skillInterfaces.Clearable;
import ch.nolix.core.skillInterfaces.Closable;
import ch.nolix.core.skillInterfaces.IRequestableContainer;
import ch.nolix.core.skillInterfaces.Refreshable;
import ch.nolix.core.specificationAPI.Specifiable;

//interface
public interface IGUI<G extends IGUI<G>>
extends
Castable,
Clearable<G>,
Closable,
IRequestableContainer,
Refreshable,
Specifiable<G> {
	
	//abstract method
	public abstract IGUIController getRefController();
	
	//abstract method
	public abstract IContainer<Widget<?, ?>> getRefWidgetsRecursively();
	
	//abstract method
	public abstract boolean hasController();
	
	//abstract method
	public abstract boolean isRootGUI();
}
