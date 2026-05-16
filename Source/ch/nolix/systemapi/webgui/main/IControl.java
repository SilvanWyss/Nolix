/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webgui.main;

import java.util.Optional;
import java.util.function.Consumer;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
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
ISizeAdjustableBox<C>,
IHtmlGetter,
IStylableElement<C>,
IUserInputCell<C>,
Linkable,
PresenceSettable<C> {
  boolean belongsToControl();

  boolean belongsToGui();

  boolean belongsToLayer();

  C editStyle(Consumer<S> styleEditor);

  IContainer<ICssRule> getCssRules();

  CursorIcon getCursorIcon();

  String getInternalId();

  Optional<String> getOptionalJavaScriptUserInputFunction();

  Optional<IControl<?, ?>> getOptionalStoredChildControlByInternalId(String internalId);

  IContainer<IControl<?, ?>> getStoredChildControls();

  IControl<?, ?> getStoredParentControl();

  IWebGui<?> getStoredParentGui();

  ILayer getStoredParentLayer();

  <T extends IControl<T, X>, X extends IControlStyle<X>> IContainer<T> getStoredStructureControls();

  S getStoredStyle();

  boolean hasInternalId(String internalId);

  void internalSetControlParent(IControlParent controlParent);

  void internalSetParentControl(IControl<?, ?> parentControl);

  void internalSetParentLayer(ILayer parentLayer);

  void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);

  void runHtmlEvent(String htmlEvent);

  C setCursorIcon(CursorIcon cursorIcon);
}
