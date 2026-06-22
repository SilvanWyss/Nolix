/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.baseapi.component.guicomponent.GuiComponent;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.objectcomposition.linking.Linkable;
import ch.nolix.baseapi.web.cssmodel.ICssRule;
import ch.nolix.baseapi.web.html.IHtmlGetter;
import ch.nolix.systemapi.gui.box.ISizeAdjustableBox;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.gui.presence.PresenceSettable;
import ch.nolix.systemapi.style.stylable.IStylableElement;
import ch.nolix.systemapi.webgui.controlstructure.IControlParent;
import ch.nolix.systemapi.webgui.controlstyle.IControlStyle;

/**
 * @author Silvan Wyss
 * @param <C> is the type of a {@link IControl}.
 * @param <S> is the type of the {@link IControlStyle} of a {@link IControl}.
 */
public interface IControl<C extends IControl<C, S>, S extends IControlStyle<S>>
extends
GuiComponent<IWebGui<?>>,
IHtmlGetter,
ISizeAdjustableBox<C>,
IStylableElement<C>,
IUserInputCell<C>,
Linkable,
PresenceSettable<C> {
  boolean belongsToControl();

  boolean belongsToLayer();

  C editStyle(Consumer<S> styleEditor);

  ExtendedIterable<ICssRule> getCssRules();

  CursorIcon getCursorIcon();

  String getInternalId();

  Optional<String> getOptionalJavaScriptUserInputFunction();

  Optional<IControl<?, ?>> getOptionalStoredChildControlByInternalId(String internalId);

  ExtendedIterable<IControl<?, ?>> getStoredChildControls();

  IControl<?, ?> getStoredParentControl();

  ILayer getStoredParentLayer();

  ExtendedIterable<IControl<?, ?>> getStoredStructureControls();

  S getStoredStyle();

  boolean hasInternalId(String internalId);

  void internalRemoveControlParent();

  void internalSetControlParent(IControlParent controlParent);

  void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);

  void runHtmlEvent(String htmlEvent);

  C setCursorIcon(CursorIcon cursorIcon);
}
