package com.tcg.json;

import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class FileHandle.
 */
public class FileHandle {

	/**
	 * Input stream from file.
	 *
	 * @param path the path
	 * @return the input stream
	 */
	public static InputStream inputStreamFromFile(String path) {
		try {
			InputStream inputStream = FileHandle.class.getResourceAsStream(path);
			return inputStream;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}