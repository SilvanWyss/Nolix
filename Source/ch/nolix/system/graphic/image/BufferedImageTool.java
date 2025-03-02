package ch.nolix.system.graphic.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.nolix.core.errorcontrol.exception.WrapperException;

public final class BufferedImageTool {

  public BufferedImage fromBytes(final byte[] bytes) {
    try {
      return ImageIO.read(new ByteArrayInputStream(bytes));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  public BufferedImage fromFile(final String filePath) {
    try {
      return ImageIO.read(new File(filePath));
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
