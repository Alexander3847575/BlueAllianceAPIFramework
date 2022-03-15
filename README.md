# BlueAllianceAPIFramework
A high level interface to interact with the BlueAlliance API through Java. Built for OPR processing.

## Initial design specifications as follows:

Get the matches for any particular team from the Blue Alliance API
 - take the JSON return and serialize it as an array of Match objects in a team file
 - compile all team files into a single de-duplicated master file
 - create high-level interface 
