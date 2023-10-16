//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentTitleable;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-07-31
 * @param <G> is the type of a {@link IGui}.
 */
public interface IGui<G extends IGui<G>> extends FluentTitleable<G> {

  //method declaration
  /**
   * @return the {@link IFrontEndReader} of the current {@link IGui}.
   */
  IFrontEndReader fromFrontEnd();

  //method declaration
  /**
   * @return the icon of the current {@link IGui}.
   */
  IImage getIcon();

  //method declaration
  /**
   * A root {@link IGui} is a {@link IGui} that is not contained in another
   * {@link IGui}.
   * 
   * @return true if the current {@link IGui} is a root {@link IGui}.
   */
  boolean isRootGui();

  //method declaration
  /**
   * @return the {@link IFrontEndWriter} of the current {@link IGui}.
   */
  IFrontEndWriter onFrontEnd();

  //method
  /**
   * Sets the icon of the current{@link IGui}.
   * 
   * @param icon
   * @return the current{@link IGui}.
   */
  G setIcon(IImage icon);
}
