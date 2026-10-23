/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.counterpart;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public interface IUpdateCommandCreator {
  /**
   * @param webGui
   * @return a new command to set the CSS of the given webGui
   */
  ChainedNode createSetCssCommandForWebGui(IWebGui<?> webGui);

  /**
   * @param webGui
   * @return a new command to set the event functions of the given webGui
   */
  ChainedNode createSetEventFunctionsCommandForWebGui(IWebGui<?> webGui);

  /**
   * @param icon
   * @return a new command to set the given icon
   */
  ChainedNode createSetIconCommandForIcon(Image icon);

  /**
   * @param webGui
   * @return a new command to set the icon of the given webGui
   */
  ChainedNode createSetIconCommandForWebGui(IWebGui<?> webGui);

  /**
   * @param control
   * @return a new command to set the root HTML element of the given control
   */
  ChainedNode createSetRootHtmlElementCommandForControl(Control<?, ?> control);

  /**
   * @param webGui
   * @return a new command to set the root HTML element of the given webGui
   */
  ChainedNode createSetRootHtmlElementCommandForWebGui(IWebGui<?> webGui);

  /**
   * @param title
   * @return a new command to set the given title
   */
  ChainedNode createSetTitleCommandForTitle(String title);

  /**
   * @param webGui
   * @return a new command to set the title of the given webGui
   */
  ChainedNode createSetTitleCommandForWebGui(IWebGui<?> webGui);

  /**
   * @param webGui
   * @return a new command to set the user input functions of the given webGui
   */
  ChainedNode createSetUserInputFunctionsCommandForWebGui(IWebGui<?> webGui);

  /**
   * @param controls
   * @param updateConstellationOrStyle
   * @return new commands to update the given controls
   */
  ExtendedIterable<ChainedNode> createUpdateCommandsForControls(
    ExtendedIterable<Control<?, ?>> controls,
    boolean updateConstellationOrStyle);

  /**
   * @param webGui
   * @return new commands to update the given webGui
   */
  ExtendedIterable<ChainedNode> createUpdateCommandsForWebGui(IWebGui<?> webGui);
}
