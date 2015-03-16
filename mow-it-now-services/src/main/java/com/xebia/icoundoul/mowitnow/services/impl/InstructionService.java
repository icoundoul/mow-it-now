package com.xebia.icoundoul.mowitnow.services.impl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.xebia.icoundoul.mowitnow.model.Field;
import com.xebia.icoundoul.mowitnow.model.Instruction;
import com.xebia.icoundoul.mowitnow.model.Mower;
import com.xebia.icoundoul.mowitnow.model.Orientation;
import com.xebia.icoundoul.mowitnow.model.Position;
import com.xebia.icoundoul.mowitnow.services.IInstructionService;

/**
 * Read the instructions from file and process them
 * @author icoundoul
 *
 */
@Service
public class InstructionService implements IInstructionService {

	@Override
	public void procesInstructions(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);

		// read the definition of the field
		Field field = createField(scanner);

		while (true) {

			// read the mower and the instructions (and apply them)
			Mower mower = createMower(scanner);
			applyInstructions(scanner, field, mower);

			// print out the result
			print(mower, out);

			// stop when there's no more input
			if (scanner.hasNext() == false)
				break;
		}

		scanner.close();
	}

	private Field createField(Scanner scanner) {

		// notice that we add 1 because the inputs are 0-based
		int width = scanner.nextInt() + 1;
		int heigth = scanner.nextInt() + 1;

		Field field = new Field(width, heigth);
		return field;
	}

	private Mower createMower(Scanner scanner) {

		int x = scanner.nextInt();
		int y = scanner.nextInt();
		String cardinal = scanner.next("[NSEW]");

		Orientation orientation = Orientation.getOrientationByCode(cardinal);
		Position position = new Position(x, y, orientation);

		Mower mower = new Mower(position);
		return mower;
	}

	private void applyInstructions(Scanner scanner, Field field, Mower mower) {

		String instructions = scanner.next("[DGA]+");
		for (char c : instructions.toCharArray()) {
			Instruction instruction = Instruction.getInstructionByCode(String.valueOf(c));
			applyInstruction(field, mower, instruction);
		}
	}

	private void print(Mower mower, PrintStream out) {
		Position position = mower.getPosition();
		int x = position.getX();
		int y = position.getY();
		Orientation orientation = position.getOrientation();
		char c = orientation.getOrientationCode().charAt(0);
		out.println(x + " " + y + " " + c);
	}

	public void applyInstruction(Field field, Mower mower, Instruction instruction) {

		// apply the instruction to compute the next position (may be an invalid position)
		Position nextPosition = computeNextPosition(mower.getPosition(), instruction);

		// check if the position is valid (within the field)
		boolean isValid = isWithinField(nextPosition, field);

		// only set the new position if it is valid
		if (isValid)
			mower.setPosition(nextPosition);
	}

	/**
	 * Checks whether the position is within the boundaries of the field.
	 */
	private boolean isWithinField(Position position, Field field) {

		int x = position.getX();
		if (x < 0 || x >= field.getWidth())
			return false;

		int y = position.getY();
		if (y < 0 || y >= field.getHeight())
			return false;

		return true;
	}

	/**
	 * Move the mover and return the position
	 * @param position
	 * @param instruction
	 * @return the position
	 */
	private Position computeNextPosition(Position position, Instruction instruction) {

		Position res;
		switch (instruction) {
			case MoveForward:
				res = moveForward(position);
				break;
			case TurnLeft:
				res = turnLeft(position);
				break;
			case TurnRight:
				res = turnRight(position);
				break;
			default:
				throw new RuntimeException("Instruction not supported: " + instruction);
		}
		return res;
	}

	/**
	 * Move forward and return the position
	 * @param position
	 * @return Position: the position
	 */

	private Position moveForward(Position position) {

		Orientation orientation = position.getOrientation();
		int x = position.getX();
		int y = position.getY();

		if (orientation == Orientation.North)
			y++;
		else if (orientation == Orientation.South)
			y--;
		else if (orientation == Orientation.East)
			x++;
		else if (orientation == Orientation.West)
			x--;

		Position res = new Position(x, y, orientation);
		return res;
	}

	/**
	 * Returns a new position after turning left.
	 */

	/**
	 * Turning left and return the position 
	 * @param position
	 * @return Position, the position
	 */
	private Position turnLeft(Position position) {

		Orientation orientation = position.getOrientation();
		int x = position.getX();
		int y = position.getY();

		switch (orientation) {
			case North:
				orientation = Orientation.West;
				break;
			case South:
				orientation = Orientation.East;
				break;
			case East:
				orientation = Orientation.North;
				break;
			case West:
				orientation = Orientation.South;
				break;
		}
		Position res = new Position(x, y, orientation);
		return res;
	}

	/**
	 *  Turning right and return the position
	 * @param position
	 * @return Position, the position
	 */
	private Position turnRight(Position position) {

		Orientation orientation = position.getOrientation();
		int x = position.getX();
		int y = position.getY();

		switch (orientation) {
			case North:
				orientation = Orientation.East;
				break;
			case South:
				orientation = Orientation.West;
				break;
			case East:
				orientation = Orientation.South;
				break;
			case West:
				orientation = Orientation.North;
				break;
		}
		Position res = new Position(x, y, orientation);
		return res;

	}

}
