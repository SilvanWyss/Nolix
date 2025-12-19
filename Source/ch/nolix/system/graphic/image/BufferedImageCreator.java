package ch.nolix.system.graphic.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;

/**
 * @author Silvan Wyss
 */
public final class BufferedImageCreator {
  private BufferedImageCreator() {
  }

  public static BufferedImage createBufferedImageFromBytes(final byte[] bytes) {
    try {
      return ImageIO.read(new ByteArrayInputStream(bytes));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  public static BufferedImage createBufferedImageFromFileWithFilePath(final String filePath) {
    try {
      return ImageIO.read(new File(filePath));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
