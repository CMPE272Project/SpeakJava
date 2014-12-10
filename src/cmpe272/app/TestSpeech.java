package cmpe272.app;

public class TestSpeech {
	
	public static void main(String args[])
    {	
		Speech spc = new Speech();
		String str;
		for(int i=0;i<3;i++){
		str=spc.getUserInput(2000);
		System.out.println(str);
		}
    }
}
