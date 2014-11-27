package cmpe272.classObj;

public abstract class BodyDecorator extends Body{
	
	
	
	public BodyDecorator(BodyDecorator tempBody, String description){
		super(tempBody,description);
	}
	
	public BodyDecorator(String description)
	{
		super(description);
	}
	
}
