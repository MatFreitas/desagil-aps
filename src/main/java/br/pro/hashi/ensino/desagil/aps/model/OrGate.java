package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {

    private final NandGate nandCima;
    private final NandGate nandBaixo;
    private final NandGate nandDir;


    public OrGate() {
        super("OR", 2);

        nandCima = new NandGate();
        nandBaixo = new NandGate();
        nandDir = new NandGate();

    }

    @Override
    public boolean read() {
        nandDir.connect(0, nandCima);
        nandDir.connect(1, nandBaixo);

        return nandDir.read();
    }

    @Override
    public void connect(int inputIndex, Emitter emitter) {
        switch (inputIndex) {
            case 0:

                nandCima.connect(0, emitter);
                nandCima.connect(1, emitter);
                break;

            case 1:

                nandBaixo.connect(0, emitter);
                nandBaixo.connect(1, emitter);
                break;

            default:

                throw new IndexOutOfBoundsException(inputIndex);
        }
    }

}
