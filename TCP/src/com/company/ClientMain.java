package com.company;

import java.io.IOException;

/**
 * Created by Joel.Bartlett18 on 5/25/2017.
 */
public class ClientMain {
    public static void main (String[] args) throws IOException {
        Client cli = new Client();
        cli.cilentRun("127.0.0.1",3000);
    }
}
