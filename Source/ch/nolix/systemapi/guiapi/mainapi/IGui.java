package ch.nolix.systemapi.guiapi.mainapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableTitleHolder;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.frontendapi.IFrontEndWriter;

/**
 * @author Silvan Wyss
 * @version 2022-07-31
 * @param <G> is the type of a {@link IGui}.
 */
public interface IGui<G extends IGui<G>> extends IFluentMutableTitleHolder<G> {

  /**
   * @return the {@link IFrontEndReader} of the current {@link IGui}.
   */
  IFrontEndReader fromFrontEnd();

  /**
   * @return the icon of the current {@link IGui}.
   */
  IImage getIcon();

  /**
   * A root {@link IGui} is a {@link IGui} that is not contained in another
   * {@link IGui}.
   * 
   * @return true if the current {@link IGui} is a root {@link IGui}.
   */
  boolean isRootGui();

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
