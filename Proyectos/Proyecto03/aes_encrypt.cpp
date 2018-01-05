#include <iostream>
#include <iomanip>

#include "crypto++/modes.h"
#include "crypto++/aes.h"
#include "crypto++/filters.h"

//int main(int argc, char* argv[]){
using namespace std;

int main(){

  byte key[ CryptoPP::AES::DEFAULT_KEYLENGTH ], iv[ CryptoPP::AES::BLOCKSIZE ];
  memset(key, 0x00, CryptoPP::AES::DEFAULT_KEYLENGTH);
  memset(iv, 0x00, CryptoPP::AES::BLOCKSIZE);

  //
  //String and Sink setup
  //
  //  string plaintext = "Now is the time for all good men to come to the aide...";
  //  string plaintext = "Now is the time for all good men to come to the aide...";
  string plaintext = "E";
  string ciphertext;
  //  string decryptedtext;

  //
  // Dump plain text
  //
  cout << "Plain text (" << plaintext.size() << " bytes)" << endl;
  cout << plaintext;
  cout << endl << endl;

  //
  // Create Cipher Text
  //
  CryptoPP::AES::Encryption aesEncryption(key, CryptoPP::AES::DEFAULT_KEYLENGTH);
  CryptoPP::CBC_Mode_ExternalCipher::Encryption cbcEncryption(aesEncryption, iv);

  CryptoPP::StreamTransformationFilter stfEncryptor(cbcEncryption, new CryptoPP::StringSink(ciphertext));
  stfEncryptor.Put(reinterpret_cast<const unsigned char*>(plaintext.c_str()),plaintext.length() + 1);
  stfEncryptor.MessageEnd();

  //
  // Dump Cipher Text
  //
  cout << "Cipher Text (" << ciphertext.size() << " bytes) " << endl;
  for(int i = 0; i < ciphertext.size(); i++){
    cout << "0x" << hex << (0xFF & static_cast<byte>(ciphertext[i])) << " ";
  }
  cout << endl << endl;

  //  cout << ciphertext << " HOOOOLAAAA" << endl;
  cout << ciphertext << endl;
  /*
  //
  // Decrypt
  //
  CryptoPP::AES::Decryption aesDecryption(key, CryptoPP::AES::DEFAULT_KEYLENGTH);
  CryptoPP::CBC_Mode_ExternalCipher::Decryption cbcDecryption(aesDecryption, iv);

  CryptoPP::StreamTransformationFilter stfDecryptor(cbcDecryption, new CryptoPP::StringSink(decryptedtext));
  stfDecryptor.Put(reinterpret_cast<const unsigned char*>(ciphertext.c_str()), ciphertext.size());
  stfDecryptor.MessageEnd();

  //
  // Dump Decrypted Text
  //
  cout << "Decrypted Text: " << endl;
  cout << decryptedtext;
  cout <<  endl << endl;
*/
  return 0;
}
