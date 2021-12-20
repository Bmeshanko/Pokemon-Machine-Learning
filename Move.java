public class Move {
	public String name;
	public int action;
	public int damage;
	public int attackIncrease;
	public int defenseIncrease;
	public int attackDecrease;
	public int defenseDecrease;
	public int evasivenessIncrease;
	public int accuracyDecrease;
	
	public Move(String name, int action, int amount) {
		this.action = action;
		switch(action) {
		
		case 0:
			this.damage = amount;
			break;
		case 1:
			this.attackIncrease = amount;
			break;
		case 2:
			this.defenseIncrease = amount;
			break;
		case 3:
			this.attackDecrease = amount;
			break;
		case 4:
			this.defenseDecrease = amount;
			break;
		case 5:
			this.evasivenessIncrease = amount;
			break;
		case 6:
			this.accuracyDecrease = amount;
			break;
		}
		
	}
}
