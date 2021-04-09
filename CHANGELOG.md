# Changelog
Note: version releases in the 0.x.y range may introduce breaking changes.

## [Unreleased]
### Added
- Flat: support ctx variables (see https://github.com/ehrbase/openEHR_SDK/pull/206)
- Webtemplate: set inContext (see https://github.com/ehrbase/openEHR_SDK/pull/206)
### Fixed

- Lost of small fixes to flat (see https://github.com/ehrbase/openEHR_SDK/pull/204)
- Aql parsing : fix parsing of contains without archetypeId (see https://github.com/ehrbase/openEHR_SDK/pull/205)
- Aql building : fix building of aql with more from Dto with more than 2 where ANDs

## 1.3.0
### Added

- Webtemplate: Generate cardinalities for Nodes (see https://github.com/ehrbase/openEHR_SDK/pull/198)
- ClientTemplateProvider: For dynamically retrieving templates from ehr server (see https://github.com/ehrbase/openEHR_SDK/pull/198)
- DefaultRestClient: remove the need to provide a template Provider (see https://github.com/ehrbase/openEHR_SDK/pull/197)

### Fixed

- Webtemplate: Fix erroneous skipping of Event. Happened when there  were a EVENT and a EVENT Spezialisation as Children of the same Node (see https://github.com/ehrbase/openEHR_SDK/pull/197)

## 1.0.0
### Added
- Basic support for flat formats (see https://github.com/ehrbase/openEHR_SDK/pull/67) 
- Web-Templates (see https://github.com/ehrbase/openEHR_SDK/pull/81)
- Allow Native Parts in Entity Queries  (see https://github.com/ehrbase/openEHR_SDK/pull/106)
- New endpoint for retrieving all templates (see https://github.com/ehrbase/openEHR_SDK/pull/153)
- New Dto -> RmClasses -> Dto mapper (see https://github.com/ehrbase/openEHR_SDK/pull/153)
- New ClassGenerator
    - Configurable via YAML
    - Optimized class generation
    - Optimized name Generation
    - Support Language specific Characters
    - Generate Interfaces for RM Attributes
    - Add null_flavor fields
    - Generate shared fields in Interfaces for Choice Elements
    - Support IsmTransition
    - Generate Javadoc
    - Add Generated Annotation
    
### Fixed
- Error when extracting name from a template where name has more than one child (see https://github.com/ehrbase/openEHR_SDK/pull/79)
- Enums where not correctly generated for value-sets (see https://github.com/ehrbase/openEHR_SDK/pull/92)  

## 0.3.5

- patch: openEHR SDK containing the contents of EHRbase serialization, response DTOs, terminology and validation


