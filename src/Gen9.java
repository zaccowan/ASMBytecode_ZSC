import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * <pre>
 *Class and Application to generate class file that prompts the user to enter an integer value, stores the value,
 * and loop up to the value using a while / for loop. Each iteration of the loop prints the value.
 *
 *
 *Bytecode generated with ASM. This particular generation does basic things with Java bytecode such as:
 *      Getting user keyboard input using a scanner
 *      Storing primitive values
 *      Primitive Comparison
 *      Jump Instructions
 *      Loops
 *      Printing primitive (Integer) and derived (String) type values
 * </pre>
 *
 * @author Zachary Cowan
 * @version 02-10-2024
 * Spring 2024
 */
public class Gen9 {

    /**
     * Application entry point.
     * @param args arguments for main method
     */
    public static void main(String [] args ) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program9", null, "java/lang/Object", null);

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

            // Initializes a new scanner
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
            mv.visitInsn(Opcodes.DUP);

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;" );
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitVarInsn(Opcodes.ASTORE, 1); // store the scanner object to allow future method calls from it


            // Print prompt to user
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Enter a number to loop to.");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

            // Load the scanner
            mv.visitVarInsn(Opcodes.ALOAD, 1);

            // Make scanner call to take in integer value from user
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I", false);
            mv.visitVarInsn(Opcodes.ISTORE, 2);

            // Print blank line for formatting
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);



            //
            // Loop to print values from 0 to users integer entered above
            Label endLabel = new Label();
            Label whileStartLabel = new Label();

            // initial count = 0
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitVarInsn(Opcodes.ISTORE, 3);
            mv.visitLabel(whileStartLabel);
            mv.visitVarInsn(Opcodes.ILOAD, 3);

            // Greater than conditional include the value entered by the user in the loop (as compared to GE)
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, endLabel);

            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitIincInsn(3,1);
            mv.visitJumpInsn(Opcodes.GOTO, whileStartLabel);

            mv.visitLabel(endLabel);



            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        Utilities.writeFile(b,"program9.class");

        System.out.println("Done!");

    }
}
