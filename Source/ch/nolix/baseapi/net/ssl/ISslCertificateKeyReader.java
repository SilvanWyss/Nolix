/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.ssl;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificateKeyReader {
  String getKeyFromPemFileLines(ExtendedIterable<String> pemFileLines);

  String readKeyFromPemFile(String pemFilePath);
}
