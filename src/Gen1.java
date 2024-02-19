import org.objectweb.asm.*;
import org.objectweb.asm.Opcodes;


/**
 * <pre>
 *Class and Application to generate class file that performs the multiplication of:
 *      Two Integers: 10 * 5 = 50
 *      Two Longs: 13L * 2L = 26L
 *      Two Floats: 2.5F * 5.0F = 12.5F
 *      Two Doubles: 5.5 * 5.0 = 0.5
 *
 *Bytecode generated with ASM. This particular generation does basic things with Java bytecode such as:
 *      Storing different primitive values
 *      Multiplying primitive values
 *      Printing Primitive Values
 * </pre>
 *
 * @author Zachary Cowan
 * @version 02-10-2024
 * Spring 2024
 */
public class Gen1 {

    /**
     * Application entry point.
     * @param args arguments for main method
     */
    public static void main(String [] args ) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); // New ClassWriter where stack map frames are automatically computed from scratch. (Per ASM Documentation)
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program1", null, "java/lang/Object", null);

        {   // Setup for Class.
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>","()V", false);

            mv.visitInsn(Opcodes.RETURN); // Return from class
            mv.visitMaxs(1,1); // max stack size of 1 and max number of local variable of 1
            mv.visitEnd(); // visit end of class
        }

        {
            // Setup for main method
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();

            {   // Multiplying Two Int Values
                mv.visitLdcInsn((Integer) 10); // Load constant integer value 10.
                mv.visitVarInsn(Opcodes.ISTORE, 1); // Store constant in a local variable
                mv.visitLdcInsn((Integer) 5); // Load constant integer value 5.
                mv.visitVarInsn(Opcodes.ISTORE, 3); // Store constant in a local variable
                mv.visitVarInsn(Opcodes.ILOAD, 1); // Load local int var = 10
                mv.visitVarInsn(Opcodes.ILOAD, 3); // Load local int var = 5
                mv.visitInsn(Opcodes.IMUL); // Multiply two loaded vars, 5 * 10
                mv.visitVarInsn(Opcodes.ISTORE, 5); // Store result of above multiplication in an integer local variable

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"); // Setup System.out
                mv.visitVarInsn(Opcodes.ILOAD, 5); // Load integer stored at 5, which is the product of our two integers.
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false); // Invoke method to take loaded integer and print it using println()
            }


             /*The following perform the same task except for different data types. The following changes need to be made for the different data types:
             *      ISTORE (for integers) -->  LSTORE (for longs), FSTORE (for floats), or DSTORE (for doubles)
             *      ILOAD (for integers) -->  LLOAD (for longs), FLOAD (for floats), or DLOAD (for doubles)
             *      IMUL (for integers) -->  LMUL (for longs), FMUL (for floats), or DMUL (for doubles)
             *
             * Additionally, you must load the proper constant before each respective store.
             *
             * Changing some portions of the println() INVOKEVIRTUAL call is also required. The descriptor must reflect the following for each primitive type:
             *      (I)V  -->  Integer argument with return void.
             *      (J)V  -->  Long argument with return void.
             *      (F)V  -->  Float argument with return void.
             *      (D)V  -->  Double argument with return void.
             *
             */


            {   // Multiplying Two Long Values
                mv.visitLdcInsn((Long) 13L);
                mv.visitVarInsn(Opcodes.LSTORE, 1);
                mv.visitLdcInsn((Long) 2L);
                mv.visitVarInsn(Opcodes.LSTORE, 3);
                mv.visitVarInsn(Opcodes.LLOAD, 1);
                mv.visitVarInsn(Opcodes.LLOAD, 3);
                mv.visitInsn(Opcodes.LMUL);
                mv.visitVarInsn(Opcodes.LSTORE, 5);

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.LLOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            }

            {   // Multiplying Two Float Values
                mv.visitLdcInsn((Float) 2.5F);
                mv.visitVarInsn(Opcodes.FSTORE, 1);
                mv.visitLdcInsn((Float) 5.0F);
                mv.visitVarInsn(Opcodes.FSTORE, 3);
                mv.visitVarInsn(Opcodes.FLOAD, 1);
                mv.visitVarInsn(Opcodes.FLOAD, 3);
                mv.visitInsn(Opcodes.FMUL);
                mv.visitVarInsn(Opcodes.FSTORE, 5);

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.FLOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);
            }

            {   // Multiplying Two Double Values
                mv.visitLdcInsn((Double) 5.5);
                mv.visitVarInsn(Opcodes.DSTORE, 1);
                mv.visitLdcInsn((Double) 5.0);
                mv.visitVarInsn(Opcodes.DSTORE, 3);
                mv.visitVarInsn(Opcodes.DLOAD, 1);
                mv.visitVarInsn(Opcodes.DLOAD, 3);
                mv.visitInsn(Opcodes.DMUL);
                mv.visitVarInsn(Opcodes.DSTORE, 5);

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.DLOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            }

            // Termination of method visitor
            mv.visitInsn(Opcodes.RETURN); // Return from main method.
            mv.visitMaxs(0,0); // maximum stack size and max number of local variable for main.
            mv.visitEnd(); // end the main method.
        }

        cw.visitEnd(); // Termination point for class writer.

        byte[] b = cw.toByteArray(); // Store data created by Class Writer to a byte array

        Utilities.writeFile(b,"program1.class"); // Write the byte array out to a class file

        System.out.println("Done!"); // Print Completion Message

    }
}
