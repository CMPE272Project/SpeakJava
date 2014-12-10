package cmpe272.app;
import cmpe272.speech2text.microphone.*;
import cmpe272.speech2text.recognizer.*;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.applet.*;
import java.net.*;

public class Speech {
	
	private Microphone mic;
	private RecognizerChunked recognize;
	private List<String> lst;
	private FlacEncoder flacEncoder;
	private AudioClip clip;
	private int count=0;
	
	public Speech()
	{	
		mic = new Microphone(AudioFileFormat.Type.WAVE);
		recognize= new RecognizerChunked("AIzaSyBTwtRpwRDtMTBtdzjR3HHsf6DwUTNwHQA");
		lst= new ArrayList<String>();
		flacEncoder=new FlacEncoder();
		try {
			clip = Applet.newAudioClip(new URL(new URL("file:"), "sounds/beep.wav"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void recordInput(int microsec)
	{
		try{
			System.out.println("Please speak after the beep:");
			Thread.sleep(500);
			clip.play();
			Thread.sleep(250);
			mic.open();
			mic.captureAudioToFile("temp/temp.wav");
			Thread.sleep(microsec);
			mic.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void convertToFlac()
	{	
		count++;
		flacEncoder.convertWaveToFlac("temp/temp.wav", "temp/temp" + count + ".flac");
	}
	 
	public String getUserInput(int microsec)
	{
	 	String out="";
	 	
	 	recordInput(microsec);
	 	convertToFlac();
	 	
	        try {
	        	lst=recognize.getRecognizedDataForFlac("temp/temp" + count + ".flac", 8000);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	        
	        
	        if(lst!=null)
	        {
	        	out=lst.get(0);
	        	//uncomment for pause  feature
	        	//if(out.equals("pause"))
	        		//{ out=pauseExec();}
	        }
	        		
	     
	        System.out.println("you said :"+ out);

	 	
	 	return(out);
    }
	
	public String pauseExec()
	{
		String out="";
		getResume();
		out=getUserInput(2000);
		return out;
	}
	
	public void getResume()
	{
		String out="";
		
		while(!out.equals("resume")) {
		try{
			//System.out.println("Please speak after the beep:");
			//Thread.sleep(500);
			//clip.play();
			//Thread.sleep(250);
			mic.open();
			mic.captureAudioToFile("temp/temp.wav");
			Thread.sleep(3000);
			mic.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		convertToFlac();
		
	 	
	 	//recordInput(microsec);
	 	//convertToFlac();
	 	
	        try {
	        	lst=recognize.getRecognizedDataForFlac("temp/temp" + count + ".flac", 8000);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	        
	        
	        if(lst!=null)
	        {
	        	out=lst.get(0);
	        }
	        		
	     
	        //System.out.println("you said :"+ out);
	        System.out.println("inside pause...");
		}
	 	
    }
}
