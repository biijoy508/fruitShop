package se.sjv.happyblueberry.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static String[] readLines(final String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines.toArray(new String[lines.size()]);
	}

	public static void writeLines(final String filename, final String[] content)
			throws IOException {
		FileWriter writer = new FileWriter(filename, false);
		for (int i = 0; i < content.length; i++) {
			writer.append(content[i]);
		}
		writer.close();
	}
}
