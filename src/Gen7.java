import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * <pre>
 *Class and Application to generate class file that performs a simple while loop.
 *In this specific implementation, the while loop prints a value i that is incremented every iteration until the value i reaches constant 4.
 *This is semantically identical to a for loop.
 *
 *Bytecode generated with ASM. This particular generation does basic things with Java bytecode such as:
 *      Storing primitive values
 *      Using Integer Constants
 *      Incrementing a value
 *      Primitive Comparisons
 *      Jump Instructions
 *      Printing primitive values
 * </pre>
 *
 * @author Zachary Cowan
 * @version 02-10-2024
 * Spring 2024
 */
public class Gen7 {

    /**
     * Application entry point.
     * @param args arguments for main method
     */
    public static void main(String [] args ) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program7", null, "java/lang/Object", null);

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

            Label endLabel = new Label();
            Label whileStartLabel = new Label();

            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitLabel(whileStartLabel);
            mv.visitVarInsn(Opcodes.ILOAD, 1);

            mv.visitInsn(Opcodes.ICONST_5);
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, endLabel);

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitIincInsn(1,1);
            mv.visitJumpInsn(Opcodes.GOTO, whileStartLabel);

            mv.visitLabel(endLabel);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        Utilities.writeFile(b,"program7.class");

        System.out.println("Done!");

    }
}
