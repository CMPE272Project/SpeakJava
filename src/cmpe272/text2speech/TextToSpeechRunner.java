
//This class is responsible for converting text to speech
package cmpe272.text2speech;

import java.util.List;

public class TextToSpeechRunner  {
	
	private String voiceName ="alan";
	
	public void speak(String text){
		
		ITTSOnlineService ttsService = new TTSOnlineService();
		
		try{
			ttsService.playTextToSpeech(text,voiceName);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void speak(List<String> list){
		
		ITTSOnlineService ttsService = new TTSOnlineService();
		
		for(String str: list) {
			try{
				ttsService.playTextToSpeech(str,voiceName);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
