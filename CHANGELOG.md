# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.6.1] - 24/01/2019
### Fixed
- `AffineTransformation` method `applyScaleAndDisplacement` for negative scales 

## [1.6.0] - 24/01/2019
### Changed
- `Size` constructor now private. Use `Size.make` which makes sure width and height are positive.

## [1.5.0] - 13/01/2019
### Added
- `AffineTransformation` inverse transformation lazy property

## [1.4.0] - 04/01/2019
### Added
- `Polyline` affine transformation

## [1.3.0] - 04/01/2019
### Added
- `Polyline` primitive implementing `ProximityCheckable`

## [1.2.0] - 03/01/2019
### Added
- Implement `equals`, `hashCode` and `toString` for ``AffineTransform
- Concatenation of `AffineTransform`
- Interpolation of double values
- Creation of sequences of `AffineTransform` to produce animations
- `DiagramPolygon`: polygon representation of diagrams with their values associated to vertices

## [1.1.3] - 29/12/2018
### Added
- `TParam` can generate positions for subdividing its range
- `Segment` can generate positions for subdividing its length

## [1.1.2] - 28/12/2018
### Added
- `Segment` can generate a segment with ordered end points

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
[1.6.1]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.6.0...v1.6.1
[1.6.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.5.0...v1.6.0
[1.5.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.4.0...v1.5.0
[1.4.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.3.0...v1.4.0
[1.3.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.2.0...v1.3.0
[1.2.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.1.3...v1.2.0
[1.1.3]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.1.2...v1.1.3
[1.1.2]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.1.1...v1.1.2
[1.1.1]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.1.0...v1.1.1
[1.1.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.0.0...v1.1.0
