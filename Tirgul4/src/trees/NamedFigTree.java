package trees;

public class NamedFigTree extends FigTree {
	
	private String name;
	public NamedFigTree(String name) {
		super();
		this.name=name;
	}
	
	@Override
	public String getName() {
		return name + " " +"Fig";
	}
}
