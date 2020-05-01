import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ClientThread extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor 
    public ClientThread(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        String received;
        String toreturn;
        while (true) {
            try {

                // Ask user what he wants 
                dos.writeUTF("Type Stop to close the server..\n" +
                        "Type Exit to terminate connection.");

                // receive the answer from client 
                received = dis.readUTF();

                if (received.equals("Exit")) {
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    dos.writeUTF("Exitting");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }

                else if (received.equals("Stop")) {
                    System.out.println("Client " + this.s + " sends stop...");
                    System.out.println("Closing the server.");
                    dos.writeUTF("Server stopped");
                    try {
                        // closing resources
                        this.dis.close();
                        this.dos.close();
                        this.s.close();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else dos.writeUTF("Server received the request ... ");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
} 