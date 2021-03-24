package trees;

public abstract class Tree {
	protected int bic;

	public Tree(int buyingCost) { //constractor
		bic = buyingCost;
	}

	public abstract String getName(); //abstract 

	protected abstract int costAtYear(int year); //abstract

	public int totalCost(int years) {
		int sum = this.bic;
		for (int i = 0; i < years; i++) {
			sum += costAtYear(i);
		}
		return sum;
	}
}
