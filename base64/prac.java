package practice;

import java.util.*;

public class prac {
	
	public static String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	
	
	public static String base64encode(String input)
	{
		int size = input.length();
		int len = (int)Math.ceil(size*4.0/3);
		
		int idx = 0;
		int i = 0;
		char[] result = new char[len];
		char[] s = input.toCharArray();
		for(i=0;i+3<=size;i+=3)
		{
			result[idx++] = base.charAt(s[i]>>2);
			result[idx++] = base.charAt(((s[i]<<4) | (s[i+1]>>4))&0x3f);
			result[idx++] = base.charAt(((s[i+1]<<2) | (s[i+2]>>6))&0x3f);
			result[idx++] = base.charAt(s[i+2] &0x3f);
		}
		int rem = size%3;
		String out = new String();
		switch(rem)
		{
			case 0:
				break;
			case 1:
				result[idx++] = base.charAt(s[i]>>2);
				result[idx++] = base.charAt((s[i]<<4) & 0x3f);
				out = "==";
				break;
			case 2:
				result[idx++] = base.charAt(s[i]>>2);
				result[idx++] = base.charAt(((s[i]<<4) | (s[i+1]>>4)) & 0x3f);
				result[idx++] = base.charAt((s[i+1]<<2) &0x3f);
				out="=";
				break;
				
		}
		
		return new String(result)+out;

	}
	
	public static String base64decode(String input)
	{
		byte[] dtable = new byte[256];
		for(int i=0;i<256;i++)
			dtable[i] = -1;
		dtable['='] = 0;
		for(int i=0, len = base.length();i<len;i++)
			dtable[base.charAt(i)] = (byte)i;
		
		int blen = input.length();
		int real_len = 0;
		while(real_len<blen && dtable[input.charAt(real_len)]!=-1)
			real_len++;
		
		
		int i =0;
		int idx = 0;
		int b64size = (int)Math.ceil(real_len/4.0*3);
		char[] s = input.toCharArray();
		char[] out = new char[b64size];
		for(i = 0;i+4<real_len;i+=4)
		{
			out[idx++] = (char)((dtable[s[i]]<<2 | dtable[s[i+1]]>>4) & 0xff);
			out[idx++] = (char)((dtable[s[i+1]]<<4 | dtable[s[i+2]]>>2) &0xff);
			out[idx++] = (char)((dtable[s[i+2]]<<6 | dtable[s[i+3]]) &0xff);
		}
		
		if(real_len-i>1)
		{
			out[idx++] = (char)((dtable[s[i]]<<2 | dtable[s[i+1]]>>4) &0xff);
			
		}
		if(real_len-i>2)
		{
			out[idx++] = (char)((dtable[s[i+1]]<<4 | dtable[s[i+2]]>>2) & 0xff);
			
		}
		if(real_len-i>3)
		{
			out[idx++] = (char)((dtable[s[i+2]]<<6 | dtable[s[i+3]]) &0xff);
			
		}
		
		
		
		return new String(out);
		
	}
	
	
	public static void main(String[] args)
	{
		System.out.println(base64encode("Man"));
		System.out.println(base64encode("Many"));
		System.out.println(base64encode("tei Love!"));
		
		System.out.println(base64decode("TWFu"));
		System.out.println(base64decode("TWFueQ=="));
		System.out.println(base64decode("dGVpIExvdmUh"));
		
		
	}

}
