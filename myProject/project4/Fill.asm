// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.

//// Replace this comment with your code.

    (START)
    @KBD
    D=M
    @WHITE
    D;JEQ

    //set color
    (BLACK)
    @color
    M=-1
    @ENDCOLOR
    0;JMP
    (WHITE)
    @color
    M=0
    (ENDCOLOR)
    // n = 256 rows
    @256
    D=A
    @n
    M=D
    @SCREEN
    D=A
    @addr
    M=D
    // write screen
    (LOOP)
    //n <= 0
    @n
    D=M
    @END
    D;JLE
        @addr
        D=M
        @tempAddr
        M=D
        // inner loop, set columns
        // m = 16 columns(a column is a word)
        @31
        D=A
        @m
        M=D
        (INNERLOOP)
        @m
        D=M
        @ENDINNERLOOP
        D;JLT

        @addr
        D=M
        @m
        D=D+M
        @addr
        M=D
        @color
        D=M
        @addr
        A=M
        M=D
        
        // restore addr
        @tempAddr
        D=M
        @addr
        M=D
        // m--
        @m
        MD=M-1
        @INNERLOOP
        0;JMP
    (ENDINNERLOOP)
    // n--
    @n
    MD=M-1
    // addr += 32 , move to next row
    @addr
    D=M
    @32
    D=D+A
    @addr
    M=D

    @LOOP
    0;JMP

    (END)
    @START
    0;JMP
    