package trees;

public class Forest {
	private Tree[] trees;

	public Forest(Tree[] trees) {
		this.trees = trees;
	}

	public int totalCost(int years) {
		int sum = 0;
		for (Tree t : trees)
			sum += t.totalCost(years); //totalcost for each tree in X years to sum.
		return sum;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(Tree t : trees) {
			str.append(t.getName());
			str.append(" ");
		}
	return str.toString();
	}
}
