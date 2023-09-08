Feature: Rays

  Scenario: Creating and querying a ray
    Given origin ← point(1, 2, 3)
    And direction ← vector(4, 5, 6)
    When r ← ray(origin, direction)
    Then r.origin = origin
    And r.direction = direction

  Scenario: Computing a point from a distance
    Given r ← ray(point(2, 3, 4), vector(1, 0, 0))
    Then position(r, 0) = point(2, 3, 4)
    And position(r, 1) = point(3, 3, 4)
    And position(r, -1) = point(1, 3, 4)
    And position(r, 2.5) = point(4.5, 3, 4)

  Scenario: A ray intersects a sphere at two points
    Given r ← ray(point(0, 0, -5), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 2
    And xs[0] = 4.0
    And xs[1] = 6.0

  Scenario: A ray intersects a sphere at a tangent
    Given r ← ray(point(0, 1, -5), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 2
    And xs[0] = 5.0
    And xs[1] = 5.0

  Scenario: A ray misses a sphere
    Given r ← ray(point(0, 2, -5), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 0

  Scenario: A ray originates inside a sphere
    Given r ← ray(point(0, 0, 0), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 2
    And xs[0] = -1.0
    And xs[1] = 1.0

  Scenario: A sphere is behind a ray
    Given r ← ray(point(0, 0, 5), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 2
    And xs[0] = -6.0
    And xs[1] = -4.0
