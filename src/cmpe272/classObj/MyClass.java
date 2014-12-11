package cmpe272.classObj;


public class MyClass extends CompositeBody{
	
	String name="Class1";
	String type="general";
	String ext;
	String[] impl;
	String acc_spec="public";
	
	public MyClass(String description)
	{
		super(description);
	}
	
	public MyClass(String name, String type, String ext, String[] impl, String acc_spec)
	{
		this.name = name;
		this.type=type;
		this.ext=ext;
		this.impl=impl;
		this.acc_spec=acc_spec;
		updateDescription();
	}
	
	
	public void updateDescription()
	{
		switch(type)
		{
		case "general": description=acc_spec+ " class " + name;
						if(ext!="")
							description+=" extends "+ext;
						if(impl.length!=0) {
							description+=" implements ";
							for(String str: impl)
								description+=str+" ,";
						}
						break;
		case "interface": description=acc_spec+ " interface " + name;
						break;
		
		case "abstract": description=acc_spec+ " abstract class " + name;
						if(ext!="")
							description+=" extends "+ext;
						if(impl.length!=0) {
							description+=" implements ";
							for(String str: impl)
								description+=str+" ,";
						}
						break;
		}
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
