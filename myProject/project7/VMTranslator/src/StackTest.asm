//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

//eq
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@EQUAL1
D;JEQ
@SP
A=M
M=0
@END1
0;JMP
(EQUAL1)
@SP
A=M
M=-1
(END1)
@SP
M=M+1

//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

//eq
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@EQUAL2
D;JEQ
@SP
A=M
M=0
@END2
0;JMP
(EQUAL2)
@SP
A=M
M=-1
(END2)
@SP
M=M+1

//push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

//eq
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@EQUAL3
D;JEQ
@SP
A=M
M=0
@END3
0;JMP
(EQUAL3)
@SP
A=M
M=-1
(END3)
@SP
M=M+1

//push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

//lt
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@LESS4
D;JLT
@SP
A=M
M=0
@END4
0;JMP
(LESS4)
@SP
A=M
M=-1
(END4)
@SP
M=M+1

//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

//lt
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@LESS5
D;JLT
@SP
A=M
M=0
@END5
0;JMP
(LESS5)
@SP
A=M
M=-1
(END5)
@SP
M=M+1

//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

//lt
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@LESS6
D;JLT
@SP
A=M
M=0
@END6
0;JMP
(LESS6)
@SP
A=M
M=-1
(END6)
@SP
M=M+1

//push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

//gt
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@GREAT7
D;JGT
@SP
A=M
M=0
@END7
0;JMP
(GREAT7)
@SP
A=M
M=-1
(END7)
@SP
M=M+1

//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

//gt
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@GREAT8
D;JGT
@SP
A=M
M=0
@END8
0;JMP
(GREAT8)
@SP
A=M
M=-1
(END8)
@SP
M=M+1

//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

//gt
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
D=M-D
@GREAT9
D;JGT
@SP
A=M
M=0
@END9
0;JMP
(GREAT9)
@SP
A=M
M=-1
(END9)
@SP
M=M+1

//push constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1

//push constant 53
@53
D=A
@SP
A=M
M=D
@SP
M=M+1

//add
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=D+M
M=D
@SP
M=M+1

//push constant 112
@112
D=A
@SP
A=M
M=D
@SP
M=M+1

//sub
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
M=M-D
@SP
M=M+1

//neg
@SP
M=M-1
@SP
A=M
D=M
D=-D
M=D
@SP
M=M+1

//and
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
M=D&M
@SP
M=M+1

//push constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1

//or
@SP
M=M-1
@SP
A=M
D=M
@SP
M=M-1
@SP
A=M
M=D|M
@SP
M=M+1

//not
@SP
M=M-1
@SP
A=M
M=!M
@SP
M=M+1

