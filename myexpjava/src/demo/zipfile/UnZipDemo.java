package demo.zipfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnZipDemo {
	public static void main(String args[]) throws IOException
	{
		String zipFileName = "D:\\demo.zip";
		ZipFile zipFile = new ZipFile(zipFileName);
		String extractedPath = "D:\\demo";
		byte[] buffer = new byte[1024];
		Enumeration enm = zipFile.entries();
		ZipEntry ze = null;
		InputStream fis = null;
		while (enm.hasMoreElements()) {
			ze = (ZipEntry) enm.nextElement();
			System.out.println("processing = "+ze.getName());
			File newFile = new File(extractedPath + File.separator + ze.getName());
			// create all non exists folders
			new File(newFile.getParent()).mkdirs();
			FileOutputStream fos = new FileOutputStream(newFile);
			int len;
			fis = zipFile.getInputStream(ze);
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		}
	}

}
