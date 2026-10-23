/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.ssl;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificateKeyReader {
  /**
   * @param pemFileLines
   * @return the key from the given pemFileLines
   */
  String getKeyFromPemFileLines(ExtendedIterable<String> pemFileLines);

  /**
   * @param pemFilePath
   * @return the key from the PEM file with the given pemFilePath
   * @throws RuntimeException if the PEM file with the given pemFilePath cannot be
   *                          read
   */
  String readKeyFromPemFile(String pemFilePath);
}
