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
  ChainedNode createSetCssCommandForWebGui(IWebGui<?> webGui);

  ChainedNode createSetEventFunctionsCommandForWebGui(IWebGui<?> webGui);

  ChainedNode createSetIconCommandForWebGui(IWebGui<?> webGui);

  ChainedNode createSetIconCommandForIcon(Image icon);

  ChainedNode createSetRootHtmlElementCommandForControl(Control<?, ?> control);

  ChainedNode createSetRootHtmlElementCommandForWebGui(IWebGui<?> webGui);

  ChainedNode createSetTitleCommandForWebGui(IWebGui<?> webGui);

  ChainedNode createSetTitleCommandForTitle(String title);

  ChainedNode createSetUserInputFunctionsCommandForWebGui(IWebGui<?> webGui);

  ExtendedIterable<ChainedNode> createUpdateCommandsForControls(
    ExtendedIterable<Control<?, ?>> controls,
    boolean updateConstellationOrStyle);

  ExtendedIterable<ChainedNode> createUpdateCommandsForWebGui(IWebGui<?> webGui);
}
