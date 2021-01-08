.class public Demo
.super java/lang/Object

;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 4
       .limit stack 256

       ; setup local variables:

       ;    1 - the PrintStream object held in java.lang.System.out
       getstatic java/lang/System/out Ljava/io/PrintStream;

       ; place your bytecodes here
       ; START

       aconst_null
       astore 3

       

       new frame_0
       dup
       invokespecial frame_0/<init>()V
       dup
       aload 3
       putfield frame_0/sl Ljava/lang/Object;
       astore 3
       

       aload 3
       putfield frame_0/v0 I
       

       L1:
       sipush 1
       isub
       ifgt L1
       sipush 0
       goto L2
       L1: sipush 1
       L2:
       ifeq L2
       convert to String;
       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       call print
       invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
       pop
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
       pop
       sipush 2
       imod
       sipush 0
       isub
       ifeq L1
       sipush 0
       goto L2
       L1: sipush 1
       L2:
       ifeq L1
       goto L2
       L1:
       L2:
       goto L1
       L2:
       aload 3
       getfield frame_0/sl Ljava/lang/Object;
       astore 3
       ;END


       ; convert to String;
       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
       ; call println 
       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V

       return

.end method
