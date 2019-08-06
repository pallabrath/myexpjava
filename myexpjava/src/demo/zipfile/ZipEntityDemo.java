package demo.zipfile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipEntityDemo {
	public InputStream extract(ZipInputStream zis, String entityName) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipEntry ze = null;
		do {
			ze = zis.getNextEntry();
			if (ze != null && ze.getName().equalsIgnoreCase(entityName)) {

				byte[] buffer = new byte[1];
				while (zis.available() == 1) {
					zis.read(buffer);
					out.write(buffer, 0, 1);
				}
				out.close();
				return new ByteArrayInputStream(out.toByteArray());
			}
		} while (ze != null);

		return null;
	}
}
