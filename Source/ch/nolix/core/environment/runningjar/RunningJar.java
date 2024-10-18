package ch.nolix.core.environment.runningjar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

public final class RunningJar {

  private RunningJar() {
  }

  public static String getResource(final String path) {
    final var stringBuilder = new StringBuilder();
    final var inputStream = RunningJar.class.getResourceAsStream("/" + path);
    try (
    final var bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      var line = bufferedReader.readLine();
      while (line != null) {
        stringBuilder.append(line).append("\n");
        line = bufferedReader.readLine();
      }
      return stringBuilder.toString();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  public static byte[] getResourceAsBytes(final String path) {
    final var inputStream = RunningJar.class.getResourceAsStream("/" + path);
    try {
      return inputStream.readAllBytes();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  public static IContainer<String> readLinesOfResource(final String path) {

    final ILinkedList<String> lines = LinkedList.createEmpty();
    final var inputStream = RunningJar.class.getResourceAsStream(StringCatalogue.SLASH + path);
    final var inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

    try (final var bufferedReader = new BufferedReader(inputStreamReader)) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        lines.addAtEnd(line);
      }
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }

    return lines;
  }
}
