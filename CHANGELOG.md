# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.1.1] - 28/12/2018
### Added
- `Point` implements `Comparable<Point>`

## [1.1.0] - 25/12/2018
### Added
- Contracts: `ProximityCheckable`
- `Segment` implements `ProximityCheckable`
- `Polygon` implements `PointContainable`
- Affine transformation creation for scaling about a point
- `TPosValue` tuple of a value associated to a position given by `TParam`
- `SegmentDiagram` to represent a diagram which x axis is a segment

### Changed
- `Polygon` geometric center as property instead of method
- Some primitives implementing properties "by lazy"
- `TParam` implements comparable to establish ordering according to value

## 1.0.0 - 24/12/2018
### Added
- Primitives: `Point`, `Vector`, `Segment`, `Rect`, `Circle`, `Polygon`
- `TParam`: parameter going from 0 to 1 used to iterate across linear geometries
- Affine Transformations
- Contracts: `PointContainable`

[Unreleased]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.0.0...HEAD
[1.1.1]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.1.0...v1.1.1
[1.1.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.0.0...v1.1.0
