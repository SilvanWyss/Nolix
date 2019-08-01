//package declaration
package ch.nolix.element.GUI_API;

//own import
import ch.nolix.element.baseGUI_API.IBaseGUI;

//interface
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 10
 * @param <G> The type of a {@link IGUI}.
 */
public interface IGUI<G extends IGUI<G>> extends IBaseGUI<IGUI<G>> {}
