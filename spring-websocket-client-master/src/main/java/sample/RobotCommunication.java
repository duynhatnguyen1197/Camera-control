/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.util.Scanner;

enum MoveDirection {
    forward,
    backward,
    left,
    right,
    forwardLeft,
    forwardRight,
    backwardLeft,
    backwardRight,
    cw,
    ccw,
    stop
}

public class RobotCommunication implements SerialPortEventListener {
    SerialPort serialPort;
    static public String RX;
    /** The port we're normally going to use. */
    private static final String PORT_NAMES[] = { 
        "/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/ttyACM0", // Raspberry Pi
        "/dev/ttyUSB0", // Linux
        "COM9", // Windows
    };
    /**
     * A BufferedReader which will be fed by a InputStreamReader 
     * converting the bytes into characters 
     * making the displayed results codepage independent
     */
    private BufferedReader input;
    /** The output stream to the port */
    public OutputStream output;
    /** Milliseconds to block while waiting for port open */
    private static final int TIME_OUT = 2000;
    /** Default bits per second for COM port. */
    private static final int DATA_RATE = 9600;

    public RobotCommunication() {}
    public void Init() {
        // the next line is for Raspberry Pi and 
        // gets us into the while loop and was suggested here was suggested https://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyUSB0");
        System.setProperty("gnu.io.rxtx.SerialPorts","COM9");

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            System.out.printf("Current port: %s", currPortId.getName());
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            return;
        }

        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();
            System.out.println("initzzzzzzzzzz");
            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * This should be called when you stop using the port.
     * This will prevent port locking on platforms like Linux.
     */
    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                String inputLine=input.readLine();
                System.out.printf("Receiv: %s%n", inputLine);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    public void run(MoveDirection move) {
        System.out.println("lenh quay trai ben code a Hung");
        try {
            switch (move)
            {
                case forward:
                    this.output.write(String.valueOf("k").getBytes());
                    break;

                case backward:
                    this.output.write(String.valueOf("j").getBytes());
                    break;

                case left:
                    this.output.write(String.valueOf("h").getBytes());
                    System.out.println("lenh quay trai ben code a Hung");
                    break;

                case right:
                    this.output.write(String.valueOf("l").getBytes());
                    break;

                case forwardLeft:
                    this.output.write(String.valueOf("u").getBytes());
                    break;
                
                case forwardRight:
                    this.output.write(String.valueOf("i").getBytes());
                    break;
                
                case backwardLeft:
                    this.output.write(String.valueOf("n").getBytes());
                    break;
                
                case backwardRight:
                    this.output.write(String.valueOf("m").getBytes());
                    break;
                
                case cw:
                    this.output.write(String.valueOf("c").getBytes());
                    break;
                
                case ccw:
                    this.output.write(String.valueOf("x").getBytes());
                    break;
                
                case stop:
                    this.output.write(String.valueOf("s").getBytes());
                    break;
                    
                    
                 
                default:
                    break;
            }
        } 
        catch (Exception e) {
        System.out.print("=============");
        System.out.print(e);
        System.out.print("=============");
        }
    }

   
}
