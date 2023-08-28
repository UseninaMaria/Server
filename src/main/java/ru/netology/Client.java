package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;



public class Client {
    private static BufferedReader reader;

    public static void main(String[] args) throws IOException, InterruptedException {

        int port = 8080;
        String host = "netology.homework";
        InetAddress inetAddress = InetAddress.getByName(host);
        System.out.println(host + ", ip address: " + inetAddress.getHostAddress());

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            reader = new BufferedReader(new InputStreamReader(System.in));

            String questionName = in.readLine();
            System.out.println("Server questions: " + questionName);

            String answerClient = reader.readLine();
            out.println(answerClient);
            Thread.sleep(1000);

            String question = in.readLine();
            System.out.println("Server questions: " + question);

            String yesOrNo = reader.readLine();
            out.println(yesOrNo);
            Thread.sleep(1000);


            String resp = in.readLine();
            System.out.println("Ответ от сервера: " + resp);

            String myPort = in.readLine();
            System.out.println(myPort);
            out.flush();
        }
    }
}

