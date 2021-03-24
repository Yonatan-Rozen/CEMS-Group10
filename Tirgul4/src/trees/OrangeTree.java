package trees;

public class OrangeTree extends Tree{

	public OrangeTree() {
		super(150);
	}

	@Override
	public String getName() {
		return "Orange";
	}

	@Override
	protected int costAtYear(int year) {
		if(year<3) return 100;
		else if(year>6) return -100; // (250*0.6)-250
		
		return 0;
	}
}
