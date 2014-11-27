package cmpe272.classObj;


public class MyClass extends CompositeBody{

	public MyClass(String description)
	{
		super(description);
	}
	
	public String getDescription() {
		String output="";
		
		output=description + "\n{\n";
		for(ClassComponents component: components)
			output+=component.getDescription();
		output+="\n}";
		return output;
	}


}
