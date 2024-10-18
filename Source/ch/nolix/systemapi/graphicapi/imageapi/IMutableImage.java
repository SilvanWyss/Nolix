package ch.nolix.systemapi.graphicapi.imageapi;

import ch.nolix.systemapi.graphicapi.colorapi.IColor;

public interface IMutableImage<MI extends IMutableImage<MI>> extends IImage {

  MI setPixel(int xPosition, int yPosition, IColor color);
}
