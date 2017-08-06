package com.bartlettpear18gmail.mouse;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client extends AsyncTask<Void, Void, Void> {

    //Debug tag
    private static String tag = "Debug";

    //Server variables
    private static Socket socket;
    private static int port = 5000;
    private String ip = "10.0.0.162"; //Hotspot IP: 192.168.43.81, Barty IP: 10.0.0.162

    //Stream variables
    private InputStream inputStream = null;
    private OutputStream outputStream = null;

    //State enums
    public static LEFT left = LEFT.OFF;
    public static RIGHT right = RIGHT.OFF;
    public static MIDDLE middle = MIDDLE.OFF;

    //Accerelometer setup
    private Mouse mouse = new Mouse();

    //Regex Base String
    private final String IP_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    //No parameter constructor for threads
    public Client() {}

    //IP Address Methods
    private boolean checkAddress(String text) {
        Pattern p = Pattern.compile(IP_PATTERN);
        Matcher m = p.matcher(text);
        return m.find();
    }
    public boolean setAddress(String newIp) {
        boolean change = false;
        if(checkAddress(newIp)) {
            ip = newIp;
            change = true;
            Log.d(tag, "IP Confirmed and set");
        } else {
            Log.d(tag, "IP Submission rejected");
            change = false;
        }
        return change;
    }
    public String getAdddress() { return ip; }

    //Socket methods
    private void setup() throws IOException {
        socket = new Socket(ip, port);
        Log.d(tag, "Socket created");
    }
    private void streams() throws IOException {
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
        Log.d(tag, "Streams created");

    }
    private void close() throws IOException {
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        Log.d(tag, "Socket and Streams closed");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            setup();
            streams();
            Log.d(tag,"Running here");
            while(true) {
                sendTest();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void streamPacket() throws IOException {
        byte[] packet = new byte[3];
        packet[0] = (byte) (left.getState() | right.getState() | middle.getState());
        packet[1] = (byte) ((0xFF) & mouse.getMovementX());
        packet[2] = (byte) ((0xFF) & mouse.getMovementY());

        for (byte data : packet) {
            Log.d(tag, String.valueOf(data));
        }

        outputStream.write(packet);
        Log.d(tag, "State packet sent");

    }

    public void sendTest() throws ClassNotFoundException, IOException, InterruptedException {
        Log.d(tag,"Sending message");
        byte[] msg = new byte[1];
        msg[0] = (byte) (left.getState() | right.getState() | middle.getState());
        Log.d(tag, String.valueOf(msg[0]));
        outputStream.write(msg);
        Log.d(tag, "Sent msg");
    }


    public enum LEFT {
        ON((byte) 0x01),
        OFF((byte) 0x00);

        private byte state;
        LEFT(byte state) {
            this.state = state;
        }
        public byte getState() { return state; }
    }

    public enum RIGHT {
        ON((byte) 0x02),
        OFF((byte) 0x00);

        private byte state;
        RIGHT(byte state) { this.state = state; }
        public byte getState() { return state; }
    }

    public enum MIDDLE {
        ON((byte) 0x04),
        OFF((byte) 0x00);

        private byte state;
        MIDDLE(byte state) { this.state = state; }
        public byte getState() { return state; }
    }

}

