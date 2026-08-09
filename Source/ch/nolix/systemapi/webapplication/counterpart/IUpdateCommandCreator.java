/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.webapplication.counterpart;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.systemapi.graphic.image.Image;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public interface IUpdateCommandCreator {
  IChainedNode createSetCssCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetEventFunctionsCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetIconCommandForIcon(Image icon);

  IChainedNode createSetRootHtmlElementCommandForControl(Control<?, ?> control);

  IChainedNode createSetRootHtmlElementCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandForWebGui(IWebGui<?> webGui);

  IChainedNode createSetTitleCommandForTitle(String title);

  IChainedNode createSetUserInputFunctionsCommandForWebGui(IWebGui<?> webGui);

  ExtendedIterable<IChainedNode> createUpdateCommandsForControls(
    ExtendedIterable<Control<?, ?>> controls,
    boolean updateConstellationOrStyle);

  ExtendedIterable<IChainedNode> createUpdateCommandsForWebGui(IWebGui<?> webGui);
}
