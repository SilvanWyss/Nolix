/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import ch.nolix.baseapi.component.guicomponent.GuiComponent;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.html.IHtmlGetter;
import ch.nolix.systemapi.gui.background.IBackgroundHolder;
import ch.nolix.systemapi.gui.box.ContentAlignment;
import ch.nolix.systemapi.style.stylable.IStylableElement;

/**
 * @author Silvan Wyss
 */
public interface ILayer
extends
IBackgroundHolder<ILayer>,
GuiComponent<IWebGui<?>>,
IHtmlGetter,
IRootControlOwner<ILayer>,
IStylableElement<ILayer> {
  boolean containsControl(IControl<?, ?> control);

  ContentAlignment getContentAlignment();

  ICssRule getCssRule();

  String getInternalId();

  double getOpacity();

  LayerRole getRole();

  IWellOrderContainer<IControl<?, ?>> getStoredStructureControls();

  boolean hasInternalId(String internalId);

  boolean hasRole();

  void internalSetParentGui(IWebGui<?> parentGui);

  void removeSelfFromGui();

  ILayer setContentAlignment(ContentAlignment contentAlignment);

  ILayer setRole(LayerRole role);

  ILayer setOpacity(double opacity);
}
