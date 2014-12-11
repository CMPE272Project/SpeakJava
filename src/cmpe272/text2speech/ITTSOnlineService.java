package cmpe272.text2speech;

/********************
 * @author Tithi Paul
 *
 ********************/

/**********************************************************************************************
 * ITTSSerivce interface provides method to convert text to speech
 * It takes input string and voice name as inputs and plays back the input string in the voice
 * set by voiceName.
 **********************************************************************************************/
public interface ITTSOnlineService {	
	void playTextToSpeech(String inputText,String voiceName);
}