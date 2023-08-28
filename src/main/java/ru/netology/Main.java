package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    System.out.println("Новое соединение принято");

                    out.println("Write your name");
                    Thread.sleep(1000);
                    String name = in.readLine();
                    System.out.println("Name Clients: " + name);


                    out.println("Are you a child? (yes/no)");
                    Thread.sleep(1000);
                    String answer = in.readLine();
                    System.out.println("Clients answer: " + answer);

                    if (answer.equalsIgnoreCase("yes")) {
                        out.println("Welcome to the kids area, " + name + "! Let's play!");
                    } else {
                        out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                    }

                    System.out.println("Пришло с порта: " + clientSocket.getPort());
                    out.println(String.format("Your port is %d", clientSocket.getPort()));
                    out.flush();
                }
            }
        }
    }
}