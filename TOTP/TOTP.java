package hello.core.rfc;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

public class TOTP {

    private TOTP(){}

    /**
     *
     * @param crpyto 암호 알고리즘 ex HmacSHA1 HmacSHA256...
     * @param keyBytes HMAC 키로 사용될 bytes
     * @param text 암호화될 메세지
     * @return
     */
    private static byte[] hmac_sha(String crpyto, byte[] keyBytes, byte[] text)
    {
        try {
            Mac hmac;
            hmac = Mac.getInstance(crpyto); // 사용할 암호 알고리즘 가져옴
            // keybytes 길이만큼
            SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
            hmac.init(macKey);
            return hmac.doFinal(text);
        }catch(GeneralSecurityException gse)
        {
            throw new UndeclaredThrowableException(gse);
        }
    }

    private static byte[] hexStr2Bytes(String hex)
    {
        byte[] bArr = new BigInteger("10"+hex, 16).toByteArray();

        byte[] ret = new byte[bArr.length-1];
        for(int i=0, len = ret.length;i<len;i++)
        {
            // 첫글자를 제외한 복사
            ret[i] = bArr[i+1];
        }
        return ret;
    }

    // 0 1 2 3 4 5 6 7 8
    private static final int[] DIGITS_POWER = {1,10,100,1000,10000,100000,1000000,10000000,100000000 };

    /**
     * generate TOTP Value
     * @param key 16진수 인코딩된 비밀
     * @param time 시간
     * @param returnDigits 리턴해야할 숫자의 갯수
     * @return
     */
    public static String generateTOTP(String key, String time, String returnDigits)
    {
        return generateTOTP(key, time, returnDigits, "HmacSHA1");
    }

    public static String generateTOTP256(String key, String time, String returnDigits)
    {
        return generateTOTP(key, time, returnDigits, "HmacSHA256");
    }

    public static String generateTOTP512(String key, String time, String returnDigits)
    {
        return generateTOTP(key, time, returnDigits, "HmacSHA512");
    }

    /**
     *
     * @param key 16진수 인코딩된 비밀
     * @param time 시간
     * @param returnDigits 리턴해야할 숫자의 갯수
     * @param crpyto 사용할 알고리즘
     * @return
     */
    public static String generateTOTP(String key, String time, String returnDigits, String crpyto)
    {
        // first 8bytes 는 이동요소
        String result = null;
        // 몇자리로 리턴할지 파악
        int codeDigits = Integer.decode(returnDigits).intValue();

        //16자리 맞추기
        while(time.length()<16)
        {
            time = "0"+time;
        }

        byte[] msg = hexStr2Bytes(time); // 시간을 bytes로
        byte[] k = hexStr2Bytes(key);

        // key는 시드값
        //시간을 암호화
        byte[] hash = hmac_sha(crpyto, k, msg);

        int offset = hash[hash.length-1] & 0xf; //헥스자릿수로 잘라냄 0xF 1111

//        System.out.println();
//        System.out.println(hash[offset]+" "+hash[offset+1]+" "+hash[offset+2]+" "+hash[offset+3]);
        // 아직까지 대부분 연산은 32비트로함 그래서 32비트만큼만 계산한듯함
        // binary는 truncatedHash임
        int binary = ((hash[offset] & 0x7f) << 24) |
                ((hash[offset+1] & 0xff) << 16) |
                ((hash[offset+2] & 0xff) << 8) |
                (hash[offset+3] & 0xff);

        //System.out.println((hash[offset] & 0x7f)<<24); 그대로 가져온후 24비트 이동
        // 0xff 음수를 양수로 변환시켜줌 자바는 unsigned가 없음
        //System.out.println(hash[hash.length-1]);
        //System.out.println(hash[hash.length-1] & 0xf);

        // hash를 10제곱 (자리수)로 나눈 나머지를 finalOTP로사용
        int otp = binary % DIGITS_POWER[codeDigits];

        result = Integer.toString(otp);
        while(result.length() < codeDigits)
        {
            result = "0"+result;
        }

        return result;
    }

    public static String calcSteps(Long time, Long T0, Long X)
    {
        long T = (time-T0)/X;

        String steps = "0";
        steps = Long.toHexString(T).toUpperCase();
        while(steps.length()<16)
            steps = "0"+steps;
        return steps;
    }


}
