package io;

import java.io.*;
import java.util.ArrayList;

public class FileDocSource extends DocSource {
    
    private ArrayList<File> _files = new ArrayList<File>();
    
    public FileDocSource(String s) {
            for (File f : FileFinder.GetAllFiles(s)) //for each file in all the fles add its name to the list of files
                _files.add(f);  
    }
    
    //returns the index of the given file
    @Override
    public String getDoc(int id) {
        String s = null;
        StringBuilder sb = new StringBuilder();
        
        try {
            BufferedReader fin = new BufferedReader(new FileReader(_files.get(id)));
            
            while ((s = fin.readLine()) != null) {
                sb.append(s);
            }
            fin.close();
            
        } catch (Exception e) {
            System.out.println("error reading file");
        }
        return sb.toString();
    }
    
    //returns the number of docs in the search
    @Override
    public int getNumDocs() {
        return _files.size();
    }
}


