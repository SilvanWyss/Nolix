package ch.nolix.coreapi.net.ssl;

public interface ISslCertificateKeyReader {

  String readKeyFromPemFile(String pemFilePath);
}
