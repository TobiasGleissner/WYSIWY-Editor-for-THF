The projects goal is to develop a WYSIWYG editer (what you see is what you get editor) for the machine readable format TPTP THF. This format is used to represent and formalize logical statements and problems in classical higher-order logic.
The editor should be capable of creating a human-readable and editable representation of a logical problem formulated in THF. 

### Editor Features
* Opening/Closing of THF files and representation of the contents in conventional logic notation similar to the interactive theorem prover Isabelle
* Preparation of a problem: Table of contents of the axioms/theorems, folding/expanding axioms
* File browser
* Syntax highlighting
* Syntax error notifications
* Semantical error notofications e.g. type errors
* Auto completition (e.g. for types and identifiers)
* Code generation (e.g. for used but undefined constants)
* Identifier following on shift click
* Auto linebreaking and line spacing for large formulas
* Simultaneous editing of the WYSIWYG representation and THF
* Graphical interface for editing semantics of non-classical logics
* Graph representation of formulas
* Full-text search

### Prover Features
Besides the editor part the software should be able to (locally and remotely) interface TPTP compliant theorem provers. Theorem provers are software systems which can analyze, process or solve logical problems. The set of features may include
* Preparing the provers return values
* Switching on/off of logical statements
* Automatic redundancy test on axiom sets
* Automatic check for superfluous axioms
* Automatic check for contradictory axioms
* Integration of additional tools for formatting, format conversion, semantic embedding of non-classical logics, ... toolchain creation
* Testing Framework

### Misc Features
* TPTP Browser
