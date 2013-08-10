/**
 * 
 * Since RSA can only encrypt 117 bytes of data so we can use rsa with aes to work together.
 * 
 * AES key is generated and used to encrypt the data.
 * The key is then encrypted by the RSA public key given.
 * 
 * @author dssb
 *
 */
package dssb.cryptography.schemes.rsaaes;