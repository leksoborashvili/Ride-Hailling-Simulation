
/**
 * Writes into CSV files given string
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.io.*;
public class Writer
{
    private static FileWriter fw;
    /**
     * sets up a FileWriter object
     * @param s the name of the file
     * @param header header for the file
     */
    public static void setFileWriter(String s,String header) throws Exception
    {
        fw = new FileWriter(s);
        fw .write(header);
        fw .write('\n');
    }
    /**
     * writers given string in the file
     * @param s string to be written in the file
     */
    public static void writeCSV(String s) throws Exception
    {
        fw.write(s);
    }
    /**
     * goes to the next line in the file
     */
    public static void nextLine() throws Exception
    {
        fw.write('\n');
    }
    /**
     * clsoes the file when done
     */
    public static void close() throws Exception
    {
        fw.close();
    }
}
