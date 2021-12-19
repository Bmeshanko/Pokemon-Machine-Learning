public class Node {
	public int hp;
	public int attack;
	public int defense;
	public int speed;
	public int initHp;
	public boolean alive;
	public int maxStat;
	
	
	public Node(int maxStat, int hp, int attack, int defense, int speed) {
		int random = (int) ((Math.random() * 10) - 5);
		int randHp = hp + random;
		random = (int) ((Math.random() * 10) - 5);
		int randAttack = attack + random;
		random = (int) ((Math.random() * 10) - 5);
		int randDefense = defense + random;
		random = (int) ((Math.random() * 10) - 5);
		int randSpeed = speed + random;
		
		double ratio = ((double) (maxStat - 10) / (randHp + randAttack + randDefense + randSpeed));
		this.maxStat = maxStat;
		this.initHp = (int) ((randHp * ratio) + 2.5);
		this.hp = initHp;
		this.attack = (int) ((randAttack * ratio) + 2.5);
		this.defense = (int) ((randDefense * ratio) + 2.5);
		this.speed = (int) ((randSpeed * ratio) + 2.5);
		this.alive = true;
	}
	
	public int damage(Node enemy) {
		double damage = 20 * (double) attack / enemy.defense;
		return (int) (damage);
	}
	
	public void fight(Node enemy) {
		while (this.hp > 0 && enemy.hp > 0) {
			if (this.speed > enemy.speed) {
				enemy.hp -= damage(enemy);
				if (enemy.hp > 0) {
					this.hp -= enemy.damage(this);
				}
			} else {
				this.hp -= enemy.damage(this);
				if (this.hp > 0) {
					enemy.hp -= damage(enemy);
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
		return new Node(maxStat, initHp, attack, defense, speed);
	}
	
	public String toString() {
		String s = "HP: " + initHp + ", Attack: " + attack +
				", Defense: " + defense + ", Speed: " + speed;
		return s;
	}
}

