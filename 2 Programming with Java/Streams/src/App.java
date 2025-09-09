import java.io.*;
import java.nio.charset.StandardCharsets;
public class App {
    public static void main(String[] args) throws Exception {
     try {
   FileOutputStream outputStream = new FileOutputStream("EmployeeList.txt");
   OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_16);
   BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

   Employee employee = new Employee("John", 23, 50000);
   bufferedWriter.write(employee.toString());
   bufferedWriter.newLine();

   bufferedWriter.close();
} catch (IOException e) {
   
   System.out.println("Exception:" + e.getMessage());
}
try {
   FileReader reader = new FileReader("EmployeeList.txt");
   BufferedReader bufferedReader = new BufferedReader(reader);

   String line;

   while ((line = bufferedReader.readLine()) != null) {
       System.out.println(line);
   }
   reader.close();

} catch (IOException e) {
      System.out.println("Exception:" + e.getMessage());;
}

    }
}
