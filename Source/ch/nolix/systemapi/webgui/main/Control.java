/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.objectcomposition.guicomponent.ControlComponent;
import ch.nolix.baseapi.objectcomposition.guicomponent.GuiComponent;
import ch.nolix.baseapi.objectcomposition.guicomponent.LayerComponent;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.systemapi.gui.box.ISizeAdjustableBox;
import ch.nolix.systemapi.gui.guiproperty.CursorIcon;
import ch.nolix.systemapi.gui.presence.PresenceSettable;
import ch.nolix.systemapi.style.stylable.StylableElement;
import ch.nolix.systemapi.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webgui.html.HtmlGetter;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link Control}.
 * @param <S> the type of the {@link ControlStyle} of a {@link Control}.
 */
public interface Control<C extends Control<C, S>, S extends ControlStyle<S>>
extends
ControlComponent<Control<?, ?>>,
GuiComponent<IWebGui<?>>,
HtmlGetter,
ISizeAdjustableBox<C>,
StylableElement<C>,
IUserInputCell<C>,
LayerComponent<ILayer>,
PresenceSettable<C> {
  C editStyle(Consumer<S> styleEditor);

  ExtendedIterable<ICssRule> getCssRules();

  CursorIcon getCursorIcon();

  String getInternalId();

  Optional<String> getOptionalJavaScriptUserInputFunction();

  Optional<Control<?, ?>> getOptionalStoredChildControlByInternalId(String internalId);

  ExtendedIterable<Control<?, ?>> getStoredChildControls();

  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  S getStoredStyle();

  boolean hasInternalId(String internalId);

  void internalRemoveControlParent();

  void internalSetControlParent(IControlParent controlParent);

  void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);

  void removeParentLayerFromGui();

  void runHtmlEvent(String htmlEvent);

  C setCursorIcon(CursorIcon cursorIcon);
}
