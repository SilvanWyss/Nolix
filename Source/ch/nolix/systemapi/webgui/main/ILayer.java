/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.objectcomposition.guicomponent.GuiComponent;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.systemapi.gui.background.BackgroundManager;
import ch.nolix.systemapi.gui.guiproperty.ContentAlignment;
import ch.nolix.systemapi.style.stylable.StylableElement;
import ch.nolix.systemapi.webgui.html.HtmlGetter;

/**
 * @author Silvan Wyss
 */
public interface ILayer
extends
BackgroundManager<ILayer>,
GuiComponent<IWebGui<?>>,
HtmlGetter,
RootControlManager<ILayer>,
StylableElement<ILayer> {
  /**
   * @param control
   * @return true if the current {@link ILayer} contains the given control, false
   *         otherwise
   */
  boolean containsControl(Control<?, ?> control);

  /**
   * @return the {@link ContentAlignment} of the current {@link ILayer}
   */
  ContentAlignment getContentAlignment();

  /**
   * @return the {@link ICssRule} of the current {@link ILayer}
   */
  ICssRule getCssRule();

  /**
   * @return the internal id of the current {@link ILayer}
   */
  String getInternalId();

  /**
   * @return the opacity of the current {@link ILayer}
   */
  double getOpacity();

  /**
   * @return the {@link LayerRole} of the current {@link ILayer}
   * @throws RuntimeException if the current {@link ILayer} does not have a role
   */
  LayerRole getRole();

  /**
   * @return the structure {@link Control}s of the current {@link ILayer}
   */
  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  /**
   * @param internalId
   * @return true if the current {@link ILayer} has the given internalId, false
   *         otherwise
   */
  boolean hasInternalId(String internalId);

  /**
   * @return true if the current {@link ILayer} has a role, false otherwise
   */
  boolean hasRole();

  /**
   * Sets the given parentGui to the current {@link ILayer}.
   * 
   * @param parentGui
   */
  void internalSetParentGui(IWebGui<?> parentGui);

  /**
   * Removes the current {@link ILayer} from its GUI.
   */
  void removeSelfFromGui();

  /**
   * Sets the given contentAlignment to the current {@link ILayer}.
   * 
   * @param contentAlignment
   * @return the current {@link ILayer}
   */
  ILayer setContentAlignment(ContentAlignment contentAlignment);

  /**
   * Sets the given opacity to the current {@link ILayer}.
   * 
   * @param opacity
   * @return the current {@link ILayer}
   */
  ILayer setOpacity(double opacity);

  /**
   * Sets the given role to the current {@link ILayer}.
   * 
   * @param role
   * @return the current {@link ILayer}
   */
  ILayer setRole(LayerRole role);
}
