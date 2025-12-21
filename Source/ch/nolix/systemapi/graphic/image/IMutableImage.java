package ch.nolix.systemapi.graphic.image;

import ch.nolix.coreapi.objectcreation.copier.Copyable;
import ch.nolix.systemapi.graphic.color.IColor;

/**
 * @author Silvan Wyss
 * @param <I> us the type of a {@link IMutableImage}.
 */
public interface IMutableImage<I extends IMutableImage<I>> extends Copyable<I>, IImage {
  I setPixel(int xPosition, int yPosition, IColor color);
}
