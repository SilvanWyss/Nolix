//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.generalSkillAPI.ISmartObject;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.skillAPI.OptionalClosable;
import ch.nolix.core.skillAPI.IRequestableContainer;
import ch.nolix.core.skillAPI.Refreshable;
import ch.nolix.element.baseAPI.IMutableElement;
import ch.nolix.element.configuration.StandardConfiguration;

//interface
public interface IGUI<G extends IGUI<G>>
extends
	Clearable<G>,
	OptionalClosable,
	ISmartObject<G>,
	IRequestableContainer,
	Refreshable,
	IMutableElement<G> {
	
	//abstract method
	/**
	 * Adds the given layer on the top of the current {@link IGUI}.
	 * 
	 * @param layer
	 * @return the current {@link IGUI}.
	 */
	public abstract G addLayerOnTop(final IGUILayer<?> layer);
	
	//abstract method
	/**
	 * Adds a new layer on the top of the current {@link IGUI}.
	 * The layer will have the given rootWidget.
	 * 
	 * @param rootWidget
	 * @return the current {@link IGUI}.
	 */
	public abstract G addLayerOnTop(final Widget<?, ?> rootWidget);
	
	//abstract method
	//public abstract int getHeight();
	
	//abstract method
	public abstract int getViewAreaHeight();
	
	//abstract method
	public abstract int getViewAreaWidth();
	
	//abstract method
	//public abstract int getWidth();
	
	//abstract method
	public abstract IGUIController getRefController();
	
	//default method
	/**
	 * @param indexPath
	 * @return the {@link Widget} with the given index path from the current {@link IGUI}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link IGUI} does not contain a {@link Widget} with the given path.
	 */
	@SuppressWarnings("unchecked")
	public default <W extends Widget<?, ?>> W getRefWidgetByIndexPath(final IContainer<Integer> indexPath) {
		
		//Checks if the given index path is not empty.
		if (indexPath.isEmpty()) {
			throw new EmptyArgumentException("index path", indexPath);
		}
		
		//Handles the case that the given index path contains 1 element.
		if (indexPath.containsOne()) {
			return getRefWidgetByIndexRecursively(indexPath.getRefOne());
		}
		
		//Handles the case that the given index path contains several elements.
		return
		(W)
		getRefWidgetByIndexRecursively(indexPath.getRefFirst())
		.as(IGUI.class)
		.getRefWidgetByIndexPath(indexPath.withoutFirst());
	}
	
	//default method
	/**
	 * @param index
	 * @return the {@link Widget} with the given index recursively from the current {@link IGUI}.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link IGUI} does not contain a {@link Widget} with the given index.
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
	
	//abstract method
	/**
	 * Removes the top layer of the current {@link IGUI}.
	 * 
	 * @return the current {@link IGUI}.
	 */
	public abstract G removeTopLayer();
	
	//abstract method
	public abstract G setConfiguration(StandardConfiguration configuration);
}
