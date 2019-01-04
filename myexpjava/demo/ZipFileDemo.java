import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;



public class ZipFileDemo {
 
 public static void main(String args[]) throws IOException {  
  // create a zip file
  String zipFileName = "D:\\demo.zip";
  ZipOutputStream zipos = new ZipOutputStream(new FileOutputStream(zipFileName));
  // In Zip Output Stream we can create file.
  ZipEntry ze1 = new ZipEntry("file1.txt");
  zipos.putNextEntry(ze1);
  zipos.write("Hello World".getBytes());
  zipos.closeEntry();
  // Now lets create another file in a directory
  zipos.putNextEntry(new ZipEntry("etc\\file2.txt"));
  zipos.write("Hello Again".getBytes());
  zipos.closeEntry();
  zipos.close();
  
  // To Read Zip file
  ZipFile zipFile = new ZipFile(zipFileName);
  // for specific entry
  ZipEntry ze2 = zipFile.getEntry("file1.txt");
  InputStream is = zipFile.getInputStream(ze2);
  Scanner scan = new Scanner(is);
  while (scan.hasNextLine())
  {
   System.out.println(scan.nextLine());
  }
  scan.close();
  Enumeration enm = zipFile.entries();
  while(enm.hasMoreElements())
  {
   ZipEntry ze = (ZipEntry) enm.nextElement();
   System.out.println(ze.getName());
  }  
 }

}
