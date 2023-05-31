import java.util.Arrays;

public class DataMemory {

    private final int SIZE = 1024;
    private byte[] mem;

    private static DataMemory dataMemory = new DataMemory();

    private DataMemory(){
        mem = new byte[SIZE];
    }

    public static DataMemory getDataMemory() {
        return dataMemory;
    }

    public void setByte(int byteNum, byte data){
        mem[byteNum] = data;
    }

    public byte getByte(int byteNum){
        return mem[byteNum];
    }

    public void print(){
        System.out.println("Contents of data memory: \n");
        System.out.println(Arrays.toString(mem));
    }
}
