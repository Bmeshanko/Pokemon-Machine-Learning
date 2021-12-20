import java.util.Random;
public class Node {
	public String name;
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
	public int[] chances;
	
	public Node(int maxStat, int hp, int attack, int defense, int speed) {
		int random = (int) (100 * Math.random());
		this.name = "Node " + random;
		
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
		this.accuracy = 0;
		this.alive = true;
		
		MoveSet ms = new MoveSet();
		this.moveSet = ms.generate();
		
		this.chances = new int[4];
		int r1 = 10 + (int) (30 * Math.random());
		int r2 = 10 + (int) (30 * Math.random());
		chances[0] = r1;
		chances[1] = 50 - r1;
		chances[2] = r2;
		chances[3] = 50 - r2;
	}
	
	public Move[] getMoves() {
		return this.moveSet;
	}
	
	public Node(Node node) {
		int random = (int) (100 * Math.random());
		this.name = "Node " + random;
		initStats(node.maxStat, node.initHp, 
				node.initAttack, node.initDefense, node.initSpeed);
		MoveSet ms = new MoveSet();
		this.moveSet = ms.generate(node.getMoves());
		this.chances = modifyChances(node.chances);
	}
	
	public int[] modifyChances(int[] chances) {
		Random random = new Random(System.nanoTime());
		double r1 = random.nextDouble();
		int[] modify = new int[4];
		for (int i = 0; i < 4; i++) {
			modify[i] = chances[i];
		}
		if (r1 < 0.50) {
			int r2 = (int) (modify.length * random.nextDouble());
			for (int i = 0; i < modify.length; i++) {
				if (i != r2 && modify[i] < 5) {
					return modify;
				}
			}
			modify[r2] += 3;
			for (int i = 0; i < modify.length; i++) {
				if (i != r2) {
					modify[i] -= 1;
				}
			}
		}
		return modify;
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
	
	public Move randomMove() {
		Random random = new Random(System.nanoTime());
		int r1 = (int) (100 * random.nextDouble());
		if (r1 < chances[0]) {
			return moveSet[0];
		}
		if (r1 < chances[0] + chances[1]) {
			return moveSet[1];
		}
		if (r1 < chances[0] + chances[1] + chances[2]) {
			return moveSet[2];
		}
		return moveSet[3];
	}
	
	public void attack(Node enemy) {
		double hitChance = Math.pow(0.7, accuracy + enemy.evasiveness);
		if (accuracy + enemy.evasiveness > 6) {
			hitChance = Math.pow(0.7, 6);
		}
		
		double hit = Math.random();
		Move move = randomMove();
		//System.out.println(name + " used " + move.name +" - " + hp + " - " + enemy.hp);
		if (hit < hitChance) {
			switch (move.action) {
			
			case 0:
				enemy.hp -= 1 + (0.75 * (double) move.damage * (double) attack / enemy.defense);
				break;
			case 1:
				if (this.attack / this.initAttack < 3) {
					this.attack *= move.attackIncrease;
				}
				break;
			case 2:
				if (this.defense / this.initDefense < 3) {
					this.defense *= move.defenseIncrease;
				}
				break;
			case 3:
				if (enemy.initAttack / enemy.attack < 3) {
					enemy.attack *= move.attackDecrease;
				}
				break;
			case 4:
				if (enemy.initDefense / enemy.defense < 3) {
					enemy.defense *= move.defenseDecrease;
				}
				break;
			case 5:
				this.evasiveness += move.evasivenessIncrease;
				break;
			case 6:
				enemy.accuracy += move.accuracyDecrease;
				break;
			}
			
		}
	}
	
	public void fight(Node enemy) {
		while (this.hp > 0 && enemy.hp > 0) {
			if (this.speed > enemy.speed) {
				attack(enemy);
				if (enemy.hp > 0) {
					enemy.attack(this);
				}
			} else {
				enemy.attack(this);
				if (this.hp > 0) {
					attack(enemy);
				}
			}
		}
		if (hp >= 0) {
			enemy.alive = false;
		} else {
			alive = false;
		}
	}
	
	public Node reproduce() {
		return new Node(this);
	}
	
	public String toString() {
		String s = name + " - HP: " + initHp + ", Attack: " + initAttack +
				", Defense: " + initDefense + ", Speed: " + initSpeed;
		if (alive) {
			s += ", Won Battle.\n";
		} else {
			s += ", Lost Battle.\n";
		}
		s += moveSet[0].name + " " + moveSet[1].name + " " + moveSet[2].name + " " + moveSet[3].name + "\n";
		s += chances[0] + " " + chances[1] + " " + chances[2] + " " + chances[3] + "\n";
		return s;
	}
}

