//package declaration
package ch.nolix.core.net.tls;

//own imports
import ch.nolix.coreapi.netapi.tlsapi.ISSLCertificate;

//record
public record SSLCertificate(String publicKey, String privateKey) implements ISSLCertificate {
	
	//method
	@Override
	public String getPrivateKey() {
		return privateKey;
	}
	
	//method
	@Override
	public String getPublicKey() {
		return publicKey;
	}
}
