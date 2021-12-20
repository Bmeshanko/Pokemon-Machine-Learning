public class Move {
	public String name;
	public int action;
	public int damage;
	public double attackIncrease;
	public double defenseIncrease;
	public double attackDecrease;
	public double defenseDecrease;
	public double evasivenessIncrease;
	public double accuracyDecrease;
	
	public Move(String name, int action, double amount) {
		this.name = name;
		this.action = action;
		switch(action) {
		
		case 0:
			this.damage = (int) amount;
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
