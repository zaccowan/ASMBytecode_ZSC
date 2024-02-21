import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * <pre>
 *Class and Application to generate class file that performs the initializes a string and prints it.
 *String value in this case is: "Hello World"
 *
 *Bytecode generated with ASM. This particular generation does basic things with Java bytecode such as:
 *      Storing a derived data type (String).
 *      Printing derived data types.
 * </pre>
 *
 * @author Zachary Cowan
 * @version 02-10-2024
 * Spring 2024
 */
public class Gen5 {

    /**
     * Application entry point.
     * @param args arguments for main method
     */
    public static void main(String [] args ) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program5", null, "java/lang/Object", null);

        {
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>","()V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(1,1);
            mv.visitEnd();
        }

        {   // Storing String Type and Printing Store value
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();

            mv.visitLdcInsn("Hello World");
            mv.visitVarInsn(Opcodes.ASTORE, 1);


            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        Utilities.writeFile(b,"program5.class");

        System.out.println("Done!");

    }
}
