package ch.nolix.systemapi.graphicapi.imageapi;

import ch.nolix.coreapi.creationapi.copierapi.Copyable;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

public interface IMutableImage<I extends IMutableImage<I>> extends Copyable<I>, IImage {

  I setPixel(int xPosition, int yPosition, IColor color);
}
