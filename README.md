# KOTLIN GEOMETRY 2D

[![CircleCI](https://circleci.com/gh/angelsolaorbaiceta/geom2d/tree/master.svg?style=svg)](https://circleci.com/gh/angelsolaorbaiceta/geom2d/tree/master)

Immutable 2D Geometry library written in Kotlin.

## Primitives
- `Point`: Ordered pair (x, y) of numbers representing a position in a two-dimensional plane
- `Vector`: Geometric object that has magnitude and direction
- `Segment`: Part of a line that is bounded by two distinct end points
- `Rectangle`: Quadrilateral with four right angles
- `Circle`: Set of all points in a plane that are at a given distance from a given point, the center
- `Polygon`: Plane figure that is described by a finite number of straight line segments connected to form a polygonal chain or polygonal circuit

## Contracts
- `PointContainable`: implemented by primitives which can check if they contain a point
- `ProximityCheckable`: implemented by primitives which can check whether they are close enough to a given point, and which compute their closest point to it
