package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nand;

    public XorGate() {
        super("XOR", 2);

        nand = new NandGate();
    }

    @Override
    public boolean read() {
        return false;
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {

    }
}
