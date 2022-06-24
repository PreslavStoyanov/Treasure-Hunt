import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket acceptSocket;
    private PrintStream output;
    private BufferedReader input;
    List<ConnectionHandler> clients = new ArrayList<>();
    Object lock = new Object();

    public Server(int port){
        try{
            serverSocket = new ServerSocket(port);

            while (true) {
                acceptSocket = serverSocket.accept();

                ConnectionHandler client = new ConnectionHandler(acceptSocket,this);

                synchronized (lock) {
                    clients.add(client);
                }

                new Thread(client).start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void distributeMessage(String message,ConnectionHandler sendingClient) {
        List<ConnectionHandler> clientsCopy;
        synchronized (lock) {
            clientsCopy = new ArrayList<>(clients);
        }
        for (ConnectionHandler client : clientsCopy) {
            if(!client.equals(sendingClient))
                client.sendMessage(message);
        }
    }
}