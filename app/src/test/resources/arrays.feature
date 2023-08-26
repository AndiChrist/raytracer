Feature: Arrays

  Scenario: Concatenating two arrays should create a new array
    Given a ← array(1, 2, 3)
    And b ← array(3, 4, 5)
    When c ← a + b
    Then c = array(1, 2, 3, 3, 4, 5)

