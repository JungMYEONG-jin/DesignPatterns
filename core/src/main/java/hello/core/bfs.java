package hello.core;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Thread.sleep;

public class bfs {

    private static Long interval = 30000L;
    private static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static MessageDigest secure_algorithm;
    private static char[] hexArray = "0123456789abcdef".toCharArray();

    public static void main(String[] args) throws Exception{


        while(true)
        {
            long cur = print_interval(System.currentTimeMillis(), interval);
            String md5_hash = get_md5hash(cur);
//            String sha256hash = get_sha256hash(cur);
//            System.out.println("md5: "+md5_hash+" sha256: "+sha256hash);
            sleep(1000);
        }

    }

    private static long print_interval(long cur, long interval)
    {
        long rem_interval = cur%interval;
        long rem_second = cur%1000;
        long limited = (rem_interval-rem_second)==0?0:interval-(rem_interval-rem_second);

        System.out.println((sd.format(new Date(cur-rem_interval)))+" "+sd.format(new Date(cur))+" "+limited/1000);

        return cur-rem_interval;

    }

    public static byte[] long_to_byte(long cur)
    {
        return new byte[]{
                (byte)((cur>>56) & 0xff),
                (byte)((cur>>48) & 0xff),
                (byte)((cur>>40) & 0xff),
                (byte)((cur>>32) & 0xff),
                (byte)((cur>>24) & 0xff),
                (byte)((cur>>16) & 0xff),
                (byte)((cur>>8) & 0xff),
                (byte)((cur>>0) & 0xff)
        };
    }

    public static byte[] long_to_byte_buffer(long cur)
    {
        ByteBuffer bf = ByteBuffer.allocate(8);
        bf.putLong(cur);
        return bf.array();
    }

    public static String get_md5hash(long cur) throws NoSuchAlgorithmException
    {
        secure_algorithm = MessageDigest.getInstance("MD5");
        secure_algorithm.update(long_to_byte_buffer(cur));
        byte[] md5hash = secure_algorithm.digest();

        StringBuilder sb = new StringBuilder();
        for(byte b : md5hash)
        {
            String hexStr = String.format("%02x", b);
            sb.append(hexStr);
        }

        return sb.toString();

    }

    public static String get_sha256hash(long cur) throws NoSuchAlgorithmException
    {
        secure_algorithm = MessageDigest.getInstance("SHA-256");
        secure_algorithm.update(long_to_byte_buffer(cur));
        byte[] sha256hash = secure_algorithm.digest();
        StringBuilder sb = new StringBuilder();
        for(byte b : sha256hash)
        {
            String hexStr = String.format("%02x", b);
            sb.append(hexStr);
        }


        return sb.toString();
    }

    public static String bytesToHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for( int j = 0; j < bytes.length; j++ )
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }






}

// long 8 bytes   long to byte  is byte array length with 8
// hex로 나타내기 때문에 md5 -> 32 length sha256 - > 64 length