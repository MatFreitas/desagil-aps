package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;

    public OrGate() {
        super("OR", 2);

        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
    }


    @Override
    public boolean read() {
        return nand3.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        if (inputIndex < 0 || inputIndex > 1) {
            throw new IndexOutOfBoundsException(inputIndex);
        }

        if (inputIndex == 0) {
            nand1.connect(0, emitter);
            nand1.connect(1, emitter);
            nand3.connect(inputIndex, nand1);
        }

        if (inputIndex == 1) {
            nand2.connect(0, emitter);
            nand2.connect(1, emitter);
            nand3.connect(inputIndex, nand2);
        }
    }

}
