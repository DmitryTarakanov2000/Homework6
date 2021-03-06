import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket socket = null;
        BufferedReader reader;

        PrintWriter writer;
        try {
            server = new ServerSocket(8189);
            System.out.println("запуск сервера");

            socket = server.accept();
            System.out.println("Клиент подключился");

            Scanner sc2 = new Scanner(System.in);
            writer = new PrintWriter(socket.getOutputStream(), true);
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(in);

            new Thread(new Runnable() {
                @Override
                public void run(){

                    while (true) {

                        String str2 = sc2.nextLine();
                        writer.println(str2);
                    }
                }
            }).start();

            try {
                if (socket.isConnected()) {
                    while (true) {
                        String str = reader.readLine();
                        if (str.equalsIgnoreCase("/end")) break;
                        System.out.println("Client: " + str);
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Disconnect() {

    }


}