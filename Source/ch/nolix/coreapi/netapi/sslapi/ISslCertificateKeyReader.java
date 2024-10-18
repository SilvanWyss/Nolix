package ch.nolix.coreapi.netapi.sslapi;

public interface ISslCertificateKeyReader {

  String readKeyFromPemFile(String pemFilePath);
}
