import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class Gen2 {

    public static void main(String [] args ) {

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "program2", null, "java/lang/Object", null);

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


            {   // Subtracting Two Int Values
                mv.visitLdcInsn((Integer) 10);
                mv.visitVarInsn(Opcodes.ISTORE, 1);
                mv.visitLdcInsn((Integer) 5);
                mv.visitVarInsn(Opcodes.ISTORE, 3);
                mv.visitVarInsn(Opcodes.ILOAD, 1);
                mv.visitVarInsn(Opcodes.ILOAD, 3);
                mv.visitInsn(Opcodes.ISUB);
                mv.visitVarInsn(Opcodes.ISTORE, 5);

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.ILOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            }

            {   // Subtracting Two Long Values
                mv.visitLdcInsn((Long) 13L);
                mv.visitVarInsn(Opcodes.LSTORE, 1);
                mv.visitLdcInsn((Long) 2L);
                mv.visitVarInsn(Opcodes.LSTORE, 3);
                mv.visitVarInsn(Opcodes.LLOAD, 1);
                mv.visitVarInsn(Opcodes.LLOAD, 3);
                mv.visitInsn(Opcodes.LSUB);
                mv.visitVarInsn(Opcodes.LSTORE, 5);

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.LLOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            }

            {   // Subtracting Two Float Values
                mv.visitLdcInsn((Float) 7.5F);
                mv.visitVarInsn(Opcodes.FSTORE, 1);
                mv.visitLdcInsn((Float) 5.0F);
                mv.visitVarInsn(Opcodes.FSTORE, 3);
                mv.visitVarInsn(Opcodes.FLOAD, 1);
                mv.visitVarInsn(Opcodes.FLOAD, 3);
                mv.visitInsn(Opcodes.FSUB);
                mv.visitVarInsn(Opcodes.FSTORE, 5);

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.FLOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(F)V", false);
            }

            {   // Subtracting Two Double Values
                mv.visitLdcInsn((Double) 5.5);
                mv.visitVarInsn(Opcodes.DSTORE, 1);
                mv.visitLdcInsn((Double) 5.0);
                mv.visitVarInsn(Opcodes.DSTORE, 3);
                mv.visitVarInsn(Opcodes.DLOAD, 1);
                mv.visitVarInsn(Opcodes.DLOAD, 3);
                mv.visitInsn(Opcodes.DSUB);
                mv.visitVarInsn(Opcodes.DSTORE, 5);

                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                mv.visitVarInsn(Opcodes.DLOAD, 5);
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            }
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }
        cw.visitEnd();

        byte[] b = cw.toByteArray();

        Utilities.writeFile(b,"program2.class");

        System.out.println("Done!");

    }
}
