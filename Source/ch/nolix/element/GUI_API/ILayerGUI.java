//package declaration
package ch.nolix.element.GUI_API;

import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.element.baseAPI.IMutableElement;
import ch.nolix.element.configuration.IConfigurableElementWithOptionalConfiguration;

//interface
/**
 * A {@link ILayerGUI} is a {@link IGUI} that can contain several {@link ILayer}s, that are stacked.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 70
 * @param <G> The type of a {@link ILayerGUI}.
 */
public interface ILayerGUI<G extends ILayerGUI<G>>
extends IConfigurableElementWithOptionalConfiguration<G>, IGUI<G>, IMutableElement<G> {
	
	//abstract method
	/**
	 * Adds the given {@link ILayer} on the top of the current {@link ILayerGUI}.
	 * 
	 * @param layer
	 * @return the current {@link ILayerGUI}.
	 */
	public abstract G addLayerOnTop(final ILayer<?> layer);
	
	//abstract method
	/**
	 * Adds a new {@link ILayer} on the top of the current {@link ILayerGUI}.
	 * The {@link ILayer} will have the given rootWidget.
	 * 
	 * @param rootWidget
	 * @return the current {@link ILayerGUI}.
	 */
	public abstract G addLayerOnTop(final Widget<?, ?> rootWidget);
	
	//abstract method
	/**
	 * @return the number of {@link ILayer}s of the current {@link ILayerGUI}.
	 */
	public abstract int getLayerCount();
	
	//abstract method
	/**
	 * @return the {@link ILayer}s of the current {@link ILayerGUI}.
	 */
	public abstract IContainer<ILayer<?>> getRefLayers();
	
	//abstract method
	/**
	 * @return the triggerable {@link Widget}s of the current {@link ILayerGUI}.
	 */
	public abstract List<Widget<?, ?>> getRefTriggerableWidgets();
	
	//abstract method
	/**
	 * @return the {@link Widget}s of the current {@link ILayerGUI}.
	 */
	public abstract List<Widget<?, ?>> getRefWidgets();
	
	//abstract method
	/**
	 * Removes the top {@link ILayer} of the current {@link ILayerGUI}.
	 * 
	 * @return the current {@link ILayerGUI}.
	 */
	public abstract G removeTopLayer();
}
