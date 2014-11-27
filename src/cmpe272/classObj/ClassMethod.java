package cmpe272.classObj;

public class ClassMethod extends CompositeBody{
	
	private String name;
	private String access_specifier;
	private String return_type="void";
	
	public ClassMethod(String description)
	{
		super(description);
	}
	
	
	public ClassMethod(String name, String access_specifier, String return_type)
	{
		this.name=name;
		this.access_specifier=access_specifier;
		if(return_type!=null && return_type!="")
		this.return_type=return_type;
		
		description=access_specifier + " " + return_type + " " + name + "()";
	}
	
	public String getDescription() {
		String output="";
		
		output=description+ "\n{\n";
		for(ClassComponents component: components)
			output+=component.getDescription();
		output+="\n}\n";
		return output;
	}

}
