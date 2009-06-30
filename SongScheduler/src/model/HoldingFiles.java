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
    private File listFile;
    private LinkedList<String> list;
    private String defaultHoldingFile;

    /**
     * Initialize the holding files list. In case the holding file list
     * doesn't exist. If that happens, create a new file has only default.
     */
    public void init() {
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
    }

    /**
     * Get the holding file list as a LinkedList instance.
     * @return
     */
    public LinkedList<String> getHoldingFiles() {
        return list;
    }

    /**
     * Add a new file to the holding file list.
     * @param filename
     */
    public void addFile(String filename) {
        list.add(filename);
        writeToDisk();
    }

    /**
     * Write the <code>list</code> to the disk file.
     */
    private void writeToDisk() {
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
    public void setDefault(String filename) {
        defaultHoldingFile = filename;
    }
}
