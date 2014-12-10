package cmpe272.editor;

import java.awt.EventQueue;

public class SpeechCoder {

	public static EditorFrame editorFrame;
	SpeechCoder sc;

	public static void main(String args[]) {
		SpeechCoder sc=new SpeechCoder();
		//runApp("Test");
	}
	
	public SpeechCoder() {
		initializeFrame();
		System.out.println("Speech coder has been initialised");
	}

	public void updateUI(String code) {
		System.out.println("I will update the jsyntax pane");
		this.editorFrame.updateUI1(code, -1);
	}

	/**
	 * Launch the application.
	 */
	public void initializeFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					editorFrame = new EditorFrame();
					EditorFrame.standardOut.println("Editor Frame was created");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
