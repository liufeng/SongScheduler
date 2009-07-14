/*
 * To manage the library holding files.
 */

package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author liufeng
 */
public abstract class HoldingFiles {
    private static final String HOLDING_FILE = "holdingFile.txt";
    private static File listFile;
    private static LinkedList<String> list;
    private static String defaultHoldingFile;
    private static String currHoldingFile;

    /**
     * Initialize the holding files list. In case the holding file list
     * doesn't exist. If that happens, create a new file has only default.
     */
    public static void init() {
        list = new LinkedList<String>();

        try {
            listFile = new File(HOLDING_FILE);
            if (!listFile.exists()) {
                BufferedWriter out = new BufferedWriter(new FileWriter(listFile));
                out.write("default.txt\n");
                out.close();
            }

            BufferedReader in = new BufferedReader(new FileReader(listFile));
            String line = in.readLine();
            while (line != null) {
                list.add(line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        defaultHoldingFile = "default.txt";
        currHoldingFile = defaultHoldingFile;
    }

    /**
     * Get the holding file list as a LinkedList instance.
     * @return
     */
    public static LinkedList<String> getHoldingFiles() {
        return list;
    }

    public static Iterator<String> iterator()
    {
        return list.iterator();
    }

    /**
     * Add a new file to the holding file list.
     * @param filename
     */
    public static void addFile(String filename) {
        list.add(filename);
        writeToDisk();
    }

    /**
     * Write the <code>list</code> to the disk file.
     */
    private static void writeToDisk() {
        Iterator<String> iter = list.iterator();
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(listFile));
            while (iter.hasNext()) {
                out.write(iter.next());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the default holding file.
     * @param filename
     */
    public static void setDefault(String filename) {
        defaultHoldingFile = filename;
    }

    /**
     * Set the current holdings file
     * @param filename
     */
    public static void setCurrent(String filename){
        currHoldingFile = filename;
    }

    /**
     * Get the current holdings file
     * @return
     */
    public static String getCurrent(){
        return currHoldingFile;
    }

    public static void addSongToFile( String filename )
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
            out.write("Testing");
            out.close();
        }
        catch( IOException ioe ){}
    }
}
