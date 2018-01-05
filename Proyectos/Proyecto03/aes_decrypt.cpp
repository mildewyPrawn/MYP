#include <iostream>
#include <iomanip>

#include "crypto++/modes.h"
#include "crypto++/aes.h"
#include "crypto++/filters.h"

//int main(int argc, char* argv[]){
using namespace std;

//int main(){
int main(int argc, char* argv[]){
  
  byte key[ CryptoPP::AES::DEFAULT_KEYLENGTH ], iv[ CryptoPP::AES::BLOCKSIZE ];
  memset(key, 0x00, CryptoPP::AES::DEFAULT_KEYLENGTH);
  memset(iv, 0x00, CryptoPP::AES::BLOCKSIZE);

  //  string ciphertext = "0x9 0x3b 0xc1 0x49 0xf9 0x7c 0xa7 0x1 0x74 0x19 0xa4 0x54 0xf6 0x35 0x1b 0xcc;"
  //  string ciphertext = "	;�I�|�t�T�5�";
  string ciphertext = argv[1];
  string decryptedtext;
  
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
  
  return 0;
}
