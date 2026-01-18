/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.ssl;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificateKeyReader {
  String getKeyFromPemFileLines(IContainer<String> pemFileLines);

  String readKeyFromPemFile(String pemFilePath);
}
