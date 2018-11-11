//package declaration
package ch.nolix.element.GUI;

import ch.nolix.core.argument.ArgumentName;
//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
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
	
	//default method
	/**
	 * @param indexPath
	 * @return the {@link Widget} with the given index path from the current {@link IGUI}.
	 * @throws UnexistingAttributeException
	 * if the current {@link IGUI} contains no {@link Widget} with the given path.
	 */
	@SuppressWarnings("unchecked")
	public default <W extends Widget<?, ?>> W getRefWidgetByIndexPath(final IContainer<Integer> indexPath) {
		
		//Checks if the given index path is not empty.
		if (indexPath.isEmpty()) {
			throw new EmptyArgumentException(new ArgumentName("index path"));
		}
		
		//Handles the case that the given index path contains 1 element.
		if (indexPath.containsOne()) {
			return getRefWidgetByIndexRecursively(indexPath.getRefOne());
		}
		
		//Handles the case that the given index path contains several elements.
		return
		(W)
		getRefWidgetByIndexRecursively(indexPath.getRefFirst())
		.as(GUI.class)
		.getRefWidgetByIndexPath(indexPath.getContainerWithoutFirst());
	}
	
	//default method
	/**
	 * @param index
	 * @return the {@link Widget} with the given index recursively from the current {@link IGUI}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws UnexistingAttributeException
	 * if the current {@link IGUI} contains no {@link Widget} with the given index.
	 */
	@SuppressWarnings("unchecked")
	public default <W extends Widget<?, ?>> W getRefWidgetByIndexRecursively(final int index) {
		return (W)getRefWidgetsRecursively().getRefAt(index);
	}
	
	//abstract method
	public abstract IContainer<Widget<?, ?>> getRefWidgetsRecursively();
	
	//abstract method
	public abstract boolean hasController();
	
	//abstract method
	public abstract boolean isRootGUI();
}
