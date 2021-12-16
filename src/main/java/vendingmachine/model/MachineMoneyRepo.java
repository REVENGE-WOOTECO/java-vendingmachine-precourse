package vendingmachine.model;

import java.util.HashMap;

public class MachineMoneyRepo {
	private MachineMoneyRepo() {
	}

	private static MachineMoneyRepo instance = new MachineMoneyRepo();

	public static MachineMoneyRepo getInstance() {
		return instance;
	}

	private int machineMoney;
	private HashMap<Integer, Integer> machineCoin;

	public int getMachineMoney() {
		return machineMoney;
	}

	public HashMap<Integer, Integer> getMachineCoin() {
		return machineCoin;
	}

	public void setMachineMoney(int machineMoney) {
		this.machineMoney = machineMoney;
	}

	public void setMachineCoin(HashMap<Integer, Integer> machineCoin) {
		this.machineCoin = machineCoin;
	}
}
