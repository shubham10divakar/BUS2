package com.example.subhamdivakar.bus;

/**
 * Created by Subham Divakar on 7/3/2017.
 */
import java.io.InputStream;
import java.io.OutputStream;

public class Utils1 {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
}