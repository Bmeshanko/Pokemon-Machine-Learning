public class Group {
	public int population = 500;
	public Node[] nodes = new Node[population];
	public int maxStat;
	
	public Group(int maxStat) {
		this.maxStat = maxStat;
	}
	
	public int amountAlive() {
		int alive = 0;
		for (int i = 0; i < population; i++) {
			if (nodes[i].alive) alive++;
		}
		return alive;
	}
	
	public void genZero() {
		for (int i = 0; i < population; i++) {
			nodes[i] = new Node(maxStat, maxStat / 4, maxStat / 4, maxStat / 4, maxStat / 4);
		}
	}
	
	public void generation() {
		for (int i = 0; i < population / 2; i++) {
			nodes[i].fight(nodes[i + population / 2]);
		}
		
		for (int i = 0; i < population; i++) {
			System.out.println(nodes[i].toString());
			for (int j = 0; j < 4; j++) {
				//System.out.println(nodes[i].moveSet[j].name);
			}
		}

		Node[] nextGen = new Node[population];
		int j = 0;
		for (int i = 0; i < population; i++) {
			if (nodes[i].alive) {
				nextGen[j++] = nodes[i].reproduce();
				nextGen[j++] = nodes[i].reproduce();
			}
		}
		nodes = nextGen;
	}
	
	public static void main(String[] args) {
		Group group = new Group(1000);
		group.genZero();
		for (int i = 1; i <= 1000; i++) {
			System.out.println("Generation " + i + ": ");
			System.out.println("-----------------------------------");
			group.generation();
		}
	}
}
