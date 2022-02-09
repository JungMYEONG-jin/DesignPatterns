package hello.core;

import hello.core.rfc.CryptoType;
import hello.core.rfc.TOTP;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static hello.core.rfc.TOTP.generateTOTP;

public class Main {
    public static void main(String[] args) {

        String time = "1";
        while(time.length()<16)
            time = "0"+time;
        System.out.println(time);
        System.out.println(time.length());

        // 20 bytes
        String seed = "3132333435363738393031323334353637383930";
        // Seed for HMAC-SHA256 - 32 bytes
        String seed32 = "3132333435363738393031323334353637383930" +
                "313233343536373839303132";
        // Seed for HMAC-SHA512 - 64 bytes
        String seed64 = "3132333435363738393031323334353637383930" +
                "3132333435363738393031323334353637383930" +
                "3132333435363738393031323334353637383930" +
                "31323334";

        long T0 = 0;
        long X = 30;
        long testTime[] = {59L, 1111111109L, 1111111111L,
                1234567890L, 2000000000L, 20000000000L};

        String steps = "0";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        try{
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");
            System.out.println(
                    "|  Time(sec)    |   Time (UTC format)   " +
                            "| Value of T(Hex)  |  TOTP  | Mode   |");
            System.out.println(
                    "+---------------+-----------------------+" +
                            "------------------+--------+--------+");

            for(int i=0;i<testTime.length;i++)
            {
                /*
                long T = (testTime[i]-T0)/X;
                steps = Long.toHexString(T).toUpperCase();
                while(steps.length()<16)
                    steps = "0"+steps;
                 */
                steps = TOTP.calcSteps(testTime[i], T0, X);

                String fmtTime = String.format("%1$-11s", testTime[i]);

                // millisecond to utc time
                String utcTime = df.format(new Date(testTime[i] * 1000));

                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(generateTOTP(seed, steps, "8",
                        CryptoType.HmacSHA1.toString()) + "| SHA1   |");
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(generateTOTP(seed32, steps, "8",
                        CryptoType.HmacSHA256.toString()) + "| SHA256 |");
                System.out.print("|  " + fmtTime + "  |  " + utcTime +
                        "  | " + steps + " |");
                System.out.println(generateTOTP(seed64, steps, "8",
                        CryptoType.HmacSHA512.toString()) + "| SHA512 |");

                System.out.println(
                        "+---------------+-----------------------+" +
                                "------------------+--------+--------+");


                // 고객의 핸드폰 디바이스번호를 시드로 만들고 해도될듯
                //Calendar time = Calendar.getInstance(); 현재 시각 추출
                //String steps = TimeBasedOTP.calcSteps(time.getTimeInMillis() / 1000, 0L, 30L); 유지시간 30초로 설정
                //TOTP.generateTOTP(device.getUuid(), steps, "8", CryptoType.HmacSHA512.toString()); OTP 생성


            }
        }catch(final Exception e)
        {
            System.out.println("Error : "+e);
        }



    }
}
