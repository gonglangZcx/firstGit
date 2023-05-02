package com.ciut.tool;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readBytesFromDir {
    
    public static byte[] readBytesFrom(File file) throws IOException {
        List<Byte> byteList=new ArrayList<>();
        for (File file1: file.listFiles()
             ) {if (file1.isFile()){
            byte[] bytes = readBytesFromFile.readBytesFrom(file1);
            for (byte b:bytes
                 ) {byteList.add(b);

            }
        }
            
        }
        byte[] bytes=new byte[byteList.size()];
        for (int i=0;i<byteList.size();i++){
            bytes[i]=byteList.get(i);
        }
        return bytes;
    }
}
