package com.lora.util;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.logicalcobwebs.proxool.util.IDecryptool;

import com.dianping.cat.Cat;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 */
@SuppressWarnings("restriction")
public class AES256 implements IDecryptool {

	static String key = "lora_dev";

	public static void main(String[] args) {
		TestAes256();
	}

	public static void TestAes256() {
		String plain = "123456";
		String cipher = new AES256().encrypt(plain);
		System.out.println("明文: " + plain);
		System.out.println("加密后: " + cipher);
		String plain1 = new AES256().decrypt(cipher);
		System.out.println("解密后: " + plain1);
	}

	public String encrypt(String text) {
		try {
			byte[] cipher = Encrypt(key.getBytes(), text.getBytes());
			return new BASE64Encoder().encode(cipher);
		} catch (Exception e) {
			Cat.logError(e);
			return "";
		}
	}

	public String decrypt(String cipher) {
		try {
			BASE64Decoder base64de = new BASE64Decoder();
			byte[] cipherBytes = base64de.decodeBuffer(cipher);
			byte[] text = Decrypt(key.getBytes(), cipherBytes);
			return new String(text);
		} catch (Exception e) {
			Cat.logError(e);
			return "";
		}
	}

	static byte[] Encrypt(byte[] key, byte[] text) {

		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// byte[] aesKeyBytes = new byte[32]; //32 bytes for AES-256
			byte[] aesKeyBytes = new byte[16]; // 32 bytes for AES-256
			// int len = key.length > 32 ? 32 : key.length;
			int len = key.length > 16 ? 16 : key.length;
			System.arraycopy(key, 0, aesKeyBytes, 0, len);

			SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");

			byte[] iv = new byte[cipher.getBlockSize()];

			Random ran = new Random();
			ran.nextBytes(iv); // 随机的初始化向量
			IvParameterSpec ivSpec = new IvParameterSpec(iv);

			cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			byte[] results = cipher.doFinal(text);

			// 将iv和密文拼接后返回
			byte[] retBytes = new byte[iv.length + results.length];
			System.arraycopy(iv, 0, retBytes, 0, iv.length);
			System.arraycopy(results, 0, retBytes, iv.length, results.length);

			return retBytes;

		} catch (Exception e) {
			Cat.logError(e);
			return null;
		}
	}

	static byte[] Decrypt(byte[] key, byte[] cipher) {
		try {
			if (cipher.length < 32 || cipher.length % 16 != 0) {
				return null;
			}

			Cipher cp = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// byte[] aesKeyBytes = new byte[32]; //32 bytes for AES-256
			byte[] aesKeyBytes = new byte[16]; // 32 bytes for AES-256

			// int len = key.length > 32 ? 32 : key.length;
			int len = key.length > 16 ? 16 : key.length;
			System.arraycopy(key, 0, aesKeyBytes, 0, len);

			SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");

			byte[] iv = new byte[cp.getBlockSize()];

			System.arraycopy(cipher, 0, iv, 0, iv.length);

			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			cp.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

			byte[] cip = new byte[cipher.length - iv.length];

			System.arraycopy(cipher, iv.length, cip, 0, cip.length);
			byte[] results = cp.doFinal(cip);

			return results;
		} catch (Exception e) {
			Cat.logError(e);
			return null;
		}
	}

}
