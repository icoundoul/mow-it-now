package com.xebia.icoundoul.mowitnow.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import com.xebia.icoundoul.mowitnow.model.Field;
import com.xebia.icoundoul.mowitnow.model.Instruction;
import com.xebia.icoundoul.mowitnow.model.Mower;
import com.xebia.icoundoul.mowitnow.model.Orientation;
import com.xebia.icoundoul.mowitnow.model.Position;
import com.xebia.icoundoul.mowitnow.services.impl.InstructionService;

public class TestXebiaInstructions {

	InstructionService instructionService = new InstructionService();

	/**
	 * Test the first mower.
	 * 
	 * Instructions:
	 * 5 5
	 * 1 2 N
	 * GAGAGAGAA
	 * 
	 * Result: 1 3 N
	 */
	@Test
	public void testFirstMower() {

		Field field = new Field(6, 6); // +1 for width and height because instructions are zero-based
		Position initialPosition = new Position(1, 2, Orientation.North);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.TurnLeft);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);

		Position endingPosition = mower.getPosition();
		Assert.assertEquals(1, endingPosition.getX());
		Assert.assertEquals(3, endingPosition.getY());
		Assert.assertEquals(Orientation.North, endingPosition.getOrientation());
	}

	/**
	 * Test the second mower.
	 * 
	 * Instructions:
	 * 5 5
	 * 3 3 E
	 * AADAADADDA
	 * 
	 * Result: 5 1 E
	 */
	@Test
	public void testSecondMower() {

		Field field = new Field(6, 6); // +1 for width and height because instructions are zero-based
		Position initialPosition = new Position(3, 3, Orientation.East);
		Mower mower = new Mower(initialPosition);

		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);
		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		instructionService.applyInstruction(field, mower, Instruction.TurnRight);
		instructionService.applyInstruction(field, mower, Instruction.MoveForward);

		Position endingPosition = mower.getPosition();
		Assert.assertEquals(5, endingPosition.getX());
		Assert.assertEquals(1, endingPosition.getY());
		Assert.assertEquals(Orientation.East, endingPosition.getOrientation());
	}

	@Test
	public void testInstructions() {

		// set the line separator to avoid running into platform-specific issues
		System.setProperty("line.separator", "\n");

		// prepare the input
		String instructions = "5 5\n" + "1 2 N\n" + "GAGAGAGAA\n" + "3 3 E\n" + "AADAADADDA";
		InputStream in = new ByteArrayInputStream(instructions.getBytes());

		// prepare the output
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream printOut = new PrintStream(out);

		// apply the input into the output
		instructionService.procesInstructions(in, printOut);

		// check the output
		String actual = out.toString();
		Assert.assertEquals("1 3 N\n" + "5 1 E\n", actual);
	}

}
