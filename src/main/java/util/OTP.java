package util;

import java.util.Random;
import hibernateModel.ExternalUser;
import org.springframework.stereotype.Service;

import hibernateDao.ExternalUserDao;

@Service
public class OTP {

    public static long expiry;
    public static int otp;

    public static void GenerateOTP(int id) {
        Random rand = new Random();
        otp = 0;
        for (int i = 0; i < 5; i++) {
            otp *= 10;
            otp += rand.nextInt(10);
        }
        long now = System.currentTimeMillis();
        expiry = now + (10 * 60 * 1000);
        
        ExternalUserDao extDao = new ExternalUserDao();
        ExternalUser extuser = extDao.get(id, "user_id");
        
        SendEmail.send(extuser.getEmail(), otp);
    }
    
    public static void GenerateOTP(String email) {
        Random rand = new Random();
        otp = 0;
        for (int i = 0; i < 5; i++) {
            otp *= 10;
            otp += rand.nextInt(10);
        }
        long now = System.currentTimeMillis();
        expiry = now + (10 * 60 * 1000);
        
        ExternalUserDao extDao = new ExternalUserDao();
        ExternalUser extuser = extDao.get(email, "email");
        
        if(extuser!=null)
            SendEmail.send(extuser.getEmail(), otp);
    }

    public static boolean VerifyOPT(int input_otp) {
        boolean otpMatch = false;
        long currentTime = System.currentTimeMillis();

        if (input_otp == otp && (expiry - currentTime) > 0) {
            otpMatch = true;
        }

        return otpMatch;
    }

}