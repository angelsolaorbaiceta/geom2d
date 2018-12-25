# KOTLIN GEOMETRY 2D

[![CircleCI](https://circleci.com/gh/angelsolaorbaiceta/geom2d/tree/master.svg?style=svg)](https://circleci.com/gh/angelsolaorbaiceta/geom2d/tree/master)

Immutable 2D Geometry library written in Kotlin.

## Primitives
- `Point`: Ordered pair (x, y) of numbers representing a position in a two-dimensional plane
- `Vector`
- `Segment`: Part of a line that is bounded by two distinct end points
- `Rectangle`
- `Circle`
- `Polygon`

## Contracts
- `PointContainable`: implemented by primitives which have can check if they contain a point
- `ProximityCheckable`: implemented by primitives which can check whether they are close enough to a given point, and which compute their closest point to it

