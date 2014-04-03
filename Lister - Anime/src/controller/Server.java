package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import view.db.SocketExport;


public class Server implements Runnable
{

    private boolean stop = false, sending = false;
    private Socket socket;
    private ServerSocket server = null;
    private SocketExport export;


    public Server(SocketExport export)
    {
        this.export = export;
    }


    public void run()
    {
        if (server == null)
        {
            try
            {
                server = new ServerSocket(8888);
                export.setReady();
                sendData();
                export.setNotReady();
                server.close();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        else
        {
            try
            {
                server.close();
                server = new ServerSocket(8888);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            sendData();
        }
    }


    public boolean setStop()
    {
        if (!sending)
        {
            try
            {
                stop = true;
                server.close();
            }
            catch (IOException e)
            {
                // e.printStackTrace();
            }
            return true;
        }
        return false;
    }


    private void sendData()
    {
        while (!stop)
        {
            try
            {
                socket = server.accept();
                if (socket.isConnected())
                {
                    sending = true;
                    OutputStream out = socket.getOutputStream();
                    BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(out));
                    BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // senden der daten
                    FileReader fileReader = new FileReader(ListerProperties.getDefaultProperties().getProperty("pfad"));
                    BufferedReader bufferedFileReader = new BufferedReader(fileReader);
                    while (bufferedFileReader.ready())
                    {
                        bf.write(bufferedFileReader.readLine() + "\n");
                        bf.flush();
                        read.readLine();
                    }
                    bf.write("EOF");
                    bf.flush();
                    socket.close();
                    sending = false;
                    export.downloadUp();
                }
            }
            catch (IOException e)
            {
                // e.printStackTrace();
            }
        }
    }
}
