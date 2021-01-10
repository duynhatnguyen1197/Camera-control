/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.concurrent.atomic.AtomicBoolean;
import static sample.Application.RX;

/**
 *
 * @author nguyenduy
 */
public class ControlModule implements Runnable {
   private Thread t;
   private String threadName;
   private final AtomicBoolean running = new AtomicBoolean(false);
   private Application application;
   private RobotCommunication carSerial;
   private SerialTest cameraSerial;
   private ExecuteBashCommand bashCommand;
   private static String RX;
   ControlModule( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
      application = new Application();
      carSerial = new RobotCommunication();
      cameraSerial = new SerialTest();
      bashCommand = new ExecuteBashCommand();
      RX = "";
   }
   
   public void run() {
      running.set(true);
      RX = application.RX;
      while(RX!=null){
      switch (RX) {
	                        	case "left":
	                        		cameraSerial.run(CameraDirection.left);
	                        		break;
	                        	case "right":
	                        		cameraSerial.run(CameraDirection.right);
                                                System.out.println("Camera move right");
	                        		break;
	                        	case "forward":
	                        		cameraSerial.run(CameraDirection.forward);
	                        		break;
	                        	case "backward":
	                        		cameraSerial.run(CameraDirection.backward);
	                        		break;
	                        	case "stop":
	                        		cameraSerial.run(CameraDirection.stop);
	                        		break;
		                        case "Xtrai":
		                                carSerial.run(CarDirection.left);
		                                break;
		                        case "Xphai":
		                                carSerial.run(CarDirection.right);
                                               System.out.println("Car move right");
		                                break;
		                        case "Xtoi":
		                                carSerial.run(CarDirection.forward);
		                                break;
		                        case "Xlui":
		                                carSerial.run(CarDirection.backward);
		                                break;
		                        case "XStop":
		                                carSerial.run(CarDirection.stop);
		                                break;
                                        case "Ccapture":
                                             bashCommand.executeCommand("raspistill -vf -hf -o /home/pi/Documents/1.jpg");
                                             System.out.println("Image Captured");
                                             break;
                                        default:
                                            break;
		                            }}
		                       
   }
   public void start () {
      System.out.println("Starting " +  threadName );
      if (t == null) {
         t = new Thread (this, threadName);
         t.start ();
      }
   }
    public void stop() {
        running.set(false);
    }
}
