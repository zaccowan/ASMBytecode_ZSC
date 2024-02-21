import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * <pre>
 *Class and Application to generate class file that does basic flow control using an if-else statement.
 *In this particular implementation, the following conditional is used:  if( num1 == 1 ) {} else {}
 *
 *
 *Bytecode generated with ASM. This particular generation does basic things with Java bytecode such as:
 *      Storing primitive values
 *      Using Integer Constants
 *      Comparing primitive values of same type
 *      Jump Instructions (Conditional Jumps Specifically)
 *      Printing primitive values
 * </pre>
 *
 * @author Zachary Cowan
 * @version 02-10-2024
 * Spring 2024
 */
public class Gen8 {

    /**
     * Application entry point.
     * @param args arguments for main method
     */
    public static void main(String [] args ) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program8", null, "java/lang/Object", null);

        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>","()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }

        {   // Implements an conditional
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();

            Label elseLabel = new Label();
            Label endLabel = new Label();

            // Value to check in if statement
            mv.visitLdcInsn((Integer) 1);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            // constant value, 1, to compare variable to
            mv.visitLdcInsn(Opcodes.ICONST_1);
            mv.visitVarInsn(Opcodes.ISTORE, 3);
            
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 3);

            // if( variable == constant ) { print "X is 1" }
            // else we jump to else label
            mv.visitJumpInsn(Opcodes.IF_ICMPNE, elseLabel);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("X is 1");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, endLabel);

            // else { print "X is not 1" }
            mv.visitLabel(elseLabel);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("X is not 1");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            mv.visitLabel(endLabel);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        Utilities.writeFile(b,"program8.class");

        System.out.println("Done!");

    }
}
