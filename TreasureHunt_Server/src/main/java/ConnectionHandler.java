import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable{
    private Socket clientSocket;
    private PrintStream output;
    private BufferedReader input;
    private Server server;

    public ConnectionHandler(Socket clientSocket,Server server) {
        this.clientSocket=clientSocket;
        this.server = server;
        System.out.println("Client has joined the socket (" + clientSocket.getPort() + ")");

    }
    @Override
    public void run() {
        try {
            output = new PrintStream(clientSocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //output.println("Successfully connected to the server");
            if(clientSocket.isConnected()){
                //server.distributeMessageClientInclusive("A client has joined the game",this);
            }
            while(clientSocket.isConnected()) {

                try {
                    String message = input.readLine();
                    if(message!=null) {
                        server.distributeMessage(message,this);
                    }
                }catch (IOException x){
                    if (clientSocket != null && !clientSocket.isClosed()) {
                        try {
                            clientSocket.close();
                            System.out.println("Client has left the socket (" + clientSocket.getPort() + ")");
                        } catch (IOException e) {e.printStackTrace(System.err);}
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String msg) {
        output.println(msg);
    }
}
