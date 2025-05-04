# Entropy

## Overview

This Java application implements a simple Huffman coding algorithm to compress text at the character level. It reads an input file (`text.txt`), computes the frequency of each character, builds a Huffman tree, generates binary codes for each character, and outputs:

- Character frequencies
- Huffman encoding table (character → bit pattern)
- The compressed bit pattern for the entire input

## File Structure

- `Entropy.java` — Main driver class:
  - Reads input text from `text.txt`.
  - Generates `Node` objects for each character’s frequency.
  - Builds the Huffman tree via a recursive greedy algorithm.
  - Traverses the tree to assign codes and prints results.

- `Node.java` — Node class for the Huffman tree:
  - Implements `Comparable<Node>` to order nodes by frequency.
  - Stores left/right child links and cumulative frequency.
  - Provides an `add(Node[])` method to combine two subtrees.

- `text.txt` — Plain-text input to compress (one-line file).

## Prerequisites

- Java Development Kit (JDK) 8 or higher installed.
- A terminal/command prompt.

## Compilation

1. Open a terminal in the project directory.
2. Compile both Java files:
   ```bash
   javac Node.java Entropy.java
   ```
   This will produce `Node.class` and `Entropy.class`.

## Running the Application

Make sure `text.txt` is present in the same directory, containing the text to compress (single line). Then run:

```bash
java Entropy
```

You should see output similar to:

```text
Character Frequencies:
a: 10
b: 5
c: 3
...
Character Encoding:
a: 10
b: 110
c: 111
...
Compressed Bit Pattern:
10100110...
```

## Core Algorithm Details

1. **Frequency Counting** (`frequencyCount`)  
   Scans the input string, tallying each character in a `HashMap<Character, Integer>`.

2. **Node Wrapping** (`nodeCollection`)  
   Converts each `(char, frequency)` entry into a `Node` object, preserving the frequency.

3. **Huffman Tree Construction** (`treeBuilder`)  
   - Uses a `PriorityQueue<Node>` (via `twoMinValues`) to repeatedly extract two smallest-frequency nodes.  
   - Combines them under a new parent `Node` whose frequency is the sum.  
   - Recurses until a single root node remains.

4. **Code Assignment** (`encoder`)  
   - Traverses the tree, appending `1` for a left-branch and `0` for a right-branch (or vice versa).  
   - Records each character’s bit string in `encoded` map.

5. **Compression Output** (`printBitCompressed`)  
   - Maps each input character to its bit pattern and prints the concatenated result.

## Customization

- To change input file name, modify the `readFile("text.txt")` call in `Entropy.java`.
- Encoding direction (left=1/right=0) can be swapped by altering `encoder` calls.
