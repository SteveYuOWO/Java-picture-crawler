package com.littlepage.getImage;

import java.io.B*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import javax.swing.JFrame;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class Worm{
	public static int doDownLoad(String url){
		int successNum=0;
		try{
			// 1.get html file=
			String file = "img";
			WebClient mWebClient = new WebClient();
			HtmlPage mHtmlPage = mWebClient.getPage(url);
			String writeStr = mHtmlPage.asXml();// 文本
			Pattern pattern = Pattern.compile("http.+?(jpg|png|gif)");
			Matcher matcher = pattern.matcher(writeStr);
			ArrayList<String> arrStr = new ArrayList<String>();
			int i = 0;
			String fileRanStr=getRandomString(5);
			while (matcher.find()) {
				String temp = matcher.group();// 圖片地址
				System.out.println(temp);
				if (temp.endsWith("jpg"))
					if(downloadImage(temp, file + "\\"+fileRanStr + i + ".jpg")==true) successNum++;
				if (temp.endsWith("png"))
					if(downloadImage(temp, file + "\\"+fileRanStr + i + ".png")==true) successNum++;
				if (temp.endsWith("gif"))
					if(downloadImage(temp, file + "\\"+fileRanStr + i + ".gif")==true) successNum++;
				i++;
			}
			mWebClient.close();
		}catch(Exception e){
			System.out.println("没事，小异常不搭界");
		}
		return successNum;
	}
	/**
	 * random string
	 */
	private static String string = "abcdefghijklmnopqrstuvwxyz";
	private static int getRandom(int count) {
	   return (int)Math.round(Math.random()*(count));
	}
	private static String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		int len = string.length();
		for (int i = 0; i < length; i++) {
			sb.append(string.charAt(getRandom(len - 1)));
		}
		return sb.toString();
	}

	/**
	 * getPic
	 * 
	 * @param urlString
	 *            url address
	 * @param path
	 *            file path
	 */
	public static boolean downloadImage(String urlString, String path) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
			byte[] data = readInputStream(inStream);
			File imageFile = new File(path);
			FileOutputStream outStream = new FileOutputStream(imageFile);
			outStream.write(data);
			outStream.close();
		} catch (Exception e) {
			System.out.println("下载失败");
			return false;
		}
		System.out.println("下载成功");
		return true;
	}

	public static byte[] readInputStream(InputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			inStream.close();
			return outStream.toByteArray();
		} catch (Exception e) {
			System.out.println("读取失败");
		}
		System.out.println("读取成功");
		return null;
	}
}
