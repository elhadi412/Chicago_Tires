import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
	  public static String hashPassword(String password) throws NoSuchAlgorithmException {
	        String hashed_password = "";
	        try {
	            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	            messageDigest.update(password.getBytes());
	            byte[] hash_bytes = messageDigest.digest();
	            StringBuilder to_hex_format = new StringBuilder();
	            for (int i = 0; i < hash_bytes.length; i++) {
	                to_hex_format.append(Integer.toString((hash_bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            hashed_password = to_hex_format.toString();
	        } catch (NoSuchAlgorithmException nsa) {
	            nsa.printStackTrace();
	        }
	        System.out.println(hashed_password.toString());
	        return hashed_password;

	    }
	  public static void main(String[] args) throws NoSuchAlgorithmException{
		  PasswordHash.hashPassword("Hodhod#5");
	  }
	  //Hussein (hussein) -> Chicago_1630
	  //Mustafa (mustafa) -> Ypsm@777
	  //elhadi (elhadi) -> Hodhod#5



}
