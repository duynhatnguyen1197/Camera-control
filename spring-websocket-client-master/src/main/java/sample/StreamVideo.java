/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author nguyenduy
 */
public class StreamVideo implements Runnable {
   private Thread t;
   private String threadName;
   private final AtomicBoolean running = new AtomicBoolean(false);
   private ExecuteBashCommand bashCommand;
   StreamVideo( String name) {
      threadName = name;
      System.out.println("Creating " +  threadName );
      bashCommand = new ExecuteBashCommand();
   }
   
   public void run() {
      running.set(true);
      System.out.println("Print start video-stream command");
      bashCommand.executeCommand("ffmpeg -f v4l2 -framerate 60 -s 640x480 -t 00:10:00 -i /dev/video0 -f mpegts -codec:v mpeg1video "
                                        + "-s 640x480 -b:v 1000k -bf 0 http:35.240.243.145:8082/bigbangboom");
        
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
         System.out.println("Print stop video-stream command");
    }
}


