package com.xebia.icoundoul.mowitnow.services;

import org.junit.Assert;
import org.junit.Test;

import com.xebia.icoundoul.mowitnow.model.Field;
import com.xebia.icoundoul.mowitnow.model.Instruction;
import com.xebia.icoundoul.mowitnow.model.Mower;
import com.xebia.icoundoul.mowitnow.model.Orientation;
import com.xebia.icoundoul.mowitnow.model.Position;
import com.xebia.icoundoul.mowitnow.services.impl.InstructionService;

public class TestInstructionService {

	private InstructionService instructionService = new InstructionService();

	/**
	 * Start with a field of 3x3 and a mower at the center, heading North.
	 * Turn left 4 times, checking it doesn't move but that its orientation changes appropriately.
	 */
	@Test
	public void testTurnLeft() {

		Field field = new Field(3, 3);
		Position initialPosition = new Position(1, 1, Orientation.North);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		assertPositionEquals(1, 1, Orientation.West, mower);

		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		assertPositionEquals(1, 1, Orientation.South, mower);

		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		assertPositionEquals(1, 1, Orientation.East, mower);

		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		assertPositionEquals(1, 1, Orientation.North, mower);
	}

	/**
	 * Start with a field of 3x3 and a mower at the center, heading North.
	 * Turn right 4 times, checking it doesn't move but that its orientation changes appropriately.
	 */
	@Test
	public void testTurnRight() {

		Field field = new Field(3, 3);
		Position initialPosition = new Position(1, 1, Orientation.North);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		assertPositionEquals(1, 1, Orientation.East, mower);

		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		assertPositionEquals(1, 1, Orientation.South, mower);

		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		assertPositionEquals(1, 1, Orientation.West, mower);

		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		assertPositionEquals(1, 1, Orientation.North, mower);
	}

	/**
	 * Start with a field of 3x3 and a mower at the center, heading North.
	 * Turn right 4 times, checking it doesn't move but that its orientation changes appropriately.
	 */
	@Test
	public void testMoveToNorth() {

		Field field = new Field(3, 3);
		Position initialPosition = new Position(1, 1, Orientation.North);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(1, 2, Orientation.North, mower);

		// hitting the border of the field: position and orientation shouldn't change
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(1, 2, Orientation.North, mower);
	}

	/**
	 * Start with a field of 3x3 and a mower at the center, heading North.
	 * Turn right 4 times, checking it doesn't move but that its orientation changes appropriately.
	 */
	@Test
	public void testMoveToSouth() {

		Field field = new Field(3, 3);
		Position initialPosition = new Position(1, 1, Orientation.South);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(1, 0, Orientation.South, mower);

		// hitting the border of the field: position and orientation shouldn't change
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(1, 0, Orientation.South, mower);
	}

	/**
	 * Start with a field of 3x3 and a mower at the center, heading North.
	 * Turn right 4 times, checking it doesn't move but that its orientation changes appropriately.
	 */
	@Test
	public void testMoveToWest() {

		Field field = new Field(3, 3);
		Position initialPosition = new Position(1, 1, Orientation.West);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(0, 1, Orientation.West, mower);

		// hitting the border of the field: position and orientation shouldn't change
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(0, 1, Orientation.West, mower);
	}

	/**
	 * Start with a field of 3x3 and a mower at the center, heading North.
	 * Turn right 4 times, checking it doesn't move but that its orientation changes appropriately.
	 */
	@Test
	public void testMoveToEast() {

		Field field = new Field(3, 3);
		Position initialPosition = new Position(1, 1, Orientation.East);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(2, 1, Orientation.East, mower);

		// hitting the border of the field: position and orientation shouldn't change
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		assertPositionEquals(2, 1, Orientation.East, mower);
	}

	private void assertPositionEquals(int x, int y, Orientation orientation, Mower mower) {

		Position endingPosition = mower.getPosition();
		Assert.assertEquals(x, endingPosition.getX());
		Assert.assertEquals(y, endingPosition.getY());
		Assert.assertEquals(orientation, endingPosition.getOrientation());
	}

}
