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
import ch.nolix.systemapi.gui.guiproperty.CursorIcon;
import ch.nolix.systemapi.style.stylable.StylableElement;
import ch.nolix.systemapi.webgui.base.PresenceSettable;
import ch.nolix.systemapi.webgui.base.SizeAdjustableRectangle;
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
SizeAdjustableRectangle<C>,
StylableElement<C>,
IUserInputCell<C>,
LayerComponent<ILayer>,
PresenceSettable<C> {
  /**
   * Lets the given styleEditor edit the style of the current {@link Control}.
   * 
   * @param styleEditor
   * @return the current {@link Control}
   */
  C editStyle(Consumer<S> styleEditor);

  /**
   * @return the {@link ICssRule}s of the current {@link Control}
   */
  ExtendedIterable<ICssRule> getCssRules();

  /**
   * @return the {@link CursorIcon} of the current {@link Control}
   */
  CursorIcon getCursorIcon();

  /**
   * @return the internal id of the current {@link Control}
   */
  String getInternalId();

  /**
   * @return a {@link Optional} with the JavaScript function that returns the user
   *         input of the current {@link Control} if the current {@link Control}
   *         has such a function, an empty {@link Optional} otherwise
   */
  Optional<String> getOptionalJavaScriptUserInputFunction();

  /**
   * @param internalId
   * @return a {@link Optional} with the child {@link Control} with the given
   *         internalId of the current {@link Control} if the current
   *         {@link Control} contains such a child {@link Control}, an empty
   *         {@link Optional} otherwise
   */
  Optional<Control<?, ?>> getOptionalStoredChildControlByInternalId(String internalId);

  /**
   * @return the child {@link Control}s of the current {@link Control}
   */
  ExtendedIterable<Control<?, ?>> getStoredChildControls();

  /**
   * @return the structure {@link Control}s of the current {@link Control}
   */
  ExtendedIterable<Control<?, ?>> getStoredStructureControls();

  /**
   * @return the style of the current {@link Control}
   */
  S getStoredStyle();

  /**
   * @param internalId
   * @return true if the current {@link Control} has the given internalId, false
   *         otherwise
   */
  boolean hasInternalId(String internalId);

  /**
   * Removes the parent of the current {@link Control}.
   */
  void internalRemoveControlParent();

  /**
   * Sets the given controlParent to the current {@link Control}.
   * 
   * @param controlParent
   * @throws RuntimeException if the given controlParent is null
   * @throws RuntimeException if the current {@link Control} belongs already to a
   *                          parent
   */
  void internalSetControlParent(IControlParent controlParent);

  /**
   * Registers the {@link IHtmlElementEvent}s of the current {@link Control} at
   * the given list.
   * 
   * @param list
   */
  void registerHtmlElementEventsAt(ILinkedList<IHtmlElementEvent> list);

  /**
   * Removes the parent layer of the current {@link Control} from its GUI.
   * 
   * @throws RuntimeException if the current {@link Control} does not belong to a
   *                          layer
   */
  void removeParentLayerFromGui();

  /**
   * Runs the given htmlEvent on the current {@link Control}.
   * 
   * @param htmlEvent
   * @throws RuntimeException if the current {@link Control} does not support the
   *                          given htmlEvent
   */
  void runHtmlEvent(String htmlEvent);

  /**
   * Sets the given cursorIcon to the current {@link Control}.
   * 
   * @param cursorIcon
   * @return the current {@link Control}
   */
  C setCursorIcon(CursorIcon cursorIcon);
}
