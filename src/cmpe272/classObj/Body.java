package cmpe272.classObj;

public class Body implements ClassComponents{
	
	protected String description;
	protected BodyDecorator tempBody;
	
	public Body(BodyDecorator tempBody, String description){
		this.tempBody=tempBody;
		this.description=description;
		
	}
	
	public Body(String description)
	{
		this.description=description;
	}
	@Override
	public String getDescription() {
		
		return description;
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
