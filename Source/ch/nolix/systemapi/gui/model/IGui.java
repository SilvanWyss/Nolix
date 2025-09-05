package ch.nolix.systemapi.gui.model;

import ch.nolix.coreapi.attribute.fluentmutablemandatoryattribute.IFluentMutableTitleHolder;
import ch.nolix.coreapi.objectcomposition.hierarchy.RootRequestable;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.gui.frontend.IFrontEndReader;
import ch.nolix.systemapi.gui.frontend.IFrontEndWriter;

/**
 * @author Silvan Wyss
 * @version 2022-07-31
 * @param <G> is the type of a {@link IGui}.
 */
public interface IGui<G extends IGui<G>> extends IFluentMutableTitleHolder<G>, RootRequestable {
  /**
   * @return the {@link IFrontEndReader} of the current {@link IGui}.
   */
  IFrontEndReader fromFrontEnd();

  /**
   * @return the icon of the current {@link IGui}.
   */
  IImage getIcon();

  /**
   * @return the {@link IFrontEndWriter} of the current {@link IGui}.
   */
  IFrontEndWriter onFrontEnd();

  /**
   * Sets the icon of the current{@link IGui}.
   * 
   * @param icon
   * @return the current{@link IGui}.
   */
  G setIcon(IImage icon);
}
