#!/bin/bash

# Computer Parts Resale Store - Compile and Run Script
# This script compiles and runs the JavaFX application

# Configuration
JAVAFX_PATH="/path/to/javafx-sdk/lib"  # UPDATE THIS PATH
SRC_DIR="src"
BIN_DIR="bin"

echo "=================================="
echo "Computer Parts Resale Store"
echo "=================================="
echo ""

# Check if JavaFX path is configured
if [ "$JAVAFX_PATH" == "/path/to/javafx-sdk/lib" ]; then
    echo "⚠️  WARNING: Please update JAVAFX_PATH in this script!"
    echo "   Download JavaFX SDK from: https://openjfx.io/"
    echo "   Then edit this script and set the correct path."
    echo ""
    read -p "Continue anyway? (y/n) " -n 1 -r
    echo ""
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi

# Create bin directory if it doesn't exist
if [ ! -d "$BIN_DIR" ]; then
    mkdir "$BIN_DIR"
    echo "✓ Created $BIN_DIR directory"
fi

# Compile
echo "📦 Compiling Java files..."
javac --module-path "$JAVAFX_PATH" \
      --add-modules javafx.controls \
      -d "$BIN_DIR" \
      "$SRC_DIR/model/*.java" \
      "$SRC_DIR/service/*.java" \
      "$SRC_DIR/*.java"

if [ $? -eq 0 ]; then
    echo "✓ Compilation successful!"
    echo ""
    echo "🚀 Running application..."
    echo ""
    
    # Run
    java --module-path "$JAVAFX_PATH" \
         --add-modules javafx.controls \
         -cp "$BIN_DIR" \
         MainApp
else
    echo "❌ Compilation failed!"
    exit 1
fi

