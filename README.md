# CSARCH2SimulationProject

## GitHub Repository
https://github.com/alessgomez/CSARCH2SimulationProject

## Authors
### CSARCH2 S12 - Group 5
- FONG, Hannah Regine C.
- GOMEZ, Alessandra Pauleen I.
- KAHIL, Ibrahim D.
- ONG, Shaun Vincent N.

## Project Features
### Overview
This project simulates the inner workings of cache memory when applied with the **Block Set Associative** mapping function and **Least Recently Used** replacement algorithm.

The simulator is composed of three main classes: View, Simulator, and Controller. View contains the GUI components of the application, Simulator contains the functions that process the BSA/LRU algorithm as well as miss penalty and access time formulas, and Controller connects the two by retrieving the input values from the View and passing it on to the Simulator, then retrieving the output values from the Simulator and displaying it in View. Controller also contains the function that allows for text file exporting.

### Simulator Parameters
To simulate the Cache Memory BSA/LRU algorithm, the application takes the following user inputs:
- **Cache Memory Size**: positive integer that can be treated as blocks or words
- **Main Memory Size**: positive integer that can be treated as blocks or words
- **Set Size**: positive integer
- **Block Size**: positive integer
- **Cache Access Time**: positive float
- **Memory Access Time**: positive float
- **Read Type**: Load Through or Non Load-Through
- **Memory Type**: Contiguous or Non Contiguous (pertaining to Program Flow)
- **Input Type**: Addresses or Blocks (pertaining to Program Flow)
- **Program Flow**: List of commands, addresses, or blocks to simulate

### Simulator Results
After running the simulation, the application displays the following outputs:
- **Cache Memory Snapshot**: a two-dimensional table that depicts the values inside each block for each set after the simulation is finished
- **Number of Cache Hits**: positive integer; for this simulator, cache hits are counted **by address**
- **Number of Cache Misses**: positive integer; for this simulator, cache misses are counted **by blocks**
- **Miss Penalty**: positive float
- **Average Memory Access Time**: positive float
- **Total Memory Access Time**: positive float

## How to Use the Simulator
### Starting the Application
Download and run the `BSA-LRU-CacheMemory.jar` file.

### Program Flow Syntax
To ensure correct results, the program flow input must adhere to the following syntax conditions:
- Each address, block, or command must be separated by a newline. Whitespaces may be used to help in program flow organization.
- If input is treated as blocks, block values should be in decimal. (ex. 5, 18, 23, 100)
- If input is treated as addresses, addresses should be in hexadecimal *without* the '0x' prefix or the 'h' suffix. (ex. 214, ABC, 10F)

For contiguous data, program flow inputs can use the following keywords to group addresses or blocks:
- `RANGE <v1> <v2>`
  - Enters all values from *v1* to *v2*, inclusive, into the program flow once.
  - *v1* and *v2* should be positive integers were v1 is less than v2.
  - ex. `RANGE 5 11` places 5, 6, 7, 8, 9, 10, 11 into the program flow
- `LOOP <label> <count>` and `J <label>`
  - The LOOP command indicates the beginning of a loop block, while the J command indicates the end of the loop block.
  - *label* is a case-sensitive identifier for the LOOP command.
  - *count* is a positive integer specifying how many times the loop will be executed.
  - All commands between LOOP and J will be part of the loop block.
  - ex. Given the program flow input with input type as addresses:
    ```
    LOOP L1 3
      RANGE 0 2
    J L1
    ```
    The hexadecimal addresses 0x0, 0x1, and 0x2 will be added into the program flow three times.

**NOTE:** Inputs that do not follow the syntax may lead to either an invalid program flow error or a simulation error.

### Text File Exporting
Users will be prompted to choose the directory where they want to save the text file to. The text file is named `BSA_LRU_Cache_Simulator_Output.txt`, and will contain all the simulator results stated in the preview section of the manual.

## Sample Test Cases 
### Cache Problem Set #4
#### Input
- **Cache Memory Size**: 32 Words
- **Main Memory Size**: 1024 Words
- **Set Size**: 4
- **Block Size**: 4
- **Cache Access Time**: 1
- **Memory Access Time**: 10
- **Read Type**: Non Load-Through
- **Memory Type**: Non Contiguous
- **Input Type**: Addresses
- **Program Flow**:
  ```
  200
  204
  208
  20C
  2F4
  2F0
  200
  204
  218
  21C
  24C
  2F4
  ```
#### Output
- **Cache Hits**: 3
- **Cache Misses**: 9
- **Miss Penalty**: 42.0 ns
- **Average Memory Access Time**: 32.0 ns
- **Total Memory Access Time**: 417.0 ns
---
### Cache Problem Set #5
#### Input
- **Cache Memory Size**: 4 Blocks
- **Main Memory Size**: 6 Blocks
- **Set Size**: 2
- **Block Size**: 4
- **Cache Access Time**: 1
- **Memory Access Time**: 10
- **Read Type**: Non Load-Through
- **Memory Type**: Non Contiguous
- **Input Type**: Blocks
- **Program Flow**:
  ```
  1
  2
  3
  4
  5
  4
  6
  3
  ```
#### Output
- **Cache Hits**: 2
- **Cache Misses**: 6
- **Miss Penalty**: 42.0 ns
- **Average Memory Access Time**: 32.0 ns
- **Total Memory Access Time**: 278.0 ns
---
### Cache Problem Set #6
#### Input
- **Cache Memory Size**: 4096 Words
- **Main Memory Size**: 1048576 Words
- **Set Size**: 4
- **Block Size**: 64
- **Cache Access Time**: 1
- **Memory Access Time**: 10
- **Read Type**: Non Load-Through
- **Memory Type**: Contiguous
- **Input Type**: Addresses
- **Program Flow**:
  ```
  LOOP L1 10
    RANGE 0 10FF
  J L1
  ```
#### Output
- **Cache Hits**: 43272
- **Cache Misses**: 248
- **Miss Penalty**: 642.0 ns
- **Average Memory Access Time**: 5.0 ns
- **Total Memory Access Time**: 2944248.0 ns
---
### Cache Problem Set #7
#### Input
- **Cache Memory Size**: 1024 Words
- **Main Memory Size**: 65536 Words
- **Set Size**: 4
- **Block Size**: 128
- **Cache Access Time**: 1
- **Memory Access Time**: 10
- **Read Type**: Non Load-Through
- **Memory Type**: Contiguous
- **Input Type**: Addresses
- **Program Flow**:
  ```
  RANGE 0 7F
  LOOP L1 10
     RANGE 80 FF
     LOOP L2 20
        RANGE 100 1FF
     J L2
     RANGE 200 4FF
  J L1
  RANGE 500 5FF
  ```
#### Output
- **Cache Hits**: 60487
- **Cache Misses**: 57
- **Miss Penalty**: 1282.0 ns
- **Average Memory Access Time**: 2.0 ns
- **Total Memory Access Time**: 7822649.0
