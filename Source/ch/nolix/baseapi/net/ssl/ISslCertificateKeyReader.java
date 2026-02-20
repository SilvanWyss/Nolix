/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.ssl;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificateKeyReader {
  String getKeyFromPemFileLines(IContainer<String> pemFileLines);

  String readKeyFromPemFile(String pemFilePath);
}
