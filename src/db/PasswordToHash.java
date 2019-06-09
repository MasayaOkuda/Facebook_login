package db;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordToHash {
    //ハッシュ化のアルゴリズム
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";


    //ストレッチング回数
    private static final int ITERATION_COUNT = 10000;


    //生成される鍵の長さ
    private static final int KEY_LENGTH = 256;


    public static String getHashedPassword(String password, String salt) {
        //PBEKeyを生成に必要な値を作成
        char[] passCharAry = password.toCharArray();
        byte[] hashedSalt = getSalt(salt);


        //PBEKeyを生成
        PBEKeySpec keySpec = new PBEKeySpec(passCharAry, hashedSalt, ITERATION_COUNT, KEY_LENGTH);


        //秘密鍵を扱う用
        SecretKeyFactory skf = null;


        try {
            //「PBKDF2WithHmacSHA256」アルゴリズムの秘密鍵を変換するオブジェクトの生成
            skf = SecretKeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            //TODO　自動生成されたcatchブロック
            e.printStackTrace();
        }


        //秘密鍵のインターフェイス
        //すべての秘密鍵のインターフェイスを扱える
        SecretKey secretKey = null;
        try {
            //PBEKeyの鍵仕様で、秘密鍵を生成
            secretKey = skf.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            //TODO　自動生成されたcatchブロック
            e.printStackTrace();
        }
        //鍵（秘密鍵）を一次符号化形式
        byte[] passByteAry = secretKey.getEncoded();


        //生成されたバイト列を１６進数の文字列に変換
        StringBuffer sf = new StringBuffer(64);
        for (byte b : passByteAry) {
            sf.append(String.format("%02x", b & 0xff));
        }


        return sf.toString();
    }


    //ソルトを生成
    private static byte[] getSalt(String salt) {
        byte[] saltBytes = null;
        MessageDigest messageDigest = null;


        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            //TODO　自動生成されたcatchブロック
            e.printStackTrace();
        }


        if (messageDigest != null) {
            messageDigest.update(salt.getBytes());
            saltBytes = messageDigest.digest();
        }


        return saltBytes;
    }

}
