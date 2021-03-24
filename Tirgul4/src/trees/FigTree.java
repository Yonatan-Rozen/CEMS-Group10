package trees;

public class FigTree extends Tree{
	
	public FigTree() {
		super(100);
	}
	
	@Override
	public String getName() {
		return "Fig";
	}
	
	@Override
	protected int costAtYear(int year) {
		return 10;
	}
}
