//package declaration
package ch.nolix.coreapi.netapi.tlsapi;

//interface
public interface ISSLCertificate {
	
	//method declaration
	byte[] getPrivateKeyBytes();
	
	//method declaration
	byte[] getPublicKeyBytes();
}
