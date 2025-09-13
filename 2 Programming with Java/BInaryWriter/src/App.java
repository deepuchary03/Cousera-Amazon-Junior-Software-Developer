import java.util.*;
import java.io.FileOutputStream;
import java.io.*;
import java.lang.*;

public class App {
    public static void main(String[] args) throws Exception {
       byte[] numbers={10,20,30,40,50};
       try(FileOutputStream fileObj=new FileOutputStream("file.dat")){
        for(int i:numbers){
            fileObj.write(i);
        }
        System.out.println("File written with FileOutputStream....");
        fileObj.close();


       }catch(IOException e){
        e.getStackTrace();
       }
    }
}
