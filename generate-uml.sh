#!/bin/bash
# Script to regenerate UML diagram from PlantUML source

echo "Generating UML Class Diagram..."

# Download PlantUML if not present
if [ ! -f "plantuml.jar" ]; then
    echo "Downloading PlantUML..."
    curl -kL https://github.com/plantuml/plantuml/releases/download/v1.2024.8/plantuml-1.2024.8.jar -o plantuml.jar
fi

# Generate PNG from .puml file
echo "Generating PNG image..."
java -Djava.awt.headless=true -DPLANTUML_LIMIT_SIZE=8192 -jar plantuml.jar -tpng -Playout=smetana UML-ClassDiagram.puml

# Rename if needed (PlantUML may add spaces)
if [ -f "Computer Parts Resale Store - UML Class Diagram.png" ]; then
    mv "Computer Parts Resale Store - UML Class Diagram.png" UML-ClassDiagram.png
fi

# Clean up
rm plantuml.jar

echo "✓ UML diagram generated successfully: UML-ClassDiagram.png"

