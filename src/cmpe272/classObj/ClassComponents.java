package cmpe272.classObj;

public interface ClassComponents {
	
	 String getDescription() ;
     void addChild(ClassComponents c);
     void removeChild(ClassComponents c);
     ClassComponents getChild(int i);
}
