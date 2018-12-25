# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- Contracts: `ProximityCheckable`
- `Segment` implements `ProximityCheckable`
- `Polygon` implements `PointContainable
- Affine transformation creation for scaling about a point

### Changed
- `Polygon` geometric center as property instead of method
- Some primitives implementing properties "by lazy" 

## 1.0.0 - 2018-12-24
### Added
- Primitives: `Point`, `Vector`, `Segment`, `Rect`, `Circle`, `Polygon`
- Affine Transformations
- Contracts: `PointContainable`

[Unreleased]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.0.0...HEAD
[1.1.0]: https://github.com/angelsolaorbaiceta/geom2d/compare/v1.0.0...v1.1.0
