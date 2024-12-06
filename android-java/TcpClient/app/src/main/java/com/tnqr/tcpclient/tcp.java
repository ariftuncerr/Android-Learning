package com.tnqr.tcpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class tcp {
    private static final String SERVER_IP = "192.168.1.100"; // Sunucunun IP adresi (örneğin aynı ağdaysa)
    private static final int SERVER_PORT = 8080; // Sunucunun portu

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            // Sunucuya bağlan
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Bağlantı başarılı!");

            // Sunucuya veri gönder
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Örnek: VIEW komutunu gönder
            out.println("VIEW");
            out.flush();

            // Sunucudan yanıt oku
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Sunucudan gelen yanıt: " + response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
