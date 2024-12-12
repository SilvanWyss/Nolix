package ch.nolix.systemapi.graphicapi.imageapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;

public interface IMutableImage<I extends IMutableImage<I>> extends IImage {

  I setPixel(int xPosition, int yPosition, IColor color);
}
