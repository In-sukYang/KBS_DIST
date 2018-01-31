package kr.co.kbs.distribute.common.util;

import java.security.MessageDigest;

public class SHA512Hash {
	
	public final static byte[] encrypt(byte[] data){
		try	{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(data);
			return md.digest();
		}catch(Exception e)	{
			e.printStackTrace();
			return null;
		}
	}

	public final static String getDigest(String pwd) {
		try	{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
	        byte[] pb = messageDigest.digest(pwd.getBytes());
	        StringBuffer sb = new StringBuffer(pb.length << 1);
	        for (int i=0, iend=pb.length; i<iend ; i++) {
	            int val = (pb[i] + 256) & 0xff;
	            sb.append(Integer.toHexString(val>>4)).append(Integer.toHexString(val & 0xf));
	        }

	        return sb.toString();
		}catch(Exception e)	{
			return null;
		}
    }

}

