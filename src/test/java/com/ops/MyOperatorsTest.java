package com.ops;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.lang.annotation.Repeatable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

import jdk.jfr.Enabled;


@TestInstance(TestInstance.Lifecycle.PER_CLASS) //this will create an instance per method
//@EnabledOnOs(OS.WINDOWS)
//@EnabledOnJre(JRE.JAVA_11)
class MyOperatorsTest {
	TestInfo testInfo;
	TestReporter testReporter;
	
	MyOperators m;
	
	@BeforeEach     /*This runs before every method*/
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		m = new MyOperators();
	}
	
	@Test
	@DisplayName("Testing of product of numbers")
	void prod() {
		//This will be on console
		System.out.println("Test Class: "+testInfo.getTestClass());
		//This will be output into the junit output not on console.
		testReporter.publishEntry("Display Name: "+testInfo.getDisplayName());
		int a = m.prod(9, 4);
		assertEquals(a, 36);
	}
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This runs before all!!!");
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("Cleaning up.......!!!");
	}


	@Test
	@DisplayName("Testing of adding up numbers")
	void addTest() {

		int a = m.add(3, 4);
		assertEquals(a, 7);
	}

	@Test
	@DisplayName("Testing of subtracting up numbers")
	void diff() {
		boolean isServerUp = true;
		assumeTrue(isServerUp); //Here it will run, if the value is false, it will not.
		int a = m.diff(9, 4);
		assertEquals(a, 5);
	}

	  @Test 
	  @DisplayName("Testing of division by 0)")
	  void divideByZero() { 
		  assertThrows(ArithmeticException.class, () ->
	  m.divides(1, 0), "If divided by 0, throw."); }
	 
	
	@Test
	@DisplayName("Testing of division of numbers")
	void divide() {
		assertEquals(5, m.divides(10, 2) );
	}

	@Test
	@DisplayName("Testing area of a circle")
	void circleArea() {
		double actual = m.circleArea(10);
		assertEquals(314.1592653589793, actual, ()-> "Should return right value"); /*This only executes if the test fails*/
	}
	
	@Test
	@Disabled
	@DisplayName("Disabled Test")
	void disabledTest() {
		fail("This test should be disabled!!!");
	}
	
	@Test
	@DisplayName("AssertAll Multiply test")
	void testAssertAll() {
				
		assertAll(
				() -> assertEquals(6, m.prod(2, 3), "should return right product."),
				() -> assertEquals(5, m.add(2, 3), "should return right sum."),
				() -> assertEquals(5, m.divides(10, 2), "should return right quot." )
				
				);
	}
	
	
	/*Here we are creating a class & putting all related tests into the class*/
	@Nested
	class AddTest{
		@Test
		@DisplayName("Testing of adding up numbers")
		void addTestNegatives() {

			int a = m.add(-3, -4);
			assertEquals(a, -7);
		}
		
		@Test
		@DisplayName("Testing of adding up numbers")
		void addTestPositives() {

			int a = m.add(13, 4);
			assertEquals(a, 17);
		}
		
	}
	
	
	/*@RepetedTest*/
	@RepeatedTest(5)
	@DisplayName("Testing of division of numbers")
	void dividing(RepetitionInfo repeaterTestInfo) {
		if(repeaterTestInfo.getCurrentRepetition() == 1) {
			assertEquals(5, m.divides(10, 2) );
		}
		
	}
}

















