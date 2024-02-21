import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * <pre>
 *Class and Application to generate class file that performs the comparison of two primitive values and prints the greater.
 *These values are hardcoded and are the following for each primitive type:
 *      Integers 10 and 5  --- > greater is 10
 *      Shorts 14 and 9  --- > greater is 14
 *      Longs 25L and 19L  --- > greater is 25L
 *
 *Bytecode generated with ASM. This particular generation does basic things with Java bytecode such as:
 *      Storing primitive values
 *      Comparing primitive values of same type
 *      Printing primitive values
 * </pre>
 *
 * @author Zachary Cowan
 * @version 02-10-2024
 * Spring 2024
 */
public class Gen4 {

    /**
     * Application entry point.
     * @param args arguments for main method
     */
    public static void main(String [] args ) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program4", null, "java/lang/Object", null);

        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>","()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();

            {   // Comparing Two Integer Values and Printing the Greater
                Label elseLabel = new Label();
                Label endLabel = new Label();

                mv.visitLdcInsn((Integer) 10);
                mv.visitVarInsn(Opcodes.ISTORE, 1);
                mv.visitLdcInsn((Integer) 5);
                mv.visitVarInsn(Opcodes.ISTORE, 3);
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitVarInsn(Opcodes.ILOAD, 3);

                mv.visitJumpInsn(Opcodes.IF_ICMPLE, elseLabel);// Jumps past if block in the case that first value is <= second value
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitVarInsn(Opcodes.ISTORE, 5);
                mv.visitJumpInsn(Opcodes.GOTO,endLabel); // Jumps past else block to ensure only the if condition code is executed

                mv.visitLabel(elseLabel); // Location to jump to if when the if condition is not satisfied
                mv.visitVarInsn(Opcodes.ILOAD, 3);
                mv.visitVarInsn(Opcodes.ISTORE, 5);

                mv.visitLabel(endLabel); // Location ot jump to execute the code post if-else statement
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.ILOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            }

            {   // Comparing Two Short Values and Printing the Greater
                Label elseLabel = new Label();
                Label endLabel = new Label();

                mv.visitLdcInsn((short) 14);
                mv.visitVarInsn(Opcodes.ISTORE, 1);
                mv.visitLdcInsn((short) 9);
                mv.visitVarInsn(Opcodes.ISTORE, 3);
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitVarInsn(Opcodes.ILOAD, 3);

                mv.visitJumpInsn(Opcodes.IF_ICMPLE, elseLabel);// Jumps past if block in the case that first value is <= second value
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitVarInsn(Opcodes.ISTORE, 5);
                mv.visitJumpInsn(Opcodes.GOTO,endLabel); // Jumps past else block to ensure only the if condition code is executed

                mv.visitLabel(elseLabel); // Location to jump to if when the if condition is not satisfied
                mv.visitVarInsn(Opcodes.ILOAD, 3);
                mv.visitVarInsn(Opcodes.ISTORE, 5);

                mv.visitLabel(endLabel); // Location ot jump to execute the code post if-else statement
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.ILOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            }

            {   // Comparing Two Long Values and Printing the Greater
                Label elseLabel = new Label();
                Label endLabel = new Label();

                mv.visitLdcInsn((Long) 25L);
                mv.visitVarInsn(Opcodes.LSTORE, 1);
                mv.visitLdcInsn((Long) 19L);
                mv.visitVarInsn(Opcodes.LSTORE, 3);
                mv.visitVarInsn(Opcodes.LLOAD, 1);
                mv.visitVarInsn(Opcodes.LLOAD, 3);

                mv.visitInsn(Opcodes.LCMP);
                mv.visitJumpInsn(Opcodes.IFLE, elseLabel);// Jumps past if block in the case that first value is <= second value
                mv.visitVarInsn(Opcodes.LLOAD, 1);
                mv.visitVarInsn(Opcodes.LSTORE, 5);
                mv.visitJumpInsn(Opcodes.GOTO, endLabel); // Jumps past else block to ensure only the if condition code is executed

                mv.visitLabel(elseLabel); // Location to jump to if when the if condition is not satisfied
                mv.visitVarInsn(Opcodes.LLOAD, 3);
                mv.visitVarInsn(Opcodes.LSTORE, 5);

                mv.visitLabel(endLabel); // Location ot jump to execute the code post if-else statement
                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.LLOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            }



            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        Utilities.writeFile(b,"program4.class");

        System.out.println("Done!");

    }
}
