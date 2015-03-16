package com.xebia.icoundoul.mowitnow.model;

/**
 * An instruction for a mower.
 */
public enum Instruction {

	TurnLeft("G"),
	TurnRight("D"),
	MoveForward("A");

	private String instructionCode;

	private Instruction(String instructionCode) {
		this.instructionCode = instructionCode;
	}

	public String getInstructionCode() {
		return instructionCode;
	}

	public static Instruction getInstructionByCode(String code) {
		for (Instruction inst : Instruction.values()) {
			if (inst.getInstructionCode().equals(code)) {
				return inst;
			}
		}
		throw new IllegalArgumentException("the given code doesn't match any instruction:" + code);
	}
}
