//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.programcontrolapi.triggerapi.Refreshable;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;

//interface
/**
 * @author Silvan Wyss
 * @date 2019-08-01
 * @param <G> is the type of a {@link IExtendedGui}.
 */
public interface IExtendedGui<G extends IExtendedGui<G>>
extends GroupCloseable, IGui<G>, Refreshable {

  //method declaration
  /**
   * @return the cursor icon on the current {@link IExtendedGui}.
   */
  CursorIcon getCursorIcon();

  //method declaration
  /**
   * @return the x-position of the cursor on the view area of the current
   *         {@link IExtendedGui}.
   */
  int getCursorXPositionOnViewArea();

  //method declaration
  /**
   * @return the y-position of the cursor on the view area of the current
   *         {@link IExtendedGui}.
   */
  int getCursorYPositionOnViewArea();

  //method declaration
  /**
   * @return the height of the view area of the current {@link IExtendedGui}.
   */
  int getViewAreaHeight();

  //method declaration
  /**
   * @return the width of the view area of the current {@link IExtendedGui}.
   */
  int getViewAreaWidth();

  //method declaration
  /**
   * 
   * @return true if the current {@link IExtendedGui} is visible.
   */
  boolean isVisible();

  //method
  /**
   * Lets the current {@link IExtendedGui} note a resize. The size of the view
   * area of the current {@link IExtendedGui} will be the size of the view area of
   * the given gui.
   * 
   * @param gui
   */
  void noteResizeFrom(IExtendedGui<?> gui);
}
