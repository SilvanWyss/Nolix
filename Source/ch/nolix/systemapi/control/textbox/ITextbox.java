/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.control.textbox;

import java.util.function.Consumer;

import ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute.FluentMutableTextHolder;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public interface ITextbox extends Control<ITextbox, ITextboxStyle>, FluentMutableTextHolder<ITextbox> {
  /**
   * Empties the text of the current {@link ITextbox}.
   */
  void emptyText();

  /**
   * @return the {@link TextMode} of the current {@link ITextbox}
   */
  TextMode getTextMode();

  /**
   * Removes the update text action of the current {@link ITextbox}.
   */
  void removeUpdateTextAction();

  /**
   * Sets the given textMode to the current {@link ITextbox}.
   * 
   * @param textMode
   * @return the current {@link ITextbox}
   */
  ITextbox setTextMode(TextMode textMode);

  /**
   * Sets the given updateTextAction to the current {@link ITextbox}. The given
   * updateTextAction is run when the text of the current {@link ITextbox} is
   * updated.
   * 
   * @param updateTextAction
   * @return the current {@link ITextbox}
   */
  ITextbox setUpdateTextAction(Runnable updateTextAction);

  /**
   * Sets the given updateTextAction to the current {@link ITextbox}. The given
   * updateTextAction is run with the new text when the text of the current
   * {@link ITextbox} is updated.
   * 
   * @param updateTextAction
   * @return the current {@link ITextbox}
   */
  ITextbox setUpdateTextAction(Consumer<String> updateTextAction);
}
