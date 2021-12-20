import java.util.Random;
public class Type {
	public String name;
	public int num; // Makes comparing type-effectiveness easier.
	
	public Type(Type type) {
		Random random = new Random(System.nanoTime());
		double r1 = random.nextDouble();
		if (r1 < 0.05) {
			Type t = new Type();
			this.name = t.name;
			this.num = t.num;
		} else {
			this.name = type.name;
			this.num = type.num;
		}
	}
	
	public Type() {
		Random random = new Random(System.nanoTime());
		int r1 = (int) (10 * random.nextDouble());
		
		this.num = r1;
		assignName();
	}
	
	public Type(int num) {
		this.num = num;
		assignName();
	}
	
	public void assignName() {
		switch (this.num) {
		case 0:
			this.name = "Normal";
			break;
		case 1:
			this.name = "Fire";
			break;
		case 2:
			this.name = "Grass";
			break;
		case 3:
			this.name = "Water";
			break;
		case 4:
			this.name = "Fighting";
			break;
		case 5:
			this.name = "Flying";
			break;
		case 6:
			this.name = "Rock";
			break;
		case 7:
			this.name = "Poison";
			break;
		case 8:
			this.name = "Electric";
			break;
		case 9:
			this.name = "Bug";
			break;
		}
	}
	
	public double effectiveness(Type move, Type enemy) {
		int a = move.num;
		int b = enemy.num;
		if (a == 0 && b == 6) {
			return 0.5;
		}
		return 1;
	}
	
	public double stab(Type move) {
		// STAB - Same Type Attack Bonus. Boosts attack strength by 50%.
		if (move.equals(this)) {
			return 1.5;
		}
		return 1;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Type) {
			Type t = (Type) o;
			return t.name.equals(name);
		}
		return false;
	}
	
	
}
