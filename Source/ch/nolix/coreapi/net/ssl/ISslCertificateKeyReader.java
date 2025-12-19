package ch.nolix.coreapi.net.ssl;

/**
 * @author Silvan Wyss
 */
public interface ISslCertificateKeyReader {
  String readKeyFromPemFile(String pemFilePath);
}
