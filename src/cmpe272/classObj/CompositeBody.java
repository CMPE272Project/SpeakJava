package cmpe272.classObj;

import java.util.ArrayList;
import java.util.List;

public class CompositeBody implements ClassComponents{
	
	protected List<ClassComponents> components= new ArrayList<ClassComponents>();
	protected String description;
	
	public CompositeBody()
	{
		
	}
	public CompositeBody(String description)
	{
		this.description=description;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChild(ClassComponents c) {
		components.add(c);
		
	}

	@Override
	public void removeChild(ClassComponents c) {
		components.remove(c);
		
	}

	@Override
	public ClassComponents getChild(int i) {
		return(components.get(i));
	}
	
}
