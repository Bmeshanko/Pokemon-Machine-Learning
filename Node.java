public class Node {
	public int initHp;
	public int hp;
	public int initAttack;
	public int attack;
	public int initDefense;
	public int defense;
	public int initSpeed;
	public int speed;
	public int evasiveness;
	public int accuracy;
	public boolean alive;
	public int maxStat;
	public Move[] moveSet;
	
	public Node(int maxStat, int hp, int attack, int defense, int speed) {
		this.maxStat = maxStat;
		this.initHp = hp;
		this.hp = this.initHp;
		this.initAttack = attack;
		this.attack = this.initAttack;
		this.initDefense = defense;
		this.defense = this.initDefense;
		this.initSpeed = speed;
		this.speed = this.initSpeed;
		this.evasiveness = 0;
		this.accuracy = 100;
		this.alive = true;
		
		MoveSet ms = new MoveSet();
		this.moveSet = ms.generate();
	}
	
	public Node(Node node) {
		initStats(node.maxStat, node.initHp, 
				node.initAttack, node.initDefense, node.initSpeed);
		MoveSet ms = new MoveSet();
		this.moveSet = ms.generate(node.moveSet);
	}
	
	public void initStats(int maxStat, int hp, int attack, int defense, int speed) {
		int random = (int) ((Math.random() * 10) - 5);
		int randHp = hp + random;
		random = (int) ((Math.random() * 10) - 5);
		int randAttack = attack + random;
		random = (int) ((Math.random() * 10) - 5);
		int randDefense = defense + random;
		random = (int) ((Math.random() * 10) - 5);
		int randSpeed = speed + random;
		
		double ratio = ((double) (maxStat - 8) / (randHp + randAttack + randDefense + randSpeed));
		this.maxStat = maxStat;
		this.initHp = (int) ((randHp * ratio) + 2);
		this.hp = this.initHp;
		this.initAttack = (int) ((randAttack * ratio) + 2);
		this.attack = this.initAttack;
		this.initDefense = (int) ((randDefense * ratio) + 2);
		this.defense = this.initDefense;
		this.initSpeed = (int) ((randSpeed * ratio) + 2);
		this.speed = this.initSpeed;
		this.alive = true;
	}
	
	
	public int damage(Node enemy, Move move) {
		
		double damage = 80 * (double) attack / enemy.defense;
		return (int) (damage);
	}
	
	public void fight(Node enemy) {
		
	}
	
	
	
	public Node reproduce() {
		return new Node(this);
	}
	
	public String toString() {
		String s = "HP: " + initHp + ", Attack: " + attack +
				", Defense: " + defense + ", Speed: " + speed;
		if (alive) {
			s += ", Won Battle.";
		} else {
			s += ", Lost Battle.";
		}
		return s;
	}
}

