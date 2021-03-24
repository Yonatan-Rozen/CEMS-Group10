package trees;

public class maintirgul4 {

	public static void main(String[] args) {
		Tree[] a = new Tree[3];
		a[0] = new FigTree();
		a[1] = new OrangeTree();
		a[2] = new NamedFigTree("Eliran");
		Forest f = new Forest(a);
		System.out.println(f);
		for (int i = 0; i < 10; i++)
		System.out.print(f.totalCost(i) + " ");


	}

}
