import java.io.*;

class Test 
{
    public static void main(String args[])
    {
        try
        {
            FileInputStream fstream = new FileInputStream("SmallAreaIncomePovertyEstDataFixed.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            int lines = 0;
            
            while (br.readLine() != null) 
            {
                lines++;
            }
                    
            //Close the input stream
            fstream.close();
            
            //Print the number of lines 
            System.out.println("Number of lines in the file = " + lines);
        } 
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }       
        
        
        // try
        // {
            // FileInputStream fstream = new FileInputStream("SmallAreaIncomePovertyEstDataFixed.txt");
            // BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            // String strLine;
            
            ////Loop through and check if a header or footer line, if not
            ////equate a substring to a temp variable and print it....
            // while ( (strLine = br.readLine()) != null )   
            // {
                // String tempName = strLine.substring(9,80);
                // System.out.println(tempName);
            // }
        
            ////Close the input stream
            // fstream.close();
        // } 
        
        // catch (Exception e) 
        // {
            // e.printStackTrace();
        // }
    }
}