/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohit.notepadgui.support;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Mohit
 */
public class FileSupport {
    
    public static String fileReader(String file) throws IOException
    {
        BufferedReader reader=new BufferedReader(new FileReader(file));
        String line="";
        StringBuilder content=new StringBuilder();
            while((line=reader.readLine())!=null)
            {
                content.append(line +"\t\n");
            }
            
            reader.close();
            return content.toString();
    
    }
    
    public static void fileWriter(String file,String content) throws IOException
    {
       FileWriter writer=new FileWriter(file);
       writer.write(content);
      writer.close();
       
    }
}
