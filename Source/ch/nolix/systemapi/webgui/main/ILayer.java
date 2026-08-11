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
  boolean containsControl(Control<?, ?> control);

  ContentAlignment getContentAlignment();

  ICssRule getCssRule();

  String getInternalId();

  double getOpacity();

  LayerRole getRole();

  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  boolean hasInternalId(String internalId);

  boolean hasRole();

  void internalSetParentGui(IWebGui<?> parentGui);

  void removeSelfFromGui();

  ILayer setContentAlignment(ContentAlignment contentAlignment);

  ILayer setRole(LayerRole role);

  ILayer setOpacity(double opacity);
}
