// ***************************************************************************
// Copyright (c) 2020, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.tailqueue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class TailTest {
	private static Tail tailOneUrgentNow;
	private static Tail secondTailOneUrgentNow;
	private static Tail tailTwoUrgentNow;
	private static Tail tailOneImportantNow;
	private static Tail tailTwoImportantNow;;
	private static Tail tailTwoUrgentLater;
	private static Tail tailTwoRequestingNow;
	private static Tail tailTwoDismissedNow;

	@BeforeClass
	public static void setup() {
		// Initialise variables
		Calendar now = Calendar.getInstance();
		Calendar later = Calendar.getInstance();
		later.add(Calendar.SECOND, 1);
		tailOneUrgentNow = new Tail(1, Tail.URGENT, now);
		secondTailOneUrgentNow = new Tail(1, Tail.URGENT, now);
		tailTwoUrgentNow = new Tail(2, Tail.URGENT, now);
		tailOneImportantNow = new Tail(1, Tail.IMPORTANT, now);
		tailTwoImportantNow = new Tail(2, Tail.IMPORTANT, now);
		tailTwoUrgentLater = new Tail(2, Tail.URGENT, later);
		tailTwoRequestingNow = new Tail(2, Tail.REQUESTING, now);
		tailTwoDismissedNow = new Tail(2, Tail.DISMISSED, now);
	}

	private static void equals(Tail firstTail, Tail secondTail) {
		assertEquals(0, firstTail.compareTo(secondTail));
		assertEquals(0, secondTail.compareTo(firstTail));
	}

	private static void firstLessThanSecond(Tail first, Tail second) {
		assertEquals(-1, first.compareTo(second));
		assertEquals(1, second.compareTo(first));
	}

	@Test
	public void equalsSelf() {
		assertEquals(0, tailOneUrgentNow.compareTo(tailOneUrgentNow));
	}

	@Test
	public void sameIDSamePriority() {
		equals(tailOneUrgentNow, secondTailOneUrgentNow);
	}

	@Test
	public void sameIDDifferentPriority() {
		equals(tailOneUrgentNow, tailOneImportantNow);
	}

	@Test
	public void differentIDDifferentPriority() {
		firstLessThanSecond(tailOneUrgentNow, tailTwoImportantNow);
		firstLessThanSecond(tailOneUrgentNow, tailTwoRequestingNow);
		firstLessThanSecond(tailOneUrgentNow, tailTwoDismissedNow);
	}

	@Test
	public void differentIDSamePriorityDifferentCalendar() {
		firstLessThanSecond(tailOneUrgentNow, tailTwoUrgentLater);
	}

	@Test
	public void differentIDSamePrioritySameCalendar() {
		firstLessThanSecond(tailOneUrgentNow, tailTwoUrgentNow);
	}
}

