package cmpe272.text2speech;

import java.io.InputStream;

import com.gtranslate.Audio;
import com.gtranslate.Language;



/***********************************************
 * @author Tithi Paul
 ***********************************************/

/******************************************************************
 * This class implements the Text to Speech Interface (IITSservice). 
 * Uses the freetts library
 ******************************************************************/

public class TTSOnlineService implements ITTSOnlineService {

	@Override
	public void playTextToSpeech(String inputText,String voiceName) {
		
		Audio audio = Audio.getInstance();
		try{
			InputStream sound = audio.getAudio(inputText, Language.ENGLISH);
			audio.play(sound);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
