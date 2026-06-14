/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.ssl;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificateKeyReader {
  String getKeyFromPemFileLines(IWellOrderContainer<String> pemFileLines);

  String readKeyFromPemFile(String pemFilePath);
}
