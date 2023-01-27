//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi.Titleble;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-07-31
 * @param <GUI> is the type of a {@link IGUI}.
 */
public interface IGUI<GUI extends IGUI<GUI>> extends Titleble<GUI> {
	
	//method declaration
	/**
	 * @return the {@link IFrontEndReader} of the current {@link IGUI}.
	 */
	IFrontEndReader fromFrontEnd();
	
	//method declaration
	/**
	 * @return the icon of the current {@link IGUI}.
	 */
	IImage getIcon();
	
	//method declaration
	/**
	 * A root {@link IGUI} is a {@link IGUI} that is not contained in another {@link IGUI}.
	 * 
	 * @return true if the current {@link IGUI} is a root {@link IGUI}.
	 */
	boolean isRootGUI();
	
	//method declaration
	/**
	 * @return the {@link IFrontEndWriter} of the current {@link IGUI}.
	 */
	IFrontEndWriter onFrontEnd();
	
	//method
	/**
	 * Sets the icon of the current{@link IGUI}.
	 * 
	 * @param icon
	 * @return the current{@link IGUI}.
	 */
	GUI setIcon(IImage icon);
}
