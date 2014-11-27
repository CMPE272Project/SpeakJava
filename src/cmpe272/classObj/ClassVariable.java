package cmpe272.classObj;

public class ClassVariable implements ClassComponents{

	private String name;
	private String access_specifier="public";
	private String type;
	private String initial_value;
	
	@Override
	public String getDescription() {
		String output="";
		output=output+access_specifier + " " + type + " " + name;
		if(initial_value!=null)
			output=output+" = "+initial_value; 
		output+=";";
		
		return output;
	}

	@Override
	public void addChild(ClassComponents c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeChild(ClassComponents c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClassComponents getChild(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
